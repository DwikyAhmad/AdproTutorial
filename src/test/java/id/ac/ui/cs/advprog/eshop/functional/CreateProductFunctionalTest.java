package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.util.List;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {
    /**
     *  The port number assigned to the running application during test execution.
     *  Set automatically during each test run by Spring Framework's test context.
     */
    @LocalServerPort
    private int serverPort;

    /**
     *  The base URL for testing. Default to {@code http://localhost}.
     */
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test 
    void createProductPage_isCorrect(ChromeDriver driver) throws Exception {
        String createUrl = baseUrl + "/product/create";
        driver.get(createUrl);

        String pageTitle = driver.getTitle();
        assertEquals("Create New Product", pageTitle);
    }

    @Test
    void listProductPage_isCorrect(ChromeDriver driver) throws Exception {
        String createUrl = baseUrl + "/product/list";
        driver.get(createUrl);

        String pageTitle = driver.getTitle();
        assertEquals("Product List", pageTitle);
    }

    @Test
    void createProduct_isWorking(ChromeDriver driver) throws Exception {
        String createUrl = baseUrl + "/product/create";
        driver.get(createUrl);

        WebElement nameInput = driver.findElement(By.id("nameInput"));
        WebElement quantityInput = driver.findElement(By.id("quantityInput"));
        WebElement submitButton = driver.findElement(By.tagName("button"));

        String productName = "Sword";
        String productQuantity = "50";

        nameInput.sendKeys(productName);
        quantityInput.sendKeys(productQuantity);
        submitButton.click();

        Thread.sleep(300);

        List<WebElement> savedProduct = driver.findElements(By.tagName("td"));
        assertEquals(productName, savedProduct.get(0).getText());
        assertEquals(productQuantity, savedProduct.get(1).getText());
    }
}
