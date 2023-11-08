package org.n1ska.utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class PageInput {
    private SelenideElement root;
    private SelenideElement input;
    private String caption;

    public PageInput(SelenideElement root, String caption) {
        this.root = root;
        this.input = root.find("input");
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

    public void setValue(String value) {
        input.setValue(value);
    }

    public void shouldBeAndHaveText(String text) {
        root.find(".input__sub").shouldBe(Condition.exist).shouldHave(Condition.text(text));
    }
}
