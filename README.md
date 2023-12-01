# Produtos de seguro
## Índice
* [Stack](#stack)
* [Configuração](#configuracao)
    * [Debug (IDE)](#debug_ide)
    * [Docker](#docker)
* [Arquitetura Limpa](#arquitetura)
    * [Projeto](#arquitetura_projeto)
* [Banco de dados](#banco_de_dados)
* [Fluxo](#fluxo)


### <a name="stack">Stack</a>
- Java 17
- Maven 3.8.4
- Spring 3.2.0
- MySQL 8.0.33
- REST API
### <a name="configuracao">Configuração</a>
### <a name="debug_ide">Debug IDE</a>
### <a name="docker">Docker</a>
### <a name="arquitetura">Arquitetura Limpa</a>
Este projeto está baseado em Arquitetura Limpa. A ideia dessa aquitetura é fazer com que cada parte do código fique desacoplada, de forma que camadas de entrada ou de busca de informações não acessem o _core_ do sistema, Essa forma de configuração facilita a manutenção do sistema, uma vez que o sistema está mais desacoplado.
Esse desacoplamento se da através da inversão de dependência, fazendo com que seja possível a comunicação entre camadas porém, sem acessa-lás diretamente. Dessa forma nossa camada principal do sistema pode depender apenas de contratos e qualquer um que cumpra esses contratos pode se comunicar com a camada principal do sistema, o que facilita muito a troca de um banco de dados, por exemplo.
Ao usar a arquitetura limpa, inicialmente, pode parecer que existem muitos objetos duplicados e tem mesmo! Acontece que como as camadas não se acessam diretamente é necessário você ter uma representação de produto em cada camada, por exemplo. Outra coisa que pode assustar é a utilização de muitos _gateways_, essas coisas realmente fazem parecer que estamos escrevendo muito código além do necessário mas, uma vez que a arquitetura esté bem estruturada é muito mais fácil fazer alterações (mesmo que pareça que esteja escrevendo mais código), além disso você pode ter muito mais certeza de que suas alterações não irão "quebrar" seu sistema.
Para mais detalhes sobre arquitetura limpa leia [este texto](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html).
### <a name="arquitetura_projeto">Projeto</a>
Se você viu o link do uncle bob acima, você deve ter visto muitas camadas no desenho, a arquitetura deste projeto é baseada naquele desenho porém está adaptada.
Nesse sistema existem três camadas: **entrypoint**, **dataprovider**, **core**, abaixo será detalhado sobre cada uma delas.
- **entrypoint**: Camada responsável por receber requisições externas, pode ser via mensageria, enpoint e etc, neste sistemas temos apenas requisições via endpoint REST. **Essa camada tem acesso a camada core e não pode ser acessada por ninguém**.
- **dataprovider**: Camada responsável por buscar informações em sistemas externos, pode ser outro serviço, banco de dados e etc, neste projeto temos apenas acesso ao banco de dados MySQL.**Essa camada tem acesso a camada core e não pode ser acessada por ninguém**.
- **core**: Camada responsável por manter todo o core da aplicação, as regras de negócio **devem** estar aqui. **Essa camada não deve conhecer nenhuma outra camada e pode ser acessada pelo entrypoint e dataprovider **.

Sendo assim, temos uma arquitetura conforme a imagem abaixo:
<IMAGEM_AQUI>
Existem algumas formas de lidar com diferentes camadas, como criar projetos multi-módulos, por exemplo, o que garantiria que as regras de acessos entre camadas não fossem quebradas, uma vez que seria necessário adicionar as camadas como dependência nos módulos. Para simplificar, neste projeto foi utilizado apenas um módulo e para garantir que não haja quebra no acesso entre as camadas foi criado [esse](linkaqui) teste de arquitetura.

### <a name="banco_de_dados">Banco de dados</a>
Uma vez que esse sistema não está sendo projetado para uma grande quantidade de dados, possui uma estrura bem definida do seu domínio, foi optado por se utilizar um banco de dado SQL, no caso o MySQL.
O banco possui apenas uma tabela, conforme abaixo:
<IMAGEM_AQUI>
### <a name="fluxo">Fluxo</a>
O fluxo presente nesse sistema é bastante simples e pode ser identificado através do diagrama abaixo:
<IMAGEM_AQUI>
