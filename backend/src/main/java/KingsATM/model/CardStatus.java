package KingsATM.model;

public enum CardStatus {
    INACTIVE,
    ACTIVE,
    BANNED,
    LOST,
    CONFISCATED,
    EXPIRED;

    public String getStatusString() {
        // 'The card has been...'
        return switch (this) {
            case LOST -> "lost";
            case ACTIVE -> "active";
            case BANNED -> "exceeded login attempts";
            case EXPIRED -> "past expiry date";
            case INACTIVE -> "set to inactive";
            case CONFISCATED -> "confiscated";
        };
    }
}
