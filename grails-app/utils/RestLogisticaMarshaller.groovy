 
import grails.converters.JSON
import restlogistica.*
 
 // classe que personaliza retorno dos dados no formato json
class RestLogisticaMarshaller {

    void register() {

        JSON.registerObjectMarshaller (Mapa) { Mapa mapa ->
            return [
                id: mapa.id,
                nome: mapa.nome,
                rotas: mapa.rotas
            ]
        }

        JSON.registerObjectMarshaller (Rota) { Rota rota ->
            return [
                id: rota.id,
                origem: rota.origem,
                destino: rota.destino,
                distancia: rota.distancia
            ]
        }
    }
}