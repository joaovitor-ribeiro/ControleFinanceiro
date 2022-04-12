import { AlertModalService } from './../../shared/alert-modal/alert-modal.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { PageEvent } from '@angular/material/paginator';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { finalize } from 'rxjs/operators';
import { Categoria } from 'src/app/categoria/categoria.model';
import { ErrorModalService } from 'src/app/shared/error-modal/error-modal.service';

import { Despesa } from '../despesa.model';
import { CategoriaService } from './../../categoria/categoria.service';
import { ConfirmModalService } from './../../shared/confirm-modal/confirm-modal.service';
import { PageDespesa, Paginacao } from './../despesa.model';
import { DespesaService } from './../despesa.service';

@Component({
  selector: 'app-despesa-lista',
  templateUrl: './despesa-lista.component.html',
  styleUrls: ['./despesa-lista.component.scss']
})
export class DespesaListaComponent implements OnInit {

  filtroFormulario!: FormGroup;
  carregando = true;
  displayedColumns: string[] = ['cartao', 'descricao', 'categoria', 'valor', 'data', 'acoes'];
  dispesas!: Despesa[];
  categorias!: Categoria[];
  pageDespesa!: PageDespesa;
  paginacao: Paginacao = {page: 0 , size: 5};

  get propriedade() {
    return this.filtroFormulario.controls;
  }

  constructor(
    private router: Router,
    private despesaSerivce: DespesaService,
    private spinner: NgxSpinnerService,
    private categoriaService: CategoriaService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private erroService: ErrorModalService,
    private alertService: AlertModalService,
    private adapter: DateAdapter<any>,
    private dialogService: ConfirmModalService
  ) { }

  async ngOnInit() {
    this.carregando = true;
    this.spinner.show();

    this.filtroFormulario = this.formBuilder.group({
      descricao: [''],
      categorias: [''],
      dataInicial: [''],
      dataFinal: [''],
    });

    this.adapter.setLocale('pt-br');

    await this.carregarCategoria();

    this.route.queryParams.subscribe(filtro => {
      if(filtro) {
        if (filtro?.descricao) {
          this.filtroFormulario.get('descricao')?.setValue(filtro?.descricao);
        }

        const categorias: Number[] = filtro?.categorias ? filtro.categorias.split(',') : [];
        if (categorias.length > 0) {
          this.filtroFormulario.get('categorias')?.setValue(categorias.map(categoria => Number(categoria)));
        }

        if (filtro?.dataInicial) {
          this.filtroFormulario.get('dataInicial')?.setValue(new Date(filtro?.dataInicial));
        }

        if (filtro?.dataFinal) {
          this.filtroFormulario.get('dataFinal')?.setValue(new Date(filtro?.dataFinal));
        }
      }
    });

    this.listar();
  }

  listar(reset?: boolean) {
    this.carregando = true;
    this.spinner.show();

    if (reset) {
      this.paginacao.page = 0;
    }

    let filtro = this.filtroFormulario?.getRawValue();

    filtro.dataInicial = String(filtro?.dataInicial) !== 'Invalid Date' ? filtro.dataInicial : null;
    filtro.dataFinal   = String(filtro?.dataFinal)   !== 'Invalid Date' ? filtro.dataFinal   : null;
    filtro.categorias  = filtro?.categorias?.length > 0 ? filtro.categorias : null;

    this.despesaSerivce.listar(filtro, this.paginacao).pipe(finalize(() => {
      this.carregando = false;
      this.spinner.hide();
      this.anexarConsulta();
    })).subscribe(despesas => {
      this.pageDespesa = despesas;
      this.paginacao.page = despesas?.number;
      this.paginacao.size = despesas?.size;
      this.dispesas = despesas.content;
    },
    error => {
      this.erroService.showError(error?.error?.message || 'Falha na conexão');
    });
  }

  private async carregarCategoria() {
    await this.categoriaService.listar().toPromise().then(categorias => {
      this.categorias = categorias;
    },
    () => {});
  }

  inserir(){
    this.router.navigate(['despesa/inserir']);
  }

  limparBotoes(valor: string) {
    this.filtroFormulario.get(valor)?.setValue(undefined);
  }

  editar(id: number){
    this.router.navigate([`/despesa/${id}`])
  }

  excluir(id: number){
    this.dialogService.showConfirm('Deseja realmente excluir essa despesa?').subscribe(result => {
      if (result) {
        this.despesaSerivce.excluir(id).subscribe(() => {
          this.listar(true);
          this.alertService.showAlertSuccess('Despesa excluída com sucesso!');
        },
        error => {
          this.erroService.showError(error?.error?.message || 'Falha na conexão');
        });
      }
    })
  }

  anexarConsulta() {

    const categorias: [] = this.filtroFormulario.get('categorias')?.value;
    const dataInicial = this.filtroFormulario.get('dataInicial')?.value;
    const dataFinal = this.filtroFormulario.get('dataFinal')?.value;

    this.router.navigate([], {
      queryParams: {
        descricao: this.filtroFormulario.get('descricao')?.value || null,
        categorias: categorias?.length > 0 ? categorias?.join(',') : null,
        dataInicial: (String(dataInicial) !== 'Invalid Date' && dataInicial) ? this.filtroFormulario.get('dataInicial')?.value : null,
        dataFinal: (String(dataFinal) !== 'Invalid Date' && dataFinal) ? this.filtroFormulario.get('dataFinal')?.value : null,
      },
      queryParamsHandling: 'merge',
      preserveFragment: true
    });
  }

  paginar(event: PageEvent) {
    this.paginacao.page = event.pageIndex;
    this.paginacao.size = event.pageSize;
    this.listar();
  }

}
