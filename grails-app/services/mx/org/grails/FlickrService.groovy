/* Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mx.org.grails

import org.codehaus.groovy.grails.commons.ConfigurationHolder


class FlickrService {

  static transactional = false
  
  XmlParser xmlParser
  String api_key
  
  public FlickrService() {
    xmlParser = new XmlParser()
    //  API key obtained from Config
    api_key = ConfigurationHolder.config.grails.plugins.flickr.apiKey
  }

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
    List<FlickrImage> flickrImages = new ArrayList<FlickrImage>();
    
    // We use the parser to obtain XML
    def rsp = xmlParser.parse(api_url)
    def urls = []
    if(rsp.'@stat'=="fail"){
      throw new FlickrException(message:rsp.err.'@msg'[0])
    }
    else{
      // If there's no error so we start to iterate
      rsp.photos.photo.each{
        def mediaId = it.@id
        def farmId = it.@farm
        def serverId = it.@server
        def secret = it.@secret
        def title = it.@title
        def publicMedia = it.@ispublic == 1 ? true : false
        
        // We generate links and images based on results of REST consume
        def flickrImage = new FlickrImage(mediaId: mediaId, farmId: farmId, serverId: serverId,
                                          secret: secret, title: title, publicMedia: publicMedia)
        
        flickrImages.add(flickrImage)   					
      }	
    }
    
    long currentPage = rsp.photos.'@page'[0] as long
    long totalPages =  rsp.photos.'@pages'[0] as long
    [photos: flickrImages, page: currentPage, pages: totalPages]
  }
}
