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

public class RequestOrder {


    WebDriver driver;


    @BeforeAll
    static void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\_Git Projects\\RequestOrder\\driver\\linux\\chromedriver.exe");
    }

    @BeforeEach
    void setUp2() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void close () {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void shouldSubmitRequest() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id = name] input").setValue("Орлов Олег");
        form.$("[data-test-id = phone] input").setValue("+79167824318");
        form.$("[data-test-id = agreement]").click();
        form.$(".button__content").click();
        $("[data-test-id = order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void  shouldAppearErrorMessage() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id = name] input").setValue("Orlov Oleg");
        form.$("[data-test-id = phone] input").setValue("+79167824318");
        form.$("[data-test-id = agreement]").click();
        form.$(".button__content").click();
        $(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

}