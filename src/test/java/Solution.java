class Solution {
    public static int numIdenticalPairs(int[] nums) {
        int count[]=new int[101];
        int sum=0;
        for(int num:nums)
        {
            sum+=count[num];
            count[num]++;
        }
        return sum;
    }
    public static void main(String [] args) {
    	int nums [] = {1,2,3,1,1,3};
    	System.out.println(numIdenticalPairs(nums));
    }
}