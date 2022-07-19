import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { environment } from 'src/environments/environment';
import { loginForm, Token } from './login.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private url = `${environment.url}autenticacao`
  loginUsuario = new Subject<boolean>();

  constructor(
    private http: HttpClient
  ) { }

  login(loginForm: loginForm) {
    return this.http.post<Token>(this.url, loginForm);
  }

}
