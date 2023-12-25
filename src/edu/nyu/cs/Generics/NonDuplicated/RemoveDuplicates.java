package edu.nyu.cs.Generics.NonDuplicated;

import java.util.ArrayList;
import java.util.Arrays;

public class RemoveDuplicates {
    
    public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {
        if (list.size() == 0 || list.size() == 1) return list;

        ArrayList<E> newList = new ArrayList<>();
        for (E o : list) {
            if (!newList.contains(o)) newList.add(o);
        }

        return newList;
    }

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("party", "Tomorrow", "Friday", "cereal", "14",
         "real","pizza", "party", "Friday", "Thursday", "weekends", "14", "party"));
        
        System.out.println(removeDuplicates(list));
    }

}
