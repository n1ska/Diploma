package org.n1ska.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.n1ska.utils.PageButton;
import org.n1ska.utils.PageCardInputForm;

import java.time.Duration;
import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TravelDayPage {
    public final String BuyButtonCaption = "Купить";
    public final String CreditBuyButtonCaption = "Купить в кредит";
    private ArrayList<PageButton> extractedActionButtons;

    public TravelDayPage() {
        extractedActionButtons = extractActionButtons();
    }

    public PageCardInputForm clickBuyButtonAndReturnForm() {
        clickActionButton(extractedActionButtons, BuyButtonCaption);
        return new PageCardInputForm();
    }

    public PageCardInputForm clickCreditBuyButtonAndReturnForm() {
        clickActionButton(extractedActionButtons, CreditBuyButtonCaption);
        return new PageCardInputForm();
    }

    private void clickActionButton(ArrayList<PageButton> buttons, String buttonText) {
        for (PageButton pageButton : buttons) {
            if (pageButton.text().equalsIgnoreCase(buttonText)) {
                pageButton.click();
                break;
            }
        }
    }

    public void notificationSuccessVisibleAndContains(String text) {
        $(".notification_status_ok .notification__content").shouldBe(Condition.visible, Duration.ofSeconds(15)).shouldHave(Condition.text(text));
    }


    public void notificationErrorVisibleAndContains(String text) {
        $(".notification_status_error .notification__content").shouldBe(Condition.visible, Duration.ofSeconds(15)).shouldHave(Condition.text(text));
    }

    private ArrayList<PageButton> extractActionButtons() {
        var resultList = new ArrayList<PageButton>();

        var buttons = $$(".button");
        for (SelenideElement button : buttons) {
            var buttonText = button.find(".button__text").getText();
            resultList.add(new PageButton(button, buttonText));
        }

        return resultList;
    }
}
