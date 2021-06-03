package com.example.demo.controller;

import com.example.demo.Service.SystemService;
import com.example.demo.model.Citoyen;
import com.example.demo.model.Permis;
import com.example.demo.repository.CitoyenRepository;
import com.example.demo.repository.EnfantRepository;
import com.example.demo.repository.PermisRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // C'est un controller de type MVC ET NON rest
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class AdminController {

    @Autowired
    CitoyenRepository citoyenRepository;

    @Autowired
    EnfantRepository enfantRepository;

    @Autowired
    PermisRepository permisRepository;

    @Autowired
    SystemService systemService;

    @RequestMapping (path = "/")
    public String index(){
        return "index";
    }

    @GetMapping("/citoyens")
    public String getAllCitoyens(Model model){
        model.addAttribute("listeCitoyens", citoyenRepository.findAll());
        model.addAttribute("listeEnfants", enfantRepository.findAll());
        return "citoyens";
    }

    @RequestMapping("/ajouterCitoyen")
    public String ajouterCitoyen(Model model){
        model.addAttribute("citoyen1", new Citoyen());
        return "ajouterCitoyen";
    }

    @RequestMapping("/editCitoyen/{id}")
    public String editCitoyen(Model model, @PathVariable("id") Integer id){
        model.addAttribute("citoyen", citoyenRepository.findCitoyenById(id));
        return "editCitoyen";
    }

    @GetMapping("/afficherQr/{id}")
    public void getCodeQrByIdPermis(@PathVariable("id") Integer id,
                                    HttpServletResponse response) throws IOException {
        systemService.genererCodeQr(id,response);
    }

    @PostMapping("/saveCitoyen")
    public String saveCitoyen(Citoyen citoyen) throws Exception {
        return systemService.saveCitoyen(citoyen);
    }

    @GetMapping("/permis")
    public String getAllPermis(Model model){
        model.addAttribute("listePermis", permisRepository.findAll());
        return "permis";
    }

    @GetMapping("/citoyens/supprimer/{id}")
    public String supprimerCitoyen(@PathVariable("id") Integer id){
        return systemService.supprimerCitoyen(id);
    }

    @GetMapping("/permis/supprimer/{id}")
    public String supprimerPermis(@PathVariable("id") Integer id){
        return systemService.supprimerPermis(id);
    }
}
