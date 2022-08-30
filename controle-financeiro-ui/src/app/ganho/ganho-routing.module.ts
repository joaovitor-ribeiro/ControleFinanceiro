import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from '../guard/auth.guard';
import { GanhoFormComponent } from './ganho-form/ganho-form.component';
import { GanhoListaComponent } from './ganho-lista/ganho-lista.component';

const ganhoRoutes: Routes = [
  {path: 'inserir', component: GanhoFormComponent,  canActivate: [AuthGuard]},
  {path: 'listar',  component: GanhoListaComponent, canActivate: [AuthGuard]},
  {path: ':id',     component: GanhoFormComponent,  canActivate: [AuthGuard]},
];

@NgModule({
  imports: [ RouterModule.forChild(ganhoRoutes)],
  exports: [RouterModule]
})
export class GanhoRoutingModule { }
