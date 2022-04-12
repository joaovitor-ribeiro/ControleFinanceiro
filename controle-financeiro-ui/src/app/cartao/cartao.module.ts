import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CartaoFormModule } from './cartao-form/cartao-form.module';
import { CartaoListaModule } from './cartao-lista/cartao-lista.module';
import { CartaoRoutingModule } from './cartao-routing.module';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    CartaoFormModule,
    CartaoListaModule,
    CartaoRoutingModule
  ]
})
export class CartaoModule { }
