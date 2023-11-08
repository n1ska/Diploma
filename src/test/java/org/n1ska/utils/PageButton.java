package org.n1ska.utils;

import com.codeborne.selenide.SelenideElement;

public class PageButton {
    private SelenideElement element;
    private String text;

    public PageButton(SelenideElement element, String text) {
        this.element = element;
        this.text = text;
    }

    public void click() {
        element.click();
    }

    public String text() {
        return text;
    }
}
