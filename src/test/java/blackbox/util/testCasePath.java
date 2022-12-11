package blackbox.util;

public class testCasePath {

	public static String get(String check, String fileName) {
		return "src/test/java/blackbox/cases/" + check + "/" + fileName + ".java";
	}
}
