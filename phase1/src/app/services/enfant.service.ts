import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Child } from '../models/child';
import { GenericService } from './genericService';

@Injectable({
  providedIn: 'root'
})
export class EnfantService extends GenericService<Child, Number>{

  constructor(http: HttpClient) { 
    super(http, "http://localhost:2021/permisSante");
  }
}
