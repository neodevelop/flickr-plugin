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

class FlickrImageTests extends GrailsUnitTestCase {
  
  private static final String MEDIA_ID = "TEST_MEDIA_ID"
  
  private static final String FARM_ID = "TEST_FARM_ID"
  
  private static final String SERVER_ID = "TEST_SERVER_ID"
  
  private static final String SECRET = "TEST_SECRET"
  
  private static final String TITLE = "TEST_TITLE"
  
  private FlickrImage flickrImage  
  
  protected void setUp() {
        super.setUp()
        flickrImage = new FlickrImage([mediaId: MEDIA_ID, farmId: FARM_ID, serverId: SERVER_ID, secret: SECRET, title: TITLE]);
    }

    void testGetUrl() {
      final String expectedString = "http://farm${FARM_ID}.static.flickr.com/${SERVER_ID}/${MEDIA_ID}_${SECRET}.jpg"
      assertEquals(expectedString, flickrImage.getUrl())
    }
    
    void testGetSmallSquareUrl() {
      final String sizeSuffix = SizeSuffix.SMALL_SQUARE.getSuffix()
      final String expectedString = "http://farm${FARM_ID}.static.flickr.com/${SERVER_ID}/${MEDIA_ID}_${SECRET}_${sizeSuffix}.jpg"
      assertEquals(expectedString, flickrImage.getUrl(SizeSuffix.SMALL_SQUARE))
    }
    
    void testGetThumbnailUrl() {
      final String sizeSuffix = SizeSuffix.THUMBNAIL.getSuffix()
      final String expectedString = "http://farm${FARM_ID}.static.flickr.com/${SERVER_ID}/${MEDIA_ID}_${SECRET}_${sizeSuffix}.jpg"
      assertEquals(expectedString, flickrImage.getUrl(SizeSuffix.THUMBNAIL))
    }
    
    void testGetSmallUrl() {
      final String sizeSuffix = SizeSuffix.SMALL.getSuffix()
      final String expectedString = "http://farm${FARM_ID}.static.flickr.com/${SERVER_ID}/${MEDIA_ID}_${SECRET}_${sizeSuffix}.jpg"
      assertEquals(expectedString, flickrImage.getUrl(SizeSuffix.SMALL))
    }
    
    void testGetLargeUrl() {
      final String sizeSuffix = SizeSuffix.LARGE.getSuffix()
      final String expectedString = "http://farm${FARM_ID}.static.flickr.com/${SERVER_ID}/${MEDIA_ID}_${SECRET}_${sizeSuffix}.jpg"
      assertEquals(expectedString, flickrImage.getUrl(SizeSuffix.LARGE))
    }
}
