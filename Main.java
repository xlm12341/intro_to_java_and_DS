import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class Main {
    public static void main(String[] args) throws IOException,ClassNotFoundException {
        Item[] items = new Item[10];
        for (int i = 0; i < 10; i++) {
            items[i] = new Item(i);
        }
        List<Item> list =new ArrayList<>();
        Collections.addAll(list, items);
        System.out.println(list);
        System.out.println(Arrays.toString(items));;

        Collections.shuffle(list);
        System.out.println(list);

        int k = 0;
        for (Item i : list) {
            items[k] = i;
            k++;
        }
        System.out.println(Arrays.toString(items));;

        bucketSort(items);
        System.out.println(Arrays.toString(items));;
    }


        public static < E extends Item> void bucketSort(E[] list) {
            ArrayList[] bucket =  new ArrayList[10];
            for (E e : list) {
                int key = e.getKey();
                if (bucket[key] == null) {
                    bucket[key] = new ArrayList<E>();
                }
                bucket[key].add(e);
            }

            int k = 0;
            for (int i = 0; i < bucket.length; i++) {
                if (bucket[i] != null) {
                    for (int j = 0; j < bucket[i].size(); j++) {
                        list[k++] = (E) bucket[i].get(j);
                    }
                }
            }



    }

}
class Item {
    private Integer i;
    public Item(Integer i) {
        this.i = i;
    }
    public Integer getKey() {
        return this.i;
    }
    public String toString() {
        return Integer.toString(i);
    }
}


