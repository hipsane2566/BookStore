
public class Goodpairs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] nums = {1,2,3,1,1,3};
		 int count =0;
	        for (int i = 0; i<nums.length; i++){
	            for(int j = 1 ; j<nums.length; j++){
	                if(nums[i]==nums[j] && i<j){
	                    count = count+1; //1,2,3
	                }
	            }
	        }System.out.println(count);
	}
}
