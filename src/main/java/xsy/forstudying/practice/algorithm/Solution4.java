package xsy.forstudying.practice.algorithm;

import java.util.Arrays;

/**
 * @author XuSiYu
 * @version 1.0
 * @date 2022-02-11 14:16
 **/
public class Solution4 {
    public static int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int numsLength=nums.length;
        if(k==1){
            return 0;
        }
        else if(1<k&&k<numsLength){
            int min=nums[k-1]-nums[0];
            int temp=numsLength-k;
            for(int i=0;i<temp;i++){
                min=min<nums[k-1]-nums[i]?min:nums[k-1]-nums[i];
            }
        }
        else if(k>=numsLength){
            return nums[numsLength-1];
        }
        return 0;
    }

    public static void main(String[] args){
        int[] nums=new int[]{87063,61094,44530,21297,95857,93551,9918};
        System.out.println(minimumDifference(nums,6));
    }
}
