import restlogistica.Mapa
import restlogistica.Rota
import org.springframework.web.context.support.WebApplicationContextUtils

class BootStrap {

    def init = { servletContext ->

        def springContext = WebApplicationContextUtils.getWebApplicationContext( servletContext )
        springContext.getBean( "restLogisticaMarshaller").register()

    /*	só para teste em memoria do banco h2 
    
        new Mapa(nome:"Sao Carlos")
    	.addToRotas(new Rota(origem:"A",destino:"B",distancia:new Long(200)))
    	.addToRotas(new Rota(origem:"B",destino:"C",distancia:new Long(100)))
    	.addToRotas(new Rota(origem:"A",destino:"C",distancia:new Long(500)))
    	.save()
        new restlogistica.Mapa(nome:"Araraquara").save()*/
    }
    def destroy = {
    }
}
