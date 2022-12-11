package checks;

import com.puppycrawl.tools.checkstyle.api.DetailAST;

import util.HalsteadMath;

public class HalsteadLengthCheck extends BaseHalsteadCheck {

	private double length = 0;
	
	public double getLength() {
		return length;
	}
	
	@Override
	public void finishTree(DetailAST aAST) {
		super.finishTree(aAST);
		if (this.numOperators != 0 || this.numOperands != 0) {
			length =  HalsteadMath.halsteadLength(this.numOperators, this.numOperands);
			log(aAST.getLineNo(), "Halstead Length is " + length + " -SG");
		}
	}
}
