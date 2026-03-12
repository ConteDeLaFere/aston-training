package ru.aston.training.step1.module1;

import java.util.List;

public final class ImmutableList {

    private final int size;
    private final List<Integer> integers;

    public ImmutableList(List<Integer> integers) {
        this.integers = (integers == null) ? List.of() : List.copyOf(integers);
        this.size = this.integers.size();
    }

    public List<Integer> getIntegers() {
        return List.copyOf(integers);
    }

    public int getSize() {
        return size;
    }
}
