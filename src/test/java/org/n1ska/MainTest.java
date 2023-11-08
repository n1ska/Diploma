package org.n1ska;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.n1ska.utils.DataGenerator;
import org.n1ska.page.TravelDayPage;
import org.n1ska.utils.PlasticCard;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
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
        var page = new TravelDayPage();
        var form = page.clickBuyButtonAndReturnForm();
        plasticValidCard.setExpiryMonth("14");
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        form.getCardExpiryMonthInput().shouldBeAndHaveText("Неверно указан срок действия карты");
    }

    @Test
    void buyUI_CheckNotValidFormatExpiryMonth_ReturnUnderlineMessage() {
        var page = new TravelDayPage();
        var form = page.clickBuyButtonAndReturnForm();
        plasticValidCard.setExpiryMonth("4");
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        form.getCardExpiryMonthInput().shouldBeAndHaveText("Неверный формат");
    }

    @Test
    void buyUI_CheckNotValidExpiryYear_ReturnUnderlineMessage() {
        var page = new TravelDayPage();
        var form = page.clickBuyButtonAndReturnForm();
        plasticValidCard.setExpiryYear(String.valueOf(DataGenerator.generateCardExpiryYearNoValid()));
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        form.getCardExpiryYearInput().shouldBeAndHaveText("Истёк срок действия карты");
    }

    @Test
    void buyUI_CheckNotValidFormatExpiryYear_ReturnUnderlineMessage() {
        var page = new TravelDayPage();
        var form = page.clickBuyButtonAndReturnForm();
        plasticValidCard.setExpiryYear("1");
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        form.getCardExpiryYearInput().shouldBeAndHaveText("Неверный формат");
    }

    @Test
    void buyUI_CheckNotValidFormatPassCode_ReturnUnderlineMessage() {
        var page = new TravelDayPage();
        var form = page.clickBuyButtonAndReturnForm();
        plasticValidCard.setPassCode(String.valueOf(DataGenerator.generateCardPassCodeNotValid()));
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        form.getCardPassCodeInput().shouldBeAndHaveText("Неверный формат");
    }

    @Test
    void buyUI_CheckEmptyCardHolder_ReturnUnderlineMessage() {
        var page = new TravelDayPage();
        var form = page.clickBuyButtonAndReturnForm();
        plasticValidCard.setHolderName("");
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        form.getCardHolderInput().shouldBeAndHaveText("Поле обязательно для заполнения");
    }

    @Test
    void buyUI_CheckCardRejectFromBankGate_ReturnUnderlineMessage() {
        var page = new TravelDayPage();
        var form = page.clickBuyButtonAndReturnForm();
        plasticValidCard.setCardNo(DataGenerator.generateCardNo());
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        page.notificationErrorVisibleAndContains("Ошибка! Банк отказал в проведении операции.");
    }

    @Test
    void buyUI_CheckCardSuccessFromBankGate_ReturnSuccessMessage() {
        var page = new TravelDayPage();
        var form = page.clickBuyButtonAndReturnForm();
        plasticValidCard.setCardNo(ValidPlasicCardNo);
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        page.notificationSuccessVisibleAndContains("Операция одобрена Банком.");
    }

    @Test
    void buyCreditUI_CheckNotValidExpiryMonth_ReturnUnderlineMessage() {
        var page = new TravelDayPage();
        var form = page.clickCreditBuyButtonAndReturnForm();
        plasticValidCard.setExpiryMonth("14");
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        form.getCardExpiryMonthInput().shouldBeAndHaveText("Неверно указан срок действия карты");
    }

    @Test
    void buyCreditUI_CheckNotValidFormatExpiryMonth_ReturnUnderlineMessage() {
        var page = new TravelDayPage();
        var form = page.clickCreditBuyButtonAndReturnForm();
        plasticValidCard.setExpiryMonth("4");
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        form.getCardExpiryMonthInput().shouldBeAndHaveText("Неверный формат");
    }

    @Test
    void buyCreditUI_CheckNotValidExpiryYear_ReturnUnderlineMessage() {
        var page = new TravelDayPage();
        var form = page.clickCreditBuyButtonAndReturnForm();
        plasticValidCard.setExpiryYear(String.valueOf(DataGenerator.generateCardExpiryYearNoValid()));
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        form.getCardExpiryYearInput().shouldBeAndHaveText("Истёк срок действия карты");
    }

    @Test
    void buyCreditUI_CheckNotValidFormatExpiryYear_ReturnUnderlineMessage() {
        var page = new TravelDayPage();
        var form = page.clickCreditBuyButtonAndReturnForm();
        plasticValidCard.setExpiryYear("1");
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        form.getCardExpiryYearInput().shouldBeAndHaveText("Неверный формат");
    }

    @Test
    void buyCreditUI_CheckNotValidFormatPassCode_ReturnUnderlineMessage() {
        var page = new TravelDayPage();
        var form = page.clickCreditBuyButtonAndReturnForm();
        plasticValidCard.setPassCode(String.valueOf(DataGenerator.generateCardPassCodeNotValid()));
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        form.getCardPassCodeInput().shouldBeAndHaveText("Неверный формат");
    }

    @Test
    void buyCreditUI_CheckEmptyCardHolder_ReturnUnderlineMessage() {
        var page = new TravelDayPage();
        var form = page.clickCreditBuyButtonAndReturnForm();
        plasticValidCard.setHolderName("");
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        form.getCardHolderInput().shouldBeAndHaveText("Поле обязательно для заполнения");
    }

    @Test
    void buyCreditUI_CheckCardRejectFromBankGate_ReturnUnderlineMessage() {
        var page = new TravelDayPage();
        var form = page.clickCreditBuyButtonAndReturnForm();
        plasticValidCard.setCardNo(DataGenerator.generateCardNo());
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        page.notificationErrorVisibleAndContains("Ошибка! Банк отказал в проведении операции.");
    }

    @Test
    void buyCreditUI_CheckCardSuccessFromBankGate_ReturnSuccessMessage() {
        var page = new TravelDayPage();
        var form = page.clickCreditBuyButtonAndReturnForm();
        plasticValidCard.setCardNo(ValidPlasicCardNo);
        form.setCard(plasticValidCard);

        form.clickContinueButton();

        page.notificationSuccessVisibleAndContains("Операция одобрена Банком.");
    }

    @Test
    void buyCredit_CheckExistingApprovedRecordsInDatabase() {
        int countRecords = 0;

        try (Connection conn = DriverManager.getConnection(dataBaseUrl, dataBaseUserName, dataBaseUserPass);
             Statement stmt = conn.createStatement();) {
            stmt.executeUpdate("DELETE FROM credit_request_entity");

            var page = new TravelDayPage();
            var form = page.clickCreditBuyButtonAndReturnForm();
            plasticValidCard.setCardNo(ValidPlasicCardNo);
            form.setCard(plasticValidCard);
            form.clickContinueButton();
            page.notificationSuccessVisibleAndContains("Операция одобрена Банком.");

            try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM credit_request_entity WHERE STATUS='APPROVED'")) {
                if (rs.next()) {
                    countRecords = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(1, countRecords);
    }

    @Test
    void buy_CheckExistingApprovedRecordsInDatabase() {
        int countRecords = 0;

        try (Connection conn = DriverManager.getConnection(dataBaseUrl, dataBaseUserName, dataBaseUserPass);
             Statement stmt = conn.createStatement();) {
            stmt.executeUpdate("DELETE FROM payment_entity");

            var page = new TravelDayPage();
            var form = page.clickBuyButtonAndReturnForm();
            plasticValidCard.setCardNo(ValidPlasicCardNo);
            form.setCard(plasticValidCard);
            form.clickContinueButton();
            page.notificationSuccessVisibleAndContains("Операция одобрена Банком.");

            try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM payment_entity WHERE STATUS='APPROVED'")) {
                if (rs.next()) {
                    countRecords = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(1, countRecords);
    }
}