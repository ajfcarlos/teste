package restlogistica

import grails.rest.*

@Resource(uri='/mapas', formats=['json', 'xml'])
class Mapa {

    String nome

    static hasMany = [rotas: Rota]

    static constraints = {
        nome blank:false
    }
}