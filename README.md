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

* todos os algoritmos de Dijkstra tanto em Java quanto em Groovy,isoladamente funcionaram
mas a integração com a api rest nao funcionou.
