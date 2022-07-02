# language: pt

@cadastro-ganhos
Funcionalidade: Cadastro de ganhos
    Como um usuário do sistema controle financeiro
		Eu desejo cadastrar meus ganhos
		Para meu controle financeiro
    
	Contexto: Acessar cadastro de ganho
		Dado que estou na página de cadastro de ganho
	
	@ganho-cadastro-sucesso
	Cenario: Cadastro de ganho realizado com sucesso
    Quando preencho os campos descricao "Prêmio bingo" categoria "Bingo" valor "1.500" data "06/14/2022" 
    E aciono o botão salvar do cadastro de ganho
    Entao o usuário será redirecionado para a grid de ganho 
    
   @ganho-cadastro-validacoes-mensagens
	 Esquema do Cenario: Validação da mensagem "<mensagem>"
    Quando preencho os campos descricao "<descricao>" categoria "<categoria>" valor "<valor>" data "<data>"
    E aciono o botão salvar do cadastro de ganho
    Entao será exibida a mensagem "<mensagem>" de erro no cadastro de ganho
    
   Exemplos:
   
   | descricao     		| categoria | valor 	| data 	     | mensagem 																					 |
   | 				  		 		| Salario   | 1850,69 |	06/14/2022 | Insira a descrição. 																 |
   | Ap							  | Salario	 	| 1850,69 |	06/14/2022 | A descrição não pode ter menos do que 3 caracteres. |
   | App motivacional | 				 	| 1850,69	|	06/14/2022 | Escolha a categoria. 															 |
   | App motivacional | Salario	 	| 				|	06/14/2022 | Insira o valor.     																 |
   | App motivacional | Salario	 	| 0,00		|	06/14/2022 | O valor tem que ser maior que 0. 									 |
   
    
 
	