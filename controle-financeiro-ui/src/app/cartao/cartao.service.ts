import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Cartao } from './cartao.model';
import { Observable } from 'rxjs';

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
    return this.http.get<Cartao[]>(this.url + '/listar');
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
