import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;

public class RequestOrder {
    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        $("[data-test-id = name] input").setValue("Орлов Олег");
        $("[data-test-id = phone] input").setValue("+79167824318");
        $("[data-test-id = agreement]").click();
        $(".button__text").click();
        $("[data-test-id = order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void  shouldAppearErrorMessageIfIncorrectName() {
        open("http://localhost:9999");
        $("[data-test-id = name] input").setValue("Orlov Oleg");
        $("[data-test-id = phone] input").setValue("+79167824318");
        $("[data-test-id = agreement]").click();
        $(".button__text").click();
        $("[data-test-id = name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void  shouldAppearErrorMessageIfNameIsEmpty() {
        open("http://localhost:9999");
        $("[data-test-id = name] input").setValue("");
        $("[data-test-id = phone] input").setValue("+79167824318");
        $("[data-test-id = agreement]").click();
        $(".button__text").click();
        $("[data-test-id = name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void  shouldAppearErrorMessageIfIncorrectPhone() {
        open("http://localhost:9999");
        $("[data-test-id = name] input").setValue("Орлов Олег");
        $("[data-test-id = phone] input").setValue("+22445566");
        $("[data-test-id = agreement]").click();
        $(".button__text").click();
        $("[data-test-id = phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void  shouldAppearErrorMessageIfPhoneIsEmpty() {
        open("http://localhost:9999");
        $("[data-test-id = name] input").setValue("Орлов Олег");
        $("[data-test-id = phone] input").setValue("");
        $("[data-test-id = agreement]").click();
        $(".button__text").click();
        $("[data-test-id = phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void  shouldAppearErrorMessageIfAgreementIsEmpty() {
        open("http://localhost:9999");
        $("[data-test-id = name] input").setValue("Орлов Олег");
        $("[data-test-id = phone] input").setValue("+79990056781");
        $(".button__text").click();
        $("[data-test-id = agreement].input_invalid").shouldBe(visible);
    }
}