import groovy.io.GroovyPrintStream
import org.slf4j.LoggerFactory
import ratpack.groovy.test.GroovyRatpackMainApplicationUnderTest
import ratpack.http.client.RequestSpec
import ratpack.test.ApplicationUnderTest
import ratpack.test.http.TestHttpClient
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

class HandlerSpec extends Specification {
  // Start our application and make it available for testing. `@Shared` means the same app instance will be used for _all_ tests
    @Shared
    ApplicationUnderTest appUnderTest = new GroovyRatpackMainApplicationUnderTest()

    // ApplicationUnderTest includes a TestHttpClient that we can use for sending requests to our application.
    @Delegate
    TestHttpClient testClient = appUnderTest.httpClient

    def "make sure defualt text shows up"(){
      when: "a GET request is sent with no path"
        testClient.get() // we don't have to assign the ReceivedResponse returned as TestHttpClient will keep track of this for us

        then: "a response is returned with body text that contains ratpack example app"
        testClient.response.body.text =~ /ratpack example app/ // `testClient.response` is the ReceivedResponse from the last request sent
    }
}
