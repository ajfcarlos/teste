Desafio
========

* Configuração banco de dados mysql
 
    Criar database mysql com o nome: restlogistica
    Criar usuario : user restlogistica /password restlogistica
 
 * Tomcat ==> deploy no arquivo .war gerado a partir do codigo-fonte ,atraves do comando grails prod WAR

POSTMAN => ferramenta para teste Web Service Rest para cadastro de mapas e rotas/buscar mapas e rotas 

 https://chrome.google.com/webstore/detail/postman-rest-client/fdmmgilgnpjigdojojpjoooidkmcomcm


Entrada de Dados :Cadastro de mapas
No restcliente postman

 url: http://localhost:8080/restlogistica/mapas method:post

 formato do dados em json

 Exemplo

 {
    "nome": "Principal", ==> nome mapa
    "rotas": [
        {
            "origem": "A",
            "destino": "B",
            "distancia": 10
        },
        {
            "origem": "A",
            "destino": "C",
            "distancia": 20
        },
        {
            "origem": "B",
            "destino": "E",
            "distancia": 50
        },
		{
            "origem": "B",
            "destino": "D",
            "distancia": 15
        },
        {
            "origem": "C",
            "destino": "D",
            "distancia": 30
        },
        {
            "origem": "D",
            "destino": "E",
            "distancia": 30
        }
    ]
}

* Funcionalidade :  cáclulo do caminho mais curto.
Foi testado e retornou dados consistentes.

Integrar algoritmo caminho mais curto ao projeto

* Foi integrado Dijskstra algoritmo em java ao projeto grails, deu um trabalho, compilador não respondia adequadamente.

Pele metodo show/{id} da api rest é passados os parametros via get 'inicio' e fim e é calculado o  o caminho mais curto e a distância
Ex: buscar caminho mais curto
   http://localhost:8080/restlogistica/mapas/5?inicio=A&fim=D&autonomia=10&precoGasolina=2

Tem como resposta o mapa do com as rotas,caminho mais curto e o custo da viagem.

{
    "caminho": "ABD",
    "custoViagem": 5,
    "mapa": {
        "id": 5,
        "nome": "Principal",
        "rotas": [
            {
                "id": 12,
                "origem": "C",
                "destino": "D",
                "distancia": 30
            },
            {
                "id": 7,
                "origem": "D",
                "destino": "E",
                "distancia": 30
            },
            {
                "id": 11,
                "origem": "A",
                "destino": "B",
                "distancia": 10
            },
            {
                "id": 10,
                "origem": "B",
                "destino": "D",
                "distancia": 15
            },
            {
                "id": 9,
                "origem": "B",
                "destino": "E",
                "distancia": 50
            },
            {
                "id": 8,
                "origem": "A",
                "destino": "C",
                "distancia": 20
            }
        ]
    }
}


