package checks;

import com.puppycrawl.tools.checkstyle.api.DetailAST;

import util.HalsteadMath;

public class HalsteadEffortCheck extends BaseHalsteadCheck {
	
	private double effort = 0;
	
	public double getEffort() {
		return effort;
	}

	@Override
	public void finishTree(DetailAST ast) {
		super.finishTree(ast);
		if (this.distinctOperators != 0 || this.distinctOperands != 0 || this.numOperators != 0) {
			effort = HalsteadMath.halsteadEffort(this.numOperators, this.numOperands, this.distinctOperators, this.distinctOperands);
			log(ast.getLineNo(), "Halstead Effort is " + effort + " -SG");
		}
	}
}
