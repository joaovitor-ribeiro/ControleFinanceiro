import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { ErrorModalService } from '../shared/error-modal/error-modal.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(
    private router: Router,
    private erroService: ErrorModalService,
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const token = localStorage.getItem('token');

    if(token){
      return true;
    }

    this.router.navigate(['/entrar']);
    this.erroService.showError('Realize o login para acessar a página!');
    return false;
  }

}
