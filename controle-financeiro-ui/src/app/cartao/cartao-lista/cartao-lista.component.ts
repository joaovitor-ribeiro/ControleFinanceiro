import { Component, OnInit } from '@angular/core';
import { Cartao } from '../cartao.model';
import { CartaoService } from '../cartao.service';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cartao-lista',
  templateUrl: './cartao-lista.component.html',
  styleUrls: ['./cartao-lista.component.scss']
})
export class CartaoListaComponent implements OnInit {

  cartao!: Cartao[];
  displayedColumns: string[] = ['nome', 'bandeira', 'numero', 'limite', 'acoes'];
  dataSource!: Cartao[];

  constructor(
    private cartaoService: CartaoService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.cartaoService.listar()
    .subscribe(result => {
      result.forEach(cartao => {
        cartao.limite = Number(cartao.limite).toLocaleString('pt-br', {style: 'currency', currency: 'BRL'});
        cartao.numero = cartao.numero.replace(/(\d{4})?(\d{4})?(\d{4})?(\d{4})/, '$1 $2 $3 $4');
      });
      this.cartao = result;
      this.dataSource = result;
    });
  }

  inserir(){
    this.router.navigate([`cartao`]);
  }

  editar(id: number){
    this.router.navigate([`cartao/${id}`]);
  }

}
