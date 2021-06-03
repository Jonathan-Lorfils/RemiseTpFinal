package com.tp1_jonathanlorfils.demo;

import com.tp1_jonathanlorfils.demo.Service.SystemService;
import com.tp1_jonathanlorfils.demo.model.*;
import com.tp1_jonathanlorfils.demo.repository.CitoyenRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppPermisTest {

    @Autowired
    private SystemService systemService;

    @Autowired
    private CitoyenRepository citoyenRepository;


    @Test
    public void testGenererPermis() {
        Citoyen citoyen = new Citoyen();
        citoyen.setPrenom("Jonathan"); citoyen.setNom("Lorfils"); citoyen.setNumeroAssuranceSocial("EEF51EFB"); citoyen.setTypePermis("Test");
        citoyen.setCourriel("test@gmail.com"); citoyen.setAge(18); citoyen.setMotDePasse("mdp123");
        citoyen.setPermis(systemService.genererPermis(citoyen.getTypePermis(), systemService.generateQR(citoyen)));
        assertNotNull(citoyen.getPermis());
    }

    @Test
    public void testLogin() {
        Citoyen citoyen = new Citoyen();
        citoyen.setPrenom("Jonathan"); citoyen.setNom("Lorfils"); citoyen.setNumeroAssuranceSocial("EEF51EFB"); citoyen.setTypePermis("Test");
        citoyen.setCourriel("test@gmail.com"); citoyen.setAge(18); citoyen.setMotDePasse("mdp123");
        citoyen.setPermis(systemService.genererPermis(citoyen.getTypePermis(), systemService.generateQR(citoyen)));
        citoyenRepository.save(citoyen);
        assertNotNull(systemService.login(citoyen.getCourriel(),citoyen.getMotDePasse()));
        citoyenRepository.deleteCitoyenById(citoyen.getId());
    }

    @Test
    public void testGenerateQr() {
        Citoyen citoyen = new Citoyen();
        citoyen.setPrenom("Jonathan"); citoyen.setNom("Lorfils"); citoyen.setNumeroAssuranceSocial("EEF51EFB"); citoyen.setTypePermis("Test");
        citoyen.setCourriel("test@gmail.com"); citoyen.setAge(18); citoyen.setMotDePasse("mdp123");
        assertNotNull(systemService.generateQR(citoyen));
    }

    @Test
    public void testGeneratePDF() {
        Citoyen citoyen = new Citoyen();
        citoyen.setPrenom("Jonathan"); citoyen.setNom("Lorfils"); citoyen.setNumeroAssuranceSocial("EEF51EFB"); citoyen.setTypePermis("Test");
        citoyen.setCourriel("test@gmail.com"); citoyen.setAge(18); citoyen.setMotDePasse("mdp123");
        citoyen.setPermis(systemService.genererPermis(citoyen.getTypePermis(), systemService.generateQR(citoyen)));
        assertNotNull(systemService.generatePDF(citoyen.getPermis(),citoyen));
    }

    @Test
    public void testSubscribe() {
        Citoyen citoyen = new Citoyen();
        citoyen.setPrenom("Jonathan"); citoyen.setNom("Lorfils"); citoyen.setNumeroAssuranceSocial("EEF51EFB"); citoyen.setTypePermis("Test");
        citoyen.setCourriel("test@gmail.com"); citoyen.setAge(18); citoyen.setMotDePasse("mdp123");
        assertNotNull(systemService.subscribe(citoyen));
    }

    @Test
    public void testSubscribeEnfant() {
        Enfant enfant = new Enfant();
        enfant.setPrenom("Jonathan"); enfant.setNom("Lorfils"); enfant.setNumeroAssuranceSocial("EEF51EFB"); enfant.setTypePermis("Test");
        enfant.setCourriel("test@gmail.com"); enfant.setAge(18); enfant.setMotDePasse("mdp123");
        assertNotNull(systemService.subscribe(enfant));
    }

    @Test
    public void testSendEmail() {
        Citoyen citoyen = new Citoyen();
        citoyen.setPrenom("Jonathan"); citoyen.setNom("Lorfils"); citoyen.setNumeroAssuranceSocial("EEF51EFB"); citoyen.setTypePermis("Test");
        citoyen.setCourriel("test@gmail.com"); citoyen.setAge(18); citoyen.setMotDePasse("mdp123");
        citoyen.setPermis(systemService.genererPermis(citoyen.getTypePermis(), systemService.generateQR(citoyen)));
        citoyenRepository.save(citoyen);
        assertTrue(systemService.sendEmail(citoyen.getCourriel(),"Test","Test",citoyen.getPermis(),citoyen));
        citoyenRepository.deleteCitoyenById(citoyen.getId());
    }
}
