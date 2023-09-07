package hashmapAndHeaps;
import java.math.BigInteger;
import java.util.*;
public class HashMap01 {

    // u r given a hashmap consists of employee and it's manager , u have to return a hashmap that shows
    // how many employees are there under each manager
//    static HashMap<String,Integer> empsUnderMan(HashMap<String,String> empMan){
//        HashMap<String,HashSet<String>> manEmp = new HashMap<>();
//        for(String em : empMan.keySet()){
//            if(!manEmp.containsKey(empMan.get(em))){
//                HashSet<String> s = new HashSet<>();
//                s.add(em);
//                manEmp.put(empMan.get(em),s);
//            }else {
//                manEmp.put(empMan.get(em),manEmp.get(empMan.get(em)).add(em));
//            }
//        }
//    }



  /*  // u r given a hashmap consists of source and destination, u have to find the sequence from the very first source to dest
            "chennai","bangalore"
            "bombay","delhi");
            "delhi","goa");
            "goa","chennai"); */
    static ArrayList<String> sourceDest(HashMap<String,String> route){
        ArrayList<String> ans = new ArrayList<>();
        HashMap<String,Boolean> findSource = new HashMap<>();
        for(String s : route.keySet()){
            if(!findSource.containsKey(s))findSource.put(s,true);
            ans.add(s);
        }
        for(String s : route.values()){
            findSource.put(route.get(s),false);
            if(findSource.containsKey(s)) ans.remove(s);
        }
        int i=0;
        while (i<findSource.size()-2){
            ans.add(route.get(ans.get(i)));
            i++;
        }
        return ans;
    }

//    Check if Array Pairs are Divisible by K
//    int[] arr= {11,23,45,68,20,29,27,55,32,10};  here 11 and 29 can pair to be divisible by k=10
//    23-27 can pair and so on all no. can pair do be divisible by k and no no. will remain to be paired
    static boolean pairsDivisibleByK(int[] arr, int k){
        HashMap<Integer,Integer> remFreq = new HashMap<>();
        for(int val : arr){
            int rem = val%k;
            if(remFreq.containsKey(rem)){
                remFreq.put(rem,remFreq.get(rem)+1); // if contains remainder, increment frequency
            }else {
                remFreq.put(rem,1);
            }
        }

        for(int val: arr){
            int rem = val%k;
            if(rem==0){ // if rem is 0 then frequency of 0 remaindered no.s should be even to be paired up
                if(remFreq.get(rem) % 2 != 0) return false;
            } // below, if k=10 then it will check with 2*5=10 if k=9 then if we had rem==k/2 4==4 (9/2 is 4) it's error
            else if(2*rem == k){ // if k=10 then frequency of k/2 no.s should be even i.e. 5
                if(remFreq.get(rem) % 2 != 0) return false;
            } else
            if(remFreq.get(rem) != remFreq.get(k-rem)) return false;
        }
        return true;
    }

