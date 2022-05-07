# language: pt

Funcionalidade: Apenas usuarios cadastrados podem se logar
  
  Cenario: Cadastro realizado com sucesso
    Dado O preenchimento dos dados corretamente
    Quando acionar o botao salvar 
    Entao um alerta sera exibido
    E o usuario sera redirecionado para a grid 