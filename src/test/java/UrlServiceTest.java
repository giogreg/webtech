import com.webtech.demo.model.Url;
import com.webtech.demo.repository.UrlRepository;
import com.webtech.demo.service.UrlService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Unit test for UrlService
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UrlServiceTest {

    @Mock
    private Url urlMock;

    @Mock
    private UrlRepository urlRepository;

    @InjectMocks
    private UrlService urlService;

    @InjectMocks
    private Url urlInject;



    /**
    * Test for generateUrl()
    * test when null
    */
    @Test
    public void testGenerateUrlIsNull() {
    //Arrange
    Url testResult;
    //Act
    testResult = urlService.generateUrl(urlMock);
    //Assert
    Assert.assertNull(testResult);
    }

    /**
    * Test for generateUrl()
    * test when not null
    */
    @Test
    public void testGenerateUrlIsNotNull() {
    //Arrange
    Url testResult;
    urlInject.setlongUrl("testurl.test");
    //Act
    testResult = urlService.generateUrl(urlInject);
    //Assert
    Assert.assertNotNull(testResult);
    Assert.assertNotNull(testResult.getShortUrl());
    Assert.assertEquals("testurl.test", testResult.getLongUrl());
    }

    /**
    * Test for testGenerateUrl30daysValid()
    * test validation setting
    */
    @Test
    public void testGenerateUrl30daysValidNotNull() {
    //Arrange
    Url testResult;
    urlInject.setlongUrl("testurl.test");
    //Act
    testResult = urlService.generateUrl30daysValid(urlInject);
    //Assert
    Assert.assertNotNull(testResult);
    Assert.assertNotNull(testResult.getGueltigVon());
    Assert.assertNotNull(testResult.getGueltigBis());
    }

    /**
     * Test for shortenUrl()
     * test length of returned string
     */
    @Test
    public void testShortenUrl() {
        //Arrange
        String testResult;
        //Act
        testResult = urlService.shortenUrl(); // change method to public
        //Assert
        Assert.assertNotNull(testResult);
        Assert.assertEquals(6, testResult.length());
    }

}
