package ru.netology.delivery.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;
import ru.netology.delivery.data.User;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryTest {

    @BeforeEach
    void openBrowser() {open("http://localhost:9999");
    }

    @Test
    void shouldTestOrderAdminCenter() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String date = DataGenerator.generateDate(3);

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        form.$("form button.button").click();

        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + date), Duration.ofSeconds(5))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldTestOrderAdminCenterHyphenated() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String cityHyphenated = "Санкт-Петербург";
        String date = DataGenerator.generateDate(3);

        form.$("[data-test-id = 'city'] input").setValue(cityHyphenated);
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        form.$("form button.button").click();

        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + date), Duration.ofSeconds(5))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldTestOrderCityNotAdminCenter() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String cityNotAdminCenter = "Сочи";
        String date = DataGenerator.generateDate(3);

        form.$("[data-test-id = 'city'] input").setValue(cityNotAdminCenter);
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        form.$("form button.button").click();

        $("[data-test-id='city'] [class='input__sub']").shouldHave(exactText("Доставка в выбранный город" +
                " недоступна"));
    }

    @Test
    void shouldTestOrderCityLatin() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String latinCity = "Moscow";
        String date = DataGenerator.generateDate(3);

        form.$("[data-test-id = 'city'] input").setValue(latinCity);
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        form.$("form button.button").click();

        $("[data-test-id='city'] [class='input__sub']").shouldHave(exactText("Доставка в выбранный город" +
                " недоступна"));
    }

    @Test
    void shouldTestOrderBackDate() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String date = DataGenerator.generateDate(-1);

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        form.$("form button.button").click();

        $("[data-test-id='date'] [class='input__sub']").shouldHave(exactText("Заказ на выбранную дату" +
                " невозможен"));
    }

    @Test
    void shouldTestOrderTodayDate() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String date = DataGenerator.generateDate(0);

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        form.$("form button.button").click();

        $("[data-test-id='date'] [class='input__sub']").shouldHave(exactText("Заказ на выбранную дату" +
                " невозможен"));
    }


    @Test
    void shouldTestOrderNameHyphen() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String hyphenName = "Петров-Васечкин Сергей";
        String date = DataGenerator.generateDate(3);

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        form.$("[data-test-id = 'name'] input").setValue(hyphenName);
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        form.$("form button.button").click();

        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + date), Duration.ofSeconds(5))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldTestOrderNameLatin() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String latinName = "Ivanov Ivan";
        String date = DataGenerator.generateDate(3);

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        form.$("[data-test-id = 'name'] input").setValue(latinName);
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        form.$("form button.button").click();

        $("[data-test-id='name'] [class='input__sub']").shouldHave(exactText("Имя и Фамилия указаные неверно." +
                " Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestOrderPhoneLowLength() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String lowLengthPhone = "12345";
        String date = DataGenerator.generateDate(3);

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(lowLengthPhone);
        form.$("[data-test-id = 'agreement']").click();
        form.$("form button.button").click();

        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + date), Duration.ofSeconds(5))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldTestOrderPhoneHighLength() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String highLengthPhone = "+7111111111111111111111";
        String date = DataGenerator.generateDate(3);

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(highLengthPhone);
        form.$("[data-test-id = 'agreement']").click();
        form.$("form button.button").click();

        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + date), Duration.ofSeconds(5))
                .shouldBe(Condition.visible);
    }


    @Test
    void shouldTestOrderCheckboxOff() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String date = DataGenerator.generateDate(3);

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        form.$("form button.button").click();

        $("[data-test-id='agreement'] [class='checkbox__text']").shouldHave(exactText("Я соглашаюсь с" +
                " условиями обработки и использования моих персональных данных"));
    }

    @Test
    void shouldTestReplanOrder() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String date = DataGenerator.generateDate(3);
        String replanDate = DataGenerator.generateDate(5);

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        form.$("form button.button").click();

        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + date), Duration.ofSeconds(5))
                .shouldBe(Condition.visible);
        refresh();

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(replanDate);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        form.$("form button.button").click();
        $("[data-test-id=replan-notification] .notification__content button").click();

        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + replanDate), Duration.ofSeconds(5))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldTestReplanOrderBackToTime() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String date = DataGenerator.generateDate(5);
        String replanDate = DataGenerator.generateDate(3);

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        form.$("form button.button").click();

        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + date), Duration.ofSeconds(5))
                .shouldBe(Condition.visible);
        refresh();

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(replanDate);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        form.$("form button.button").click();
        $("[data-test-id=replan-notification] .notification__content button").click();

        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + replanDate), Duration.ofSeconds(5))
                .shouldBe(Condition.visible);
    }

    @Test
    void shouldTestReplanOrderBackToPast() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String date = DataGenerator.generateDate(3);
        String replanDate = DataGenerator.generateDate(-1);

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        form.$("form button.button").click();

        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + date), Duration.ofSeconds(5))
                .shouldBe(Condition.visible);
        refresh();

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(replanDate);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        form.$("form button.button").click();

        $("[data-test-id='date'] [class='input__sub']").shouldHave(exactText("Заказ на выбранную дату" +
                " невозможен"));
    }

    @Test
    void shouldTestReplanOrderBackToToday() {
        SelenideElement form = $(".form");
        User user = DataGenerator.Registration.generateUser("ru");
        String date = DataGenerator.generateDate(3);
        String replanDate = DataGenerator.generateDate(0);

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(date);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        form.$("form button.button").click();

        $("[data-test-id=success-notification] .notification__content")
                .shouldHave(Condition.text("Встреча успешно запланирована на " + date), Duration.ofSeconds(5))
                .shouldBe(Condition.visible);
        refresh();

        form.$("[data-test-id = 'city'] input").setValue(user.getCity());
        form.$("[data-test-id = 'date'] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        form.$("[data-test-id = 'date'] input").setValue(replanDate);
        form.$("[data-test-id = 'name'] input").setValue(user.getName());
        form.$("[data-test-id = 'phone'] input").setValue(user.getPhone());
        form.$("[data-test-id = 'agreement']").click();
        form.$("form button.button").click();

        $("[data-test-id='date'] [class='input__sub']").shouldHave(exactText("Заказ на выбранную дату" +
                " невозможен"));
    }
}