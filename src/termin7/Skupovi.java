package termin7;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Skupovi {

    public static void main(String[] args) {
        Set<Integer> s = new HashSet<>(List.of(1,2,3,3));
        Set<Integer> e = new HashSet<>(s);
        System.out.println(e.size());
        s.add(5);
        System.out.println(e.size());
        Set.of(1,3,4);

        for (Integer i : s) {

        }

        s.add(1);
        s.add(3);
        s.add(3);
        s.add(4);
        System.out.println(s);

        List<Integer> x= new ArrayList<>();
        x.add(1);
        x.add(1);
        x.add(1);
        x.add(1);
        Set<Integer> xx = new HashSet<>(x);
        x.clear();
        x.addAll(xx);

        List<Integer> t = x;
    }
}
