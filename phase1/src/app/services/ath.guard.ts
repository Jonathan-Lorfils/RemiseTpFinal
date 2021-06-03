import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { PermisService } from './permis.service';

@Injectable({
  providedIn: 'root'
})
export class AthGuard implements CanActivate {


  constructor(private route: Router, private service: PermisService){}

  canActivate(
    route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
      if(this.service.userIsLoginIn()) return true;
      this.route.navigateByUrl('/home')
    return false;
  }
  
}
