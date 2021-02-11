import java.util.ArrayList;
import java.util.stream.IntStream;

public class StreamDemo {
     public static void main(String[] args) {
         ArrayList<Integer> myList = new ArrayList<>();
         for (int i=0; i < 10; i++) {
             myList.add(i);
         }

         myList.stream().forEach(item -> System.out.print(item + " "));
         System.out.println();

         myList.stream()
             .parallel()
             .filter(e -> e % 2 == 0)
             .forEach(item -> System.out.print(item + " "));

         System.out.println();

         //IntStream.range(0,500).forEach(e -> System.out.print(e + " "));

         long before = System.currentTimeMillis();
         IntStream.range(0, 100_000_000)
             .parallel()
             .mapToDouble(e -> Math.sin(e))
             .reduce(0, (a,b) -> a + b);
         System.out.println("Time = " + (System.currentTimeMillis() - before));

     }
 }
