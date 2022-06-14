import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsuarioFormComponent } from './usuario/usuario-form/usuario-form.component';

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

  {path: 'usuario/inserir', component: UsuarioFormComponent},

  {path: 'usuario/:id', component: UsuarioFormComponent},

  {path: '', redirectTo: 'usuario/inserir', pathMatch: 'full'},

  {path: '**', redirectTo: 'cartao/listar', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
