Desafio
========

* Configuração banco mysql
 criar usuario : user restlogistica /password restlogistica
 criar database restlogistica

POSTMAN => ferramenta para teste Web Service Rest para cadastro de mapas e rotas/buscar mapas e rotas 

 https://chrome.google.com/webstore/detail/postman-rest-client/fdmmgilgnpjigdojojpjoooidkmcomcm

entrada de dados exemplo 

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
Ex: postman url
    http://localhost:8080/restlogistica/mapas/5?inicio=A&fim=D

tratar a resposta 
TO DO
rota nao mosta direito cammonho
auotmia vindo em lista[]
e calcular gasolina na rota de retorno


