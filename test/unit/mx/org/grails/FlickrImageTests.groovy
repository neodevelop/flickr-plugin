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
