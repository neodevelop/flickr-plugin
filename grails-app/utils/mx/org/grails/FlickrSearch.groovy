package mx.org.grails

class FlickrSearch{

  def api_url = "http://api.flickr.com/services/rest/?"
  
  def methodMissing(String name,args){
    if(name.any{ Character.isUpperCase(it as Character) }){
      def allUpperCases = name.findAll{ it >= 'A' && it <= 'Z' }
      def since = 0
      def newNameForApi = ""
      allUpperCases.each{
        def until = name.lastIndexOf(it)
        newNameForApi += name.substring(since,until) + "_"
        since = until
      }
      newNameForApi += name.substring(since)
      newNameForApi = newNameForApi.toLowerCase()
      api_url += "&${newNameForApi}=${args[0]}" 
    }else{
     api_url += "&${name}=${args[0]}" 
    }
  }
  
  def buildUrlToSearch(api_key,closure){
    closure.delegate = this
    api_url+="method=flickr.photos.search"
    api_url+="&api_key=${api_key}"
    closure()
    api_url
  }
  
  def buildUrlToSearch(api_key,Map map){
    api_url+="method=flickr.photos.search"
    api_url+="&api_key=${api_key}"
    map.each{ methodName,args ->
      this."$methodName"(args)
    }
    api_url
  }
  
}