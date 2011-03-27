package mx.org.grails
import org.codehaus.groovy.grails.commons.ConfigurationHolder

class FlickrService {

  static transactional = false

  //  API key obtained from Config
  def api_key = ConfigurationHolder.config.grails.plugins.flickr.apiKey

  def search(closure) {
    // Check if exists an api key
    if(!api_key)
      throw new FlickrException(message:"There's not API key...")
    
    // We call a builder
    def api_url = new FlickrSearch().buildUrlToSearch(api_key,closure)
    fetchXmlFromFlickr(api_url)
  }
  
  def search(Map map){
    def api_url = new FlickrSearch().buildUrlToSearch(api_key,map)
    fetchXmlFromFlickr(api_url)
  }
  
  private def fetchXmlFromFlickr(api_url){
    // We use the parser to obtian XML
    def rsp = new XmlParser().parse(api_url)
    def urls = []
    if(rsp.'@stat'=="fail"){
      throw new FlickrException(message:rsp.err.'@msg'[0])
    }else{
      // Si no arrojo error empezamos a iterar nuestro objeto XML con uso de XmlParser
      rsp.photos.photo.each{
        // Generamos ligas e imagenes basados en los resultados del consumo REST
        urls << "http://farm${it.@farm}.static.flickr.com/${it.@server}/${it.@id}_${it.@secret}_s.jpg"  					
      }	
    }
    [urls:urls,page:rsp.photos.'@page',pages:rsp.photos.'@pages']
  }
}
