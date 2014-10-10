import grails.rest.RestfulController
import restlogistica.Mapa
import restlogistica.Rota
import grails.converters.JSON
import grails.transaction.*
import java.io.*
import java.util.*
import dijkstra.*

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
		//teste()
		respond Mapa.get(params.id)
	}

	// teste do algoritmo menor caminho integrado ao a api rest
	def teste(){
		
		graph = [  
		    new Edge(node1:'a', node2:'b', distance:4),  
		    new Edge(node1:'a', node2:'c', distance:2),  
		    new Edge(node1:'b', node2:'c', distance:3),  
		    new Edge(node1:'c', node2:'b', distance:1),  
		    new Edge(node1:'c', node2:'d', distance:5),  
		    new Edge(node1:'b', node2:'d', distance:1),  
		    new Edge(node1:'a', node2:'e', distance:1),  
		    new Edge(node1:'e', node2:'d', distance:4)  
		]  
		def dijkstra = new DijkstrasShortestPathAlgoritm(graph, 'a', 'd')  
		d = dijkstra.getShortestPathWay();  
		log.info d
		assert d == 4  
		assert dijkstra.shortestPath == ['a','c','b','d']
		
	}
}