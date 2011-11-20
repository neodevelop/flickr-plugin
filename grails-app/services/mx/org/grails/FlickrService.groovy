package mx.org.grails
import org.codehaus.groovy.grails.commons.ConfigurationHolder

import com.eddgrant.FlickrImage

class FlickrService {

  static transactional = false

  //  API key obtained from Config
  def api_key = ConfigurationHolder.config.grails.plugins.flickr.apiKey

  def search(closure) {
    // Check if exists an api key
    if(!api_key) {
      throw new FlickrException(message:"There's not API key...")
    }
    
    // We call a builder
    def api_url = new FlickrSearch().buildUrlToSearch(api_key,closure)
    fetchXmlFromFlickr(api_url)
  }
  
  def search(Map map){
    def api_url = new FlickrSearch().buildUrlToSearch(api_key,map)
    fetchXmlFromFlickr(api_url)
  }
  
  private def fetchXmlFromFlickr(api_url){
    List<FlickrImage> media = new ArrayList<FlickrImage>();
    
    // We use the parser to obtian XML
    def rsp = new XmlParser().parse(api_url)
    def urls = []
    if(rsp.'@stat'=="fail"){
      throw new FlickrException(message:rsp.err.'@msg'[0])
    }
    else{
      // Si no arrojo error empezamos a iterar nuestro objeto XML con uso de XmlParser
      rsp.photos.photo.each{
        def mediaId = it.@id
        def farmId = it.@farm
        def serverId = it.@server
        def secret = it.@secret
        def title = it.@title
        def publicMedia = it.@ispublic == 1 ? true : false
        
        // Generamos ligas e imagenes basados en los resultados del consumo REST
        urls << "http://farm${farmId}.static.flickr.com/${serverId}/${mediaId}_${secret}_s.jpg"
        def flickrImage = new FlickrImage(mediaId: mediaId, farmId: farmId, serverId: serverId,
                                          secret: secret, title: title, publicMedia: publicMedia)
        media.add(flickrImage)   					
      }	
    }
    
    long currentPage = rsp.photos.'@page'[0] as long
    long totalPages =  rsp.photos.'@pages'[0] as long
    [urls: urls, media: media, page: currentPage, pages: totalPages]
  }
}
