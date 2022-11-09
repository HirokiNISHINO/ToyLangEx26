package kut.compiler.parser.ast;

import java.io.IOException;

import kut.compiler.compiler.CodeGenerator;
import kut.compiler.compiler.WhileLabels;
import kut.compiler.exception.CompileErrorException;
import kut.compiler.lexer.Token;
import kut.compiler.symboltable.ExprType;

public class AstWhile extends AstNode 
{
	/**
	 * 
	 */
	protected Token t;
	
	protected AstNode	expr;
	protected AstNode	loopBody;
	
	protected ExprType 	etype;
	
	/**
	 * @param t
	 */
	public AstWhile(AstNode expr, AstNode loopBody, Token t)
	{
		this.expr 		= expr;
		this.loopBody 	= loopBody;
		this.t = t;
	}
	
	/**
	 * @param gen
	 */
	public void preprocessStringLiterals(CodeGenerator gen) {
		expr.preprocessStringLiterals(gen);
		loopBody.preprocessStringLiterals(gen);
		return;
	}
	
	/**
	 *
	 */
	public void printTree(int indent) {
		this.println(indent, "while:" + t);
		expr.printTree(indent + 1);
		loopBody.printTree(indent + 1);
	}

	
	/**
	 *
	 */
	public ExprType checkTypes(CodeGenerator gen) throws CompileErrorException
	{		
		etype = expr.checkTypes(gen);
	
		if (etype != ExprType.BOOLEAN) {
			throw new CompileErrorException("the condition expression must be boolean :" + t);
		}
		
		loopBody.checkTypes(gen);
		
		return ExprType.VOID;
	}
	

	/**
	 *
	 */
	@Override
	public void cgen(CodeGenerator gen) throws IOException, CompileErrorException
	{	
		WhileLabels l = gen.generateWhileLabels();
		
		gen.printLabel	(l.labelEntry);
		
		expr.cgen(gen);

		gen.printCode	("cmp rax, 0");
		gen.printCode	("je " + l.labelExit);
		
		loopBody.cgen(gen);
		
		gen.printCode	("jmp " + l.labelEntry);
		
		gen.printLabel(l.labelExit);
		
		return;
	}
	
	/**
	 *
	 */
	public void preprocessLocalVariables(CodeGenerator gen) throws CompileErrorException
	{
		this.expr.preprocessLocalVariables(gen);
		this.loopBody.preprocessLocalVariables(gen);
	}


}
