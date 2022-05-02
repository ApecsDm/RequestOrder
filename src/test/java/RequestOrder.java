import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RequestOrder {

    private WebDriver driver;

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