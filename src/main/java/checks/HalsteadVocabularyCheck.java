package checks;

import com.puppycrawl.tools.checkstyle.api.DetailAST;

import util.HalsteadMath;

public class HalsteadVocabularyCheck extends BaseHalsteadCheck {

	private double vocabulary = 0;
	
	public double getVocabulary() {
		return vocabulary;
	}
	
	@Override
	public void finishTree(DetailAST ast) {
		super.finishTree(ast);
		if (this.distinctOperators != 0 || this.distinctOperands != 0) {
			vocabulary = HalsteadMath.halsteadVocabulary(this.distinctOperators, this.distinctOperands);
			log(ast.getLineNo(), "Halstead Vocabularay is " + vocabulary + " -SG");
		}
	}
}
