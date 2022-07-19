import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { LoginService } from 'src/app/login/login.service';

import { ErrorModalService } from '../error-modal/error-modal.service';

@Injectable({
  providedIn: 'root'
})
export class InterceptService implements HttpInterceptor {

  constructor(
    private router: Router,
    private erroService: ErrorModalService,
    private loginService: LoginService,
  ) { }

  intercept( request: HttpRequest<any>, next: HttpHandler ): Observable<HttpEvent<any>> {
    const token = localStorage.getItem('token');

    const dupReq = request.clone({
      headers:  request.headers.set('Authorization', `Bearer ${token}`),
    });

    return next.handle(dupReq).pipe(catchError((error) => {

      if (error?.error?.message === 'Access Denied') {
        localStorage.removeItem('token');
        localStorage.removeItem('usuario');

        this.loginService.loginUsuario.next(false);
        this.erroService.showError('Acesso negado!').subscribe(result => {
          if (!result) {
            this.router.navigate(['/entrar']);
          }
        })

      } else {
        if (error?.error?.message) {
          this.erroService.showError(error?.error?.message);
        } else {
          this.erroService.showError('Falha na conexão!');
        }
      }

      throw error;
    }));
  }

}