    // in an every k-sized window return the no. of distinct elements
    // 0-3 , 1-4, 2-5 , and so on for k=4
    static ArrayList<Integer> kDistinctElements(int[]arr, int k){
        ArrayList<Integer> ans= new ArrayList<>();
        if(k>arr.length)return ans;
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i <= arr.length; i++) {
            if(i!=0 && i>=k){
                ans.add(map.size());
//                distinct=0;
                if(map.get(arr[i-k]) == 1){
                    map.remove(arr[i-k]);
                }else {
                    map.put(arr[i-k], map.get(arr[i-k])-1);
                }
            }
            if(i==arr.length)break;
            if(!map.containsKey(arr[i])){
                map.put(arr[i],1);
            }else {
                map.put(arr[i],map.get(arr[i])+1);
            }
        }
        return ans;
    }

    //return size of largest subarray with 0 sum in an array
    // from range of one index to another , sum is 0
    static int largestSubArray(int[]arr){
        int size=0, sum=0;
        HashMap<Integer,Integer> sumIndex = new HashMap<>(); // sum, index
        sumIndex.put(0,-1); // at first sum is zero and index is -1
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if(sumIndex.containsKey(sum)){
                int n = i-sumIndex.get(sum);
                if(size < n) size = n;
            }else {
                sumIndex.put(sum,i);
            }
        }
        return size;
    }

    // count no. of subarrays with 0 sum
    static int subArrayCount(int[]arr){
        int sum=0, count=0;
        HashMap<Integer,Integer> sumFreq = new HashMap<>(); // sum, frequency
        sumFreq.put(0,1); // at first sum is zero and is repeated/frequency is one
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if(sumFreq.containsKey(sum)){
                count += sumFreq.get(sum);
                sumFreq.put(sum,sumFreq.get(sum)+1);
            }else {
                sumFreq.put(sum,1);
            }
        }
        return count;
    }

    // length of largest subarray with contiguous elements like 16-19-18-17
    // don't count duplicates
    static int largestContiguousLength(int[] arr){
        int length=0;
        for (int i = 0; i < arr.length; i++) {
            int min=arr[i], max= arr[i];
            HashSet<Integer> val = new HashSet<>();
            val.add(arr[i]);
            for (int j = i+1; j < arr.length; j++) {
                if(val.contains(arr[j])) break; // if we found a duplicate then no more contiguous element can exist
                min = Math.min(min,arr[j]);
                max = Math.max(max,arr[j]);
                if(max-min == j-i) {
                    if(length<(j-i)+1)  length=(j-i)+1;
                }
            }
        }
        return length;
    }

    // min window substring - find min length from string1 in which all chars of string2 exist
    static int minWindow1(String s1, String s2){
        HashMap<Character,Integer> s2map = new HashMap<>();
        for(char i: s2.toCharArray()){
            s2map.put(i,s2map.getOrDefault(i,0)+1);
        }
        HashMap<Character,Integer> s1map = new HashMap<>();
        int i=0,j=0,len=Integer.MAX_VALUE,match=0;
        char[] s1arr = s1.toCharArray();
        while (i<s1arr.length){
            s1map.put(s1arr[i],s1map.getOrDefault(s1arr[i],0)+1);
            if(s1map.get(s1arr[i]) <= s2map.getOrDefault(s1arr[i],-1)) match++;

            while (match==s2.length()){
                len = Math.min(len,(i-j)+1);
                int f = s1map.get(s1arr[j]);
                if(f == 1){
                    s1map.remove(s1arr[j]);
                    if(s2map.containsKey(s1arr[j])) match--;
                }else {
                    s1map.put(s1arr[j],s1map.get(s1arr[j])-1);
                    if(s2map.getOrDefault(s1arr[j],-1) > (f-1)) match--;
                }
                j++;
            }
            i++;
        }
        return len;
    }

    // find minimum substring which contains all the unique chars of the entire string
    static int minWindow2(String s){
        HashSet<Character> uniqueSet = new HashSet<>();
        char[] arr = s.toCharArray();
        for(char ch : arr){
            uniqueSet.add(ch);
        }
        HashMap<Character,Integer> charFreq = new HashMap<>();
        int i=0,j=0,len=Integer.MAX_VALUE;
        while (i<arr.length){
            charFreq.put(arr[i],charFreq.getOrDefault(arr[i],0)+1);
            while (uniqueSet.size()==charFreq.size()){
                len = Math.min(len,(i-j)+1);
                char ch = arr[j];
                if(charFreq.get(ch)==1) charFreq.remove(ch);
                else charFreq.put(ch,charFreq.get(ch)-1);
                j++;
            }
            i++;
        }
        return len;
    }


    // longest substring without repeating characters
    static int longestUniqueSubstring(String str){
        int len=0, i=0,j=0, curLen=0;
        char[] s = str.toCharArray();
        HashSet<Character> letters = new HashSet<>();
        while (i<s.length){
            if(!letters.contains(s[i])){
                letters.add(s[i]);
                curLen++;
                if(len<curLen) len=curLen;
            }else {
                while (letters.contains(s[i])){
                    letters.remove(s[j]);
                    j++;
                    curLen--;
                }
                letters.add(s[i]);
                curLen++;
            }
            i++;
        }
        return len;
    }

    // count no. of substrings without repeating chars
    static int countUniqueSubstrings(String s){
        char[] ch = s.toCharArray();
        int i=0, j=0, count=0;
        HashSet<Character> letters = new HashSet<>();
        while (i<ch.length){
            if(!letters.contains(ch[i])){ // if does not contain
                letters.add(ch[i]);
                count = count + (i-j)+1;// add possible counts, like with 2 characters we can make 3 substring combinations
            }else {
                while (letters.contains(ch[i])){
                    letters.remove(ch[j]);
                    j++;
                }
                letters.add(ch[i]); // add current element which has a duplicate before
                count += (i-j)+1;
            }
            i++;
        }
        return count;
    }

    // length of Longest Substring With Exactly K Distinct Characters
    // like aabcbc has ans=4 for k=2 because bcbc has longest length 4 with 2 distinct characters (bc)
    static int kDistinctChars(String s, int k){
        int i=0,j=0,len=0;
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> ch = new HashMap<>();
        while (i<chars.length){
            ch.put(chars[i], ch.getOrDefault(chars[i],0)+1);
            while (ch.size()>k){
                if(ch.get(chars[j])==1){
                    ch.remove(chars[j]);
                }else ch.put(chars[j],ch.get(chars[j])-1);
                j++;
            }
            if(ch.size()==k){
                len = Math.max(len,(i-j)+1);
            }
            i++;
        }
        return len;
    }

    // Count of Equivalent Subarrays - having all the unique elements that the entire array has
    static int countEquivalent(int[] arr) {
        HashSet<Integer> cnt = new HashSet<>();
        for (int i = 0; i < arr.length; i++) { // count unique elements
            cnt.add(arr[i]);
        }
        int unique = cnt.size();
        int ans=0,i=0,j=0;
        HashMap<Integer,Integer> numFreq = new HashMap<>();
        while (i<arr.length){
            if(!numFreq.containsKey(arr[i])){
                numFreq.put(arr[i],1);
            }else {
                numFreq.put(arr[i],numFreq.get(arr[i])+1);
            }
            if(numFreq.size() == unique) {
                ans += (arr.length-i);// if till i , i have all unique elements then for i+1, i+2 and so on for all i have unique elements
                while (numFreq.size() == unique){ // until we have all elements btwn 0-i we will get we will get combinations
                    int ct = numFreq.get(arr[j]);
                    if(ct==1){
                        numFreq.remove(arr[j]);
                    }else {
                        numFreq.put(arr[j],numFreq.get(arr[j])-1);
                        ans += (arr.length-i);
                    }
                    j++;
                }
            }
            i++;
        }
        return ans;
    }

    // count max consecutive one's after flipping k zeros
    static int maxOnes(int[] arr, int k){
        int i=0,j=0,len=0,kZeros=0;
        while (i<arr.length){
            if(arr[i]==0){
                kZeros++; // if the no. is zero then increment count of flipping zero
            }
            if(kZeros==k){ // when we have flipped k zeros then we have got a potential answer
                len = Math.max(len,(i-j)+1); // no. of zeros we flipped + no. of ones we encountered
            }
             // keep traversing until a zero is decremented
                while (kZeros>k){
                    if(arr[j]==0) kZeros--;
                    j++;
                }
            i++;
        }
        return len;
    }

    // count max consecutive one's after flipping at max one zero
    static int maxOnesFlipOneZero(int[] arr){
        int i=0,j=0,len=0,kZeros=0;
        while (i<arr.length){
            if(arr[i]==0){
                kZeros++; // if the no. is zero then increment count of flipping zero
            }
            // keep traversing until a zero is decremented
            while (kZeros>1){
                if(arr[j]==0) kZeros--;
                j++;
            }
            // even if we have not flipped any zero we can count no. of ones
            len = Math.max(len,(i-j)+1); // no. of zeros we flipped + no. of ones we encountered

            i++;
        }
        return len;
    }

    // Longest Substring with at most K Unique Characters
    static int atMostKDistinct(String s, int k){
        char[] ch = s.toCharArray();
        HashMap<Character,Integer> charFreq = new HashMap<>();
        int i=0,j=0,len=0;
        while (i<ch.length){
            if(!charFreq.containsKey(ch[i])){
                charFreq.put(ch[i],1);
            }else {
                charFreq.put(ch[i], charFreq.get(ch[i])+1);
            }
            while (charFreq.size()>k){
                if(charFreq.get(ch[j])==1) charFreq.remove(ch[j]);
                else charFreq.put(ch[j], charFreq.get(ch[j])-1);
                j++;
            }
            len = Math.max(len,(i-j)+1);
            i++;
        }
        return len;
    }

    // Count of Substrings with at-most K Distinct Characters
    static int countAtMostKDistinct(String s, int k){
        HashMap<Character,Integer> charFreq = new HashMap<>();
        char[] ch = s.toCharArray();
        int i=0,j=0,count=0,tempK=0;
        while (i<ch.length){
            if(!charFreq.containsKey(ch[i])){
                charFreq.put(ch[i],1);
                tempK++;
            }else {
                charFreq.put(ch[i],charFreq.get(ch[i])+1);
            }
            while (tempK>k){
                int cnt = charFreq.get(ch[j]);
                if(cnt==1){
                    charFreq.remove(ch[j]);
                    tempK--;
                }else charFreq.put(ch[j],charFreq.get(ch[j])-1);
                j++;
            }
            count = count + (i-j)+1;
            i++;
        }
        return count;
    }

    // find all anagrams of a string from given string
    static int findAllAnagrams(String source, String pattern){
        HashMap<Character,Integer> smap = new HashMap<>();
        HashMap<Character,Integer> pmap = new HashMap<>();
        for(char i : pattern.toCharArray()){
            pmap.put(i,pmap.getOrDefault(i,0)+1);
        }
        int i=0,count=0;
        while (i<=source.length()){
            if(i< pattern.length()){
                char ch = source.charAt(i);
                smap.put(ch,smap.getOrDefault(ch,0)+1);
                i++;
                continue;
            }
            if(compare(smap,pmap)){
                count++;
            }
            if(i==source.length())break;
            char acquire = source.charAt(i);
            smap.put(acquire,smap.getOrDefault(acquire,0)+1);
            char release = source.charAt(i- pattern.length());
            if(smap.get(release)==1){
                smap.remove(release);
            }else {
                smap.put(release,smap.get(release)-1);
            }
            i++;
        }
        return count;
    }

    private static boolean compare(HashMap<Character, Integer> smap, HashMap<Character, Integer> pmap) {
        for(char p : pmap.keySet()){
            if(pmap.get(p) != smap.getOrDefault(p,0)){
                return false;
            }
        }
        return true;
    }

    // after replacing k or less characters from one string we can make both strings anagrams
    // 2 strings should be of same size to be k anagrams
    static boolean areKAnagrams(String s1, String s2, int k){
        if(s1.length() != s2.length()) return false;
        HashMap<Character,Integer> chFrq = new HashMap<>();
        int cnt=0;
        for(char i : s1.toCharArray()){ // add frequencies of chars
            chFrq.put(i,chFrq.getOrDefault(i,0)+1);
            cnt++;
        }
        for(char i : s2.toCharArray()){
            if(chFrq.containsKey(i)){
                int c = chFrq.get(i);
                if(c==1) {
                    chFrq.remove(i);
                }
                else {
                    chFrq.put(i,c-1);
                }
                cnt--;
            }
        }
        return cnt<=k;
    }

    // anagrams mapping - u r given 2 arrays, for all the elements of first array u have to return an array which
    // gives it's index in second array
    static int[] anagramMapping(int[] arr1, int[]arr2){
        HashMap<Integer,ArrayList<Integer>> valIndex = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            if(!valIndex.containsKey(arr2[i])){
                ArrayList<Integer> l = new ArrayList<>();
                l.add(i);
                valIndex.put(arr2[i],l);
            }else {
                valIndex.get(arr2[i]).add(i);
            }
        }
        int[] ans = new int[arr1.length];
        for (int i = 0; i < arr1.length; i++) {
            ans[i] = valIndex.get(arr1[i]).remove(0);
        }
        return ans;
    }

    // valid anagrams - 2 strings are anagrams of each other or not
    static boolean validAnagrams(String s1, String s2){
        HashMap<Character,Integer> map = new HashMap<>();
        for(char ch : s1.toCharArray()){
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        for(char ch : s2.toCharArray()){
            if(map.containsKey(ch)){
                if(map.get(ch)==1) map.remove(ch);
                else map.put(ch,map.get(ch)-1);
            }else {
                return false;
            }
        }
        return map.isEmpty();
    }

    // a string array is given - we have to group anagrams of same types into an arraylist for all types anagrams
    static ArrayList<ArrayList<String>> groupAnagrams(String[] str){
        HashMap<HashMap<Character,Integer>, ArrayList<String>> ans = new HashMap<>();
        for(String s : str){
            HashMap<Character,Integer> sub = new HashMap<>();
            for(char c : s.toCharArray()){
                sub.put(c,sub.getOrDefault(c,0)+1);
            }
            if(ans.containsKey(sub)){
                ans.get(sub).add(s);
            }else {
                ArrayList<String> st = new ArrayList<>();
                st.add(s);
                ans.put(sub,st);
            }
        }
        ArrayList<ArrayList<String>> answer = new ArrayList<>();
        for(ArrayList<String> s : ans.values()){
            answer.add(s);
        }
        return answer;
    }

    // acd dfg wyz yab mop bdfh x y moqs
    // here diff btn a-d is 3, c-f and d-g is 3 same for wyz(all 3 char has same diff from acd), mop, yab
    static ArrayList<ArrayList<String>> groupShiftedStr(String[] str){
        HashMap<String,ArrayList<String>> map = new HashMap<>();
        for(String s : str){
            String st = "";
            for (int i = 0; i < s.length()-1; i++) {
                int diff = s.charAt(i+1) - s.charAt(i);
                if(diff<0) {
                    diff+=26;
                }
                st += diff;
                st += "#";
            }

            if(map.containsKey(st)){
                map.get(st).add(s);
            }else {
                ArrayList<String> l = new ArrayList<>();
                l.add(s);
                map.put(st,l);
            }
        }
        ArrayList<ArrayList<String>> ans = new ArrayList<>();
        for(ArrayList<String > al : map.values()){
            ans.add(al);
        }
        return ans;
    }

    // isomorphic - a char from string1 is entirely correspondence to same char , one-to-one correspondence
    // abacba xyxzyx , here a-x b-y c-z
    static boolean isIsomorphic(String s, String t){
        if(s.length() != t.length()) return false;
        HashMap<Character,Character> match = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char k = s.charAt(i);
            char v = t.charAt(i);
            if(!match.containsKey(k) && !match.containsValue(v)){
                match.put(k,v);
            } else if(match.containsKey(k)){
                if(match.get(k) != v){
                    return false;
                }
            }else return false;
        }
        return true;
    }

    // count no. of subarrays which has sum = k
    static int kSumSubarrays(int[] nums, int k){
        HashMap<Integer, Integer> numFreq = new HashMap<>();
        numFreq.put(0,1);
        int sum = 0, cnt=0;
        for(int i : nums){
            sum += i;
            if(numFreq.containsKey(sum-k)){
                cnt += numFreq.get(sum-k);
            }
            numFreq.put(sum, numFreq.getOrDefault(sum,0)+1);
        }
        return cnt;
    }

    // longest subarray with sum divisible by k
    static int kDivisibleSubarray(int[] nums, int k){
        HashMap<Integer, Integer> remIndex = new HashMap<>(); // remainder - index
        remIndex.put(0,-1);
        int sum=0,len=0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int rem = sum%k;
            if(rem < 0){
                rem += k;
            }
            if(remIndex.containsKey(rem)){
                len = Math.max(len, i-(remIndex.get(rem)));
            }else {
                remIndex.put(rem,i);
            }
        }
        return len;
    }

    static int countKDivisible(int[] nums, int k){
        HashMap<Integer, Integer> remFreq = new HashMap<>(); // remainder - frequency
        remFreq.put(0,1);
        int sum=0,cnt=0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int rem = sum%k;
            if(rem < 0){
                rem += k;
            }
            if(remFreq.containsKey(rem)){
                cnt += remFreq.get(rem);
            }
            remFreq.put(rem, remFreq.getOrDefault(rem, 0)+1);
        }
        return cnt;
    }

    // longest contiguous subarray with equal no.s of 0's and 1's
    static int longest0s1s(int[] nums){
        HashMap<Integer, Integer> sumIndex = new HashMap<>();
        int sum=0,len=0;
        sumIndex.put(0,-1);
        for (int i = 0; i < nums.length; i++) {
            int num = (nums[i] == 0) ? -1 : 1;
            sum += num;
            if(sumIndex.containsKey(sum)){
                len = Math.max(len,i-(sumIndex.get(sum)));
            }
            else {
                sumIndex.put(sum,i);
            }
        }
        return len;
    }

    // count subarray with equal no.s of 0's and 1's
    static int count0s1s(int[] nums){
        HashMap<Integer, Integer> sumFreq = new HashMap<>();
        int sum=0,cnt=0;
        sumFreq.put(0,1);
        for (int i = 0; i < nums.length; i++) {
            int num = (nums[i] == 0) ? -1 : 1;
            sum += num;
            if(sumFreq.containsKey(sum)){
                cnt += sumFreq.get(sum);
            }
                sumFreq.put(sum, sumFreq.getOrDefault(sum,0)+1);
        }
        return cnt;
    }

    // longest subarray with equal 0s1s2s
    static int longest0s1s2s(int[] nums){
        HashMap<String, Integer> codeIndex = new HashMap<>();
        codeIndex.put("0#0",-1);
        int len=0,zeros=0,ones=0,twos=0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) zeros++;
            else if(nums[i] == 1) ones++;
            else twos++;
            String code= (ones-zeros) + "#" + (twos-ones);
            if(codeIndex.containsKey(code)){
                len = Math.max(len, i-(codeIndex.get(code)));
            }else {
                codeIndex.put(code, i);
            }
        }
        return len;
    }

    static int count0s1s2s(int[] nums){
        HashMap<String, Integer> codeFreq = new HashMap<>();
        codeFreq.put("0#0",1);
        int cnt=0,zeros=0,ones=0,twos=0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) zeros++;
            else if(nums[i] == 1) ones++;
            else twos++;
            String code= (ones-zeros) + "#" + (twos-ones);
            if(codeFreq.containsKey(code)){
                cnt += codeFreq.get(code);
            }
            codeFreq.put(code,codeFreq.getOrDefault(code,0)+1);
        }
        return cnt;
    }

    // does there exist 2 pairs such that all the values are distinct and sum of both the pairs is 2
    // [2,9,3,5,8,6,4] here 2+6=8 and 3+5=8 so ans is true
    static boolean equalSumPair(int[] nums){
        HashSet<Integer> sum = new HashSet<>();
        int sm=0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                sm = nums[i] + nums[j];
                if(sum.contains(sm)){
                    return true;
                }else {
                    sum.add(sm);
                }
            }
        }
        return false;
    }

    static ArrayList<ArrayList<Integer>> equalSumPair2(int[] nums){
        HashMap<Integer, ArrayList<Integer>> sumPair = new HashMap();
        int sm=0;
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                sm = nums[i] + nums[j];
                if(sumPair.containsKey(sm)){
                    ans.add(sumPair.get(sm));
                    ans.add(new ArrayList<>(Arrays.asList(nums[i],nums[j])));
                    return ans;
                }else {
                    sumPair.put(sm,new ArrayList<>(Arrays.asList(nums[i],nums[j])));
                }
            }
        }
        return ans;
    }

    static String fractionRecurring(int num, int den){
        StringBuilder ans = new StringBuilder("");
        HashMap<Integer, Integer> remIndex = new HashMap<>();
        int quotient = num / den;
        ans.append(quotient);
        int rem = num % den;
        if(rem == 0) return ans.toString();
        else ans.append(".");
        while (rem != 0){
            if(remIndex.containsKey(rem)){
                ans.insert(remIndex.get(rem), "(");
                ans.append(")");
                break;
            }else {
                remIndex.put(rem, ans.length());
                rem *= 10;
                ans.append(rem/den);
                rem = rem % den;
            }
        }
        return ans.toString();
    }

    // in the array each index is a rabbit and number it has is that how many other rabbits are like him
    // maybe not all the rabbits in the forest have given us this data
    // so find the min no. of rabbits that could be there in forest
    static int minRabbits(int[] nums){
        HashMap<Integer, Integer> typeFreq = new HashMap<>(); // this type of rabbit - how many have reported this type
        int min=0;
        for (int i = 0; i < nums.length; i++) {
            int type = nums[i] + 1;
            int reporters = typeFreq.getOrDefault(type, 0)+1;
            if(type == 1){
                min += 1;
            }
            else if(type == reporters){
                min += type;
                typeFreq.remove(type);
            }
            else {
                typeFreq.put(type, typeFreq.getOrDefault(type,0)+1);
            }
        }
        for(int i : typeFreq.keySet()){
            min += i;
        }
        return min;
    }

    // given an array check if all elements have common arithmetic difference
    // [1,9,5,17,13] --> 1-5-9-13-17 has diff 4
    static boolean AP(int[] nums){
        if(nums.length <= 1) return true;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        HashSet<Integer> set = new HashSet<>();
        for(int i : nums){
            min = Math.min(min,i);
            max = Math.max(max,i);
            set.add(i);
        }
        int diff = (max-min) / (nums.length-1);
        for (int i = 0; i < nums.length; i++) {
            int a = min + i * diff;
            if(!set.contains(a)){
                return false;
            }
        }
        return true;
    }

    // Smallest subarray with highest frequency of an element
    static int smallestSubarray(int[] nums){
        HashMap<Integer,Integer> frqMap = new HashMap<>();
        HashMap<Integer,Integer> fstOccurence = new HashMap<>();
        int s = -1;
        int e = -1;
        int hf = 0;
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            int frq = frqMap.getOrDefault(nums[i], 0) + 1;
            frqMap.put(nums[i], frq);
            if(!fstOccurence.containsKey(nums[i])){
                fstOccurence.put(nums[i], i);
            }
            int st = fstOccurence.get(nums[i]);
            if(hf < frq || hf == frq && ((i-st) + 1) < len){ // precedence ! ->  && -> ||
                hf = frq;
                s = st;
                e = i;
                len = (e-s) + 1;
            }
        }
        return len;
    }

     static void completeTask(int n, int m, int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for(int i : arr){
            set.add(i);
        }
        ArrayList<Integer> s1 = new ArrayList<>();
        ArrayList<Integer> s2 = new ArrayList<>();
        int i=0;
        while(i++ < n){
            if(!set.contains(i)){
                if(s1.size() == s2.size()){
                    s1.add(i);
                }else{
                    s2.add(i);
                }
            }
        }
         System.out.println(s1);
         System.out.println(s2);
    }

    // find 4 distinct indexed no.s whose some equals k
    // can't use the same index more than once
    // same answer should not be repeated
    // 2 diff quadruplets shouldn't give same answer
    // 2,2,2,3,3,4,4,4,5,5,7,7,8,8,8,10,10,10 like int this case 2(of index 0 )+3+4+8 = 17 and 2(of index 1)+3+4+8 = 17
    // in both the cases answer is 2,3,4,8
    static ArrayList<ArrayList<Integer>> quadrupletSum(int[] nums, int k){
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i != 0 && nums[i] == nums[i-1]) continue;
            for (int j = i+1; j < n; j++) {
                if (j != i+1 && nums[j] == nums[j-1]) continue;
                int s=j+1, e=n-1;
                while (s < e) {
                        int sum = nums[i] + nums[j] + nums[s] + nums[e];
                        if(sum > k) e--;
                        else if(sum < k) s++;
                        else {
                            res.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[s], nums[e])));
                            s++;
                            e--;
                            while (s<e && nums[s] == nums[s-1]) s++;
                            while (s<e && e != n-1 && nums[e] == nums[e+1]) e--;
                        }
                    }
            }
        }
        return res;
    }

    static ArrayList<ArrayList<Integer>> quadrupletSum2(int[] nums, int k, int n){
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // to avoid duplication of values
            if(i != 0 && nums[i] == nums[i-1]) continue;
            for (int j = i+1; j < n; j++) {
                // to avoid duplication of values
                if(j != i+1 && nums[j] == nums[j-1]) continue;
                int s = j+1, e = n-1;
                while (s<e){
                    int sum = nums[i] + nums[j] + nums[s] + nums[e];
                    if(sum > k) e--;
                    else if(sum < k) s++;
                    else {
                        res.add(new ArrayList<>(Arrays.asList(nums[i] , nums[j] , nums[s] , nums[e])));
                        s++;
                        e--;
                        // i and j is fixed, in case of duplicates incrementing s and e will give same result if there exist
                        while (s<e && nums[s] == nums[s-1] ) s++;
                        while (s<e && nums[e] == nums[e+1] ) e--;
                    }
                }
            }
        }
        return res;
    }

    static int quadrupletSumII(int[] nums1, int[] nums2, int[] nums3, int[] nums4){
        int cnt=0;
        HashMap<Integer,Integer> sumFrq = new HashMap<>();
        for(int n1 : nums1){
            for(int n2: nums2){
                sumFrq.put(n1+n2, sumFrq.getOrDefault(n1+n2, 0) + 1);
            }
        }
        for(int n1 : nums3){
            for(int n2: nums4){
                cnt += sumFrq.getOrDefault((n1+n2)*-1, 0);
            }
        }
        return cnt;
    }

    // in a given string return the index of char which is unique that comes first in the sequence
    static int firstUniqChar(String s) {
        HashMap<Character, Integer> frq = new HashMap<>();
        HashMap<Character, Integer> idx = new HashMap<>();
        int i=0;
        for(char c : s.toCharArray()){
            frq.put(c, frq.getOrDefault(c, 0)  + 1);
            idx.put(c, i);
            i++;
        }
        for(char c : s.toCharArray()){
            if(frq.get(c) == 1){
                return idx.get(c);
            }
        }
        return -1;
    }

    static int firstUniqChar2(String s){
        int idx = Integer.MAX_VALUE;
        for(char c = 'a'; c <= 'z'; c++){
            int index = s.indexOf(c);
            if(index != -1 && index == s.lastIndexOf(c)){
                idx = Math.min(idx, index);
            }
        }
        return (idx == Integer.MAX_VALUE) ? -1 : idx;
    }

    // leetcode 554
    // draw a vertical line which cuts least no. of bricks
    static int leastBricks(List<List<Integer>> wall) {
        HashMap<Integer, Integer> map = new HashMap<>(); // at this index - no. of bricks splitting
        int split=0;
        for(int i=0; i<wall.size(); i++){
            int sum=0;
            for(int j=0; j<wall.get(i).size()-1; j++){
                sum += wall.get(i).get(j);
                int splt = map.getOrDefault(sum, 0) + 1;
                split = Math.max(split, splt);
                map.put(sum, splt);
            }
        }
        return (wall.size() - split);
    }

    // max frequency stack (push-pop)
    // while popping, pop the most frequent element if there is a tie then pop the one on top
    static class FreqStack {
        private int maxFrq;
        private HashMap<Integer, Integer> frqMap;
        private HashMap<Integer, Stack> frqStack;
        public FreqStack() {
            frqMap = new HashMap<>();
            frqStack = new HashMap<>();
            maxFrq = 0;
        }

        public void push(int val) {
            int frq = frqMap.getOrDefault(val, 0) + 1;
            frqMap.put(val, frq);
            maxFrq = Math.max(maxFrq, frq);
            Stack<Integer> st = frqStack.getOrDefault(frq, new Stack<Integer>());
            st.push(val);
            frqStack.put(frq, st);
        }

        public int pop() {
            Stack<Integer> st = frqStack.get(maxFrq);
            int ans = st.pop();
            if(st.isEmpty()){
                frqStack.remove(maxFrq);
                maxFrq--;
            }
            int frq = frqMap.get(ans);
            if(frq == 1) frqMap.remove(ans);
            else frqMap.put(ans, frq-1);
            return ans;
        }
    }

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
            if(map.size() == 2){
                len = Math.max(len, i-j);
            }

        }
        return len;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringTwoDistinct("abcccdbaa"));

