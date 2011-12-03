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

import grails.test.*
import org.codehaus.groovy.grails.commons.ConfigurationHolder

class FlickrServiceTests extends GrailsUnitTestCase {

  def flickrService

  protected void setUp() {
    super.setUp()
    mockConfig('''
      grails{
        plugins{
          flickr{
            apiKey = 'YOUR_API_KEY'
          }
        }
      }
    ''')
  }

  protected void tearDown() {
    super.tearDown()
  }

  void testSearch() {

    def flickrSearchMock = mockFor(FlickrSearch)
    flickrSearchMock.demand.static.buildUrlToSearch{api_key,closure ->
      "http://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=YOUR_API_KEY"
    }
    
    def xml = '''
    <rsp stat="ok">
    <photos page="1" pages="2" perpage="100" total="114">
    	<photo id="3982370167" owner="29282267@N02" secret="6295d375ff" server="2487" farm="3" title="my_title" ispublic="1" isfriend="0" isfamily="0" />
    </photos>
    </rsp>
    '''
    def document = new XmlParser().parseText(xml)
    
    def mockXmlParser = mockFor(XmlParser)
    mockXmlParser.demand.parse { api_url ->
      document
    }

    flickrService = new FlickrService()
    flickrService.xmlParser = mockXmlParser.createMock() 

    def data = flickrService.search{
      tags "springhispano"
    }
    assert data.photos.size() == 1
  }
}
