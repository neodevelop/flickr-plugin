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

class FlickrServiceTests extends GrailsUnitTestCase {
  
    def flickrService
  
    protected void setUp() {
        super.setUp()
        flickrService = new FlickrService()
        
        /*def mockXmlParser = new MockFor(XmlParser)
        mockXmlParser.demand.parse {
          return "this is my xml"
        }
        
        flickrService.setXmlParser(mockXmlParser.proxyInstance())*/
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSearch() {
      def urls = flickrService.search("springhispano")
      assert urls.size() > 0
    }
}
