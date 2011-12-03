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
import groovy.mock.interceptor.MockFor

class FlickrServiceIntegrationTests extends GrailsUnitTestCase {
  
    def flickrService
  
    protected void setUp() {
        super.setUp()
        flickrService = new FlickrService()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSearchReturnsNotNullResponse() {
      def response = flickrService.search{
        tags 'springhispano'
      }
      assert response.size() > 0
    }
    
    void testPageAndCurrentPageAreReturnedAsNumbers() {
      def response = flickrService.search{
        tags 'springhispano'
      }
      assert response.size() > 0
      assertEquals(1, response.page)
      assertTrue(response.page instanceof Long)
      assertTrue(response.pages instanceof Long)
    }
    
    void testResultsAreBundlesIntoFlickrPhotoObjects() {
      def response = flickrService.search{
        tags 'springhispano'
      }
      assert response.size() > 0
      assertNotNull(response.photos)
      response.photos.each {
        assertNotNull(it.mediaId)
        assertNotNull(it.farmId)
        assertNotNull(it.serverId)
        assertNotNull(it.secret)
        assertNotNull(it.title)
        assertNotNull(it.publicMedia)
      }
    }
}
