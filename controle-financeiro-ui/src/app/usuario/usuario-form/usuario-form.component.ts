import { UsuarioService } from './../usuario.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { finalize } from 'rxjs/operators';
import { AlertModalService } from 'src/app/shared/alert-modal/alert-modal.service';
import { ErrorModalService } from 'src/app/shared/error-modal/error-modal.service';
import { Usuario } from '../usuario.model';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-usuario-form',
  templateUrl: './usuario-form.component.html',
  styleUrls: ['./usuario-form.component.scss']
})
export class UsuarioFormComponent implements OnInit {
  carregando!: boolean;
  usuarioFormulario!: FormGroup;
  id!: number;
  editar!: boolean;
  foto!: File;
  usuario!: Usuario;
  image!: any;

  get propriedade() {
    return this.usuarioFormulario.controls;
  }

  constructor(private formBuilder: FormBuilder,
    private spinner: NgxSpinnerService,
    private erroService: ErrorModalService,
    private router: Router,
    private route: ActivatedRoute,
    private alertService: AlertModalService,
    private usuarioService: UsuarioService,
    private sanitizer: DomSanitizer
  ) { }

  ngOnInit(): void {
    this.carregando = true;
    this.spinner.show();

    this.usuarioFormulario = this.formBuilder.group({
      nome: ['', Validators.required],
      cpf: ['', Validators.required],
      email: ['', Validators.required],
      senha: ['', Validators.required]
    });

    this.route.params?.subscribe(value => {
      if (value?.id){
        this.id = value.id;
        this.editar = true;
        this.carregando = true;
        this.spinner.show();
        this.usuarioService.retornarUsuarioId(this.id).subscribe( usuario => {
          this.usuario = usuario;
          let objectURL = 'data:image/png;base64,' + usuario.foto;
          this.image = this.sanitizer.bypassSecurityTrustUrl(objectURL);
          this.preencherFormulario();
        });
      }
    });

    this.spinner.hide();
    this.carregando = false;

    this.colocarFocoCampoNome();
  }

  preencherFormulario(){
    this.usuarioFormulario.patchValue({
      nome: this.usuario.nome,
      cpf: this.usuario.cpf,
      email: this.usuario.email,
      senha: this.usuario.senha
    })
  }

  enviarFormulario() {
    if (this.usuarioFormulario.invalid) {
      this.usuarioFormulario.markAllAsTouched();
    }else{
      const usuario = this.usuarioFormulario.getRawValue();
      if (this.editar) {
        this.usuarioService.editar(this.id, usuario).subscribe(() => {
          this.salvarFoto();
          this.router.navigate(['/despesa/listar'], { queryParamsHandling: 'preserve' });
          this.alertService.showAlertSuccess('Usuário editado com sucesso');
        },
        error => {
          this.erroService.showError(error?.error?.message || 'Falha na conexão');
        });
      } else {
        this.usuarioService.inserir(usuario).subscribe(id => {
          this.id = id;
          this.salvarFoto();
        },
        error => {
          this.erroService.showError(error?.error?.message || 'Falha na conexão');
        });
      }
    }
  }

  salvarFoto(){
    if (this.foto){
      this.usuarioService.inserirFoto(this.foto, this.id).subscribe( () => {
        this.salvoComSucesso();
      },
      error => {
        this.erroService.showError(error?.error?.message || 'Não foi possível salvar a foto');
      });
    } else {
      this.salvoComSucesso();
    }
  }

  salvoComSucesso(){
    this.router.navigate(['*'], { queryParamsHandling: 'preserve' });

    if (this.editar) {
      this.alertService.showAlertSuccess('Usuário editado com sucesso');
    }
    else {
      this.alertService.showAlertSuccess('Usuário cadastrado com sucesso');
    }

  }

  limparBotoes(campo: string) {
    this.usuarioFormulario.get(campo)?.setValue('');
  }

  colocarFocoCampoNome() {
    setTimeout(() => {
      document.getElementById('nome')?.focus();
    }, 100);
  }

  selecionarFoto(fileInput: any): void {
    this.foto = fileInput.target.files[0] as File;

    const reader = new FileReader();
    reader.onload = function (e: any) {
      document.getElementById('foto')?.removeAttribute('hidden');
      document.getElementById('foto')?.setAttribute('src', e.target.result);
    };

    if (!this.foto?.type?.startsWith("image")) {
      this.erroService.showError('Esse tipo de arquivo não é suportado! Escolha uma imagem.');
      this.foto = {} as File;
    } else if (this.foto?.size > 1048576) {
      this.erroService.showError('O tamanho dessa imagem não é suportada! Escolha uma imagem que o tamanho seja inferior a 1048 KB.');
      this.foto = {} as File;
    }

    if (this.editar && this.foto?.size > 0) {
      this.image = null;
    }

    reader.readAsDataURL(this.foto);
  }
}
