import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { NgxSpinnerModule } from 'ngx-spinner';

import { CategoriaListaComponent } from './categoria-lista.component';

@NgModule({
  declarations: [CategoriaListaComponent],
  imports: [
    CommonModule,
    MatTableModule,
    MatDividerModule,
    MatGridListModule,
    MatButtonModule,
    MatIconModule,
    NgxSpinnerModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    MatSelectModule,
  ]
})
export class CategoriaListaModule { }
