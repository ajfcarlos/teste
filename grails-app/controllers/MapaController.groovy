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

		buscarCaminhoMaisCurto(params.id as Long,params.inicio,params.fim)
		respond Mapa.get(params.id)
	}

	// teste do algoritmo menor caminho integrado ao a api rest
	def buscarCaminhoMaisCurto(long id, String inicio,String fim){
		//passar rota inicial ,rota final ,edge
		//busca no banco
		log.debug id
		log.debug inicio
		log.debug fim
		def mapa = Mapa.get(params.id)

		// instancia disjkstra
		Dijkstra teste = new Dijkstra();
		List<Rota2> lista = new ArrayList<Rota2>();

		//busca lista rotas
		def rotas = mapa?.rotas;
		rotas.each{ rota->
			lista.add(new Rota2(rota.origem, rota.destino, rota.distancia.intValue()));
		}

		teste.init(lista,inicio,fim);
		log.debug "fim7 ";
		
	}
}