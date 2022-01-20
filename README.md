Orientações para executar a aplicação:

    - realize o clone do projeto disponível em "https://github.com/Jeickt/bikescanoas" e entre na branch develop;
    - abra o terminal na pasta principal do projeto;
    - execute os comandos "mvn clean package" e "java -jar ./target/bikescanoas-0.0.1-SNAPSHOT.jar".

A URI base disponível é "http://localhost:8080".

A aplicação conta com autenticação via token JWT, que pode ser obtido via POST em "http://localhost:8080/login", a partir de arquivo json com os dados de usuário informados acima.

A documentação de acesso ao servidor web encontra-se em "http://localhost:8080/swagger-ui.html".

Um usuário comum tem acesso a:
	- métodos POST e PUT de "/usuarios" para cadastro;
	- método POST de "/login" para autenticação e obtenção do bearer;
	- métodos GET de "/bicicletas", "/categorias", "/oficinas" e "/terminais" para informação; 
	- método POST de "/cessoes" e GET de "/cessoes/user" para viasualizações das bicicletas cedidas à plataforma;
	- métodos POST e PUT de "/usos" e GET de "/usos/{id}" para gerenciamento de um uso específico de uma bicicleta;
	- método GET de "/manutencoes/{id}" para visualização de uma manutenção específica de bicicleta;
	- método GET de pagamentos{id}" para visualização de um pagamento específico de bicicleta;
As demais operações são próprias do sistema e de usuários administradores.

Para acesso ao link de produção, acesse: "https://bikes-canoas.herokuapp.com/"
