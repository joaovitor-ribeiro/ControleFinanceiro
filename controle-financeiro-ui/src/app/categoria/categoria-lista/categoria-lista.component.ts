import { finalize } from 'rxjs/operators';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { AlertModalService } from 'src/app/shared/alert-modal/alert-modal.service';
import { ConfirmModalService } from 'src/app/shared/confirm-modal/confirm-modal.service';
import { ErrorModalService } from 'src/app/shared/error-modal/error-modal.service';

import { Categoria } from '../categoria.model';
import { CategoriaService } from '../categoria.service';

interface Tipo {
  label: string;
  value: string;
}

@Component({
  selector: 'app-categoria-lista',
  templateUrl: './categoria-lista.component.html',
  styleUrls: ['./categoria-lista.component.scss']
})
export class CategoriaListaComponent implements OnInit {

  filtroFormulario!: FormGroup;
  carregando = true;
  displayedColumns: string[] = ['nome', 'tipo', 'acoes'];
  categorias!: Categoria[];

  tipos: Tipo[] = [
    {
      label: 'Ganho',
      value: 'G'
    },
    {
      label: 'Despesa',
      value: 'D'
    },
    {
      label: 'Todos',
      value: 'T'
    }
  ];

  constructor(
    private router: Router,
    private spinner: NgxSpinnerService,
    private categoriaService: CategoriaService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private erroService: ErrorModalService,
    private alertService: AlertModalService,
    private dialogService: ConfirmModalService
  ) { }

  ngOnInit(): void {
    this.spinner.show();

    this.filtroFormulario = this.formBuilder.group({
      nome: [''],
      tipo: ['T'],
    });

    this.anexarConsulta();

    this.route.queryParams.subscribe(filtro => {
      if(filtro) {
        if (filtro?.nome) {
          this.filtroFormulario.get('nome')?.setValue(filtro?.nome);
        }
        if (filtro?.tipo) {
          this.filtroFormulario.get('tipo')?.setValue(filtro?.tipo);
        }
      }
    });

    this.listarCategoria();
  }

  listarCategoria() {
    this.carregando = true;
    this.spinner.show();

    const filtro = this.filtroFormulario?.getRawValue();

    this.categoriaService.listar(filtro).pipe(finalize(() => {
      this.carregando = false;
      this.spinner.hide();
    }))
    .subscribe(categorias => {
      this.categorias = categorias;
      this.anexarConsulta();
    },
    error => {
      this.erroService.showError(error?.error?.message || 'Falha na conexão');
    });
  }

  inserir(){
    this.router.navigate(['categoria/inserir']);
  }

  limparBotoes(valor: string) {
    this.filtroFormulario.get(valor)?.setValue(undefined);
  }

  editar(id: number){
    this.router.navigate([`/categoria/${id}`])
  }

  excluir(id: number){
    this.dialogService.showConfirm('Deseja realmente excluir essa categoria?').subscribe(result => {
      if (result) {
        this.carregando = true;
        this.spinner.show();
        this.categoriaService.excluir(id).pipe(finalize(() => {
          this.carregando = false;
          this.spinner.hide();
        })).subscribe(() => {
          this.alertService.showAlertSuccess('Categoria excluída com sucesso!');
        },
        error => {
          this.erroService.showError(error?.error?.message || 'Falha na conexão');
        });
      }
    })
  }

  anexarConsulta() {
    this.router.navigate([], {
      queryParams: {
        nome: this.filtroFormulario.get('nome')?.value || null,
        tipo: this.filtroFormulario.get('tipo')?.value || 'T',
      },
      queryParamsHandling: 'merge',
      preserveFragment: true
    });
  }

  tipoCategoria(tipo: string) {
    return tipo === 'G' ? 'Ganho' : 'Despesa';
  }


}
