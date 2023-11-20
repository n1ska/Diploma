package org.n1ska;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.n1ska.page.MainPage;
import org.n1ska.utils.DataBaseHelper;
import org.n1ska.utils.DataGenerator;
import org.n1ska.utils.PlasticCard;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.*;


class MainTest {
    private PlasticCard plasticValidCard;
    private final String ValidPlasicCardNo = "4444 4444 4444 4441";
    private String dataBaseUrl;
    private String dataBaseUserName;
    private String dataBaseUserPass;

    @BeforeEach
    void setup() {
        readCurrentDataBaseSettings();
        open("http://localhost:8080");
        plasticValidCard = generateValidCard();
    }

    public void readCurrentDataBaseSettings() {
        Properties props = new Properties();
        try {
            try (var inStream = new FileInputStream("artifacts/application.properties")) {
                props.load(inStream);
                dataBaseUrl = props.getProperty("spring.datasource.url");
                dataBaseUserName = props.getProperty("spring.datasource.username");
                dataBaseUserPass = props.getProperty("spring.datasource.password");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private PlasticCard generateValidCard() {
        int expiryYear = DataGenerator.generateCardExpiryYearValid();
        var card = new PlasticCard();
        card.setCardNo(ValidPlasicCardNo);
        card.setExpiryMonth(String.format("%02d", DataGenerator.generateCardExpiryMonthValid(expiryYear)));
        card.setExpiryYear(String.valueOf(expiryYear).substring(2));
        card.setHolderName(DataGenerator.generateCardHolderName());
        card.setPassCode(String.valueOf(DataGenerator.generateCardPassCodeValid()));
        return card;
    }

    @Test
    void buyUI_CheckNotValidExpiryMonth_ReturnUnderlineMessage() {
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        plasticValidCard.setExpiryMonth("14");
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        form.getCardExpiryMonthInput().shouldBeAndHaveText("Неверно указан срок действия карты");
    }

    @Test
    void buyUI_CheckNotValidFormatExpiryMonth_ReturnUnderlineMessage() {
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        plasticValidCard.setExpiryMonth("4");
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        form.getCardExpiryMonthInput().shouldBeAndHaveText("Неверный формат");
    }

    @Test
    void buyUI_CheckNotValidExpiryYear_ReturnUnderlineMessage() {
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        plasticValidCard.setExpiryYear(String.valueOf(DataGenerator.generateCardExpiryYearNoValid()));
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        form.getCardExpiryYearInput().shouldBeAndHaveText("Истёк срок действия карты");
    }

    @Test
    void buyUI_CheckNotValidFormatExpiryYear_ReturnUnderlineMessage() {
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        plasticValidCard.setExpiryYear("1");
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        form.getCardExpiryYearInput().shouldBeAndHaveText("Неверный формат");
    }

    @Test
    void buyUI_CheckNotValidFormatPassCode_ReturnUnderlineMessage() {
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        plasticValidCard.setPassCode(String.valueOf(DataGenerator.generateCardPassCodeNotValid()));
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        form.getCardPassCodeInput().shouldBeAndHaveText("Неверный формат");
    }

    @Test
    void buyUI_CheckEmptyCardHolder_ReturnUnderlineMessage() {
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        plasticValidCard.setHolderName("");
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        form.getCardHolderInput().shouldBeAndHaveText("Поле обязательно для заполнения");
    }

    @Test
    void buyUI_CheckCardRejectFromBankGate_ReturnUnderlineMessage() {
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        plasticValidCard.setCardNo(DataGenerator.generateCardNo());
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        page.notificationErrorVisibleAndContains("Ошибка! Банк отказал в проведении операции.");
    }

    @Test
    void buyUI_CheckCardSuccessFromBankGate_ReturnSuccessMessage() {
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        plasticValidCard.setCardNo(ValidPlasicCardNo);
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        page.notificationSuccessVisibleAndContains("Операция одобрена Банком.");
    }

    @Test
    void buyCreditUI_CheckNotValidExpiryMonth_ReturnUnderlineMessage() {
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        plasticValidCard.setExpiryMonth("14");
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        form.getCardExpiryMonthInput().shouldBeAndHaveText("Неверно указан срок действия карты");
    }

    @Test
    void buyCreditUI_CheckNotValidFormatExpiryMonth_ReturnUnderlineMessage() {
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        plasticValidCard.setExpiryMonth("4");
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        form.getCardExpiryMonthInput().shouldBeAndHaveText("Неверный формат");
    }

    @Test
    void buyCreditUI_CheckNotValidExpiryYear_ReturnUnderlineMessage() {
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        plasticValidCard.setExpiryYear(String.valueOf(DataGenerator.generateCardExpiryYearNoValid()));
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        form.getCardExpiryYearInput().shouldBeAndHaveText("Истёк срок действия карты");
    }

    @Test
    void buyCreditUI_CheckNotValidFormatExpiryYear_ReturnUnderlineMessage() {
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        plasticValidCard.setExpiryYear("1");
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        form.getCardExpiryYearInput().shouldBeAndHaveText("Неверный формат");
    }

    @Test
    void buyCreditUI_CheckNotValidFormatPassCode_ReturnUnderlineMessage() {
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        plasticValidCard.setPassCode(String.valueOf(DataGenerator.generateCardPassCodeNotValid()));
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        form.getCardPassCodeInput().shouldBeAndHaveText("Неверный формат");
    }

    @Test
    void buyCreditUI_CheckEmptyCardHolder_ReturnUnderlineMessage() {
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        plasticValidCard.setHolderName("");
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        form.getCardHolderInput().shouldBeAndHaveText("Поле обязательно для заполнения");
    }

    @Test
    void buyCreditUI_CheckCardRejectFromBankGate_ReturnUnderlineMessage() {
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        plasticValidCard.setCardNo(DataGenerator.generateCardNo());
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        page.notificationErrorVisibleAndContains("Ошибка! Банк отказал в проведении операции.");
    }

    @Test
    void buyCreditUI_CheckCardSuccessFromBankGate_ReturnSuccessMessage() {
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        plasticValidCard.setCardNo(ValidPlasicCardNo);
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        page.notificationSuccessVisibleAndContains("Операция одобрена Банком.");
    }

    @Test
    void buyCredit_CheckExistingApprovedRecordsInDatabase() {
        DataBaseHelper.deleteAllCreditRequestEntityTableRecords(dataBaseUrl, dataBaseUserName, dataBaseUserPass);

        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        plasticValidCard.setCardNo(ValidPlasicCardNo);
        form.setCard(plasticValidCard);
        form.clickContinueButton();
        page.notificationSuccessVisibleAndContains("Операция одобрена Банком.");

        int countRecords = DataBaseHelper.getCountApprovedCreditRequestEntities(dataBaseUrl, dataBaseUserName, dataBaseUserPass);
        Assertions.assertEquals(1, countRecords);
    }

    @Test
    void buy_CheckExistingApprovedRecordsInDatabase() {
        DataBaseHelper.deleteAllPaymentEntityTableRecords(dataBaseUrl, dataBaseUserName, dataBaseUserPass);

        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        plasticValidCard.setCardNo(ValidPlasicCardNo);
        form.setCard(plasticValidCard);
        form.clickContinueButton();
        page.notificationSuccessVisibleAndContains("Операция одобрена Банком.");

        int countRecords = DataBaseHelper.getCountApprovedPaymentEntities(dataBaseUrl, dataBaseUserName, dataBaseUserPass);
        Assertions.assertEquals(1, countRecords);
    }
}