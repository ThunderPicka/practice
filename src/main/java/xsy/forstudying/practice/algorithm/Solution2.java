package xsy.forstudying.practice.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XuSiYu
 * @version 1.0
 * @date 2021-12-31 9:45
 **/
public class Solution2 {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(map.containsKey(target-nums[i])){
                return new int[]{i,map.get(target-nums[i])};
            }
            map.put(nums[i],i);
        }
        return null;
    }

    public static int[] twoSum2(int[] nums, int target) {
        int[] result=new int[2];
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                if(target-nums[i]==nums[j]){
                    result[0]=i;
                    result[1]=j;
                }
            }
        }
        return result;
    }

    public static void main(String[] args){
        int[] result=twoSum(new int[]{2,3,5,6,1},5);
        for(int a:result){
            System.out.println(a);
        }
    }
}
