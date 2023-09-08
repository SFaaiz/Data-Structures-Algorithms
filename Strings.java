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
