# language: pt

@cadastro-categoria
Funcionalidade: Cadastro de categoria
    Como um usuário do sistema controle financeiro
		Eu desejo cadastrar minhas categorias
		Para identificar meus ganhos e despesas.
    
	Contexto: Acessar cadastro de categoria
		Dado que estou na página de cadastro de categoria
	
	@categoria-cadastra-sucesso
	Esquema do Cenario: Cadastro da categoria "<nome>" realizado com sucesso
    Quando O preencho os campos nome "<nome>" tipo "<tipo>" 
    E aciono o botão salvar do cadastro de categoria
    Entao será exibido a mensagem "Categoria cadastrada com sucesso"
    E o usuário será redirecionado para a grid da categoria
  
	Exemplos:
	
	| nome                 | tipo | 
	| Salário              | G    | 
	| Cartão de crédito    | D    | 
	
	