//        FreqStack fst = new FreqStack();
//        fst.push(1);
//        fst.push(2);
//        fst.push(3);
//        fst.push(3);
//        fst.push(4);
//        fst.push(4);
//        fst.push(4);
//        System.out.println(fst.pop());
//        System.out.println(fst.pop());
//        System.out.println(fst.pop());
//        System.out.println(fst.pop());
//        System.out.println(fst.pop());
//        System.out.println(fst.pop());
//        System.out.println(fst.pop());

//        String s = "loveleetcode";
//        System.out.println(firstUniqChar2(s));
//        System.out.println(firstUniqChar(s));

//        int[] nums = {2,2,2,3,3,4,4,4,5,5,7,7,8,8,8,10,10,10};
////        int[] nums = {1, 0, -1, 0, -2, 2};
//        System.out.println(quadrupletSum(nums,17));
//        System.out.println(quadrupletSum2(nums,17,nums.length));

//        completeTask(15, 6, new int[]{2,5,6,7,9,4});

//        int[] nums = {1,3,2,4,2,3,4,2,5,6,5,5,7};
//        System.out.println(smallestSubarray(nums));

//        System.out.println(AP(new int[]{3,5,1,8}));

//        System.out.println(minRabbits(new int[]{2,2,3,1,0,2,2,3,1}));

