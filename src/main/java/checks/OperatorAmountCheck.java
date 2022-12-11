package checks;

import com.puppycrawl.tools.checkstyle.api.DetailAST;

public class OperatorAmountCheck extends BaseHalsteadCheck {
	@Override
	public void finishTree(DetailAST aAST) {
		super.finishTree(aAST);
		log(aAST.getLineNo(), "Number of Operators is " + this.numOperators + " -SG");
	}
}
