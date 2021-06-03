package com.ministere.demo;

import com.ministere.demo.model.Citoyen;
import com.ministere.demo.model.Enfant;
import com.ministere.demo.repository.CitoyenRepository;
import com.ministere.demo.repository.EnfantRepository;
import com.ministere.demo.service.MinistereService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppMinistereTest {

    @Autowired
    private MinistereService ministereService;

    @Autowired
    private CitoyenRepository citoyenRepository;

    @Autowired
    private EnfantRepository enfantRepository;

    @Test
    public void testCheckCitizenValidity(){
        Citoyen citoyen = new Citoyen();
        citoyen.setNomComplet("Jonathan Lorfils"); citoyen.setNassm("LORJ1960"); citoyen.setType("TEST");
        citoyen.setId(21);
        citoyenRepository.save(citoyen);
        assertTrue(ministereService.checkCitizenValidity("LORJ1960"));
        citoyenRepository.deleteCitoyenById(21);
    }

    @Test
    public void testCheckCitizenValidityEnfant(){
        Enfant enfant = new Enfant();
        enfant.setNomComplet("Jonathan Lorfils"); enfant.setNassm("LORJ1960"); enfant.setType("TEST");
        enfant.setId(19);
        enfantRepository.save(enfant);
        assertTrue(ministereService.checkCitizenValidity("LORJ1960"));
        enfantRepository.deleteEnfantsById(19);
    }

    @Test
    public void testCheckCitizenType(){
        Citoyen citoyen = new Citoyen();
        citoyen.setNomComplet("Jonathan Lorfils"); citoyen.setNassm("LORJ1960"); citoyen.setType("TEST");
        citoyen.setId(21);
        citoyenRepository.save(citoyen);
        assertTrue(ministereService.checkCitoyenType(citoyen.getNassm(), citoyen.getType()));
        citoyenRepository.deleteCitoyenById(21);
    }

}
