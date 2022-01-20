Orientações para executar a aplicação:

    - realize o clone do projeto disponível em "https://github.com/Jeickt/bikescanoas" e entre na branch develop;
    - abra o terminal na pasta principal do projeto;
    - execute os comandos "mvn clean package" e "java -jar ./target/bikescanoas-0.0.1-SNAPSHOT.jar".

O banco de dados possui dois usuários cadastrados com as seguintes identificações:

    email: jorge@gmail.com, senha: senha;
    email: ana@gmail.com, senha: outrasenha.

A URI base disponível é "http://localhost:8080".

A aplicação conta com autenticação via token JWT, que pode ser obtido via POST em "http://localhost:8080/login", a partir de arquivo json com os dados de usuário informados acima.

A documentação de acesso ao servidor web encontra-se em "http://localhost:8080/swagger-ui.html".

*****

Para acesso ao link de produção, acesse: 
