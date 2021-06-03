package com.tp1_jonathanlorfils.demo.controller;

import com.tp1_jonathanlorfils.demo.Service.SystemService;
import com.tp1_jonathanlorfils.demo.model.*;
import com.tp1_jonathanlorfils.demo.repository.CitoyenRepository;
import com.tp1_jonathanlorfils.demo.repository.EnfantRepository;
import com.tp1_jonathanlorfils.demo.repository.PermisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "http://localhost:4220")
public class CitoyenController {

    @Autowired
    CitoyenRepository citoyenRepository;

    @Autowired
    PermisRepository permisRepository;

    @Autowired
    SystemService systemService;

    @Autowired
    EnfantRepository enfantRepository;

    @GetMapping("/permisSante/login/{email}/{password}")
    public Citoyen login(@PathVariable("email") String email, @PathVariable("password") String password){
        return systemService.login(email,password);
    }

    @GetMapping("/permisSante/renouveller/{id}/{typePermis}")
    public boolean renouvellerPermis(@PathVariable("id") Integer id, @PathVariable("typePermis") String typePermis) {
        return systemService.renouvellerPermis(id,typePermis);
    }

    @RequestMapping(value = "/permisSante", method = RequestMethod.POST)
    public Citoyen subscribe(@RequestBody Citoyen citoyen) {
        return systemService.subscribe(citoyen);
    }

    @GetMapping("/permisSante/{nassm}/{nom}/{prenom}/{age}/{courriel}/{mdp}/{typePermis}")
    public Citoyen subscribeMobile(@PathVariable("nassm") String nassm, @PathVariable("nom") String nom,
                                   @PathVariable("prenom") String prenom, @PathVariable("age") String age,
                                   @PathVariable("courriel") String courriel, @PathVariable("mdp") String mdp,
                                   @PathVariable("typePermis") String typePermis) {
       return systemService.subscribeMobile(nassm, nom, prenom, age, courriel, mdp, typePermis);
    }

    @GetMapping("/permisSante/qrCode/{nassm}")
    public void getCodeQrByIdPermis(@PathVariable("nassm") String nassm,
                                    HttpServletResponse response){
        systemService.afficherQr(nassm,response);
    }

    @RequestMapping(value = "/permisSante/ajoutEnfant", method = RequestMethod.POST)
    public Enfant addEnfant(@RequestBody Enfant enfant) {
        return systemService.subscribeEnfant(enfant);
    }
}
