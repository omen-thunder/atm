package KingsATM.dto;

import KingsATM.model.CashAmount;

public class CashStoreDto {

    private int num5c = 0;
    private int num10c = 0;
    private int num20c = 0;
    private int num50c = 0;
    private int num1 = 0;
    private int num2 = 0;
    private int num5 = 0;
    private int num10 = 0;
    private int num20 = 0;
    private int num50 = 0;
    private int num100 = 0;

    protected CashStoreDto() {
    }

    public CashStoreDto(int num5c, int num10c, int num20c, int num50c, int num1, int num2, int num5, int num10, int num20, int num50, int num100) {
        this.num5c = num5c;
        this.num10c = num10c;
        this.num20c = num20c;
        this.num50c = num50c;
        this.num1 = num1;
        this.num2 = num2;
        this.num5 = num5;
        this.num10 = num10;
        this.num20 = num20;
        this.num50 = num50;
        this.num100 = num100;
    }

    public long getTotalAmount() {
        return (num5c * 5 + num10c * 10 + num20c * 20 + num50c * 50 + num1 * 100 +
                num2 * 200 + num5 * 500 + num10 * 1000 + num20 * 2000 + num50 * 5000 + num100 * 10000);
    }

    public int getNum5c() {
        return num5c;
    }

    public int getNum10c() {
        return num10c;
    }

    public int getNum20c() {
        return num20c;
    }

    public int getNum50c() {
        return num50c;
    }

    public int getNum1() {
        return num1;
    }

    public int getNum2() {
        return num2;
    }

    public int getNum5() {
        return num5;
    }

    public int getNum10() {
        return num10;
    }

    public int getNum20() {
        return num20;
    }

    public int getNum50() {
        return num50;
    }

    public int getNum100() {
        return num100;
    }

    public void setNum(CashAmount cash, int amount) {
        switch (cash) {
            case FIVECENT -> this.num5c = amount;
            case TENCENT -> this.num10c = amount;
            case TWENTYCENT -> this.num20c = amount;
            case FIFTYCENT -> this.num50c = amount;
            case ONEDOLLAR -> this.num1 = amount;
            case TWODOLLAR -> this.num2 = amount;
            case FIVEDOLLAR -> this.num5 = amount;
            case TENDOLLAR -> this.num10 = amount;
            case TWENTYDOLLAR -> this.num20 = amount;
            case FIFTYDOLLAR -> this.num50 = amount;
            case HUNDREDDOLLAR -> this.num100 = amount;
        };
    }
}
