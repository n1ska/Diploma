package org.n1ska;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.n1ska.page.MainPage;
import org.n1ska.utils.DataBaseHelper;
import org.n1ska.utils.DataGenerator;
import org.n1ska.utils.PlasticCard;
import static com.codeborne.selenide.Selenide.*;

class MainTest {
    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @Test
    void buyUI_CheckNotValidExpiryMonth_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthNoValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameCyrilic(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.getElement(form.CardExpiryMonthCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text("Неверно указан срок действия карты"));
    }

    @Test
    void buyUI_CheckNotValidZeroExpiryMonth_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                "0",
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameCyrilic(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.getElement(form.CardExpiryMonthCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text("Неверный формат"));
    }

    @Test
    void buyUI_CheckEmptyExpiryMonth_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                "",
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameCyrilic(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.getElement(form.CardExpiryMonthCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void buyUI_CheckNotValidFormatExpiryMonth_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthNoValidFormat(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameCyrilic(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.getElement(form.CardExpiryMonthCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text("Неверный формат"));
    }

    @Test
    void buyUI_CheckNotValidExpiryYear_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearNoValid(),
                DataGenerator.generateCardHolderNameCyrilic(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.getElement(form.CardExpiryYearCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text("Истёк срок действия карты"));
    }

    @Test
    void buyUI_CheckNotValidFormatExpiryYear_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearNoValidFormat(),
                DataGenerator.generateCardHolderNameCyrilic(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.getElement(form.CardExpiryYearCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text("Неверный формат"));
    }

    @Test
    void buyUI_CheckNotValidFormatZeroExpiryYear_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                "0",
                DataGenerator.generateCardHolderNameCyrilic(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.getElement(form.CardExpiryYearCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text("Неверный формат"));
    }


    @Test
    void buyUI_CheckEmptyExpiryYear_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                "",
                DataGenerator.generateCardHolderNameCyrilic(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.getElement(form.CardExpiryYearCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void buyUI_CheckNotValidFormatPassCode_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameCyrilic(),
                DataGenerator.generateCardPassCodeNotValidFormat());
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.getElement(form.CardPassCodeCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text("Неверный формат"));
    }

    @Test
    void buyUI_CheckNotValidZeroPassCode_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameCyrilic(),
                "0");
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.getElement(form.CardPassCodeCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text("Неверный формат"));
    }

    @Test
    void buyUI_CheckEmptyPassCode_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameCyrilic(),
                "");
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.getElement(form.CardPassCodeCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void buyUI_CheckEmptyCardHolder_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                "",
                DataGenerator.generateCardPassCodeValid());

        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.getElement(form.CardHolderCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void buyUI_CheckCardRejectFromBankGate_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberNotValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameCyrilic(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        page.notificationErrorVisibleAndContains("Ошибка! Банк отказал в проведении операции.");
    }

    @Test
    void buyUI_CheckCardSuccessFromBankGate_ReturnSuccessMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameCyrilic(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        page.notificationSuccessVisibleAndContains("Операция одобрена Банком.");
    }

    @Test
    void buyCreditUI_CheckNotValidExpiryMonth_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthNoValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameCyrilic(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.getInputElement(form.CardExpiryMonthCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text("Неверно указан срок действия карты"));
    }

    @Test
    void buyCreditUI_CheckNotValidZeroExpiryMonth_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                "0",
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameCyrilic(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.getInputElement(form.CardExpiryMonthCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text("Неверный формат"));
    }

    @Test
    void buyCreditUI_CheckEmptyExpiryMonth_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                "",
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameCyrilic(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.getInputElement(form.CardExpiryMonthCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void buyCreditUI_CheckNotValidFormatExpiryMonth_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthNoValidFormat(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameCyrilic(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.getInputElement(form.CardExpiryMonthCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text("Неверный формат"));
    }

    @Test
    void buyCreditUI_CheckNotValidExpiryYear_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearNoValid(),
                DataGenerator.generateCardHolderNameCyrilic(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.getInputElement(form.CardExpiryYearCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text("Истёк срок действия карты"));
    }

    @Test
    void buyCreditUI_CheckEmptyExpiryYear_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                "",
                DataGenerator.generateCardHolderNameCyrilic(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.getInputElement(form.CardExpiryYearCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void buyCreditUI_CheckZeroExpiryYear_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                "0",
                DataGenerator.generateCardHolderNameCyrilic(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.getInputElement(form.CardExpiryYearCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text("Неверный формат"));
    }

    @Test
    void buyCreditUI_CheckNotValidFormatExpiryYear_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearNoValidFormat(),
                DataGenerator.generateCardHolderNameCyrilic(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.getInputElement(form.CardExpiryYearCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text("Неверный формат"));
    }

    @Test
    void buyCreditUI_CheckNotValidFormatPassCode_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameCyrilic(),
                DataGenerator.generateCardPassCodeNotValidFormat());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.getInputElement(form.CardPassCodeCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text("Неверный формат"));
    }

    @Test
    void buyCreditUI_CheckEmptyPassCode_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameCyrilic(),
                "");
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.getInputElement(form.CardPassCodeCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void buyCreditUI_CheckNotValidZeroFormatPassCode_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameCyrilic(),
                "0");
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.getInputElement(form.CardPassCodeCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text("Неверный формат"));
    }
    @Test
    void buyCreditUI_CheckEmptyCardHolder_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                "",
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.getInputElement(form.CardHolderCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void buyCreditUI_CheckCardRejectFromBankGate_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberNotValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameCyrilic(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        page.notificationErrorVisibleAndContains("Ошибка! Банк отказал в проведении операции.");
    }

    @Test
    void buyCreditUI_CheckCardSuccessFromBankGate_ReturnSuccessMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameCyrilic(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        page.notificationSuccessVisibleAndContains("Операция одобрена Банком.");
    }

    @Test
    void buyCredit_CheckExistingApprovedRecordsInDatabase() {
        DataBaseHelper.deleteAllCreditRequestEntityTableRecords();
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameCyrilic(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);
        form.clickContinueButton();
        page.notificationSuccessVisibleAndContains("Операция одобрена Банком.");

        int countRecords = DataBaseHelper.getCountApprovedCreditRequestEntities();
        Assertions.assertEquals(1, countRecords);
    }

    @Test
    void buy_CheckExistingApprovedRecordsInDatabase() {
        DataBaseHelper.deleteAllPaymentEntityTableRecords();
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameCyrilic(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);
        form.clickContinueButton();
        page.notificationSuccessVisibleAndContains("Операция одобрена Банком.");

        int countRecords = DataBaseHelper.getCountApprovedPaymentEntities();
        Assertions.assertEquals(1, countRecords);
    }

    @Test
    void buyUI_CheckCardHolderEmptyValue_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                "",
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.getInputElement(form.CardHolderCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void buyUI_CheckCardHolderCyrilicValue_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameCyrilic(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        page.notificationSuccessVisibleAndContains("Операция одобрена Банком.");
    }

    @Test
    void buyUI_CheckCardHolderEngValue_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameEng(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        page.notificationSuccessVisibleAndContains("Операция одобрена Банком.");
    }

    @Test
    void buyUI_CheckCardHolderSymbolsValue_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameCharacters(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        page.notificationSuccessVisibleAndContains("Операция одобрена Банком.");
    }
}