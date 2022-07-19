import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from '../guard/auth.guard';
import { CartaoFormComponent } from './cartao-form/cartao-form.component';
import { CartaoListaComponent } from './cartao-lista/cartao-lista.component';

const cartaoRoutes: Routes = [
  {path: 'listar',  component: CartaoListaComponent, canActivate: [AuthGuard]},
  {path: 'inserir', component: CartaoFormComponent, canActivate: [AuthGuard]},
  {path: ':id',     component: CartaoFormComponent, canActivate: [AuthGuard]},
];

@NgModule({
  imports: [RouterModule.forChild(cartaoRoutes)],
  exports: [RouterModule]
})
export class CartaoRoutingModule { }
