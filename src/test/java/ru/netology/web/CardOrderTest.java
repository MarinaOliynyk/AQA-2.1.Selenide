package ru.netology.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class CardOrderTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTest() {
        $("[data-test-id=name] input").setValue("Марина Олийнык");
        $("[data-test-id=phone] input").setValue("+79370000000");
        $("[data-test-id=agreement]").click();
        $(".button").click();
        $(".Success_successBlock__2L3Cw").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestForm() {
        $("[data-test-id=name] input").setValue("Марина Олийнык-Шпак");
        $("[data-test-id=phone] input").setValue("+79370000000");
        $("[data-test-id=agreement]").click();
        $("[type='button']").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestFieldName() {
        $("[data-test-id=name] input").setValue("Marina Oliynyk");
        $("[data-test-id=phone] input").setValue("+79370000000");
        $("[data-test-id=agreement]").click();
        $("[type='button']").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestFieldPhone() {
        $("[data-test-id=name] input").setValue("Марина Олийнык");
        $("[data-test-id=phone] input").setValue("89370000000");
        $("[data-test-id=agreement]").click();
        $("[type='button']").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestFieldAgreement() {
        $("[data-test-id=name] input").setValue("Марина Олийнык");
        $("[data-test-id=phone] input").setValue("89370000000");
        $("[data-test-id=agreement] .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
        $("[type='button']").click();
    }

    @Test
    void shouldTestFieldNonInput() {
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("[type='button']").click();
        $(".input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
}

