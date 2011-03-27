package mx.org.grails

class FlickrController {

  def flickrService

  def index = { }

  def changePage = {
    def data = flickrService.search(params.tag,params.perPage as Integer,(params.page as Integer) + 1)
    render(
      template:'/flickr/listPhotos',
      plugin:'flickr',
      model:[
        urls:data.urls,
        tag:params.tag,
        page:data.page[0],
        pages:data.pages[0],
        perPage:params.perPage,
      ])
  }

}
