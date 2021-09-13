package KingsATM.dto;

public class CardDtoReq {
    private String pin;

    public CardDtoReq() {
    }

    public CardDtoReq(String pin) {
        this.pin = pin;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
