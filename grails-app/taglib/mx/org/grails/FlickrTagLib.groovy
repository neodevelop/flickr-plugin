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
          photos:data.photos,
          tag:attrs.tags.replace(',','_'),
          page:data.page,
          pages:data.pages,
          perPage:attrs.perPage as Integer ?: 12,
          animated:attrs.animated ?: false,
          id:"${attrs.tags.replace(',','_')}${new Date().time}"
        ])
    }catch(FlickrException e){
      out << "<h6> ${e.message} </h6>"
    }
  }
  
}
