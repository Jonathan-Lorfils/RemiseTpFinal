import { state } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Citizen } from 'src/app/models/citizen';
import { PermisService } from 'src/app/services/permis.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  validMessage: string = "";
  //email: string = "";
  //password: string = "";
  flag: boolean;
  citizen: Citizen;


  constructor(private service: PermisService, private route: Router) { }

  ngOnInit(): void {
  }

  loginForm = new FormGroup({
    courriel: new FormControl('', Validators.required),
    motDePasse: new FormControl('', Validators.required)
  });

  public onLogin(){
    this.service.login(this.loginForm.get('courriel').value, this.loginForm.get('motDePasse').value).subscribe(
      (data) => {
        if (data != null){
            sessionStorage.setItem('courriel',this.loginForm.get('courriel').value);
            this.citizen = data;
            this.route.navigateByUrl('/dashboard', {state: this.citizen});
        }  else {
          this.validMessage = "Le login ou mot de passe est erronee!"
        }
      }
    );
    
  }

}


