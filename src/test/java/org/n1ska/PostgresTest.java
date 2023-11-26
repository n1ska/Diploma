package org.n1ska;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.n1ska.page.MainPage;
import org.n1ska.utils.DataBaseHelper;
import org.n1ska.utils.DataGenerator;
import org.n1ska.utils.PlasticCard;

import static com.codeborne.selenide.Selenide.*;

class PostgresTest {

    @BeforeEach
    public void setup(){
        open("http://localhost:8082");
    }

    @Test
    void expiryMonth_PutNotValidValue_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthNoValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameValid(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.ExistCaptionForExpiryMonthInput("Неверно указан срок действия карты");
    }
    @Test
    void expiryMonth_PutNotValidFormatValue_ReturnInvalidFormatUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthNoValidFormat(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameValid(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.ExistCaptionForExpiryMonthInput("Неверный формат");
    }
    @Test
    void expiryMonth_PutEmptyValue_ReturnMandatoryUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                "",
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameValid(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.ExistCaptionForExpiryMonthInput("Поле обязательно для заполнения");
    }
    @Test
    void expiryMonth_PutZeroValue_ReturnInvalidFormatUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                "0",
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameValid(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.ExistCaptionForExpiryMonthInput("Неверный формат");
    }

    //Expiry Year
    @Test
    void expiryYear_PutNotValidValue_ReturnExpiryCardViolationUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearNoValid(),
                DataGenerator.generateCardHolderNameValid(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.ExistCaptionForExpiryYearInput("Истёк срок действия карты");
    }
    @Test
    void expiryYear_PutNotValidFormatValue_ReturnInvalidFormatUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearNoValidFormat(),
                DataGenerator.generateCardHolderNameValid(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.ExistCaptionForExpiryYearInput("Неверный формат");
    }
    @Test
    void expiryYear_PutEmptyValue_ReturnMandatoryUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                "",
                DataGenerator.generateCardHolderNameValid(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.ExistCaptionForExpiryYearInput("Поле обязательно для заполнения");
    }
    @Test
    void expiryYear_PutZeroValue_ReturnInvalidFormatUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                "0",
                DataGenerator.generateCardHolderNameValid(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.ExistCaptionForExpiryYearInput("Неверный формат");
    }

    //Pass Code
    @Test
    void passCode_PutNotValidValue_ReturnInvalidFormatUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameValid(),
                DataGenerator.generateCardPassCodeNotValid());
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.ExistCaptionForPassCodeInput("Неверный формат");
    }
    @Test
    void passCode_PutZeroValue_ReturnInvalidFormatUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameValid(),
                "0");
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.ExistCaptionForPassCodeInput("Неверный формат");
    }
    @Test
    void passCode_PutEmptyValue_ReturnMandatoryUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameValid(),
                "");
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.ExistCaptionForPassCodeInput("Поле обязательно для заполнения");
    }

    //Card Holder
    @Test
    void cardHolder_PutEmptyValue_ReturnMandatoryUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                "",
                DataGenerator.generateCardPassCodeValid());

        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.ExistCaptionForHolderInput("Поле обязательно для заполнения");
    }
    @Test
    void cardHolder_PutCyrillicValue_ReturnInvalidFormatUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameCyrillic(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.ExistCaptionForHolderInput("Неверный формат");
    }
    @Test
    void cardHolder_PutSpecialCharactersValue_ReturnInvalidFormatUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameSpecialCharacters(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.ExistCaptionForHolderInput("Неверный формат");
    }

    //Card Number
    @Test
    void cardNumber_PutNotValidValue_ReturnRejectBankOperationUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberNotValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameValid(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        page.notificationErrorVisibleAndContains("Ошибка! Банк отказал в проведении операции.");
    }
    @Test
    void cardNumber_PutValidValue_ReturnSuccessBankOperationUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameValid(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        page.notificationSuccessVisibleAndContains("Операция одобрена Банком.");
    }

    //Database
    @Test
    void database_SendValidCardUI_ReturnShouldBeApprovedOneRecordForPaymentTableInDb() {
        DataBaseHelper.deleteAllPaymentEntityTableRecordsPostgres();
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameValid(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickBuyButtonAndReturnPage();
        form.setCard(card);
        form.clickContinueButton();
        page.notificationSuccessVisibleAndContains("Операция одобрена Банком.");

        int countRecords = DataBaseHelper.getCountApprovedPaymentEntitiesPostgres();
        Assertions.assertEquals(1, countRecords);
    }

    //Credit

    @Test
    void expiryMonth_PutNotValidValueForCreditPage_ReturnUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthNoValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameValid(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.ExistCaptionForExpiryMonthInput("Неверно указан срок действия карты");
    }
    @Test
    void expiryMonth_PutNotValidFormatValueForCreditPage_ReturnInvalidFormatUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthNoValidFormat(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameValid(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.ExistCaptionForExpiryMonthInput("Неверный формат");
    }
    @Test
    void expiryMonth_PutEmptyValueForCreditPage_ReturnMandatoryUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                "",
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameValid(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.ExistCaptionForExpiryMonthInput("Поле обязательно для заполнения");
    }
    @Test
    void expiryMonth_PutZeroValueForCreditPage_ReturnInvalidFormatUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                "0",
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameValid(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.ExistCaptionForExpiryMonthInput("Неверный формат");
    }

    //Expiry Year
    @Test
    void expiryYear_PutNotValidValueForCreditPage_ReturnExpiryCardViolationUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearNoValid(),
                DataGenerator.generateCardHolderNameValid(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.ExistCaptionForExpiryYearInput("Истёк срок действия карты");
    }
    @Test
    void expiryYear_PutNotValidFormatValueForCreditPage_ReturnInvalidFormatUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearNoValidFormat(),
                DataGenerator.generateCardHolderNameValid(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.ExistCaptionForExpiryYearInput("Неверный формат");
    }
    @Test
    void expiryYear_PutEmptyValueForCreditPage_ReturnMandatoryUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                "",
                DataGenerator.generateCardHolderNameValid(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.ExistCaptionForExpiryYearInput("Поле обязательно для заполнения");
    }
    @Test
    void expiryYear_PutZeroValueForCreditPage_ReturnInvalidFormatUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                "0",
                DataGenerator.generateCardHolderNameValid(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.ExistCaptionForExpiryYearInput("Неверный формат");
    }

    //Pass Code
    @Test
    void passCode_PutNotValidValueForCreditPage_ReturnInvalidFormatUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameValid(),
                DataGenerator.generateCardPassCodeNotValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.ExistCaptionForPassCodeInput("Неверный формат");
    }
    @Test
    void passCode_PutZeroValueForCreditPage_ReturnInvalidFormatUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameValid(),
                "0");
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.ExistCaptionForPassCodeInput("Неверный формат");
    }
    @Test
    void passCode_PutEmptyValueForCreditPage_ReturnMandatoryUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameValid(),
                "");
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.ExistCaptionForPassCodeInput("Поле обязательно для заполнения");
    }

    //Card Holder
    @Test
    void cardHolder_PutEmptyValueForCreditPage_ReturnMandatoryUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                "",
                DataGenerator.generateCardPassCodeValid());

        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.ExistCaptionForHolderInput("Поле обязательно для заполнения");
    }
    @Test
    void cardHolder_PutCyrillicValueForCreditPage_ReturnInvalidFormatUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameCyrillic(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.ExistCaptionForHolderInput("Неверный формат");
    }
    @Test
    void cardHolder_PutSpecialCharactersValueForCreditPage_ReturnInvalidFormatUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameSpecialCharacters(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        form.ExistCaptionForHolderInput("Неверный формат");
    }

    //Card Number
    @Test
    void cardNumber_PutNotValidValueForCreditPage_ReturnRejectBankOperationUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberNotValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameValid(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        page.notificationErrorVisibleAndContains("Ошибка! Банк отказал в проведении операции.");
    }
    @Test
    void cardNumber_PutValidValueForCreditPage_ReturnSuccessBankOperationUnderlineMessage() {
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameValid(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);

        form.clickContinueButton();

        page.notificationSuccessVisibleAndContains("Операция одобрена Банком.");
    }

    //Database
    @Test
    void database_SendValidCardUI_ReturnShouldBeApprovedOneRecordForCreditTableInDb() {
        DataBaseHelper.deleteAllCreditRequestEntityTableRecordsPostgres();
        var card = new PlasticCard(DataGenerator.generateCardNumberValid(),
                DataGenerator.generateCardExpiryMonthValid(),
                DataGenerator.generateCardExpiryYearValid(),
                DataGenerator.generateCardHolderNameValid(),
                DataGenerator.generateCardPassCodeValid());
        var page = new MainPage();
        var form = page.clickCreditBuyButtonAndReturnPage();
        form.setCard(card);
        form.clickContinueButton();
        page.notificationSuccessVisibleAndContains("Операция одобрена Банком.");

        int countRecords = DataBaseHelper.getCountApprovedCreditRequestEntitiesPostgres();
        Assertions.assertEquals(1, countRecords);
    }
}