//        System.out.println(fractionRecurring(7,-12));
//        System.out.println(fractionRecurring(53,-12));
//        System.out.println(fractionRecurring(0,10));
//        System.out.println(fractionRecurring(14,3));

//        int[] nums = {2,9,3,5,8,6,4};
//        System.out.println(equalSumPair2(nums));
//        System.out.println(equalSumPair(nums));

//        int[] nums = {0,1,2,0,2,2,1,0,1,1};
//        System.out.println(count0s1s2s(nums));
//        System.out.println(longest0s1s2s(nums));

//        int[] nums.= {0,0,1,0,1,0,1,1,0,0,1,1,1,0};
//        int[] nums = {0,0,1,0,1,0,1};
//        System.out.println(count0s1s(nums));
//        System.out.println(longest0s1s(nums));

//        int[] nums = {-6,12};
//        System.out.println(kDivisibleSubarray(nums,2));
//        System.out.println(countKDivisible(nums,5));

//        System.out.println(kSumSubarrays(new int[]{2,3,7,4,-11,5}, 5));

//        System.out.println(isIsomorphic("abacbd","xyxzyx"));

//        System.out.println(groupShiftedStr(new String[]{"acd","dfg","wyz","yab","mop","bdfh","x","y","moqs"}));

//        System.out.println(groupAnagrams(new String[]{"abcd","efgh","wxyz","acbd","ghfe","yxzw"}));

