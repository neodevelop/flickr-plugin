package mx.org.grails

import grails.test.*

class FlickrServiceTests extends GrailsUnitTestCase {
  
    def flickrService
  
    protected void setUp() {
        super.setUp()
        flickrService = new FlickrService();
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSearch() {
      def urls = flickrService.search{
        tags 'springhispano'
      }
      assert urls.size() > 0
    }
}