package mx.org.grails

class FlickrTagLib {
  
  def flickrService
  
  static namespace = "flickr"
  
  // This taglib receives tag, perPage
  def search = { attrs, body ->
    def data = flickrService.search(attrs.tag,attrs.perPage as Integer,1)
    out << render(
      template:'/flickr/searchResult',
      plugin:'flickr',
      model:[
        urls:data.urls,
        tag:attrs.tag,
        page:data.page[0],
        pages:data.pages[0],
        perPage:attrs.perPage as Integer ?: 12,
      ]
    )
  }
  
}
