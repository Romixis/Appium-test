import io.appium.java_client.AppiumDriver;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import java.net.URL;

public class FirstTest extends CoreTestCase {

    private AppiumDriver<WebElement> driver;

    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "your_device_name");
        // Добавьте другие необходимые capabilities
        URL serverUrl = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver<>(serverUrl, capabilities);
    }

    @Test
    public void testSearchTest(){
        SearchPageObject searchPageObject = new SearchPageObject(driver);
        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Хоббит, или Туда и обратно");
        searchPageObject.waitForSearchResultAndClick("Хоббит, или Туда и обратно");
        searchPageObject.saveToList();
        searchPageObject.goToBack();
        searchPageObject.goToSaveList();
        searchPageObject.goToSaveListInSavedAndGetOptions();
        searchPageObject.deleteSavedTitle();
    }
}