//        System.out.println(validAnagrams("bcda","cabd"));
//        System.out.println(validAnagrams("bcda","cabdl"));

//        int[] arr1 = {2,7,9,2,8,1,1,9,9};
//        int[] arr2 = {9,1,2,9,8,1,7,9,2};
//        System.out.println(Arrays.toString(anagramMapping(arr1,arr2)));

//        System.out.println(areKAnagrams("abcbaad","aaaeebc",1));
//        System.out.println(areKAnagrams("abcbaad","aaaeebc",2));
//        System.out.println(areKAnagrams("abcbaad","aaaeebc",3));

//        System.out.println(findAllAnagrams("abcabacdcbaa","abac"));

//        System.out.println(countAtMostKDistinct("aabcbcdbca",2));

//        System.out.println(atMostKDistinct("aabcdaabbc",3));

//        int[] arr = {1,1,0,1,0,0,1,0,1,1,0,1,1};
//        System.out.println(maxOnesFlipOneZero(arr));
//        System.out.println(maxOnes(arr,3));

//        System.out.println(countEquivalent(new int[]{2,5,3,5,2,4,1,3,1,4}));

//        System.out.println(kDistinctChars("aabcbcdccbaabb",3));
//        System.out.println(kDistinctChars("aabcbcacbdbbaa",2));

