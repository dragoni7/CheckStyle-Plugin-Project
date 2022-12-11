package checks;

import com.puppycrawl.tools.checkstyle.api.DetailAST;

import util.HalsteadMath;

public class HalsteadDifficultyCheck extends BaseHalsteadCheck {
	
	private double difficulty = 0;
	
	public double getDifficulty() {
		return difficulty;
	}
	
	@Override
	public void finishTree(DetailAST ast) {
		super.finishTree(ast);
		if (this.distinctOperators != 0 || this.distinctOperands != 0 || this.numOperands != 0) {
			difficulty = HalsteadMath.halsteadDifficulty(this.distinctOperators, this.numOperands, this.distinctOperands);
			log(ast.getLineNo(), "Halstead Difficulty is " + difficulty + " -SG");
		}
	}

}
