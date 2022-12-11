package checks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import util.HalsteadMath;

public class HalsteadMathTests {
	
	private int n1 = 14;
	private int n2 = 10;
	private int N1 = 53;
	private int N2 = 38;
	
	// Testing values taken from Halstead's Software Metrics: https://www.geeksforgeeks.org/software-engineering-halsteads-software-metrics/

	@Test
	public void halsteadLengthTest() {
		Assertions.assertEquals(91, HalsteadMath.halsteadLength(N1, N2)); // Line 9, True
		Assertions.assertEquals(0, HalsteadMath.halsteadLength(-1, N2)); // Line 9, False
		Assertions.assertEquals(0, HalsteadMath.halsteadLength(N1, -1)); // Line 9, False
		Assertions.assertNotNull(HalsteadMath.halsteadLength(N1, N2));
	}
	
	@Test
	public void halsteadDifficultyTest() {
		Assertions.assertEquals(26.6, HalsteadMath.halsteadDifficulty(N2, n1, n2)); // Line 20, True
		Assertions.assertEquals(0, HalsteadMath.halsteadDifficulty(-1, n1, n2)); // Line 20, False
		Assertions.assertEquals(0, HalsteadMath.halsteadDifficulty(N2, -1, n2)); // Line 20, False
		Assertions.assertEquals(0, HalsteadMath.halsteadDifficulty(N2, n1, 0)); // Line 20, False
		Assertions.assertNotNull(HalsteadMath.halsteadDifficulty(N1, n1, n2));
	}
	
	@Test
	public void halsteadVocabularyTest() {
		Assertions.assertEquals(24, HalsteadMath.halsteadVocabulary(n1, n2)); // Line 37, True
		Assertions.assertEquals(0, HalsteadMath.halsteadVocabulary(-1, n2)); // Line 37, False
		Assertions.assertEquals(0, HalsteadMath.halsteadVocabulary(n1, -1)); // Line 37, False
		Assertions.assertNotNull(HalsteadMath.halsteadVocabulary(n1, n2));
	}
	
	@Test
	public void halsteadVolumeTest() {
		Assertions.assertEquals(417.24, HalsteadMath.halsteadVolume(n1, n2, N1, N2)); // Line 49, True
		Assertions.assertEquals(0, HalsteadMath.halsteadVolume(-1, n2, N1, N2)); // Line 49, False
		Assertions.assertEquals(0, HalsteadMath.halsteadVolume(n1, -1, N1, N2)); // Line 49, False
		Assertions.assertEquals(0, HalsteadMath.halsteadVolume(n1, n2, -1, N2)); // Line 49, False
		Assertions.assertEquals(0, HalsteadMath.halsteadVolume(n1, n2, N1, -1)); // Line 49, False
		Assertions.assertNotNull(HalsteadMath.halsteadVolume(n1, n2, N1, N2));
	}
	
	@Test
	public void halsteadEffortTest() {
		Assertions.assertEquals(417.24 * 26.6, HalsteadMath.halsteadEffort(N1, N2, n1, n2)); // Line 49, True
		Assertions.assertEquals(0, HalsteadMath.halsteadEffort(-1, N2, n1, n2)); // Line 49, False
		Assertions.assertEquals(0, HalsteadMath.halsteadEffort(N1, -1, n1, n2)); // Line 49, False
		Assertions.assertEquals(0, HalsteadMath.halsteadEffort(N1, N2, -1, n2)); // Line 49, False
		Assertions.assertEquals(0, HalsteadMath.halsteadEffort(N1, N2, n1, -1)); // Line 49, False
		Assertions.assertNotNull(HalsteadMath.halsteadEffort(N1, N2, n1, n2));
	}
}
