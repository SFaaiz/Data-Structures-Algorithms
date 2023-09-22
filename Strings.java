// 409. Longest Palindrome
// TC is O(2n) = O(n)
public int longestPalindrome(String s) {
    Map<Character, Integer> map = new HashMap<>();
    char[] arr = s.toCharArray();
    for(char c: arr){
        map.put(c, map.getOrDefault(c, 0)+1);
    }

    int cnt=0;
    boolean hasSingle = false;
    for(Map.Entry<Character, Integer> entry : map.entrySet()){
        if(entry.getValue()%2==0){
            cnt+=entry.getValue();
        }else{
            cnt += entry.getValue()-1;
            hasSingle = true;
        }
    }
    if(hasSingle) return cnt+1;
    return cnt;
} 

// 392. Is Subsequence
public boolean isSubsequence(String s, String t) {
    if(s.isEmpty()) return true;
    if(t.isEmpty()) return false;
    if(s.charAt(0)==t.charAt(0)){
        return isSubsequence(s.substring(1),t.substring(1));
    }else{
        return isSubsequence(s, t.substring(1));
    }
}


// 205. Isomorphic Strings
// TC is O(n)
public boolean isIsomorphic(String s, String t) {
    if(s.length() != t.length()) return false;
    HashMap<Character,Character> match = new HashMap<>();
    for(int i=0; i<s.length(); i++){
        char a = s.charAt(i);
        char b =t.charAt(i);
        if(!match.containsKey(a) && !match.containsValue(b)){
            match.put(a,b);
        }else if(match.containsKey(a) && match.get(a) != b) return false;
        else if(!match.containsKey(a)) return false;
    }
    return true;
}

// 76. Minimum Window Substring
// sliding window

public String minWindow(String s1, String s2) {
    int nlen = s1.length(), mlen = s2.length();
    Map<Character, Integer> toFind = new HashMap<>();
    Map<Character, Integer> s1map = new HashMap<>();
    for(char c: s2.toCharArray()){
        toFind.put(c, toFind.getOrDefault(c, 0) + 1);
    }
    char[] arr = s1.toCharArray();
    int n1=0, n2=0, min = Integer.MAX_VALUE, match=0;
    int[] res = {0,0};
    while(n2 < nlen){
        char c = arr[n2];
        s1map.put(c, s1map.getOrDefault(c,0)+1);
        if(s1map.get(c) <= toFind.getOrDefault(c,-1)) match++;

        while(mlen==match){
            int len = (n2-n1)+1;
            if(min>len){
                min = len;
                res[0]=n1;
                res[1]=n2;
            }
            int cnt = s1map.get(arr[n1]);
            if(toFind.getOrDefault(arr[n1],-1)>=cnt) match--;
            s1map.put(arr[n1],s1map.get(arr[n1])-1);
            if(s1map.get(arr[n1])==0) {
                s1map.remove(n1);
            }
            
            n1++;
        }
        n2++;
    }
    if(min == Integer.MAX_VALUE) return "";
    return s1.substring(res[0],res[1]+1);
}
