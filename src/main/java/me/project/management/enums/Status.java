package me.project.management.enums;

import java.util.Arrays;
import java.util.Optional;

public enum Status {

    PRE(0, "pre"), START(1, "start"), END(2, "end");

    private Integer value;
    private String text;

    Status(Integer value, String text) {
        this.value = value;
        this.text = text;
    }

    public Integer getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public static Optional<Status> getByValue(Integer value) {
        return Arrays.stream(Status.values())
                .filter(wallet -> wallet.getValue().equals(value))
                .findAny();
    }
}
