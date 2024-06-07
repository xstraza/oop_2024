package termin7;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Mape {

    public static void main(String[] args) {
//        Map<String, Integer> m = new HashMap<>();
//        m.put("a", 5);
//        Integer a1 = m.get("a");
//        a1 += 3;
//        m.put("a",a1);
//        m.getOrDefault(
//                "a",10
//        );


        List<Integer> y = List.of(1,2,4,5,6);
        List<Integer> yy = new ArrayList<>();

//        Set<Integer> collect = y.stream()
//        Map<Integer, String> collect = y.stream()
//                y.stream().map(x -> x + 2)
//                .map(x -> x * 2)
//                .filter(x -> x > 5)
//                .map(x -> x / 3)
//                .filter(x -> (x % 3) != 0)
//                .map(Mape::povecajZa2);
//        .forEach(x -> yy.add(x));
//                .collect(Collectors.toMap(
//                        x -> x + 2,
//                        x -> x.toString()
//                ));
//        collect.forEach(System.out::println);
    }

    private static int povecajZa2(int x, int y ) {
        return x+y;
    }
}
