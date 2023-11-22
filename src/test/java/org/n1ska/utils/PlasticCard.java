package org.n1ska.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Value;

@Value
public class PlasticCard {
    String cardNo;
    String expiryMonth;
    String expiryYear;
    String holderName;
    String passCode;
}