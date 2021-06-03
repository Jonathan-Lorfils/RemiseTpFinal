import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Citizen } from '../models/citizen';
import { GenericService } from './genericService';

@Injectable({
  providedIn: 'root'
})
export class MinistereService extends GenericService<Citizen, Number>{

  constructor(http: HttpClient) { 
    super(http, "http://localhost:9393/ministere");
  }
}
