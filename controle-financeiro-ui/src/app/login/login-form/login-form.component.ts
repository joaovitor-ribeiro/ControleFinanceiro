import { finalize } from 'rxjs/operators';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';

import { LoginService } from './../login.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.scss']
})
export class LoginFormComponent implements OnInit {

  carregando!: boolean;
  loginFormulario!: FormGroup;
  id!: number;
  editar!: boolean;
  foto!: File;

  get propriedade() {
    return this.loginFormulario.controls;
  }

  constructor(
    private formBuilder: FormBuilder,
    private spinner: NgxSpinnerService,
    private router: Router,
    private loginService: LoginService,
  ) { }

  ngOnInit(): void {
    this.carregando = true;
    this.spinner.show();

    this.loginFormulario = this.formBuilder.group({
      email: ['',[Validators.required, Validators.email]],
      senha: ['', Validators.required]
    });

    this.spinner.hide();
    this.carregando = false;

    this.colocarFocoCampoEmail();
  }

  enviarFormulario() {
    if (this.loginFormulario.invalid) {
      this.loginFormulario.markAllAsTouched();
    }else{
      this.carregando = true;
      this.spinner.show();
      const usuario = this.loginFormulario.getRawValue();
      this.loginService.login(usuario).pipe(finalize(() => {
        this.carregando = false;
        this.spinner.hide();
      }))
      .subscribe(result => {
        this.router.navigate(['painel']);
        localStorage.setItem('token', result.token);
        localStorage.setItem('usuario', JSON.stringify(result));
        this.loginService.loginUsuario.next(true);
      });
    }
  }

  limparBotoes(campo: string) {
    this.loginFormulario.get(campo)?.setValue('');
  }

  colocarFocoCampoEmail() {
    setTimeout(() => {
      document.getElementById('email')?.focus();
    }, 100);
  }

}
