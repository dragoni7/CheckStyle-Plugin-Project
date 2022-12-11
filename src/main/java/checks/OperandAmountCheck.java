package checks;

import com.puppycrawl.tools.checkstyle.api.DetailAST;

public class OperandAmountCheck extends BaseHalsteadCheck {
	
	@Override
	public void finishTree(DetailAST aAST) {
		super.finishTree(aAST);
		log(aAST.getLineNo(), "Number of Operands is " + this.numOperands + " -SG");
	}
}
