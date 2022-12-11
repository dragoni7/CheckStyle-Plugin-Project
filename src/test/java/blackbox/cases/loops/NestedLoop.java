package blackbox.cases.loops;

public class NestedLoop {
	public void testCase() {
		// nested loop
		int k = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				while (k < 10) {
					k++;
				}
				k = 0;
			}
		}
	}
}
