import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UsuarioFormComponent } from './usuario-form/usuario-form.component';

const usuarioRoutes: Routes = [
  {path: 'inserir', component: UsuarioFormComponent},
//{path: 'listar',  component: UsuarioListaComponent},
  {path: ':id',     component: UsuarioFormComponent},
];

@NgModule({
  imports: [RouterModule.forChild(usuarioRoutes)],
  exports: [RouterModule]
})
export class UsuarioRoutingModule { }

