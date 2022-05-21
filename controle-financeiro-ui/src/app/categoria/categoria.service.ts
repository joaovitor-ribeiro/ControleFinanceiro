import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

import { Categoria, FiltroCategoria } from './categoria.model';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  private url = `${environment.url}categoria/`

  constructor(
    private http: HttpClient,
  ) { }

  inserir(categoria: Categoria) {
    return this.http.post<void>(this.url + 'inserir', categoria);
  }

  listar(filtro?: FiltroCategoria) {
    let params: any = {
      nome: filtro?.nome || '',
      tipo: filtro?.tipo ||  '',
    };

    return this.http.get<Categoria[]>(this.url + 'listar', {params});
  }

  editar(id: number, categoria: Categoria) {
    return this.http.put<void>(this.url + `editar/${id}`, categoria);
  }

  excluir(id: number) {
    return this.http.delete<Categoria[]>(this.url + `excluir/${id}`);
  }

  retornarCategoriaId(id: number) {
    return this.http.get<Categoria>(this.url + id);
  }
}
