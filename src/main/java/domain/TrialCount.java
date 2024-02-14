package domain;

public class TrialCount {

    private final int amount;

    public TrialCount(int amount) {
        validate(amount);

        this.amount = amount;
    }

    private void validate(int amount) {
        if (amount < 1) {
            throw new IllegalArgumentException("시도 횟수는 1 이상이어야 합니다");
        }
    }

    public void repeat(Runnable consumer) {
        for (int i = 0; i < amount; i++) {
            consumer.run();
        }
    }

    public int getAmount() {
        return amount;
    }
}