# language: pt

@cadastro-categoria
Funcionalidade: Cadastro de categoria
    Como um usuário do sistema controle financeiro
		Eu desejo cadastrar minhas categorias
		Para identificar meus ganhos e despesas.
    
	Contexto: Acessar cadastro de categoria
		Dado que estou logado na aplicação com o email "usuario@gmail.com" e a senha "123456" para validar a categoria
		E que estou na página de cadastro de categoria
	
	@categoria-cadastro-sucesso
	Esquema do Cenario: Cadastro da categoria "<nome>" realizado com sucesso
    Quando preencho os campos nome "<nome>" tipo "<tipo>" 
    E aciono o botão salvar do cadastro de categoria
    Entao será exibido a mensagem "Categoria cadastrada com sucesso"
    E o usuário será redirecionado para a grid da categoria
  
	Exemplos:
	
	| nome                 | tipo | 
	| Salário              | G    | 
	| Cartão de crédito    | D    | 
	
	@categoria-cadastro-validacoes-mensagens
	Esquema do Cenario: Validação da mensagem "<mensagem>"
    Quando preencho os campos nome "<nome>" tipo "<tipo>"
    E aciono o botão salvar do cadastro de categoria
    Entao será exibida a mensagem "<mensagem>" de erro no cadastro de categoria
    
   Exemplos:
   
   | nome     | tipo | mensagem 		  															 |
   | 				  | 	G	 | Insira o nome. 															 |
   |	Sa			|		G	 | O nome não pode ter menos do que 3 caracteres.|
   | Salário	|			 | Escolha o tipo. 															 |
    
    
    
    
	
	