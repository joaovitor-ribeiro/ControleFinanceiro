# language: pt

@cadastro-despesas
Funcionalidade: Cadastro de despesas
    Como um usuário do sistema controle financeiro
		Eu desejo cadastrar minhas despesas
		Para meu controle financeiro
    
	Contexto: Acessar cadastro de despesa
		Dado que estou logado na aplicação com o email "usuario@gmail.com" e a senha "123456" para validar a despesa 
		E que estou na página de cadastro de despesa
	
	@despesa-cadastro-sucesso
	Cenario: Cadastro de despesa realizado com sucesso
   	Quando preencho os campos cartao "5173 8634 0599 6183" descricao "Conta de Luz" categoria "Faculdade" valor "1500.00" data "06/14/2022" 
    E aciono o botão salvar do cadastro de despesa
    Entao o usuário será redirecionado para a grid de despesa 
    
  @despesa-cadastro-validacoes-mensagens
  Esquema do Cenario: Validação da mensagem "<mensagem>"
    Quando preencho os campos cartao "<cartao>" descricao "<descricao>" categoria "<categoria>" valor "<valor>" data "<data>"
    E aciono o botão salvar do cadastro de despesa
    Entao será exibida a mensagem "<mensagem>" de erro no cadastro de despesa

   Exemplos:

   | cartao              | descricao            | categoria | valor     | data          | mensagem                                            |
   |                     | Prestação faculdade  | Faculdade | 845,00    | 06/14/2022    | Escolha o cartão.                                   |
   | 5173 8634 0599 6183 |                      | Faculdade | 845,00    | 06/14/2022    | Insira uma descrição.                               |
   | 5173 8634 0599 6183 | Pr                   | Faculdade | 845,00    | 06/14/2022    | A descrição não pode ter menos do que 3 caracteres. |
   | 5173 8634 0599 6183 | Prestação faculdade  |           | 845,00    | 06/14/2022    | Escolha a categoria.                                |
   | 5173 8634 0599 6183 | Prestação faculdade  | Faculdade |           | 06/14/2022    | Insira o valor.                                     |
   | 5173 8634 0599 6183 | Prestação faculdade  | Faculdade | 0,00      | 06/14/2022    | O valor tem que ser maior que 0.                    |
 
	