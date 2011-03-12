package org.mx.grails

class FlickrService {

  static transactional = false

  def search(tag) {
    def api_key="533fbdc437466773ba34415203ddee23"
    // Armamos la URL para consumo REST
    def apiUrl = "http://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=${api_key}&tags=${tag}"
    // Usamos nuestro parser para consultarlo y recibir el XML
    def rsp = new XmlParser().parse(apiUrl)
    def urls = []
    if(rsp.'@stat'=="fail"){
      // TODO: Validar que la api no ha sido correctamente invocada
      println """
      <h1>Codigo: <b>${rsp.err.'@code'}</b> Mensaje: <b>${rsp.err.'@msg'}</b></h1>
      """
      }else{
        // Si no arrojo error empezamos a iterar nuestro objeto XML con uso de XmlParser
        rsp.photos.photo.each{
          // Generamos ligas e imagenes basados en los resultados del consumo REST
          urls << "http://farm${it.@farm}.static.flickr.com/${it.@server}/${it.@id}_${it.@secret}_s.jpg"  					
        }	
      }
      return urls
    }
  }
