package checks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class BaseHalsteadCheck extends AbstractCheck {
	
	private static final ArrayList<Integer> operatorTokens = new ArrayList<Integer>(Arrays.asList(
			TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR,
			TokenTypes.BSR_ASSIGN, TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, TokenTypes.COMMA, TokenTypes.DEC, TokenTypes.DIV,
			TokenTypes.DIV_ASSIGN, TokenTypes.DOT, TokenTypes.EQUAL, TokenTypes.GE, TokenTypes.GT, TokenTypes.INC, TokenTypes.LAND,
			TokenTypes.LE, TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LT, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN,
			TokenTypes.MOD, TokenTypes.NOT_EQUAL, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.QUESTION,
			TokenTypes.SL, TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR, TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS,
			TokenTypes.UNARY_PLUS, TokenTypes.DO_WHILE, TokenTypes.LITERAL_WHILE, TokenTypes.FOR_EACH_CLAUSE, TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_IF,
			TokenTypes.LITERAL_SWITCH, TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_RETURN, TokenTypes.LITERAL_DEFAULT, TokenTypes.LITERAL_CONTINUE, TokenTypes.LITERAL_BREAK,
			TokenTypes.RBRACK, TokenTypes.RCURLY, TokenTypes.RPAREN, TokenTypes.VARIABLE_DEF, TokenTypes.SEMI));
	
	private static final ArrayList<Integer> operandTokens = new ArrayList<Integer>(Arrays.asList(
			TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.IDENT,
			TokenTypes.NUM_LONG, TokenTypes.CHAR_LITERAL,  TokenTypes.STRING_LITERAL));
	
	private HashMap<Integer, Integer> operators = new HashMap<Integer, Integer>();
	private HashMap<String, Integer> operands = new HashMap<String, Integer>();
	
	private List<Integer> linesToIgnore = new ArrayList<Integer>();
	
	protected int distinctOperators = 0;
	protected int distinctOperands = 0;
	protected int numOperators = 0;
	protected int numOperands = 0;
	
	public HashMap<Integer, Integer> getOperatorsMap() {
		return operators;
	}
	
	public void putOperator(int tokenType, int count) {
		operators.put(tokenType, count);
	}
	
	public HashMap<String, Integer> getOperandsMap() {
		return operands;
	}
	
	public void putOperand(String key, int count) {
		operands.put(key, count);
	}
	
	public int getOperatorCount() {
		return numOperators;
	}
	
	public int getOperandCount() {
		return numOperands;
	}
	
	public int getDistinctOperatorCount() {
		return distinctOperators;
	}
	
	public int getDistinctOperandCount() {
		return distinctOperands;
	}
	
	
	
	@Override
	public int[] getDefaultTokens() {
		return new int [] {
			TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF, TokenTypes.METHOD_DEF, TokenTypes.ASSIGN, TokenTypes.BAND, TokenTypes.BAND_ASSIGN, TokenTypes.BNOT, TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BSR,
			TokenTypes.BSR_ASSIGN, TokenTypes.BXOR, TokenTypes.BXOR_ASSIGN, TokenTypes.COLON, TokenTypes.COMMA, TokenTypes.DEC, TokenTypes.DIV,
			TokenTypes.DIV_ASSIGN, TokenTypes.DOT, TokenTypes.EQUAL, TokenTypes.GE, TokenTypes.GT, TokenTypes.INC, TokenTypes.INDEX_OP, TokenTypes.LAND,
			TokenTypes.LE, TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LNOT, TokenTypes.LOR, TokenTypes.LT, TokenTypes.MINUS, TokenTypes.MINUS_ASSIGN,
			TokenTypes.MOD, TokenTypes.NOT_EQUAL, TokenTypes.PLUS, TokenTypes.PLUS_ASSIGN, TokenTypes.POST_DEC, TokenTypes.POST_INC, TokenTypes.QUESTION,
			TokenTypes.SL, TokenTypes.SL_ASSIGN, TokenTypes.SR, TokenTypes.SR_ASSIGN, TokenTypes.STAR, TokenTypes.STAR_ASSIGN, TokenTypes.UNARY_MINUS,
			TokenTypes.UNARY_PLUS, TokenTypes.DO_WHILE, TokenTypes.LITERAL_WHILE, TokenTypes.FOR_EACH_CLAUSE, TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_IF,
			TokenTypes.LITERAL_SWITCH, TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_RETURN, TokenTypes.LITERAL_DEFAULT, TokenTypes.LITERAL_CONTINUE, TokenTypes.LITERAL_BREAK,
			TokenTypes.RBRACK, TokenTypes.RCURLY, TokenTypes.SEMI, TokenTypes.TYPE, TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT,
			TokenTypes.NUM_LONG, TokenTypes.CHAR_LITERAL,  TokenTypes.STRING_LITERAL};
	}
	
	@Override
    public void visitToken(DetailAST aAST) {
		int tokenType = aAST.getType();
		
		// add package def and import lines to ignored lines list.
		if (tokenType == TokenTypes.PACKAGE_DEF || tokenType == TokenTypes.IMPORT || tokenType == TokenTypes.STATIC_IMPORT) {
			linesToIgnore.add(aAST.getLineNo());
			return;
		}
		// ignore package def and import lines.
		if (!linesToIgnore.contains(aAST.getLineNo())) {
			if (operatorTokens.contains(tokenType)) {
				if (getOperatorsMap().containsKey(tokenType)) {
					putOperator(tokenType, operators.get(tokenType) + 1);
				}
				else {
					putOperator(tokenType, 1);
				}
			}
			
			String operandTxt = aAST.getText();
			
			if (operandTokens.contains(tokenType) && !((aAST.getParent().getType() == TokenTypes.TYPE) && (aAST.getType() == TokenTypes.IDENT))) {
				if (getOperandsMap().containsKey(operandTxt)) {
					putOperand(operandTxt, operands.get(operandTxt) + 1);
				}
				else {
					putOperand(operandTxt, 1);
				}
			}
		}
    }
	
	public void beginTree(DetailAST aAST) {
		distinctOperators = 0;
		distinctOperands = 0;
		numOperators  = 0;
		numOperands = 0;
		this.getOperandsMap().clear();
		this.getOperatorsMap().clear();
	}
	
	public void finishTree(DetailAST aAST) {
		distinctOperators = this.getOperatorsMap().keySet().size();
		distinctOperands = this.getOperandsMap().keySet().size();
		
		for (int key : this.getOperatorsMap().keySet()) {
			numOperators += this.getOperatorsMap().get(key);
		}
		
		for (String key : this.getOperandsMap().keySet()) {
			numOperands += this.getOperandsMap().get(key);
		}
	}

	@Override
	public int[] getAcceptableTokens() {
		return this.getDefaultTokens();
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}
}
