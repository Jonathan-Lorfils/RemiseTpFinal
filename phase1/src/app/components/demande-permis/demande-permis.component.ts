import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Citizen } from 'src/app/models/citizen';
import { CitizenService } from 'src/app/services/citizen.service';
import { MinistereService } from 'src/app/services/ministere.service';

@Component({
  selector: 'app-demande-permis',
  templateUrl: './demande-permis.component.html',
  styleUrls: ['./demande-permis.component.css']
})
export class DemandePermisComponent implements OnInit {

  validMessage: string = "";
  citizen: Citizen;
  flag: boolean; // si info rentrer sont bonnes
  valide: boolean;


  constructor(private service: MinistereService, private router: Router) { }

  ngOnInit(): void {

  }

  demandePermisAdulteForm = new FormGroup({
      nassm: new FormControl('', Validators.required),
      courriel: new FormControl('', Validators.required)
  });  

  demandePermisEnfantForm = new FormGroup({
    nassmParent: new FormControl('', Validators.required),
    nassmEnfant: new FormControl('', Validators.required),
    courriel: new FormControl('', Validators.required)
});  

public onSubmitAdulteForm() {
  if(this.demandePermisAdulteForm.valid){
   this.service.checkCitizenValidity(this.demandePermisAdulteForm.get('nassm').value).subscribe(
     (data) => {
       if (data){
        this.router.navigateByUrl('/subscribe', {state: {id: 1, nassm: this.demandePermisAdulteForm.get('nassm').value, courriel: this.demandePermisAdulteForm.get('courriel').value}})
       } else {
        this.validMessage= 'Numero assurance social invalide'
       }
     },
    (err) => {
      console.log(err);
    }
   )
  } else {
    this.validMessage= 'Please fill the form before submitting!';
  }
}

  public onSubmitEnfantForm() {
    if(this.service.checkEnfantValidity(this.demandePermisEnfantForm.get('nassmParent').value,this.demandePermisEnfantForm.get("nassmEnfant").value)){
     this.service.checkCitizenValidity(this.demandePermisEnfantForm.get('nassmEnfant').value).subscribe(
       (data) => {
         if (data){
          this.router.navigateByUrl('/subscribe', {state: {nassm: this.demandePermisEnfantForm.get('nassmEnfant').value, courriel: this.demandePermisEnfantForm.get('courriel').value, nassmParent: this.demandePermisEnfantForm.get('nassmParent').value}})
         } else {
          this.validMessage= 'Numero assurance social invalide'
         }
       },
      (err) => {
        console.log(err);
      }
     )
    } else {
      this.validMessage= 'Please fill the form before submitting!';
    }
  }

}
