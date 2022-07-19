import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from '../guard/auth.guard';
import { DespesaFormComponent } from './despesa-form/despesa-form.component';
import { DespesaListaComponent } from './despesa-lista/despesa-lista.component';

const despesasRoutes: Routes = [
  {path: 'inserir', component: DespesaFormComponent,  canActivate: [AuthGuard]},
  {path: 'listar',  component: DespesaListaComponent, canActivate: [AuthGuard]},
  {path: ':id',     component: DespesaFormComponent,  canActivate: [AuthGuard]},
];

@NgModule({
  imports: [RouterModule.forChild(despesasRoutes)],
  exports: [RouterModule]
})
export class DespesaRoutingModule { }
