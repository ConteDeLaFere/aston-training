package step1.module1;

import java.util.List;

public final class ImmutableList {

    private final int size;
    private final List<Integer> integers;

    public ImmutableList(List<Integer> integers) {
        this.size = integers.size();
        this.integers = List.copyOf(integers);
    }

    public List<Integer> getIntegers() {
        return List.copyOf(integers);
    }

    public int getSize() {
        return size;
    }
}
