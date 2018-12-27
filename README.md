# Aplicativo do Camarão (Protótipo)

Aplicativo do camarão foi um desafio da disciplina de Tópicos de Programação orientado pelo professor [Thiago Souza](https://www.facebook.com/thiago.silvadesouza.33) na Instituição [Unigranrio](http://www.unigranrio.com.br). Essa aplicação tem a responsabilidade de ajudar os funcionários a gerenciar os tanques de camarão, os camarões são frágeis e a água precisa estar devidamente tratada para o crescimento saudável dos mesmos, sendo assim, algumas propriedades da água precisam estar em constante monitoramento, as  principais propriedades são: Amônia Total, Nitrato, Nitrito, Oxigênio Dissolvido, PH, Salinidade, Temperatura e Transparência. Para isso, foi necessário desenvolver um projeto Com  [Arduino](https://www.google.com/search?q=arduino&oq=Arduin&aqs=chrome.0.0j69i60l3j69i57j0.2365j0j7&sourceid=chrome&ie=UTF-8) que de alguma forma pudesse comunicar com os dispositivos móveis dos funcionários. Tal arquitetura foi pensada para essa solução: 

![arranjo](https://i.imgur.com/Oah6LgX.jpg)

# Principais tecnologias utilizadas
  - Ionic
  - NPM
  - Node
  - API REST JAVA
  - Arduino
  - Sensor de temperatura
  - SGBD MySQL

# Funcionalidades
  - Listagem em tempo real de todas as propriedades citadas
  - Gráficos ilustrativos  em tempo real de todas as propriedades citadas
  - Gerenciamento de tanques (CRUD)
  - Localização de Tanques (Aplicação usa o GPS para a localização de todos os tanques cadastrados)
  - Gerenciamento de Funcionários habilitado apenas para gerentes (CRUD)
  - Gerenciamento de Avisos ou Mensagens, uma espécie de quadro de avisos (CRUD).
  - Disparo de E-mails e Notificação Via Push Notification do Firebase para todos os funcionário ao sistema checar que um propriedade passou do seu range limite
  - Disparo de E-mails e Notificação Via Push Notification do Firebase ao envio de avisos
  - Tela de Ajuda com tradução para mais dois idiomas, além do português
  - Possibilidade de envio de fotos do usuário, através da camera e galeria
  - Aplicativo dinâmico, algumas funcionalidades são habilitadas apenas para funcionário Administradores

### Link dos Projetos
- [API REST JAVA](https://github.com/kelvi-ribeiro/api-camarao)
- [Projeto Ionic](https://github.com/kelvi-ribeiro/app-camarao)
- [Projeto Arduino](https://github.com/kelvi-ribeiro/prj-arduino-camarao)

### Instalação

É necessário ter instalado na máquina, Ionic 3, NPM, Node, Java 1.8 e SGBD MySQL.

##### API E BANCO

- Crie um banco chamado bd_camarao
- Clone o [Projeto](https://github.com/kelvi-ribeiro/api-camarao)
- Vá em api-camarao/src/main/resources/application-dev.properties e troque as credenciais do SGBD e do seu E-mail(Opcional).
- Agora é só ligar a API executando o seguinte arquivo api-camarao/src/main/java/com/macuxi/camarao/MacuxiCamaraoApplication.java

##### Aplicativo Ionic

- Clone o [Projeto](https://github.com/kelvi-ribeiro/app-camarao)
- Com o terminal, vá ao diretório, use o comando "cd" para isso, ao chegar no diretório do app-camarao, execute o comando "npm install" para instalar todas as dependências do projeto
- Após a finalização do comando "npm install", execute o comando "ionic serve" para subir a aplicação 
- Agora é só logar na aplicação, as credenciais são: ("kelvi.ribeiro@macuxi.com" para o email) e ("123" para a senha)
- É possível possível ligar a perspectiva de dispositível móvel, Se estiver no Google Chrome, é só apertar a tecla "f12"  
e clicar no ícone de celular. Lembrando que a funcionalidade de Localizar não funciona se estiver sendo executada no Chrome, para isso, é necessário gerar o APK do aplicativo para ser testado no celular de fato

##### Aplicação Arduino
- Não foi possível criar o tutorial para a integração com arduino, pois no momento, estou sem os equipamentos necessário para isso, o [Projeto Arduino](https://github.com/kelvi-ribeiro/prj-arduino-camarao) já está configurado para isso,  basta você preparar o seu Arduino, sensor de temperatura e ligar a aplicação, única coisa que você deve se certificar, é se está apontando para o local correto no arquivo prj-arduino-camarao/src/Serial/SerialRxTx.java, configure a variável API_TEMPERATURA_URL para o endpoint correto da [API Java](https://github.com/kelvi-ribeiro/api-camarao) 
- Futuramente, irei colocar uma melhor explicação, talvez até com um vídeo para facilitar na parte de configurar o Arduino

## Imagens do aplicativo:

##### Interface de Login:

![interface-login](https://i.imgur.com/mfoS5JK.png)

#####  Interface Principal de listagem de medições:

![interface-principal](https://i.imgur.com/9rFE22W.png)

#####  Interface de Gráfico sobre as medições:

![interface-grafico](https://i.imgur.com/4cTIvya.png)

##### Side Menu(Menu Lateral):

![side-menu](https://i.imgur.com/2YvQNdx.png)

##### Interface de relatório completo:

![interface-relatorio](https://i.imgur.com/RolNt8x.png)

##### Interface de Listagem de avisos:

![interface-avisos](https://i.imgur.com/3I3VPYf.png)

##### Interface de detalhes do aviso:

![detalhes-aviso](https://i.imgur.com/Y13sOpl.png)

##### Interface de Gerenciamento de tanques:

![gerenciamento-tanque](https://i.imgur.com/M7NIRGe.png)

##### Interface de ajuda:

![interface-ajuda](https://i.imgur.com/W0Hle1P.png)


### Todos (Funcionalidades Futuras)

 - Adicionar mais sensores, nesse protótipo, a única propriedade testada foi a de Temperatura
 - Implementar um Dashboard para o Gerente dos funcionários, contendo as principais informações das propriedades de seus funcionários, pode ser implantado um BI para isso.
 - Testar funcionalidades

License
----

MIT


