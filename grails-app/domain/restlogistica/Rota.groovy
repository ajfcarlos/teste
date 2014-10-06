package restlogistica

import grails.rest.*

@Resource(uri='/rotas', formats=['json', 'xml'])
class Rota {

    String origem

    String destino

    Long distancia

     static belongsTo = [mapa: Mapa]

    static constraints = {

    	origem(nullable: false)
    	destino(nullable: false)
    	distancia(nullable:false, min:new Long(1))
    }
}