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
