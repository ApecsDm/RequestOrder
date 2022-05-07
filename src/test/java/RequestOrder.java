import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

public class RequestOrder {


    WebDriver driver;


    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver();
    }

    @AfterEach
    public void close () {
            driver.quit();
    }

    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        $(".heading_theme_alfa-on-white");
        $("[data-test-id = name] input").setValue("Орлов Олег");
        $("[data-test-id = phone] input").setValue("+79167824318");
        $("[data-test-id = agreement]").click();
        $(".button__text").click();
        $("[data-test-id = order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

   /* @Test
    void  shouldAppearErrorMessage() {
        open("http://localhost:9999");
        $(".heading_theme_alfa-on-white");
        $("[data-test-id = name] input").setValue("Orlov Oleg");
        $("[data-test-id = phone] input").setValue("+79167824318");
        $("[data-test-id = agreement]").click();
        $(".button__text").click();
        $("[.input__sub]").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }


    */
}