package restlogistica

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class MapaSpec extends Specification {

    def setup() {

    }

    def cleanup() {
    }

	void testCadastro() {
		mockDomain Mapa, []
		def mapa1 = new Mapa(nome: "MapaSemRotas", rotas:[])
		mapa1.save()

		mockDomain Rota
		def rota = new Rota(origem:"A",destino:"B",distancia:10)
		def mapa2 = new Mapa(nome:"MapaCom Rotas", login:"joca",rotas:[rota])
		mapa2.addToRotas(rota)		
		mpa2.save()
	}
}
