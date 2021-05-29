package ru.netology.web;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class CardOrderTest {
    @Test
    void shouldTest() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Марина Олийнык");
        form.$("[data-test-id=phone] input").setValue("+79370000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".Success_successBlock__2L3Cw").shouldHave(exactText(" Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее врем."));
    }

    @Test
    void shouldTestForm() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Марина Олийнык-Шпак");
        form.$("[data-test-id=phone] input").setValue("+9370000000");
        form.$("[data-test-id=agreement]").click();
        form.$("[type='button']").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestFieldName() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Marina Oliynyk");
        form.$("[data-test-id=phone] input").setValue("+79370000000");
        form.$("[data-test-id=agreement]").click();
        form.$("[type='button']").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефис"));
    }

    @Test
    void shouldTestFieldPhone() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Марина Олийнык");
        form.$("[data-test-id=phone] input").setValue("89370000000");
        form.$("[data-test-id=agreement]").click();
        form.$("[type='button']").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012"));
    }

    @Test
    void shouldTestFieldAgreement() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Марина Олийнык");
        form.$("[data-test-id=phone] input").setValue("89370000000");
        $("[data-test-id=agreement] .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных истори"));
        form.$("[type='button']").click();
    }

    @Test
    void shouldTestFieldNonInput() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$("[type='button']").click();
        $(".input_invalid .input__sub").shouldHave(exactText("оле обязательно для заполнения"));
    }
}

