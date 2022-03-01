export interface Cartao {
  id: number;
  nome: string;
  bandeira: string;
  numero: string;
  limite: string;
}

export interface FiltroCartao {
  nome: string;
  bandeiras: string[];
}

export enum Bandeira {
  visa = 'Visa',
  mastercard = 'Mastercard',
  americanExpress = 'American Express',
  jcb = 'JCB',
  disnerClub = 'Diners Club',
  aura = 'Aura',
  hipercard = 'Hipercard',
}
