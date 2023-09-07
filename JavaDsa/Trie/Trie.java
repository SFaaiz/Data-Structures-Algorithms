package Trie;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;

class Tries{
    public class Node{
        Node[] links;
        boolean isEnd;
        public Node(){
            links = new Node[26];
        }
        boolean containsKey(char ch) {
            return (links[ch - 'a'] != null);
        }
        Node get(char ch) {
            return links[ch-'a'];
        }
        void put(char ch, Node node) {
            links[ch-'a'] = node;
        }
    }

    Node root = new Node();

    public void insert(String word){
        int idx = 0;
        int l = word.length();
        Node cur = root;
        for (int i = 0; i < l; i++) {
            idx = word.charAt(i)-'a';
            if(cur.links[idx] == null){
                cur.links[idx] = new Node();
            }
            cur = cur.links[idx];
        }
        cur.isEnd = true;
    }

    public boolean search(String key){
        Node cur = root;
        int idx = 0;
        for (int i = 0; i < key.length(); i++) {
            idx = key.charAt(i) - 'a';
            if(cur.links[idx] == null){
                return false;
            }
            cur = cur.links[idx];
        }
        return cur.isEnd;
    }

    // given a string and an array of words - return true if the string can separated into different parts
    // where each part is there in the array (dictionary)
    // dictionary - [ "i", "love", "like", "samsung"]
    // string - "ilikesamsung" --> "i like samsung"
    public boolean wordBreak(String s){
        if(s.length() == 0) return true;

        for (int i = 1; i <= s.length(); i++) {
            if(search(s.substring(0,i)) && wordBreak(s.substring(i))){
                return true;
            }
        }
        return false;
    }

    public boolean startsWith(String s){
        Node cur = root;
        int idx = 0;
        for (int i = 0; i < s.length(); i++) {
            idx = s.charAt(i) - 'a';
            if(cur.links[idx] == null){
                return false;
            }
            cur = cur.links[idx];
        }
        return true;
    }

    public int distinctSubstrings(String s){
        int n = s.length();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            Node cur = root;
            for (int j = i; j < n; j++) {
                Node idx = cur.links[s.charAt(j)-'a'];
                if(idx == null){
                    idx = new Node();
                    cnt++;
                }
                cur = idx;
            }
//            Node node = root;
//            for(int j = i;j<n;j++) {
//                if(!node.containsKey(s.charAt(j))) {
//                    node.put(s.charAt(j), new Node());
//                    cnt++;
//                }
//                node = node.get(s.charAt(j));
//            }
        }
        return cnt+1;
    }
}

public class Trie {
    public static void main(String[] args) {
//        Tries ob = new Tries();
//        System.out.println(ob.distinctSubstrings("aaa"));
        System.out.println((8 >> 3) & 1);
        System.out.println((8 >> 2) & 1);
        System.out.println((8 >> 1) & 1);
        System.out.println((8 >> 0) & 1);
//        String[] words = {"apple", "app", "their","there"};
//        for(String s : words){
//            ob.insert(s);
//        }
//        System.out.println(ob.startsWith("ap"));
//        System.out.println(ob.startsWith("the"));
//        System.out.println(ob.startsWith("apt"));
//        System.out.println(ob.wordBreak("appthere"));
//        System.out.println(ob.wordBreak("apptheres"));
    }
}
