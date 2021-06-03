package com.tp1_jonathanlorfils.demo.Service;

import com.tp1_jonathanlorfils.demo.model.*;
import com.tp1_jonathanlorfils.demo.repository.CitoyenRepository;
import com.tp1_jonathanlorfils.demo.repository.PermisRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.imageio.ImageIO;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDate;

@Service
public class SystemService {

    @Autowired
    private CitoyenRepository citoyenRepository;

    @Autowired
    private PermisRepository permisRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment environment;


    public Permis genererPermis(String typePermis, ByteArrayOutputStream codeQr){
        Permis permis = new Permis();
        if (typePermis.equals("Vaccin")){
            permis.setExpirationPermisTest(LocalDate.now().plusYears(100));
            permis.setTypePermis("Vaccin");
        } else if (typePermis.equals("Test")){
            permis.setTypePermis("Test");
        }
        permis.setCodeQR(codeQr.toByteArray());
        permisRepository.save(permis);
        return permis;
    }

    public Citoyen setCitoyen(String numeroAssuranceSocial, String nom, String prenom, int age, String courriel, String motDePasse, String typePermis){
        Citoyen citoyen = new Citoyen();
        citoyen.setNumeroAssuranceSocial(numeroAssuranceSocial);
        citoyen.setNom(nom);
        citoyen.setPrenom(prenom);
        citoyen.setAge(age);
        citoyen.setCourriel(courriel);
        citoyen.setMotDePasse(motDePasse);
        citoyen.setTypePermis(typePermis);
        return citoyen;
    }

    public Citoyen login(String courriel, String mdp){
        return citoyenRepository.findCitoyenByCourrielAndMotDePasse(courriel, mdp);
    }

    public boolean renouvellerPermis(Integer id, String typePermis) {
        boolean flag = false;
        try {
            Citoyen citoyen = citoyenRepository.findCitoyenById(id);
            if (citoyen != null){
                Permis ancienPermis = citoyen.getPermis();
                citoyen.setPermis(genererPermis(typePermis,generateQR(citoyen)));
                permisRepository.delete(ancienPermis);
                flag = true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    public ByteArrayOutputStream generateQR(Citoyen citoyen){
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BufferedImage qrCode =
                    MatrixToImageWriter.toBufferedImage(qrCodeWriter.encode(
                            citoyen.getNumeroAssuranceSocial() + "|" +
                                    citoyen.getPrenom() + "|" + citoyen.getNom(),
                            BarcodeFormat.QR_CODE,
                            Integer.parseInt(environment.getProperty("qrCode.width")),
                            Integer.parseInt(environment.getProperty("qrCode.height"))
                    ));
            ByteArrayOutputStream boas = new ByteArrayOutputStream();
            ImageIO.write(qrCode, "jpg", boas);
            return boas;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void afficherQr(String nassm, HttpServletResponse response){
        try {
            response.setContentType("images/jpeg");
            Permis permis = permisRepository.getOne(citoyenRepository.findCitoyenByNumeroAssuranceSocial(nassm).getPermis().getIdPermis());

            InputStream is = new ByteArrayInputStream(permis.getCodeQR());
            IOUtils.copy(is, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Citoyen subscribe(Citoyen citoyen){
        try{
            String nom = citoyen.getNom();
            String prenom = citoyen.getPrenom();
            Permis permis = genererPermis(citoyen.getTypePermis(),generateQR(citoyen));
            citoyen.setPermis(permis);
            sendEmail(citoyen.getCourriel(), "Permis sante","Voici votre permis sante :",citoyen.getPermis(),citoyen);
            return citoyenRepository.save(citoyen);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Enfant subscribeEnfant(Enfant enfant) {
        try {
            String nom = enfant.getNom();
            String prenom = enfant.getPrenom();
            Permis permis = genererPermis(enfant.getTypePermis(),generateQR(enfant));
            enfant.setPermis(permis);
            sendEmail(enfant.getCourriel(), "Permis sante","Voici votre permis sante :",enfant.getPermis(),enfant);
            return citoyenRepository.save(enfant);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Citoyen subscribeMobile(String nassm, String nom,
                                   String prenom,String age,
                                   String courriel, String mdp,
                                   String typePermis){
        Citoyen citoyen = setCitoyen(nassm,nom,prenom,Integer.parseInt(age),courriel,mdp,typePermis);
        Permis permis = genererPermis(typePermis,generateQR(citoyen));
        citoyen.setPermis(permis);
        sendEmail("jonathan.11911@gmail.com","Permis sante","Coucou",citoyen.getPermis(),citoyen);
        return citoyenRepository.save(citoyen);
    }

    public ByteArrayOutputStream generatePDF(Permis permis, Citoyen citoyen) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdf = new PdfDocument(writer);

        Document document = new Document(pdf);
        Image image = new Image(ImageDataFactory.create(permis.getCodeQR()));

        Paragraph p = new Paragraph("Bonjour, " + citoyen.getNom() + " " + citoyen.getPrenom() + "\n ").add("Voici votre permis \n").add(image);
        document.add(p);
        document.close();

        return byteArrayOutputStream;
    }

    public boolean sendEmail(String destinataire, String subject, String body, Permis permis, Citoyen citoyen) {
        boolean flag = false;
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(destinataire);
            helper.setSubject(subject);
            helper.setText(body);

            helper.addAttachment("codeQR.png", new ByteArrayResource(permis.getCodeQR()));
            helper.addAttachment("pdfQR.pdf", new ByteArrayResource(generatePDF(permis, citoyen).toByteArray()));

            mailSender.send(message);
            flag = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
