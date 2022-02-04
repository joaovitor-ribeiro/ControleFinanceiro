import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatInputModule} from '@angular/material/input';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import { CartaoFormComponent } from './cartao-form.component';
import {MatDividerModule} from '@angular/material/divider';
import { NgxMaskModule } from 'ngx-mask';


@NgModule({
  declarations: [
    CartaoFormComponent
  ],
  imports: [
    CommonModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    MatIconModule,
    MatButtonModule,
    MatCardModule,
    MatDividerModule,
    NgxMaskModule,
    NgxMaskModule.forRoot(),
  ]
})
export class CartaoFormModule { }
