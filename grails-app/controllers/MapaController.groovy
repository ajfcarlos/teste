import grails.rest.RestfulController
import restlogistica.Mapa
import restlogistica.Rota
import grails.converters.JSON
import grails.transaction.*
import java.io.*
import java.util.*
import dijkstra.Dijkstra
import dijkstra.Rota2

// controller api rest salvar mapa com rotas e mostrar dados gravados
@Transactional(readOnly = false)
class MapaController extends RestfulController {
    static responseFormats = ['json']

   def graph= []

    MapaController() {
        super(Mapa)
    }

    def index (Integer max){
		params.max = Math.min(max ?: 20, 100)
		respond Mapa.list(max: params.max)
	}

	def save(){
		def data = request.JSON
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
		teste()
		respond Mapa.get(params.id)
	}

	// teste do algoritmo menor caminho integrado ao a api rest
	def teste(){
		//passar rota inicial ,rota final ,edge
		Dijkstra teste = new Dijkstra();
		List<Rota2> lista = new ArrayList<Rota2>();
		lista.add(new Rota2("a", "b", 10));
	    lista.add(new Rota2("b", "d", 15));
	    lista.add(new Rota2("a", "c", 20));
	    lista.add(new Rota2("c", "d", 30));
	    lista.add(new Rota2("b", "e", 50));
	    lista.add(new Rota2("d", "e", 30));
		teste.init(lista,"a","d");
		log.debug "fim3 ";
		
	}
}