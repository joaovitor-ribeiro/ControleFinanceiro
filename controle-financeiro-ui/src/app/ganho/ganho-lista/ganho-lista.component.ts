import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { PageEvent } from '@angular/material/paginator';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { finalize } from 'rxjs/operators';
import { Categoria } from 'src/app/categoria/categoria.model';
import { CategoriaService } from 'src/app/categoria/categoria.service';
import { AlertModalService } from 'src/app/shared/alert-modal/alert-modal.service';
import { ConfirmModalService } from 'src/app/shared/confirm-modal/confirm-modal.service';
import { ErrorModalService } from 'src/app/shared/error-modal/error-modal.service';

import { Ganho, PageGanho, Paginacao } from '../ganho.model';
import { GanhoService } from '../ganho.service';

@Component({
  selector: 'app-ganho-lista',
  templateUrl: './ganho-lista.component.html',
  styleUrls: ['./ganho-lista.component.scss']
})
export class GanhoListaComponent implements OnInit {

  filtroFormulario!: FormGroup;
  carregando = true;
  displayedColumns: string[] = ['descricao', 'categoria', 'valor', 'data', 'acoes'];
  ganhos!: Ganho[];
  categorias!: Categoria[];
  pageGanho!: PageGanho;
  paginacao: Paginacao = {page: 0 , size: 5};

  get propriedade() {
    return this.filtroFormulario.controls;
  }

  constructor(
    private router: Router,
    private ganhoService: GanhoService,
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

  inserir(){
    this.router.navigate(['ganho/inserir']);
  }

  editar(id: number){
    this.router.navigate([`/ganho/${id}`])
  }

  private async carregarCategoria() {
    await this.categoriaService.listar().toPromise().then(categorias => {
      this.categorias = categorias;
    },
    () => {});
  }

  limparBotoes(valor: string) {
    this.filtroFormulario.get(valor)?.setValue(undefined);
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

    this.ganhoService.listar(filtro, this.paginacao).pipe(finalize(() => {
      this.carregando = false;
      this.spinner.hide();
      this.anexarConsulta();
    })).subscribe(ganhos => {
      this.pageGanho = ganhos;
      this.paginacao.page = ganhos?.number;
      this.paginacao.size = ganhos?.size;
      this.ganhos = ganhos.content;
    },
    error => {
      this.erroService.showError(error?.error?.message || 'Falha na conexão');
    });
  }

  excluir(id: number){
    this.dialogService.showConfirm('Deseja realmente excluir esse Ganho?').subscribe(result => {
      if (result) {
        this.ganhoService.excluir(id).subscribe(() => {
          this.listar(true);
          this.alertService.showAlertSuccess('Ganho excluído com sucesso!');
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
