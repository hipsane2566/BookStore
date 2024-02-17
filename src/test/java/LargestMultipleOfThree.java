import java.util.Arrays;

public class LargestMultipleOfThree {
	public static String largestMultipleOfThree(int []digit) {
		int temp ;
		String s ="";
		for(int i =0; i<digit.length; i++) {
			for(int j = 0; j<digit.length-1;j++) { //0,1
				if(digit[i]<digit[j+1]) { //8!<1, 8<9
						temp = digit[j+1];// temp = 9
						digit[j+1]=digit[j];// 
						digit[j] = temp;
					
				}
			}
		}return arrayToString(digit);
	}
	
	 public static String arrayToString(int[] array) {
	        StringBuilder sb = new StringBuilder();
	        for (int num : array) {
	            sb.append(num);
	        }
	        return sb.toString();
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int digits [] = {8,1,9};
		System.out.println(largestMultipleOfThree(digits));
	}

}
