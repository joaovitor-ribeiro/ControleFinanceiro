import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Categoria } from '../categoria.model';

@Injectable({
  providedIn: 'root'
})
export class CategoriaFormService {

  private url = `${environment.url}categoria`

  constructor(
    private http: HttpClient,
  ) { }

  inserir(categoria: Categoria) {
    return this.http.post<void>(this.url + '/inserir', categoria);
  }

}
