package xsy.forstudying.practice.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XuSiYu
 * @version 1.0
 * @date 2022-04-02 11:12
 **/
public class Solution6 {
    public int lengthOfLongestSubstring(String s) {
        if(s.length()==0){
            return 0;
        }
        int result=1;
        char[] chars=new char[s.length()];
        s.getChars(0,s.length(),chars,0);
        Map<Character,Integer> duplicateChar=new HashMap<>();
        for(int i=0;i<s.length();i++){
            if(i+1<s.length()){
                if(chars[i]==chars[i+1]){
                    duplicateChar.put(chars[i],duplicateChar.get(chars[i])==null?1:duplicateChar.get(chars[i])+1);
                }else if(duplicateChar.get(chars[i])==null||duplicateChar.get(chars[i])==0){
                    result++;
                    duplicateChar.put(chars[i],duplicateChar.get(chars[i])==null?1:duplicateChar.get(chars[i])+1);
                }
            }
        }
        if(duplicateChar.get(chars[0])!=null&&duplicateChar.get(chars[0])==s.length()){
            return 1;
        }
        if(duplicateChar.get(s.length()-1)!=null&&duplicateChar.get(s.length()-1)>=1){
            result--;
        }
        return result;
    }

    public static void main(String[] args){
        Solution6 solution6=new Solution6();
        System.out.println(solution6.lengthOfLongestSubstring("abcda"));

    }
}
