package mx.org.grails

import grails.test.*

class FlickrServiceIntegrationTests extends GrailsUnitTestCase {
  
    def flickrService
  
    protected void setUp() {
        super.setUp()
        flickrService = new FlickrService();
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
      assertNotNull(response.media)
      response.media.each {
        assertNotNull(it.mediaId)
        assertNotNull(it.farmId)
        assertNotNull(it.serverId)
        assertNotNull(it.secret)
        assertNotNull(it.title)
        assertNotNull(it.publicMedia)
      }
    }
}
