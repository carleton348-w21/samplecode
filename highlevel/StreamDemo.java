import java.util.ArrayList;
import java.util.stream.IntStream;

public class StreamDemo {
    public static int total = 0;

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
         // Intermediaries generate more streams
         // Terminals consume streams and generate a result
         // long before = System.currentTimeMillis();
         // IntStream.range(0, 100_000_000)
         //     .mapToDouble(e -> Math.sin(e))
         //     .reduce(0, (a,b) -> a + b);
         long before = System.currentTimeMillis();
         total = 0;
         IntStream.range(0, 1000)
             .parallel()
             .mapToDouble(e -> {
                     total = total + e;
                     return 0.0;
                 })

             .reduce(0, (a,b) -> a + b);

         System.out.println(total);
         System.out.println("Time = " + (System.currentTimeMillis() - before));

     }
 }
