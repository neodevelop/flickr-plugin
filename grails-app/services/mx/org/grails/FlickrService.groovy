package mx.org.grails

import org.codehaus.groovy.grails.commons.ConfigurationHolder


class FlickrService {

  static transactional = false
  
  XmlParser xmlParser
  
  public FlickrService() {
    xmlParser = new XmlParser()
  }

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
    
    // We use the parser to obtain XML
    def rsp = xmlParser.parse(api_url)
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
        def flickrImage = new FlickrImage(mediaId: mediaId, farmId: farmId, serverId: serverId,
                                          secret: secret, title: title, publicMedia: publicMedia)
        
        //TODO: The collection of URL Strings is now superceded by the FlickrImage List. Should we remove this?
        urls << flickrImage.getUrl(SizeSuffix.SMALL)
        media.add(flickrImage)   					
      }	
    }
    
    long currentPage = rsp.photos.'@page'[0] as long
    long totalPages =  rsp.photos.'@pages'[0] as long
    [urls: urls, media: media, page: currentPage, pages: totalPages]
  }
}
