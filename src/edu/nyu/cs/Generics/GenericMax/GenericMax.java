package edu.nyu.cs.Generics.GenericMax;

public class GenericMax {

    public static <E extends Comparable<E>> E max(E[] list) {
        E currentMax = list[0];

        for (int i = 1; i < list.length; i++) {
            if (currentMax.compareTo(list[i]) < 0) {
                currentMax = list[i];
            }
        }
        return currentMax;
    }

    public static void main(String[] args) {
        Double[] vals = {4400.1, 1203.3, 3100.0, 18700.8, 4100.6};
        Integer[] arr = {1, 124, 534, 64, 234};
        String[] strings = {"party", "Tomorrow", "Friday", "cereal", "14", "real","pizza", "party", "Friday", "Thursday", "weekends", "14", "party"};

        System.out.println(max(vals));
        System.out.println(max(arr));
        System.out.println(max(strings));
    }

}
