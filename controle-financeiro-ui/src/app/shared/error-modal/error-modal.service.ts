import { Injectable } from '@angular/core';
import { BsModalRef, BsModalService } from 'ngx-bootstrap/modal';
import { ErrorModalComponent } from './error-modal.component';

@Injectable({
  providedIn: 'root'
})
export class ErrorModalService {

  constructor(private modalService: BsModalService) {}

  showError(msg: string) {
    const bsModalRef: BsModalRef = this.modalService.show(ErrorModalComponent);
    bsModalRef.content.msg = msg;

    return (<ErrorModalComponent>bsModalRef.content).errorResult;
  }
}
