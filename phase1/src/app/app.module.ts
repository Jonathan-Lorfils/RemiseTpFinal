import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DemandePermisComponent } from './components/demande-permis/demande-permis.component';
import { LoginComponent } from './components/login/login.component';
import { Errors404Component } from './components/errors404/errors404.component';
import { LogoutComponent } from './components/logout/logout.component';
import { SubscribeComponent } from './components/subscribe/subscribe.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { FooterComponent } from './components/footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    DemandePermisComponent,
    LoginComponent,
    Errors404Component,
    LogoutComponent,
    SubscribeComponent,
    DashboardComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
