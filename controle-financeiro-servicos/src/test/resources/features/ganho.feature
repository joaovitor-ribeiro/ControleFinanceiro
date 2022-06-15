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
    Quando preencho os campos descricao "Prêmio bingo" categorias "Bingo" valor "1500.00" data "06/14/2022" 
    E aciono o botão salvar do cadastro de ganho
    Entao o usuário será redirecionado para a grid de ganho 
 
	