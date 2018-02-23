package models;

public enum Priority {
    HIGH(1, "high"),
    NORMAL(0, "normal"),
    LOW(-1, "low");

    int value; // Allows for simple test on sign: > 0 -> high, < 0 -> low
    String name;

    Priority(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static Priority optionsPriority(int index) {
        switch (index){
            case 1:
                return HIGH;
            case 2:
                return NORMAL;
            case 3:
                return LOW;
        }
        throw new IllegalStateException("Selection doesn't exist");
    }
    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