//        System.out.println(countUniqueSubstrings("abcb"));
//        System.out.println(countUniqueSubstrings("abca"));
//        System.out.println(countUniqueSubstrings("abcd"));
//        System.out.println(countUniqueSubstrings("abbacbcdba"));

//        System.out.println(longestUniqueSubstring("abebajklcbcdba"));

//        System.out.println(minWindow2("abbbaccdbcacd"));

//        System.out.println(minWindow1("abccaabbcadabb","abac"));

//        int[] arr = {9,2,7,5,6,23,24,19,17,16,18,19};
//        System.out.println(largestContiguousLength(arr));

//        int[] arr = {2,8,-3,-5,2,-4,6,1,-5};
//        System.out.println(subArrayCount(arr));
//        System.out.println(largestSubArray(arr));

//        int[]arr={2,3,4,4,2,3,5,7,8,5,3,2};
//        System.out.println(kDistinctElements(arr,4));

//        int[] arr= {11,23,45,68,20,29,27,55,32,10,44};
//        System.out.println(pairsDivisibleByK(arr,10));

//        HashMap<String,String> route = new HashMap<>();
//        route.put("chennai","bangalore");
//        route.put("bombay","delhi");
//        route.put("delhi","goa");
//        route.put("goa","chennai");
////        route.put("bangalore","gujrat");
//        System.out.println(sourceDest(route));

