package models;

public enum Priority {
    NORMAL(0, "normal"),
    HIGH(1, "high"),
    LOW(-1, "low");

    int value; // Allows for simple test on sign: > 0 -> high, < 0 -> low
    String name;

    Priority(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
