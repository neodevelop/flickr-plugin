package mx.org.grails

class FlickrTagLib {
  
  def flickrService
  
  def pluginManager
  
  static namespace = "flickr"
  
  // This taglib receives tag, perPage, cols
  def search = { attrs, body ->
    
    def plugin = pluginManager.getGrailsPlugin('flickr')
    
    def urls = flickrService.search(attrs.tag,attrs.perPage)
    out << "<table id='${attrs.tag}'><tr>"
    def contador = 0
    urls.each{ url ->
      def urlModificada = url.replace("_s.jpg","_b.jpg")
      out << "<td> <a href='${urlModificada}'><img src='${url}' /></a> </td>"
      contador++
      if((contador % (attrs?.cols as Integer ?: 3)) == 0){
        out << "</tr><tr>"
      }
    }
    
    out << "</tr></table>"
  }
  
}
