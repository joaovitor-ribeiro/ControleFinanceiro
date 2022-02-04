import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { CartaoListaComponent } from './cartao-lista.component';
import { MatDividerModule } from '@angular/material/divider';
import { MatGridListModule } from '@angular/material/grid-list';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';

@NgModule({
  declarations: [
    CartaoListaComponent
  ],
  imports: [
    CommonModule,
    MatTableModule,
    MatDividerModule,
    MatGridListModule,
    MatButtonModule,
    MatIconModule
  ]
})
export class CartaoListaModule { }
