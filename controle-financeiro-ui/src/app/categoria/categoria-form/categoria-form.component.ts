import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { finalize } from 'rxjs/operators';
import { AlertModalService } from 'src/app/shared/alert-modal/alert-modal.service';
import { ErrorModalService } from 'src/app/shared/error-modal/error-modal.service';
import { Categoria } from '../categoria.model';

import { CategoriaService } from '../categoria.service';

interface Tipo {
  label: string;
  value: string;
}

@Component({
  selector: 'app-categoria-form',
  templateUrl: './categoria-form.component.html',
  styleUrls: ['./categoria-form.component.scss']
})
export class CategoriaFormComponent implements OnInit {

  categoriaFormulario!: FormGroup;
  carregando = true;
  id!: number;
  categoria!: Categoria;
  editar!: boolean;

  get propriedade() {
    return this.categoriaFormulario.controls;
  }

  tipos: Tipo[] = [
    {
      label: 'Ganho',
      value: 'G'
    },
    {
      label: 'Despesa',
      value: 'D'
    }];

  constructor(
    private formBuilder: FormBuilder,
    private service: CategoriaService,
    private spinner: NgxSpinnerService,
    private erroService: ErrorModalService,
    private router: Router,
    private route: ActivatedRoute,
    private alertService: AlertModalService,
  ) { }

  ngOnInit(): void {
    this.spinner.show();

    this.categoriaFormulario = this.formBuilder.group({
      nome:['', [Validators.required, Validators.minLength(3)]],
      tipo:['', [Validators.required]],
    });

    this.route.params?.subscribe(value => {
      if (value?.id) {
        this.id = value.id;
        this.editar = true;
        this.service.retornarCategoriaId(this.id).subscribe( result => {
          this.categoria = result;
          this.preencherFormulario();
        });
      }
    });

    this.spinner.hide();
    this.carregando = false;

    this.colocarFocoCampoNome();
  }

  preencherFormulario() {
    this.categoriaFormulario.patchValue({
      nome: this.categoria.nome,
      tipo: this.categoria.tipo,
    });
  }

  enviarFormulario() {
    if (this.categoriaFormulario.invalid) {
      this.categoriaFormulario.markAllAsTouched();
    }else{
      const categoria = this.categoriaFormulario.getRawValue();
      if (this.editar) {
        this.service.editar(this.id, categoria).subscribe(() => {
          this.router.navigate(['categoria/listar'], { queryParamsHandling: 'preserve' });
           this.alertService.showAlertSuccess('Categoria editada com sucesso');
        },
        error => {
          this.erroService.showError(error?.error?.message || 'Falha na conexão');
        });
      } else {
        this.service.inserir(categoria).subscribe(() => {
          this.router.navigate(['categoria/listar'], { queryParamsHandling: 'preserve' });
           this.alertService.showAlertSuccess('Categoria cadastrada com sucesso');
        },
        error => {
          this.erroService.showError(error?.error?.message || 'Falha na conexão');
        });
      }
    }
  }

  limparBotoes(campo:string) {
    this.categoriaFormulario.get(campo)?.setValue('');
  }

  voltar() {
    this.router.navigate(['categoria/listar']);
  }

  colocarFocoCampoNome() {
    setTimeout(() => {
      document.getElementById('nome')?.focus();
    }, 100);
  }
}
