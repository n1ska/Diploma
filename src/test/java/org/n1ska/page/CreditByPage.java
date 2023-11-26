package org.n1ska.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.n1ska.utils.PlasticCard;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditByPage {
    private final String CardNoCaption = "Номер карты";
    private final String CardExpiryMonthCaption = "Месяц";
    private final String CardExpiryYearCaption = "Год";
    private final String CardHolderCaption = "Владелец";
    private final String CardPassCodeCaption = "CVC/CVV";

    public CreditByPage() {

    }

    public void setCard(PlasticCard card) {
        setValue(CardNoCaption, card.getCardNo());
        setValue(CardExpiryMonthCaption, card.getExpiryMonth());
        setValue(CardExpiryYearCaption, card.getExpiryYear());
        setValue(CardHolderCaption, card.getHolderName());
        setValue(CardPassCodeCaption, card.getPassCode());
    }

    public void clickContinueButton() {
        $("form[action='/'] button").click();
    }

    private void setValue(String caption, String value) {
        getElement(caption).find("input").setValue(value);
    }

    public SelenideElement getElement(String caption){
        var inputs = $$(".input__inner");
        for (SelenideElement item : inputs) {
            var captionElement = item.find(".input__top").getText();

            if (captionElement.equalsIgnoreCase(caption)){
                return item;
            }
        }
        return null;
    }

    public void NotExistHolderNameInput()
    {
        getElement(CardExpiryMonthCaption)
                .find(".input__sub")
                .shouldNotBe(Condition.exist);
    }

    public void ContainsEmptyValueIntoPassCodeInput()
    {
        getElement(CardPassCodeCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text(""));
    }

    public void ExistCaptionForExpiryMonthInput(String message)
    {
        getElement(CardExpiryMonthCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text(message));
    }

    public void ExistCaptionForExpiryYearInput(String message)
    {
        getElement(CardExpiryYearCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text(message));
    }

    public void ExistCaptionForPassCodeInput(String message)
    {
        getElement(CardPassCodeCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text(message));
    }

    public void ExistCaptionForHolderInput(String message)
    {
        getElement(CardHolderCaption)
                .find(".input__sub")
                .shouldBe(Condition.exist)
                .shouldHave(Condition.text(message));
    }
}
