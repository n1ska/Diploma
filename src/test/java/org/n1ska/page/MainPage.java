package org.n1ska.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    public final String BuyButtonCaption = "Купить";
    public final String CreditBuyButtonCaption = "Купить в кредит";

    public MainPage() {

    }

    public BuyPage clickBuyButtonAndReturnPage() {
        var buttons = $$(".button");
        for (SelenideElement button : buttons) {
            var buttonText = button.find(".button__text").getText();
            if (buttonText.equalsIgnoreCase(BuyButtonCaption)){
                button.click();
            }
        }

        return new BuyPage();
    }

    public CreditByPage clickCreditBuyButtonAndReturnPage() {
        var buttons = $$(".button");
        for (SelenideElement button : buttons) {
            var buttonText = button.find(".button__text").getText();
            if (buttonText.equalsIgnoreCase(CreditBuyButtonCaption)){
                button.click();
            }
        }

        return new CreditByPage();
    }

    public void notificationSuccessVisibleAndContains(String text) {
        $(".notification_status_ok .notification__content").shouldBe(Condition.visible, Duration.ofSeconds(15)).shouldHave(Condition.text(text));
    }

    public void notificationErrorVisibleAndContains(String text) {
        $(".notification_status_error .notification__content").shouldBe(Condition.visible, Duration.ofSeconds(15)).shouldHave(Condition.text(text));
    }

}
