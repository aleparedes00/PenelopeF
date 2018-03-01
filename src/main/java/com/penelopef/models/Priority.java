package com.penelopef.models;

import static com.penelopef.tools.ScannerTools.scanInt;

public enum Priority {
    HIGH(1, "High"),
    NORMAL(0, "Normal"),
    LOW(-1, "Low");

    int value; // Allows for simple test on sign: > 0 -> high, < 0 -> low
    String name;

    Priority(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static Priority selectPriority() {
        System.out.println("Select a priority:");
        for (Priority priority : Priority.values())
            System.out.println((priority.ordinal() + 1) + ".- " + priority);
        return optionsPriority(scanInt(1, Priority.values().length));
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

    @Override
    public String toString() {
        return name;
    }
}
