import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EnfantService } from 'src/app/services/enfant.service';
import { MinistereService } from 'src/app/services/ministere.service';
import { PermisService } from 'src/app/services/permis.service';

@Component({
  selector: 'app-subscribe',
  templateUrl: './subscribe.component.html',
  styleUrls: ['./subscribe.component.css']
})
export class SubscribeComponent implements OnInit {

  validMessage: string = "";
  flag: boolean;
  nassm: string = "";
  courriel: string = "";
  nassmParent: string ="";
  reponse: boolean;

  constructor(private service: PermisService,private serviceChild: EnfantService,private serviceMinistere: MinistereService ,private route: Router) { }

  ngOnInit(): void {
    this.nassm = history.state.nassm;
    this.courriel = history.state.courriel;
    this.nassmParent = history.state.nassmParent;
  }

  subscribeForm = new FormGroup({
    numeroAssuranceSocial: new FormControl(''),
    prenom: new FormControl('', Validators.required),
    nom: new FormControl('', Validators.required),
    age: new FormControl('', Validators.required),
    courriel: new FormControl(''),
    motDePasse: new FormControl('', Validators.required),
    typePermis: new FormControl('', Validators.required)
  });

  subscribeEnfantForm = new FormGroup({});


    

  onSubscribe(){
    if (this.subscribeForm.valid){
      this.subscribeForm.get("numeroAssuranceSocial").setValue(this.nassm);
      this.subscribeForm.get("courriel").setValue(this.courriel);
      this.serviceMinistere.validationTypeVaccin(this.nassm,this.subscribeForm.get("typePermis").value).subscribe(
        (data) => {
          this.reponse = data;
          if(this.reponse){
            if(this.nassmParent == null){
              if(this.subscribeForm.get("age").value >= 18){
              this.service.save(this.subscribeForm.value).subscribe(
              (data) => {
                this.route.navigateByUrl('/dashboard', {state: data});
              },
              (err) => {
                console.log(err);
              }
            );
              } else {
                this.validMessage="Age invalide pour creation d'un permis ADULTE"
              }
              
            } else {
              if(this.subscribeForm.get("age").value < 18 || this.subscribeForm.get("age").value > 0){
              this.subscribeEnfantForm = this.subscribeForm;
              this.subscribeEnfantForm.get("numeroAssuranceSocial").setValue(this.subscribeForm.get("numeroAssuranceSocial").value)
              this.subscribeEnfantForm.addControl("nassmParent", new FormControl('', Validators.required));
              this.subscribeEnfantForm.get("nassmParent").setValue(this.nassmParent);
              console.log(this.subscribeEnfantForm);
              this.serviceChild.saveEnfant(this.subscribeEnfantForm.value).subscribe(
                (data) => {
                  this.route.navigateByUrl('/dashboard', {state: data});
                },
                (err) => {
                  console.log(err);
                }
              ) 
              } else {
                this.validMessage = "Age Invalide"
              }
            } 
          } else {
            this.validMessage = 'Type permis de Invalide'
          }
        }
      )
    
    } else {
      this.validMessage = "Veuillez remplir le formulaire"
    }
  }

}

