package nez.expr;

import nez.ast.SourcePosition;
import nez.runtime.Instruction;
import nez.runtime.RuntimeCompiler;

public class Capture extends ASTOperation {
	public final New begin;
	int shift;
	Capture(SourcePosition s, New begin, int shift) {
		super(s);
		this.shift = shift;
		this.begin = begin;
	}
	@Override
	public String getPredicate() { 
		return "}";
	}
	@Override
	public String getInterningKey() {
		return shift == 0 ? "}" : "}"+shift;
	}
	@Override
	public Expression checkTypestate(GrammarChecker checker, Typestate c) {
		return this.checkTypestate(checker, c, "}");
	}
	
	@Override
	public Instruction encode(RuntimeCompiler bc, Instruction next, boolean[] dfa) {
		return bc.encodeCapture(this, next, dfa);
	}
}
