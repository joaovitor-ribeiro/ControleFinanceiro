import { CategoriaFormService } from './categoria-form.service';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';

interface Tipo {
  label: string;
  value: string;
}

@Component({
  selector: 'app-categoria-form',
  templateUrl: './categoria-form.component.html',
  styleUrls: ['./categoria-form.component.scss']
})
export class CategoriaFormComponent implements OnInit {
  categoriaFormulario!: FormGroup;
  carregando!:boolean;
  get propriedade() {
    return this.categoriaFormulario.controls;
  }
  tipos: Tipo[] = [
    {
      label: 'Ganho',
      value: 'G'
    },
    {
      label: 'Despesa',
      value: 'D'
    }];

  constructor(private formBuilder: FormBuilder, private service:CategoriaFormService) { }

  ngOnInit(): void {
    this.categoriaFormulario = this.formBuilder.group({
      nome:[''],
      tipo:[''],

    })
  }
    enviarFormulario(){
    const categoria = this.categoriaFormulario.getRawValue();
    this.service.inserir(categoria).subscribe();
    }
    limparBotoes(valor:string){
    }
    voltar(){
    }
}
