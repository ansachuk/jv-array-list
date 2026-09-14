package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private int maxCapacity;
    private int size;
    private Object[] inner;

    public ArrayList() {
        this.inner = new Object[DEFAULT_CAPACITY];
        this.maxCapacity = DEFAULT_CAPACITY;
        this.size = 0;
    }

    @Override
    public void add(T value) {
        checkIfGrowingIsNeed();

        inner[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index > size || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't add element to index "
                    + index);
        } else if (index == size) {
            add(value);
        } else {
            Object[] prePart = Arrays.copyOfRange(inner, 0, index == 0 ? index + 1 : index);
            Object[] postPart = Arrays.copyOfRange(inner, index == 0 ? 0 : index - 1, inner.length);
            if (index == 0) {
                prePart[0] = value;
            } else {
                postPart[0] = value;
            }

            Object[] result = Arrays.copyOf(prePart, prePart.length + postPart.length);
            System.arraycopy(postPart, 0, result, prePart.length, postPart.length);

            inner = result;
            size++;
        }
    }

    @Override
    public void addAll(List<T> addList) {
        for (int i = 0; i < addList.size(); i++) {
            add(addList.get(i));
        }
    }

    @Override
    public T get(int index) {
        if (size < index + 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't reach element with index "
                    + index);
        }

        return (T) inner[index];
    }

    @Override
    public void set(T value, int index) {
        if (size < index + 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't reach element with index "
                    + index
                    + ". You need to add it first, or change the index ro correct one.");
        }

        inner[index] = value;
    }

    @Override
    public T remove(int index) {
        if (size < index + 1 || index < 0) {
            throw new ArrayListIndexOutOfBoundsException("Can't reach element with index "
                    + index
                    + ". You need to add it first, or change the index ro correct one.");
        }

        final T deletedElement = (T) inner[index];

        Object[] prePart = Arrays.copyOf(inner, index);
        Object[] postPart = Arrays.copyOfRange(inner, index + 1, inner.length);

        inner = Arrays.copyOf(prePart, maxCapacity);
        System.arraycopy(postPart, 0, inner, prePart.length, postPart.length);

        size--;

        return deletedElement;
    }

    @Override
    public T remove(T element) {
        int deletedElementIndex = -1;

        for (int i = 0; i < inner.length; i++) {
            if (Objects.equals(inner[i], element)) {
                deletedElementIndex = i;
                break;
            }
        }

        if (deletedElementIndex == -1) {
            throw new NoSuchElementException("Can't find"
                    + element
                    + " element");
        }

        T deletedElement = get(deletedElementIndex);

        remove(deletedElementIndex);

        return deletedElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void checkIfGrowingIsNeed() {
        if (maxCapacity - 1 == size) {
            grow();
        }
    }

    private void grow() {
        maxCapacity = maxCapacity + maxCapacity / 2;
        inner = Arrays.copyOf(inner, maxCapacity);
    }
}
