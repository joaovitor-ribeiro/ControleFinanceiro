import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CategoriaFormComponent } from './categoria-form.component';
import {MatInputModule} from '@angular/material/input';
import {MatButtonModule} from '@angular/material/button';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { NgxMaskModule } from 'ngx-mask';
import { AlertModalModule } from 'src/app/shared/alert-modal/alert-modal.module';
import { NgxSpinnerModule } from 'ngx-spinner';



@NgModule({
  declarations: [CategoriaFormComponent],
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
    AlertModalModule,
    NgxSpinnerModule,
    MatSelectModule,

  ]
})
export class CategoriaFormModule { }
