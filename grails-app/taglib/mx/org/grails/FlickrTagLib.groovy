package mx.org.grails

class FlickrTagLib {

  def flickrService

  static namespace = "flickr"

  // This taglib receives tag, perPage
  def search = { attrs, body ->
    try{
      def data = flickrService.search(attrs)
      out << render(
        template:'/flickr/searchResult',
        plugin:'flickr',
        model:[
          urls:data.urls,
          tag:attrs.tag,
          page:data.page[0],
          pages:data.pages[0],
          perPage:attrs.perPage as Integer ?: 12,
          animated:attrs.animated ?: false,
          id:"${attrs.tag}${new Date().time}"
        ])
    }catch(FlickrException e){
      out << "<h6> ${e.message} </h6>"
    }
  }
  
}
