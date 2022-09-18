import { AfterContentChecked, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { finalize } from 'rxjs/operators';
import { AlertModalService } from 'src/app/shared/alert-modal/alert-modal.service';

import { Categoria, FiltroCategoria } from './../../categoria/categoria.model';
import { CategoriaService } from './../../categoria/categoria.service';
import { Ganho } from './../ganho.model';
import { GanhoService } from './../ganho.service';

@Component({
  selector: 'app-ganho-form',
  templateUrl: './ganho-form.component.html',
  styleUrls: ['./ganho-form.component.scss']
})
export class GanhoFormComponent implements OnInit, AfterContentChecked {

  ganhoFormulario!: FormGroup; // ! A variável não foi inicializada e será usada dentro do componente
  editar = false;
  //carregando?: boolean = false; // ? Posso ou não usar a variável
  carregando = false;
  id!: number;
  ganho!: Ganho;
  categorias!: Categoria[];

  get propriedade() {
    return this.ganhoFormulario.controls;
  }

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private  ganhoService: GanhoService,
    private categoriaService: CategoriaService,
    private router: Router,
    private alertService: AlertModalService,
    private spinner: NgxSpinnerService,
    private adapter: DateAdapter<any>,
    private cdr: ChangeDetectorRef,
  ) { }

  ngAfterContentChecked(): void {
    this.cdr.detectChanges();
  }

  async ngOnInit() {
    this.adapter.setLocale('pt-br');

    this.carregando = true;
    this.spinner.show();

    this.ganhoFormulario = this.formBuilder.group({
      descricao: ['', [Validators.required, Validators.minLength(3)]],
      categoria: ['', [Validators.required]],
      valor: ['', [Validators.required], [this.validarValor.bind(this)]],
      data: ['', Validators.required]
    });

    this.route.params?.subscribe(value => {
      if (value?.id){
        this.id = value.id;
        this.editar = true;
        this.carregando = true;
        this.spinner.show();
        this.ganhoService.retornarGanhoId(this.id).pipe(finalize(() => {
          this.spinner.hide();
          this.carregando = false;
        })).subscribe( result => {
          this.ganho = result;
          this.preencherFormulario();
        });
      }
    });

    await this.carregarCategoria();

    this.spinner.hide();
    this.carregando = false;

  }

  preencherFormulario(){
    this.ganhoFormulario.patchValue({
      descricao: this.ganho.descricao,
      categoria: this.ganho.categoria.id,
      valor: Number(this.ganho.valor).toLocaleString('pt-BR', {minimumFractionDigits: 2}),
      data: new Date(this.ganho.data),
    });
  }

  private async carregarCategoria() {
    await this.categoriaService.listar({nome: '', tipo: 'G'} as FiltroCategoria).toPromise().then(categorias => {
      this.categorias = categorias;
    }).catch (() => {
      this.spinner.hide();
      this.carregando = false;
    });
  }

  limparBotoes(campo: string) {
    this.ganhoFormulario.get(campo)?.setValue('');
  }

  enviarFormulario() {
    if (this.ganhoFormulario.invalid) {
      this.ganhoFormulario.markAllAsTouched();
    } else {
      const ganho = this.returnGanho();

      if (this.editar){
        this.ganhoService.editar(this.id, ganho).subscribe(() => {
          this.router.navigate(['/ganho/listar'], { queryParamsHandling: 'preserve'});
          this.alertService.showAlertSuccess('Ganho editado com sucesso');
        })
      }else{
        this.ganhoService.inserir(ganho).subscribe(() => {
          this.router.navigate(['/ganho/listar'], { queryParamsHandling: 'preserve' });
          this.alertService.showAlertSuccess('Ganho cadastrado com sucesso');
        });
      }
    }
  }

  returnGanho(){
    const ganho = {
      descricao: this.ganhoFormulario.get('descricao')?.value,
      valor: this.ganhoFormulario.get('valor')?.value,
      data: this.ganhoFormulario.get('data')?.value,
      categoria: this.categorias.filter(categoria => categoria.id === Number(this.ganhoFormulario.get('categoria')?.value))[0],
    } as Ganho;

    // let valor = String(ganho.valor).replace(/\D/g, '');
    ganho.valor =  this.formataValor(ganho.valor);

    return ganho;
  }

  voltar() {
    this.router.navigate(['/ganho/listar']); //this.router.navigate(['/ganho/listar'], { queryParamsHandling: 'preserve'});
  }

  async validarValor(formControl: FormControl) {
    return formControl.value?.replace(',', '.') <= 0 ? { valorInvalido: true } : null;
  }

  formataValor(valor: string) {
    if (String(valor).includes('.') || String(valor).includes(',')) {
      valor = valor.replace('/[^0-9]/g', '');
      valor = valor.replace('.', '')
      valor = Number(valor.replace(',', '.')) + "";
    }
    return valor;
  }

}
