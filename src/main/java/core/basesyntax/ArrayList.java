package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private int currentCapacity;
    private int size;
    private Object[] inner;

    public ArrayList() {
        this.inner = new Object[DEFAULT_CAPACITY];
        this.currentCapacity = DEFAULT_CAPACITY;
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
        if (size < index || index < 0) {
            throwExceptionIfInvalidIndex("Can't add element to index "
                    + index);
        } else if (index == size) {
            add(value);
        } else {
            Object[][] separated = splitInnerArrayByIndex(index);
            Object[] prePart = separated[0];
            Object[] postPart = separated[1];

            Object[] result = new Object[prePart.length + postPart.length + 1];
            System.arraycopy(prePart, 0, result, 0, prePart.length);
            result[index] = value;
            System.arraycopy(postPart, 0, result, index + 1, postPart.length);

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
            throwExceptionIfInvalidIndex("Can't reach element with index "
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
            throwExceptionIfInvalidIndex("Can't reach element with index "
                    + index
                    + ". You need to add it first, or change the index ro correct one.");
        }

        final T deletedElement = (T) inner[index];

        Object[][] separated = splitInnerArrayByIndex(index);
        Object[] prePart = separated[0];
        Object[] postPart = separated[1];

        Object[] result = new Object[prePart.length + postPart.length - 1];
        System.arraycopy(prePart, 0, result, 0, prePart.length);
        System.arraycopy(postPart, 1, result, index, postPart.length - 1);

        inner = result;
        size--;

        return deletedElement;
    }

    @Override
    public T remove(T element) {
        int deletedElementIndex = -1;

        for (int i = 0; i < size; i++) {
            if (element == inner[i] || (element != null && element.equals(inner[i]))) {
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
        if (currentCapacity == size) {
            grow();
        }
    }

    private void grow() {
        currentCapacity = currentCapacity + (currentCapacity >> 1);
        Object[] newInner = new Object[currentCapacity];
        System.arraycopy(inner, 0, newInner, 0, inner.length);

        inner = newInner;
    }

    private void throwExceptionIfInvalidIndex(String message) {
        throw new ArrayListIndexOutOfBoundsException(message);
    }

    private Object[][] splitInnerArrayByIndex(int index) {
        Object[] prePart = new Object[index];
        Object[] postPart = new Object[inner.length - index];

        System.arraycopy(inner, 0, prePart, 0, index);
        System.arraycopy(inner, index, postPart, 0, inner.length - index);

        return new Object[][]{
                prePart, postPart
        };
    }
}
