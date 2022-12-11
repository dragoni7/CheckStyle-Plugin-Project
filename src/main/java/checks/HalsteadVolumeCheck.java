package checks;

import com.puppycrawl.tools.checkstyle.api.DetailAST;

import util.HalsteadMath;

public class HalsteadVolumeCheck extends BaseHalsteadCheck {

	private double volume = 0;
	
	public double getVolume() {
		return volume;
	}
	
	@Override
	public void finishTree(DetailAST ast) {
		super.finishTree(ast);
		if (this.distinctOperators != 0 || this.distinctOperands != 0) {
			volume = HalsteadMath.halsteadVolume(this.distinctOperators, this.distinctOperands, this.numOperators, this.numOperands);
			log(ast.getLineNo(), "Halstead Volume is " + volume + " -SG");
		}
	}
}
