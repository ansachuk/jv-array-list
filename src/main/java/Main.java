import core.basesyntax.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> firstlLst = new ArrayList<>();

        firstlLst.add(1);
        firstlLst.add(2);

        ArrayList<Integer> list = new ArrayList<>();

        list.addAll(firstlLst);

        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        list.add(11);
        list.add(12);
        list.add(13);
        list.add(14);
        list.add(15);
        list.add(16);
        list.add(5, 5);


    }
}
