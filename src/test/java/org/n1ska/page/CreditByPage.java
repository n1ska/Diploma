package org.n1ska.page;

import com.codeborne.selenide.SelenideElement;
import org.n1ska.utils.PlasticCard;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditByPage {
    public final String CardNoCaption = "Номер карты";
    public final String CardExpiryMonthCaption = "Месяц";
    public final String CardExpiryYearCaption = "Год";
    public final String CardHolderCaption = "Владелец";
    public final String CardPassCodeCaption = "CVC/CVV";

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
        getInputElement(caption).find("input").setValue(value);
    }

    public SelenideElement getInputElement(String caption){
        var inputs = $$(".input__inner");
        for (SelenideElement item : inputs) {
            var captionElement = item.find(".input__top").getText();

            if (captionElement.equalsIgnoreCase(caption)){
                return item;
            }
        }
        return null;
    }


//    public void shouldBeAndHaveText(String text) {
//        root.find(".input__sub").shouldBe(Condition.exist).shouldHave(Condition.text(text));
//    }

//    public ArrayList<PageInput> extractInputControls() {
//        $("form[action='/']").shouldBe(Condition.exist);
//
//        var resultList = new ArrayList<PageInput>();
//        var inputs = $$(".input__inner");
//        for (SelenideElement item : inputs) {
//            var caption = item.find(".input__top").getText();
//            resultList.add(new PageInput(item, caption));
//        }
//
//        return resultList;
//    }
}
