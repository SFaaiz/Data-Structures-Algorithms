package hashmapAndHeaps;

import java.util.HashMap;
import java.util.PriorityQueue;

public class maxHeap {
    public static void main(String[] args) {
        HashMap<Character,Integer> map = new HashMap<>(); // freq map
        map.put('a',3);
        map.put('b',1);
        map.put('n',2);
        // sorting char on the basis of their freq in map
        PriorityQueue<Character> pq = new PriorityQueue<>((a,b) -> map.get(b) - map.get(a));
        pq.addAll(map.keySet());
        while (!pq.isEmpty()){
            System.out.println(pq.remove());
        }
    }
}