//        HashMap<String,String> empMan = new HashMap<>();
//        empMan.put("A","C");
//        empMan.put("B","C");
//        empMan.put("C","F");
//        empMan.put("D","E");
//        empMan.put("E","F");
//        empMan.put("F","F");

//        LRU c = new LRU(2);
//        c.put(1,1);
//        c.put(2,2);
//        System.out.println(c.get(1));
//        c.put(3,3);
//        System.out.println(c.get(2));
    }
}
class LRU {
    int capacity;
    Node head, tail;
    HashMap<Integer, Node> map = new HashMap<>(); // key - it's node's address
    public LRU(int capacity){
        this.capacity = capacity;
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
    }
    private class Node{
        int val, key;
        Node prev, next;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }
    public void put(int key, int val){
        if(capacity == 0) return;
        if(map.containsKey(key)){
            Node n = map.get(key);
            n.val = val;
            remove(n);
            insert(n);
            return;
        }
        if(map.size() == capacity){
            map.remove(tail.prev.key);
            remove(tail.prev);
        }
        Node n = new Node(key, val);
        insert(n);
        map.put(key, n);
    }
    public int get(int key){
        if(map.containsKey(key)){
            Node n = map.get(key);
            remove(n);
            insert(n);
            return n.val;
        }else {
            return -1;
        }
    }

