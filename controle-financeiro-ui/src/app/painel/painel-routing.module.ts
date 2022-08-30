import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from './../guard/auth.guard';
import { PainelComponent } from './painel.component';

const PainelRoute: Routes = [
  {path: '', component: PainelComponent, canActivate: [AuthGuard]},
];

@NgModule({
  imports: [RouterModule.forChild(PainelRoute)],
  exports: [RouterModule]
})
export class PainelRoutingModule { }

