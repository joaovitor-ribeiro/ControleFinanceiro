import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AlertModalComponent } from './alert-modal.component';
import { BsModalService } from 'ngx-bootstrap/modal';

@NgModule({
  declarations: [AlertModalComponent],
  imports: [
    CommonModule
  ],
  exports: [AlertModalComponent],
  providers: [BsModalService]
})
export class AlertModalModule { }
