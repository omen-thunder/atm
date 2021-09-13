package KingsATM.dto;

public class LoginDto {
    private Integer cardNumber;
    private String cardPin;

    public LoginDto() {
    }

    public LoginDto(Integer cardNumber, String cardPin) {
        this.cardNumber = cardNumber;
        this.cardPin = cardPin;
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardPin() {
        return cardPin;
    }

    public void setCardPin(String cardPin) {
        this.cardPin = cardPin;
    }
}
