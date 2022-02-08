import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CartaoFormModule } from './cartao-form/cartao-form.module';
import { CartaoListaModule } from './cartao-lista/cartao-lista.module';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    CartaoFormModule,
    CartaoListaModule
  ]
})
export class CartaoModule { }
