package com.tp1_jonathanlorfils.demo;

import com.tp1_jonathanlorfils.demo.Service.SystemService;
import com.tp1_jonathanlorfils.demo.model.Citoyen;
import com.tp1_jonathanlorfils.demo.model.Permis;
import com.tp1_jonathanlorfils.demo.model.User;
import com.tp1_jonathanlorfils.demo.repository.CitoyenRepository;
import com.tp1_jonathanlorfils.demo.repository.PermisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Order(1)
public class insert implements CommandLineRunner {

        @Autowired
        private CitoyenRepository citoyenRepository;


        @Autowired
        private PermisRepository permisRepository;

        @Autowired
        private SystemService systemService;

        private void insertData() {
            Citoyen citoyen = new Citoyen();
            citoyen.setPrenom("Jonathan"); citoyen.setNom("Lorfils"); citoyen.setNumeroAssuranceSocial("DAAC5E61"); citoyen.setTypePermis("Test");
            citoyen.setCourriel("test@gmail.com"); citoyen.setAge(18); citoyen.setMotDePasse("mdp123");
            citoyen.setPermis(systemService.genererPermis(citoyen.getTypePermis(), systemService.generateQR(citoyen)));
            citoyenRepository.save(citoyen);
    }

    private void cleanData(){
            this.permisRepository.deleteAll();
            this.citoyenRepository.deleteAll();
    }

    @Override
    public void run(String... args) throws Exception{
            cleanData();
        insertData();
    }
}
