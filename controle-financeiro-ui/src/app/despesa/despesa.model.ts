import { Cartao } from "../cartao/cartao.model";
import { Categoria } from "../categoria/categoria.model";

export interface Despesa {
  id: number;
  categoria: Categoria;
  descricao: string;
  cartao: Cartao;
  valor: string;
  data: Date;
}

export interface FiltroDespesa {
  descricao: string;
  categorias: number[];
  dataInicial: string;
  dataFinal: string;
}
export interface Pageable {
  sort: Sort;
  offset: number;
  pageNumber: number;
  unpaged: boolean;
  paged: boolean;
}

export interface Sort {
  empty: boolean;
  sorted: boolean;
  unsorted: boolean;
}

export interface PageDespesa {
  content: Despesa[];
  pageable: Pageable;
  last: boolean;
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
  sort: Sort;
  first: boolean;
  numberOfElements: number;
  empty: boolean;
}

export interface Paginacao {
  page: number;
  size: number;
}
