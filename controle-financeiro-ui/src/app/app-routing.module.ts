import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CartaoFormComponent } from './cartao/cartao-form/cartao-form.component';
import { CartaoListaComponent } from './cartao/cartao-lista/cartao-lista.component';
import { CategoriaFormComponent } from './categoria/categoria-form/categoria-form.component';

const routes: Routes = [
  {
    path: 'despesa',
    loadChildren: () => import('./despesa/despesa.module').then(m => m.DespesaModule)
  },

  {
    path: 'cartao',
    loadChildren: () => import('./cartao/cartao.module').then(m => m.CartaoModule)
  },

  {path: 'categoria/inserir', component: CategoriaFormComponent},

  {path: '', redirectTo: 'cartao/listar', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
