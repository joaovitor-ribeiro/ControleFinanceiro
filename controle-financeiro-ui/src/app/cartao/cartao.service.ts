import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cartao } from './cartao.model';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CartaoService {

  private url = `${environment.url}cartao`

  constructor(
    private http: HttpClient
  ) { }

  inserir(cartao: Cartao) {
    return this.http.post<void>(this.url + '/inserir', cartao);
  }

  listar() {
    return this.http.get<Cartao[]>(this.url + '/listar').pipe(map((result: Cartao[]) => {
      result.forEach(cartao => {
        cartao.limite = Number(cartao.limite).toLocaleString('pt-br', {style: 'currency', currency: 'BRL'});
        cartao.numero = cartao.numero.replace(/(\d{4})?(\d{4})?(\d{4})?(\d{4})/, '$1 $2 $3 $4');
      });
      return result;
    }));
  }

  retornarCartaoId(id: number) {
    return this.http.get<Cartao>(this.url + `/${id}`);
  }

  editar(id: number, cartao: Cartao) {
    return this.http.put<void>(this.url + `/editar/${id}`, cartao);
  }

  excluir(id: number) {
    return this.http.delete<void>(this.url + `/excluir/${id}`);
  }
}
