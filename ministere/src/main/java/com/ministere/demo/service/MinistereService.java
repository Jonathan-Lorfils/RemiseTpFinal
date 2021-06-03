package com.ministere.demo.service;

import com.ministere.demo.repository.CitoyenRepository;
import com.ministere.demo.repository.EnfantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MinistereService {

    @Autowired
    CitoyenRepository citoyenRepository;

    @Autowired
    EnfantRepository enfantRepository;

    public boolean checkCitizenValidity(String nassm){
        boolean flag = false;
        try{
            if (citoyenRepository.findCitoyenByNassm(nassm) != null){
                flag = true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    public boolean checkEnfantValidity(String nassmParent, String nassmEnfant){
        boolean flag = false;
        try{
            if ((citoyenRepository.findCitoyenByNassm(nassmParent) != null) || enfantRepository.findEnfantByNassm(nassmEnfant) != null){
                flag = true;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }

    public boolean checkCitoyenType(String nassm, String typePermisDemande){
        boolean flag = false;
        try{
            if(checkCitizenValidity(nassm) &&  citoyenRepository.findCitoyenByNassm(nassm).getType().equals(typePermisDemande.toUpperCase())){ // si le citoyen existe
                flag = true;
            } else {
                flag = false;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
}
