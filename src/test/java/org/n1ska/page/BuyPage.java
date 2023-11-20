package org.n1ska.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.n1ska.utils.PageInput;
import org.n1ska.utils.PlasticCard;

import java.util.ArrayList;

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

    public PageInput getInput(ArrayList<PageInput> inputs, String caption) {
        for (var item : inputs) {
            if (item.getCaption().equalsIgnoreCase(caption)) {
                return item;
            }
        }
        return null;
    }

    public PageInput getCardNoInput(ArrayList<PageInput> inputs) {
        return getInput(inputs, CardNoCaption);
    }


    public PageInput getCardExpiryMonthInput(ArrayList<PageInput> inputs) {
        return getInput(inputs, CardExpiryMonthCaption);
    }

    public PageInput getCardExpiryMonthInput() {
        ArrayList<PageInput> inputs = extractInputControls();
        return getInput(inputs, CardExpiryMonthCaption);
    }

    public PageInput getCardExpiryYearInput(ArrayList<PageInput> inputs) {
        return getInput(inputs, CardExpiryYearCaption);
    }

    public PageInput getCardExpiryYearInput() {
        ArrayList<PageInput> inputs = extractInputControls();
        return getInput(inputs, CardExpiryYearCaption);
    }

    public PageInput getCardHolderInput(ArrayList<PageInput> inputs) {
        return getInput(inputs, CardHolderCaption);
    }

    public PageInput getCardHolderInput() {
        ArrayList<PageInput> inputs = extractInputControls();
        return getInput(inputs, CardHolderCaption);
    }

    public PageInput getCardPassCodeInput(ArrayList<PageInput> inputs) {
        return getInput(inputs, CardPassCodeCaption);
    }

    public PageInput getCardPassCodeInput() {
        ArrayList<PageInput> inputs = extractInputControls();
        return getInput(inputs, CardPassCodeCaption);
    }

    public void setCard(PlasticCard card) {
        ArrayList<PageInput> inputs = extractInputControls();
        getCardNoInput(inputs).setValue(card.getCardNo());
        getCardExpiryMonthInput(inputs).setValue(card.getExpiryMonth());
        getCardExpiryYearInput(inputs).setValue(card.getExpiryYear());
        getCardHolderInput(inputs).setValue(card.getHolderName());
        getCardPassCodeInput(inputs).setValue(card.getPassCode());
    }

    public void clickContinueButton() {
        $("form[action='/'] button").click();
    }

    public ArrayList<PageInput> extractInputControls() {
        $("form[action='/']").shouldBe(Condition.exist);

        var resultList = new ArrayList<PageInput>();
        var inputs = $$(".input__inner");
        for (SelenideElement item : inputs) {
            var caption = item.find(".input__top").getText();
            resultList.add(new PageInput(item, caption));
        }

        return resultList;
    }
}
