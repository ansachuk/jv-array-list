import core.basesyntax.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> firstList = new ArrayList<>();

        firstList.add(1);
        firstList.add(2);
        firstList.add(3);
        firstList.add(4);
        firstList.add(5);
        firstList.add(6);
        firstList.add(7);
        firstList.add(8);
        firstList.add(9);
        firstList.add(10);

        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(firstList);

        list.add(1, 1);

        list.size();
    }
}
