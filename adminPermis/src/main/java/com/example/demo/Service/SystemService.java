package com.example.demo.Service;

import com.example.demo.model.Citoyen;
import com.example.demo.model.Permis;
import com.example.demo.repository.CitoyenRepository;
import com.example.demo.repository.PermisRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDate;

@Service
public class SystemService {

    @Autowired
    PermisRepository permisRepository;

    @Autowired
    CitoyenRepository citoyenRepository;

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

    public String saveCitoyen(Citoyen citoyen) throws Exception {
        if((citoyen.getTypePermis().toUpperCase().equals("VACCIN") || citoyen.getTypePermis().toUpperCase().equals("TEST"))){
            if(citoyenRepository.findCitoyenByNumeroAssuranceSocial(citoyen.getNumeroAssuranceSocial()) == null){ // si le citoyen est nouveau
                citoyen.setPermis(genererPermis(citoyen.getTypePermis(),
                        generateQR(citoyen.getNom(),citoyen.getPrenom(),citoyen.getNumeroAssuranceSocial())));
                citoyenRepository.save(citoyen);
            } else {
                supprimerCitoyen(citoyen.getId());
                saveCitoyen(citoyen);
            }
        }
        return "citoyens";
    }

    public void genererCodeQr(Integer id, HttpServletResponse response){
        try {
            response.setContentType("images/jpeg");
            Permis permis = permisRepository.findPermisByIdPermis(id);

            InputStream is = new ByteArrayInputStream(permis.getCodeQR());
            IOUtils.copy(is, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String supprimerPermis(Integer id){
        citoyenRepository.findCitoyenByPermis(permisRepository.findPermisByIdPermis(id)).setPermis(null);
        permisRepository.deleteByIdPermis(id);
        return "redirect:/permis";
    }

    public String supprimerCitoyen(Integer id){
        Permis permisCitoyenSupprime = citoyenRepository.findCitoyenById(id).getPermis();
        citoyenRepository.deleteCitoyenById(id);
        permisRepository.deleteByIdPermis(permisCitoyenSupprime.getIdPermis());
        return "redirect:/citoyens";
    }

    public ByteArrayOutputStream generateQR(String nom, String prenom, String nassm) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BufferedImage qrCode =
                MatrixToImageWriter.toBufferedImage(qrCodeWriter.encode(
                        nassm + "|" +
                                prenom + "|" + nom,
                        BarcodeFormat.QR_CODE,
                        Integer.parseInt(environment.getProperty("qrCode.width")),
                        Integer.parseInt(environment.getProperty("qrCode.height"))
                ));
        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        ImageIO.write(qrCode, "jpg", boas);
        return boas;
    }

}
