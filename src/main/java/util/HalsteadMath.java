package util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class HalsteadMath {

	/**
	* Calculates Halstead length of the program. N1 = Total number of occurrences of operators, N2 = Total number of occurrences of operands
	*/
	public static int halsteadLength(int N1, int N2) {
		if (N1 >= 0 && N2 >= 0) {
			return N1 + N2;
		}
		return 0;
	}
	
	/**
	* Calculates Halstead difficulty of the program. N2 = total number of occurrences of operands, n1 = number of distinct operators, n2 = number of distinct operands
	*/
	public static double halsteadDifficulty(int N2, int n1, int n2) {
		if (n2 > 0 && n1 >= 0 && N2 >= 0) {
			// Use BigDecimal to fix double result precision
			BigDecimal result = BigDecimal.valueOf(((double)n1 / 2D) * ((double)N2 / (double)n2));
			result = result.setScale(2, RoundingMode.CEILING);
			return result.doubleValue();
		}
		
		return 0D;
	}
	
	/**
	* Calculates Halstead vocabulary of the program. n1 = number of distinct operators, n2 = number of distinct operands
	*/
	public static int halsteadVocabulary(int n1, int n2) {
		if (n1 >= 0 && n2 >= 0) {
			return n1 + n2;
		}
		
		return 0;
	}
	
	/**
	* Calculates Halstead volume of the program. n1 = number of distinct operators, n2 = number of distinct operands, N1 = Total number of occurrences of operators, N2 = Total number of occurrences of operands
	*/
	public static double halsteadVolume(int n1, int n2, int N1, int N2) {
		if (n1 >= 0 && n2 >= 0 && N1 >= 0 && N2 >= 0) {
			BigDecimal result = BigDecimal.valueOf(halsteadLength(N1, N2) * (Math.log(halsteadVocabulary(n1, n2)) / Math.log(2)));
			result = result.setScale(2, RoundingMode.CEILING);
			return result.doubleValue();
		}
		
		return 0D;
	}
	
	/**
	* Calculates Halstead effort of the program. N1 = total number of occurrences of operators, N2 = total number of occurrences of operands, n1 = number of distinct operators, n2 = number of distinct operands
	*/
	public static double halsteadEffort(int N1, int N2, int n1, int n2) {
		if (n1 >= 0 && n2 >= 0 && N1 >= 0 && N2 >= 0) {
			return halsteadDifficulty(N2, n1, n2) * halsteadVolume(n1, n2, N1, N2);
		}
		
		return 0D;
	}
}
