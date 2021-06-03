import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Citizen } from '../models/citizen';
import { GenericService } from './genericService';

@Injectable({
  providedIn: 'root'
})
export class PermisService extends GenericService<Citizen, Number>{

  constructor(http: HttpClient) { 
    super(http, 'http://localhost:2021/permisSante') // Lien vers les permis
  }

  public userIsLoginIn(){
    let courriel = sessionStorage.getItem('courriel');
    if(courriel != null){
    return true
    }
    return false;
  }

  public logout(){
    sessionStorage.clear();
  }
}
