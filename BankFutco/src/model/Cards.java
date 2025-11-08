package model;

import java.math.BigDecimal;

public class Cards {

    private String cardNumber;
    private String Type; // Debit - Credit
    private BigDecimal totalLimit;
    private BigDecimal amountUsed;
    private BigDecimal available;


    public String getCardNumber() {
        return cardNumber;
    }
    public String getType() {
        return Type;
    }
    public BigDecimal getTotalLimit() {
        return totalLimit;
    }
    public BigDecimal getAmountUsed() {
        return amountUsed;
    }
    public BigDecimal getAvailable() {
        return available;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public void setType(String type) {
        Type = type;
    }
    public void setTotalLimit(BigDecimal totalLimit) {
        this.totalLimit = totalLimit;
    }
    public void setAmountUsed(BigDecimal amountUsed) {
        this.amountUsed = amountUsed;
    }
    public void setAvailable(BigDecimal available) {
        this.available = available;
    }
    @Override
    public String toString() {
        return "Cards [cardNumber=" + cardNumber + ", Type=" + Type + ", totalLimit=" + totalLimit + ", amountUsed="
                + amountUsed + ", available=" + available + "]";
    }

    
}
