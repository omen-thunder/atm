package KingsATM.model;

public enum CashAmount {
    FIVECENT,
    TENCENT,
    TWENTYCENT,
    FIFTYCENT,
    ONEDOLLAR,
    TWODOLLAR,
    FIVEDOLLAR,
    TENDOLLAR,
    TWENTYDOLLAR,
    FIFTYDOLLAR,
    HUNDREDDOLLAR;

    public long getValue() {
        return switch (this) {
            case FIVECENT -> 5;
            case TENCENT -> 10;
            case TWENTYCENT -> 20;
            case FIFTYCENT -> 50;
            case ONEDOLLAR -> 100;
            case TWODOLLAR -> 200;
            case FIVEDOLLAR -> 500;
            case TENDOLLAR -> 1000;
            case TWENTYDOLLAR -> 2000;
            case FIFTYDOLLAR -> 5000;
            case HUNDREDDOLLAR -> 10000;
        };
    }

    public static CashAmount getEnum(long value) {
        return switch ((int) value) {
            case 5 -> FIVECENT;
            case 10 -> TENCENT;
            case 20 -> TWENTYCENT;
            case 50 -> FIFTYCENT;
            case 100 -> ONEDOLLAR;
            case 200 -> TWODOLLAR;
            case 500 -> FIVEDOLLAR;
            case 1000 -> TENDOLLAR;
            case 2000 -> TWENTYDOLLAR;
            case 5000 -> FIFTYDOLLAR;
            case 10000 -> HUNDREDDOLLAR;
            default -> throw new IllegalArgumentException("Cash amount doesn't exist.");
        };
    }
}
