package org.example.finalproject.user.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankInformation {
    private String cardHolder;
    private String cardNumber;
    private String expiryMonth;
    private String expiryYear;
    private String cvc;

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) return true;
//        if (obj == null || getClass() != obj.getClass()) return false;
//        BankInformation that = (BankInformation) obj;
//        return cardHolder.equals(that.cardHolder) &&
//                cardNumber.equals(that.cardNumber) &&
//                expiryMonth.equals(that.expiryMonth) &&
//                expiryYear.equals(that.expiryYear) &&
//                cvc.equals(that.cvc);
//    }

}
