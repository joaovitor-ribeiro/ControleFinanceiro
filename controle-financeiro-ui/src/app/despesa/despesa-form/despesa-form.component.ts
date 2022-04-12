import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { finalize } from 'rxjs/operators';
import { Cartao } from 'src/app/cartao/cartao.model';
import { CartaoService } from 'src/app/cartao/cartao.service';
import { Categoria } from 'src/app/categoria/categoria.model';
import { AlertModalService } from 'src/app/shared/alert-modal/alert-modal.service';
import { ErrorModalService } from 'src/app/shared/error-modal/error-modal.service';

import { CategoriaService } from './../../categoria/categoria.service';
import { Despesa } from './../despesa.model';
import { DespesaService } from './../despesa.service';

@Component({
  selector: 'app-despesa-form',
  templateUrl: './despesa-form.component.html',
  styleUrls: ['./despesa-form.component.scss'],
})
export class DespesaFormComponent implements OnInit {

  despesaFormulario!: FormGroup;
  carregando!: boolean;
  cartoes!: Cartao[];
  categorias!: Categoria[];
  id!: number;
  editar!: boolean;
  despesa!: Despesa;

  get propriedade() {
    return this.despesaFormulario.controls;
  }

  constructor(
    private formBuilder: FormBuilder,
    private cartaoService: CartaoService,
    private categoriaService: CategoriaService,
    private despesaService: DespesaService,
    private spinner: NgxSpinnerService,
    private erroService: ErrorModalService,
    private router: Router,
    private route: ActivatedRoute,
    private alertService: AlertModalService,
    private adapter: DateAdapter<any>
  ) { }

  async ngOnInit() {
    this.adapter.setLocale('pt-br');

    this.carregando = true;
    this.spinner.show();

    this.despesaFormulario = this.formBuilder.group({
      cartao: ['', Validators.required],
      descricao: ['', Validators.required],
      categoria: ['', Validators.required],
      valor: ['', Validators.required],
      data: ['', Validators.required],
    });

    this.route.params?.subscribe(value => { //subscribe é usado para receber algo que é retornado por um observable
      if (value?.id) {
        this.id = value.id;
        this.editar = true;
        this.carregando = true;
        this.spinner.show();
        this.despesaService.retornarDespesaId(this.id).pipe(finalize(() => {
          this.spinner.hide();
          this.carregando = false;
          this.colocarFocoCampoCartao();
        })).subscribe( result => {
          this.despesa = result;
          this.preencherFormulario();
        });
      }
    });

    await this.carregarCartao();
    await this.carregarCategoria();

    this.spinner.hide();
    this.carregando = false;

    this.colocarFocoCampoCartao();
  }

  preencherFormulario(){
    this.despesaFormulario.patchValue({ //Passando os valores para o formulário
      cartao: this.despesa.cartao.id,
      descricao: this.despesa.descricao,
      categoria: this.despesa.categoria.id,
      valor: this.despesa.valor,
      data: new Date(this.despesa.data),
    });
  }

  private async carregarCartao() {
    await this.cartaoService.listar().toPromise().then(cartoes => {
      this.cartoes = cartoes;
    });
  }

  private async carregarCategoria() {
    await this.categoriaService.listar().toPromise().then(categorias => {
      this.categorias = categorias;
    });
  }

  limparBotoes(campo: string) {
    this.despesaFormulario.get(campo)?.setValue('');
  }

  enviarFormulario() {
    if (this.despesaFormulario.invalid) {
      this.despesaFormulario.markAllAsTouched();
    }else{
      const despesa = this.returnDespesa();
      if (this.editar) {
        this.despesaService.editar(this.id, despesa).subscribe(() => {
          this.router.navigate(['/despesa/listar'], { queryParamsHandling: 'preserve' });
           this.alertService.showAlertSuccess('Despesa editado com sucesso');
        },
        error => {
          this.erroService.showError(error?.error?.message || 'Falha na conexão');
        });
      } else {
        this.despesaService.inserir(despesa).subscribe(() => {
          this.router.navigate(['/despesa/listar'], { queryParamsHandling: 'preserve' });
           this.alertService.showAlertSuccess('Despesa cadastrada com sucesso');
        },
        error => {
          this.erroService.showError(error?.error?.message || 'Falha na conexão');
        });
      }
    }
  }

  returnDespesa() {
    const despesa = {
      cartao: this.cartoes.filter(cartao => cartao.id === Number(this.despesaFormulario.get('cartao')?.value))[0],
      descricao: this.despesaFormulario.get('descricao')?.value,
      categoria: this.categorias.filter(categoria => categoria.id === Number(this.despesaFormulario.get('categoria')?.value))[0],
      valor: this.despesaFormulario.get('valor')?.value,
      data: this.despesaFormulario.get('data')?.value,
    } as Despesa;

    let limite = despesa.cartao.limite.replace(/\D/g, '');
    despesa.cartao.limite = limite;

    let valor = String(despesa.valor).replace(/\D/g, '');
    despesa.valor = Number(valor);

    return despesa;
  }

  voltar(){
    this.router.navigate(['despesa/listar']);
  }

  colocarFocoCampoCartao() {
    setTimeout(() => {
      document.getElementById('cartao')?.focus();
    }, 300);
  }

}
