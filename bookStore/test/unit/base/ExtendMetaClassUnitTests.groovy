package base

import config.Hotel
import grails.buildtestdata.mixin.Build
import org.junit.Before

@Build(Hotel)
class ExtendMetaClassUnitTests {

    @Before
    void setUp() {
        assert Hotel.metaClass.hasMetaMethod( "build" )
        assert !Hotel.metaClass.hasMetaMethod("bar")
        Hotel.metaClass."static".bar = {-> return "bar" }
    }

    void testBuildWithAdditionalMockedMethods() {
        def hotel = Hotel.build()
        assertNotNull hotel
        assertEquals "bar", Hotel.bar()
    }

    void testBuildStillThereAfterTearDown() {
        def hotel = Hotel.build()
        assertNotNull hotel
        assertEquals "bar", Hotel.bar()
    }
}
