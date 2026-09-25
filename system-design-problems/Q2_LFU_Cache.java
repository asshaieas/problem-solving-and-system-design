import java.util.*;

public class Main {
public static void main(String[] args) {
    LFUCache lfu = new LFUCache(2);
    lfu.put(1, 1);
    lfu.put(2, 2);
    System.out.println(lfu.get(1));  // expected 1
    lfu.put(3, 3);
    System.out.println(lfu.get(2));  // expected -1
    System.out.println(lfu.get(3));  // expected 3
    lfu.put(4, 4);
    System.out.println(lfu.get(1));  // expected -1
    System.out.println(lfu.get(3));  // expected 3
    System.out.println(lfu.get(4));  // expected 4
}
}
class LFUCache {
    // I need capacity and min frequently fields 
    int capacity;
    int minFreq;
    HashMap<Integer, Integer> values;
    HashMap<Integer, Integer> counts;
    HashMap<Integer, LinkedHashSet<Integer>> floors;

    public LFUCache(int capacity) {
      this.capacity = capacity;
      this.minFreq = 0; // this means no keys yet 
      values = new HashMap<>();
      counts = new HashMap<>();
      floors = new HashMap<>();
    }
    
    public int get(int key) {
      if (values.containsKey(key)){
        moveUp(key);
        return values.get(key);
      }
        return -1;
    }
    
    public void put(int key, int value) {
      if (values.containsKey(key)){
        // I need to update its value 
        values.put(key, value);
        moveUp(key);
        return;
      }
      if (values.size() == capacity) {
          int oldest = floors.get(minFreq).iterator().next(); // front of lowest floor
          floors.get(minFreq).remove(oldest);                 // remove from its floor
          values.remove(oldest);                              // remove its value
          counts.remove(oldest);                              // remove its counter
    }
    // adding the new key 
    values.put(key, value);
    counts.put(key, 1);
    if (!floors.containsKey(1)){
      floors.put(1, new LinkedHashSet<>());
    }
    floors.get(1).add(key);
    minFreq = 1;
    }
    private void moveUp(int key){
      int floor = counts.get(key);
      counts.put(key, floor + 1); // this will update its counter 
      floors.get(floor).remove(key); // leave the old floor 

      if (floor == minFreq && floors.get(floor).isEmpty()){
        minFreq += 1; 
      }
      if (!floors.containsKey(floor + 1)){
        floors.put(floor + 1, new LinkedHashSet<>());
      }
      floors.get(floor + 1).add(key); 
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */


 
