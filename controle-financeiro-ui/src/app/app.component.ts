import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Token } from './login/login.model';
import { LoginService } from './login/login.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  title = 'Controle Financeiro';
  exibe!: boolean;
  nome = 'USUÁRIO';
  id!: number;
  usuario!: Token;
  objectURL!: string;

  constructor(
    private router : Router,
    private loginService: LoginService,
  ) {}

  ngOnInit(): void {
    this.dadosUsuario();
    this.loginService.loginUsuario.subscribe(() => {
      this.dadosUsuario();
    });

  }

  isSelected(url: string) {
    return this.router.url.includes(url);
  }

  dadosUsuario() {
    const usuarioBean = localStorage.getItem('usuario');
    if (usuarioBean) {
      this.exibe = true;
      this.usuario = JSON.parse(usuarioBean);
      this.nome = this.usuario.nome;
      this.id = this.usuario.id;
      if (this.usuario.foto) {
        this.objectURL = 'data:image/png;base64,' + this.usuario.foto;
      } else {
        this.objectURL = '../assets/menu/usuario.jpg';
      }

    } else {
      this.exibe = false;
    }
  }

  url() {
    this.router.navigate([`usuario/${this.id}`])
  }

  sair() {
    this.router.navigate([`entrar`]);
    localStorage.removeItem('token');
    localStorage.removeItem('usuario');
    this.loginService.loginUsuario.next(false);
  }

  exibirSemLogin() {
    return (this.router.url.includes('/entrar') || this.router.url.includes('usuario/inserir') ) && !this.exibe;
  }
}
