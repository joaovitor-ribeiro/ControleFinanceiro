# language: pt

@cadastro-cartao
Funcionalidade: Cadastro de cartão
    Como um usuário do sistema controle financeiro
		Eu desejo cadastrar meus cartões
		Para utilizá-lo no pagamento das minhas despesas.
    
	Contexto: Acessar cadastro de cartão 
		Dado que estou logado na aplicação com o email "usuario@gmail.com" e a senha "123456" para validar o cartao
		E que estou na página de cadastro de cartão
	
	@cartao-cadastro-sucesso
	Esquema do Cenario: Cadastro do cartão "<nome>" realizado com sucesso
    Quando preencho os campos nome "<nome>" bandeira "<bandeira>" numero "<numero>" limite "<limite>"
    E aciono o botão salvar do cadastro de cartão 
    Entao será exibido o alerta "Cartão cadastrado com sucesso"
    E o usuário será redirecionado para a grid do cartão
  
	Exemplos:

	| nome      | bandeira         | numero           | limite |
	| Bradesco  | Visa             | 4539063642617856 | 1200   |
	| Nubank    | Mastercard       | 5481132071163156 | 1300   |
	| Centurion | American Express | 347099436377810  | 1500   |
	| Caixa     | JCB              | 3596788135012213 | 1700   |
	| ELO       | Diners Club      | 30289539153372   | 1900   |
	| Santander | Hipercard        | 6062824301637513 | 2200   |
	
	@cartao-cadastro-validacoes-mensagens
	Esquema do Cenario: Validação da mensagem "<mensagem>"
    Quando preencho os campos nome "<nome>" bandeira "<bandeira>" numero "<numero>" limite "<limite>"
    E aciono o botão salvar do cadastro de cartão 
    Entao será exibido a mensagem "<mensagem>" de erro
  
	Exemplos:

	|nome       | bandeira         | numero           | limite | mensagem       															  | 
	|           | Visa             | 4539063642617856 | 1200   | Insira o nome.                   						  | 
	| Itaú      |                  | 4539063642617856 | 1200   | Escolha a bandeira.                            | 
	| Itaú      | Visa             |                  | 1200   | Insira o número.                               | 
	| Itaú      | Visa             | 4539063642617856 |        | Insira o limite.                               | 
	| It        | Visa             | 4539063642617856 | 1200   | O nome não pode ter menos do que 3 caracteres. | 
	| Itaú      | Visa             | 45390636426      | 1200   | O número deve conter entre 13 e 16 dígitos.    | 
	| Itaú      | Visa             | 4539063111111111 | 1200   | Número de cartão inválido.                     | 
	| Itaú      | Visa             | 3596788135012213 | 1200   | O número do cartão não corresponde a bandeira. | 
	| Itaú      | Visa             | 4539063642617856 | 0      | O limite tem que ser maior que 0.              | 
