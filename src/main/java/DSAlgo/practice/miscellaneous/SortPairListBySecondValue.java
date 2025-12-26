package DSAlgo.practice.miscellaneous;

import java.util.*;

public class SortPairListBySecondValue {

    public static void main(String[] args) {

        List<List<Integer>> pairList = new ArrayList<>();
        pairList.add(Arrays.asList(1, 3));  // (1, 3)
        pairList.add(Arrays.asList(2, 2));  // (2, 2)
        pairList.add(Arrays.asList(4, 1));  // (4, 1)
        pairList.add(Arrays.asList(3, 4));  // (3, 4)

        pairList.sort(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return o1.get(1).compareTo(o2.get(1));
            }
        });

    }


}
