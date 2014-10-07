import grails.rest.RestfulController
import restlogistica.Mapa
import restlogistica.Rota
import grails.converters.JSON
import grails.transaction.*
import java.io.*;
import java.util.*;

@Transactional(readOnly = false)
class MapaController extends RestfulController {
    static responseFormats = ['json']

   def servicePath

    MapaController() {
        super(Mapa)
    }

    def index (Integer max){
		params.max = Math.min(max ?: 20, 100)
		println params.max
		respond Mapa.list(max: params.max)
	}

	def save(){
		def data = request.JSON
		println data
		def mapa = null
		Mapa.withTransaction { status ->
			mapa = new Mapa(nome : data.nome)
			data.rotas.each { item->
				def rota = new Rota(origem: item.origem, destino: item.destino, distancia: item.distancia as Long)
				mapa.addToRotas(rota)
			}
			mapa.save(flush:true, failOnError:true)
		}
		respond mapa
	}
	def show(){
		//servicePath.findPath()
		respond Mapa.get(params.id)
	}
}