package com.ministere.demo.controller;

import com.ministere.demo.model.Citoyen;
import com.ministere.demo.repository.CitoyenRepository;
import com.ministere.demo.service.MinistereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:4220")
public class MinistereController {

    @Autowired
    MinistereService service;

    @Autowired
    CitoyenRepository citoyenRepository;

    @GetMapping("/ministere/{nassm}")
    @ResponseBody
    public boolean checkCitizenValidity(@PathVariable String nassm){
        return service.checkCitizenValidity(nassm);
    }

    @GetMapping("/ministere/validationTypeVaccin/{nassm}/{typeVaccinDemande}")
    @ResponseBody
    public boolean validationTypeVaccin(@PathVariable("nassm") String nassm,@PathVariable("typeVaccinDemande") String typeVaccinDemande){
        return service.checkCitoyenType(nassm,typeVaccinDemande);
    }

    @GetMapping("/ministere/{nassmTuteur}/{nassmEnfant}")
    @ResponseBody
    public boolean checkEnfantValidity(@PathVariable String nassmTuteur, @PathVariable String nassmEnfant){
        return service.checkEnfantValidity(nassmTuteur, nassmEnfant);
    }
}