    private void insert(Node n){
        Node hn = head.next;
        head.next = n;
        n.prev = head;
        n.next = hn;
        hn.prev = n;
    }
    private void remove(Node n){
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }
}

class LRUCache {
    private int size;
    // HashMap<Integer, Integer> map;
    HashMap<Integer, DLL.Node> add;
    public LRUCache(int capacity) {
        size = capacity;
        add = new HashMap<>();
    }
    DLL list = new DLL();
    public int get(int key) {
        if(add.containsKey(key)){
            DLL.Node n = add.get(key);
            list.remove(n);
            list.insert(n);
            return n.val;
        }else{
            return -1;
        }
    }

    public void put(int key, int value) {
        if(size == 0) return;
        if(size == list.size){
            add.remove(list.tail.prev.key);
            list.remove(list.tail.prev);
        }
        DLL.Node n = new DLL.Node(key, value);
        if(add.containsKey(key)){
            list.remove(add.get(key));
        }
        list.insert(n);
        add.put(key, n);
    }

    private static class DLL{
        private int size;
        Node head, tail;
        public DLL(){
            head = new Node(0,0);
            tail = new Node(0,0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }
        static class Node{
            int key, val;
            Node prev, next;
            public Node(int key, int val){
                this.key = key;
                this.val= val;
            }
        }
        public void insert(Node n){
            head.next.prev = n;
            n.next = head.next;
            head.next = n;
            n.prev = head;
            size++;
        }
        public void remove(Node n){
            n.prev = n.next;
            n.next = n.prev;
            size--;
        }
    }
}
