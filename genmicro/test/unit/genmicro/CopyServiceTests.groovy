package genmicro

import grails.test.*

class CopyServiceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSomething() {
		def copyService = new CopyService()
		
		copyService.copy() 
    }
}
