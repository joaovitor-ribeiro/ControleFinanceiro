import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { ErrorModalService } from '../shared/error-modal/error-modal.service';
import { environment } from './../../environments/environment';
import { Ganho, FiltroGanho, PageGanho, Paginacao } from './ganho.model';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class GanhoService {

  private url = `${environment.url}ganho/`

  constructor(
    private http: HttpClient,
  ) { }

  inserir(ganho: Ganho){
    return this.http.post<void>(this.url + 'inserir', ganho);
  }

  editar(id: Number, ganho: Ganho){
    return this.http.put<void>(this.url + `editar/${id}`, ganho);
  }

  retornarGanhoId(id: Number){
    return this.http.get<Ganho>(this.url + `${id}`);
  }

  excluir(id: number){
    return this.http.delete<void>(this.url + `excluir/${id}`);
  }

  listar(filtro?: FiltroGanho, paginacao?: Paginacao) {
    let params: any = {
      descricao: filtro?.descricao || '',
      categorias: filtro?.categorias ||  '',
      page: paginacao?.page || '',
      size: paginacao?.size || '',
    };

    if (filtro?.dataInicial) {
      params = {dataInicial: filtro.dataInicial, ...params};
    }

    if (filtro?.dataFinal) {
      params = {dataFinal: filtro.dataFinal, ...params};
    }

    return this.http.get<PageGanho>(this.url + 'listar', {params}).pipe(map(pageGanho => {
      return pageGanho;
    }));
  }
}
