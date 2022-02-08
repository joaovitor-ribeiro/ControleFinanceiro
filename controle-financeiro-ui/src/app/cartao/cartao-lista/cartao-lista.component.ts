import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cartao } from '../cartao.model';
import { CartaoService } from '../cartao.service';
import { ConfirmModalService } from 'src/app/shared/confirm-modal/confirm-modal.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { finalize } from 'rxjs/operators';
import { AlertModalService } from 'src/app/shared/alert-modal/alert-modal.service';

@Component({
  selector: 'app-cartao-lista',
  templateUrl: './cartao-lista.component.html',
  styleUrls: ['./cartao-lista.component.scss']
})
export class CartaoListaComponent implements OnInit {

  cartao!: Cartao[];
  displayedColumns: string[] = ['nome', 'bandeira', 'numero', 'limite', 'acoes'];
  dataSource!: Cartao[];
  carregando = false;

  constructor(
    private cartaoService: CartaoService,
    private router: Router,
    private dialogService: ConfirmModalService,
    private spinner: NgxSpinnerService,
    private alertService: AlertModalService
  ) { }

  ngOnInit(): void {
    this.listar();
  }

  inserir(){
    this.router.navigate([`cartao/inserir`]);
  }

  editar(id: number){
    this.router.navigate([`cartao/${id}`]);
  }

  listar(){
    this.carregando = true;
    this.spinner.show();
    this.cartaoService.listar().pipe(finalize(() => {
      this.spinner.hide();
      this.carregando = false;
    })).subscribe(result => {
      this.cartao = result;
      this.dataSource = result;
    });
  }

  excluir(id: number){
    this.dialogService.showConfirm('Deseja realmente excluir esse cartão?').subscribe(result => {
      if (result) {
        this.cartaoService.excluir(id).subscribe(() => {
          this.listar();
          this.alertService.showAlertSuccess('Cartão excluído com sucesso!');
        });
      }
    })
  }

}
