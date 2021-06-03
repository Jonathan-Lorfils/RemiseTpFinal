import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DemandePermisComponent } from './components/demande-permis/demande-permis.component';
import { Errors404Component } from './components/errors404/errors404.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { SubscribeComponent } from './components/subscribe/subscribe.component';
import { AthGuard } from './services/ath.guard';

const routes: Routes = [

  {path: 'subscribe', component:SubscribeComponent},
  {path: 'dashboard', component:DashboardComponent, canActivate:[AthGuard] }, 
  {path: 'home', component:DemandePermisComponent},
  {path: 'login', component:LoginComponent},
  {path: 'logout', component:LogoutComponent, canActivate:[AthGuard]}, 
  {path: '', redirectTo:'/home', pathMatch: 'full'},
  {path: '**', component:Errors404Component}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
