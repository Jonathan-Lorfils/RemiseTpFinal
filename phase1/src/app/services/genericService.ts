import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';


export class GenericService<T, ID> {

    constructor(protected http: HttpClient, private url: string){

    } 
    
  save(t: T): Observable<T> {
    return this.http.post<T>(this.url, t);
  }

  saveEnfant(t: T): Observable<T> {
    return this.http.post<T>(this.url + "/ajoutEnfant", t);
  }


    getAll(): Observable<T[]> {
      return this.http.get<T[]>(this.url);
    }

    findById(id: ID): Observable<T> {
      return this.http.get<T>(this.url + "/" + id);
    }
    
    checkCitizenValidity(nassm: string): Observable<boolean> {
      return this.http.get<boolean>(this.url + "/" + nassm);
    }

    checkEnfantValidity(nassmParent: string, nassmEnfant: string): Observable<boolean> {
      return this.http.get<boolean>(this.url + "/" + nassmParent + "/" + nassmEnfant);
    }

    validationTypeVaccin(nassm: string, typeVaccinDemande: string): Observable<boolean> {
      return this.http.get<boolean>(this.url + "/validationTypeVaccin/" + nassm + "/" + typeVaccinDemande);
    }

    renouvellerPermis(id: number,typePermis: string): Observable<boolean> {
      return this.http.get<boolean>(this.url + "/renouveller/" + id + "/" + typePermis);
    }

    login(email: string, password: string): Observable<T>{
      return this.http.get<T>(this.url + "/login/" + email + "/" + password)
    }

    update(id: ID, t: T): Observable<T> {
      return this.http.put<T>(this.url + "/" + id, t, {});
    }

    deleteById(id: ID): Observable<T> {
      return this.http.delete<T>(this.url + "/" + id);
    }
    
}