export interface loginForm {
  email: string;
  senha: string;
}

export interface Token {
  token: string;
  tipo: string;
  id: number;
  nome: string;
  foto: File;
}
