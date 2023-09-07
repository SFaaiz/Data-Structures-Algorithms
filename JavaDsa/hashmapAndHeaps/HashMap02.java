package hashmapAndHeaps;

import java.util.*;
import java.util.Arrays;
import java.util.HashMap;

public class HashMap02 {
     static HashMap<String, String> map = new HashMap<>(); // tiny - long url
    static String encode(String longUrl){
        StringBuilder sb = new StringBuilder();
        sb.append((char)Math.floor(Math.random()*100));
        while (map.containsKey(sb.toString())){
            sb.append((char)Math.floor(Math.random()*100));
        }
        map.put(sb.toString(),longUrl);
        return sb.toString();
    }

    static String decode(String tinyUrl){
        return map.get(tinyUrl);
    }

    static List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> ans = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>(); // x axis - maxHeightAt y axis
        int prev=0,i=0;
        for(int[] ar: buildings){
            for(i=ar[0]; i<=ar[1]; i++){
                int ht = ar[2];
                if(ht > map.getOrDefault(i,0)) {
                    map.put(i, ht);
                    int prv = map.getOrDefault(i-1,0);
                    if(prv > ht) map.put(i-1, ht);
                }
                prev = ht;
            }
        }
        map.put(i-1,0);
        prev=0;
        for(int j=0; j<i; j++){
            if(map.containsKey(j)){
                int ht = map.getOrDefault(j,0);
                if(ht != prev) ans.add(new ArrayList<>(Arrays.asList(j,ht)));
                prev = ht;
            }else{
                if(map.containsKey(j-1)){
                    ans.add(new ArrayList<>(Arrays.asList(j-1,0)));
                }
                prev = 0;
            }
        }
        return ans;
    }

    // implement insert, remove, getRandom() methods in O(1) tc
    class RandomizedSet {
        HashMap<Integer, Integer> map; // val- index in arraylist
        ArrayList<Integer> idx;
        Random r;
        public RandomizedSet() {
            map = new HashMap<>();
            idx = new ArrayList<>();
            r = new Random();
        }

        public boolean insert(int val) {
            if(map.containsKey(val)) return false;
            else {
                map.put(val,idx.size());
                idx.add(val);
                return true;
            }
        }

        public boolean remove(int val) {
            if(map.containsKey(val)){
                int index = map.get(val);
                map.remove(val);
                if(index != idx.size()-1){
                    int temp = idx.get(idx.size()-1);
                    idx.set(idx.size()-1, idx.get(index));
                    idx.set(index, temp);
                    map.put(temp, index);
                }
                idx.remove(idx.size()-1);
                return true;
            }else return false;
        }

        public int getRandom() {
            return idx.get(r.nextInt(idx.size()));
        }
    }

    // same as above but here duplicacy of elements is allowed
    class RandomizedCollection {
        HashMap<Integer, HashSet<Integer>> map; // val - indices stored in hashset
        ArrayList<Integer> idx;
        Random r;
        public RandomizedCollection() {
            map = new HashMap<>();
            idx = new ArrayList<>();
            r = new Random();
        }

        public boolean insert(int val) {
            boolean hasVal = false;
            if(map.containsKey(val)) hasVal = true;
            HashSet<Integer> hs = map.getOrDefault(val, new HashSet<Integer>());
            hs.add(idx.size());
            map.put(val, hs);
            idx.add(val);
            return !hasVal;
        }

        public boolean remove(int val) {
            if(!map.containsKey(val)) return false;
            HashSet<Integer> hs = map.get(val);
            int i=-1;
            for(int j : hs){
                i = j;
                break;
            }
            if(hs.size() == 1) map.remove(val);
            else hs.remove(i);
            if(i != idx.size()-1){
                int temp = idx.get(idx.size()-1);
                idx.set(idx.size()-1, idx.get(i));
                idx.set(i, temp);
                HashSet<Integer> set = map.get(temp);
                set.remove(idx.size()-1);
                set.add(i);
                map.put(temp, set);
            }
            idx.remove(idx.size()-1);
            return true;

        }

        public int getRandom() {
            return idx.get(r.nextInt(idx.size()));
        }
    }

    // a random element is to be picked from 0 to n-1 but there are some blacklisted numbers given in an array
    // try to minimize the no of calls to random
    class RandomPick {
        HashSet<Integer> set; // storing blacklisted elements
        HashMap<Integer, Integer> map; // mapping blacklisted with non-blacklisted elements
        Random r;
        int top;
        public RandomPick(int n, int[] blacklist) {
            top = n - blacklist.length;
            map = new HashMap<>();
            set = new HashSet<>();
            for(int i : blacklist) set.add(i);
            n--;
            for(int el : blacklist){
                if(el < top){ // remained blacklisted elements in top will be mapped
                    while(set.contains(n)) n--;
                    map.put(el, n);
                    n--;
                }
            }
            r = new Random();
        }

        public int pick() {
            int rdm = r.nextInt(top);
            if(map.containsKey(rdm)) return map.get(rdm);
            return rdm;
        }
    }

    // given an array of int where no. > 0 represents it rained on that lake , 0 means no rain so when we don't have a rain
    // we can dry any of the lake to avoid flood, if on same lake it rained again then the flood will occur
    static int[] avoidFlood(int[] rains){
        HashMap<Integer, Integer> map = new HashMap<>(); // lake where last rained - it's index
        TreeSet<Integer> set = new TreeSet<>(); // this keeps the values in sorted order
        int n = rains.length;
        int[] ans= new int[n];
        for (int i = 0; i < n; i++) {
            int x = rains[i];
            if(x > 0){
                if(map.containsKey(x)){
                    Integer idx = set.higher(map.get(x));
                    if(idx == null) return new int[0];
                    set.remove(idx);
                    ans[idx] = x;
                }
                map.put(x, i);
                ans[i] = -1;
            }else {
                set.add(i);
            }
        }
        return ans;
    }

    // atmost 2 distinct characters
    static int lengthOfLongestSubstringTwoDistinct(String s) {
        int len=0, j=0;
        HashMap<Character, Integer> map = new HashMap<>();
        char[] str = s.toCharArray();
        for(int i=0; i<str.length; i++){
            char ch = str[i];
            if(!map.containsKey(ch)){
                while(map.size()==2){
                    int frq = map.get(str[j]);
                    if(frq == 1) map.remove(str[j]);
                    else {
                        map.put(str[j], map.get(str[j]) - 1);
                    }
                    j++;
                }
            }

            map.put(ch, map.getOrDefault(ch,0)+1);
            if(map.size() <= 2){
                len = Math.max(len, (i-j)+1);
            }

        }
        return len;
    }

    // given an arraylist where each index represents length of a stick
    // len of stick1 + stick2 = cost to connect both the sticks
    // find min cost to connect all the sticks
    // we can connect smaller multiple sticks at a time to lower the cost
    public static long minimumCostToConnectSticks(ArrayList<Integer> arr) {
        long cost=0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i : arr){
            pq.add(i);
        }
        while(pq.size() > 1){
            int connect = pq.remove() + pq.remove();
            cost += connect;
            pq.add(connect);
        }
        return cost;
    }

    // given an array of n length consisting 1 to n no.s
    // u have to sort the array but u can only move one no. either at start or end  at a time
    // return min no. of steps to sort the array
    static int sortingCost(int N, int arr[]){
        HashMap<Integer, Integer> map = new HashMap<>();
        int cons=0, n=arr.length;
        for(int i=0; i<n; i++){
            if(map.containsKey(arr[i]-1)){
                int len = map.get(arr[i]-1) +1;
                cons = Math.max(cons, len);
                map.put(arr[i], len);
            }
            else map.put(arr[i], 1);
        }
        return n-cons;
    }

    // reorganize given string such that no 2 char should be adjacent
    // aab-> aba , aaab-> "" , aaabbc -> abcaba
    static String reorganizeString(String s){
        Map<Character, Integer> count = new HashMap<>();
        for(char c : s.toCharArray()) count.put(c, count.getOrDefault(c,0)+1);
        PriorityQueue<Character> pq = new PriorityQueue<>((a,b) -> count.get(b) - count.get(a));
        pq.addAll(count.keySet());
        StringBuilder ans = new StringBuilder();
        while (pq.size() > 1){
            char cur = pq.remove();
            char next = pq.remove();
            ans.append(cur);
            ans.append(next);
            count.put(cur,count.get(cur)-1);
            count.put(next,count.get(next)-1);
            if(count.get(cur) > 0) pq.add(cur);
            if(count.get(next) > 0) pq.add(next);
        }
        if(pq.size() == 1){
            char c = pq.remove();
            if(count.get(c) > 1) return "";
            ans.append(c);
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        HashMap<Integer,Integer>[] arr = new HashMap[3];
//        System.out.println(lengthOfLongestSubstringTwoDistinct("abcdbaa"));

//        int[] arr = {1,0,1,1};
//        System.out.println(Arrays.toString(avoidFlood(arr)));

//        Random r = new Random();
//        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(55,7,89,53,22,43,13,90,65));
//        System.out.println(list);
//        System.out.println(list.get(r.nextInt(list.size())));
//        System.out.println(list.get(r.nextInt(list.size())));
//        System.out.println(list.get(r.nextInt(list.size())));
//        System.out.println(list.get(r.nextInt(list.size())));

//        int[][] arr= {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
//        System.out.println(getSkyline(arr));

//        String ans = encode("https//:leetcode.com");
//        System.out.println(ans);
//        System.out.println(decode(ans));
//        System.out.println();
//        String ans2 = encode("https//:leetcode.com//problems");
//        System.out.println(ans2);
//        System.out.println(decode(ans2));
    }
}
