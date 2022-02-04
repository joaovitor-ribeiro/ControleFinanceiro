import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CartaoFormComponent } from './cartao/cartao-form/cartao-form.component';
import { CartaoListaComponent } from './cartao/cartao-lista/cartao-lista.component';

const routes: Routes = [
  {path: 'cartao', component: CartaoFormComponent},

  {path: 'cartao/:id', component: CartaoFormComponent},

  {path: '', component: CartaoListaComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
