import { Component, DebugElement, OnInit } from '@angular/core';
import { Citizen } from 'src/app/models/citizen';
import { PermisService } from 'src/app/services/permis.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  citizen: Citizen;
  validMessage: string = "";
  reponse: Boolean;

  constructor(public service: PermisService) { }

  ngOnInit(): void {
    this.citizen = history.state;
  }

  public renouvellerPermis(){
    if(this.citizen.typePermis == "Test"){
      this.service.renouvellerPermis(this.citizen.id,this.citizen.typePermis).subscribe(
      (data) => {
        this.reponse = data;
        if(this.reponse){
          this.validMessage = 'Renouvellement effectuer avec succes'
        } else {
          this.validMessage = 'Erreur lors du renouvellement'
        }
      }
    )
    } else {
      this.validMessage= 'Renouvellement impossible sur les permis de type VACCIN'
    }
  }


}
