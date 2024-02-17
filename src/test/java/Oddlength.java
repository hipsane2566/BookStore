import java.util.Arrays;

public class Oddlength {
	
	public static String getOddLength(String s) {
		String tempa [] ;
		if(s.isEmpty()) 
		{
			return "String is empty";
		}else {
			String words [] = s.split(" ");
			tempa = new String [words.length];
			for(int i=0; i<words.length;i++) {
				if(words[i].length()%2==1) {
					tempa[i] =  reverse(words[i]);
				}else {
					tempa[i] = words[i];
				}
			}
		return Arrays.toString(tempa);
		}
		
	}
	public static String reverse(String s) {
		String rev = "";
		for(int j =s.length()-1; j>=0;j--) {
			rev += s.charAt(j);
		}
		return rev;
	}
	public static void main(String [] agrs) {
		// TODO Auto-generated method stub
		String s = "a bc 12faa";
		String result = getOddLength(s);
		System.out.println(result);
	}
}
