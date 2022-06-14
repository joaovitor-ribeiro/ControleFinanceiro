import { UsuarioFormComponent } from './usuario-form.component';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { NgxSpinnerModule } from 'ngx-spinner';

import { AlertModalModule } from './../../shared/alert-modal/alert-modal.module';

@NgModule({
  declarations: [UsuarioFormComponent],
  imports: [
    CommonModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    MatIconModule,
    MatButtonModule,
    MatCardModule,
    MatDividerModule,
    AlertModalModule,
    NgxSpinnerModule,
    MatSelectModule,
  ]
})
export class UsuarioFormModule { }
