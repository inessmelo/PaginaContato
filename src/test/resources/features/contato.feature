#language:pt
@contactUs
Funcionalidade: Preenchimento do contato
Como um usuario
Eu quero preencher o formulario de contato
Para abrir um chamado

	@msgErroEmail
	Cenario: enviar contato sem sucesso
		Dado que os campos obrigatorios nao tenham sido preenchido	
		Quando clicar no botao Send
		Entao aparece a mensagem de erro solicitando email
		
	@msgErroMessage
	Cenario: enviar contato sem sucesso
		Dado que no campo email foi preenchido com "fulano@teste.com"
		Quando clicar no botao Send
		Entao aparece a mensagem de erro solicitando mensagem
			
	@msgErroAssunto
	Cenario: enviar contato sem sucesso
		Dado que no campo email foi preenchido com "teste@teste.com" 
		E o campo mensagem foi preenchido com "Testando 123"
		Quando clicar no botao Send
		Entao aparece a mensagem de erro solicitando assunto
		
	@envioSucesso
	Cenario: enviar contato sem sucesso
		Dado que os campos obrigatorios foram preenchidos
		Quando clicar no botao Send
		Entao aparece a mensagem de sucesso