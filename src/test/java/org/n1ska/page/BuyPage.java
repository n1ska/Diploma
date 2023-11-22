package org.n1ska.page;

import com.codeborne.selenide.SelenideElement;
import org.n1ska.utils.PlasticCard;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class BuyPage {
    public final String CardNoCaption = "Номер карты";
    public final String CardExpiryMonthCaption = "Месяц";
    public final String CardExpiryYearCaption = "Год";
    public final String CardHolderCaption = "Владелец";
    public final String CardPassCodeCaption = "CVC/CVV";

    public BuyPage() {
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
}
