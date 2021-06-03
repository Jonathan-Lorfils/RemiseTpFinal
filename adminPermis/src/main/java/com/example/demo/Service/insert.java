package com.example.demo.Service;

import com.example.demo.model.Citoyen;
import com.example.demo.model.Enfant;
import com.example.demo.model.Permis;
import com.example.demo.repository.CitoyenRepository;
import com.example.demo.repository.EnfantRepository;
import com.example.demo.repository.PermisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class insert implements CommandLineRunner {

        @Autowired
        private CitoyenRepository citoyenRepository;

        @Autowired
        private SystemService systemService;

        @Autowired
        private PermisRepository permisRepository;

        @Autowired
        private EnfantRepository enfantRepository;

        private void insertData() throws Exception {
            Citoyen c1 = new Citoyen();
            c1.setNumeroAssuranceSocial("123456789");
            c1.setNom("Lorfils");
            c1.setPrenom("Jonathan");
            c1.setAge(18);
            c1.setCourriel("jonathan@gmail.com");
            c1.setMotDePasse("pwd");
            c1.setTypePermis("Test");
            c1.setPermis(systemService.genererPermis(c1.getTypePermis(), systemService.generateQR(c1.getNom(),c1.getPrenom(),c1.getNumeroAssuranceSocial())));

            Enfant e1 = new Enfant();
            e1.setNom("Tom"); e1.setPrenom("Clancy");
            e1.setNassmParent("123456789");
            e1.setNumeroAssuranceSocial("TOCLAN1960");
            e1.setTypePermis("Vaccin");
            e1.setAge(11);
            e1.setPermis(systemService.genererPermis(e1.getTypePermis(), systemService.generateQR(e1.getNom(),e1.getPrenom(),e1.getNumeroAssuranceSocial())));

            citoyenRepository.save(c1);
            enfantRepository.save(e1);
    }

    private void cleanData(){
            this.permisRepository.deleteAll();
            this.citoyenRepository.deleteAll();
            this.enfantRepository.deleteAll();
    }

    @Override
    public void run(String... args) throws Exception{
            cleanData();
        insertData();
    }
}
