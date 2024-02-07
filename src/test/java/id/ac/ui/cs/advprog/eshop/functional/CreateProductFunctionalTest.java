package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {
    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;
    private String baseUrl;
    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort) + "product/create";
    }
    @Test
    void createNewProduct_isCorrect(EdgeDriver driver) throws Exception {
        driver.get(baseUrl);
        WebElement checkInput = driver.findElement(By.name("submit"));
        checkInput.click();

        WebElement nameInput = driver.findElement(By.name("name_input"));
        nameInput.clear();
        WebElement qtyInput = driver.findElement(By.name("qty_input"));
        qtyInput.clear();

        String name = "Sampo Cap Bambang";
        String qty = "100";
        nameInput.sendKeys(name);
        qtyInput.sendKeys(qty);
    }
}
