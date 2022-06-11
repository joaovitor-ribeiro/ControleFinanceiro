import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'despesa',
    loadChildren: () => import('./despesa/despesa.module').then(m => m.DespesaModule)
  },

  {
    path: 'cartao',
    loadChildren: () => import('./cartao/cartao.module').then(m => m.CartaoModule)
  },

  {
    path: 'categoria',
    loadChildren: () => import('./categoria/categoria.module').then(m => m.CategoriaModule)
  },

  {
    path: 'ganho',
    loadChildren: () => import('./ganho/ganho.module').then(m => m.GanhoModule)
  },

  {path: '', redirectTo: 'cartao/listar', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
