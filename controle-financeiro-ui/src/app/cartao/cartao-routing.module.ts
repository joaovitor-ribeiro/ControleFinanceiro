import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CartaoFormComponent } from './cartao-form/cartao-form.component';
import { CartaoListaComponent } from './cartao-lista/cartao-lista.component';

const cartaoRoutes: Routes = [
  {path: 'listar', component: CartaoListaComponent},
  {path: 'inserir', component: CartaoFormComponent},
  {path: ':id', component: CartaoFormComponent},
];

@NgModule({
  imports: [RouterModule.forChild(cartaoRoutes)],
  exports: [RouterModule]
})
export class CartaoRoutingModule { }
