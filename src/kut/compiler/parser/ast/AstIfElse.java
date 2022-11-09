package kut.compiler.parser.ast;

import java.io.IOException;

import kut.compiler.compiler.CodeGenerator;
import kut.compiler.compiler.CondLabels;
import kut.compiler.exception.CompileErrorException;
import kut.compiler.lexer.Token;
import kut.compiler.symboltable.ExprType;

public class AstIfElse extends AstNode 
{
	/**
	 * 
	 */
	protected Token t;
	
	protected AstNode	expr;
	protected ExprType	etype;

	protected AstNode	thenClause;
	protected AstNode	elseClause;
	
	/**
	 * @param t
	 */
	public AstIfElse(AstNode expr, AstNode thenClause, AstNode elseClause, Token t)
	{
		this.expr 		= expr;
		this.thenClause 	= thenClause;
		this.elseClause 	= elseClause;
		this.t 			= t;
	}
	
	
	/**
	 * @param gen
	 */
	public void preprocessStringLiterals(CodeGenerator gen) {
		this.expr.preprocessStringLiterals(gen);
		this.thenClause.preprocessStringLiterals(gen);;
		
		if (this.elseClause != null) {
			this.elseClause.preprocessStringLiterals(gen);
		}	}

	/**
	 *
	 */
	@Override
	protected void printTree(int indent) {
		this.println(indent, "if : ");
		expr.printTree(indent + 1);
		this.println(indent, "then: ");
		thenClause.printTree(indent + 1);
		if (elseClause != null) {
			this.println(indent, "else: ");
			elseClause.printTree(indent + 1);
		}
	}


	
	/**
	 *
	 */
	@Override
	public void cgen(CodeGenerator gen) throws IOException, CompileErrorException
	{	
		CondLabels labels = gen.generateCondLabels();
		
		this.expr.cgen(gen);
		gen.printCode	("cmp rax, 0");
		if (this.elseClause != null) {
			gen.printCode	("je " + labels.labelFalse);
		}
		else {
			gen.printCode	("je " + labels.labelCondEnd);
		}
		
		this.thenClause.cgen(gen);
		
		if (this.elseClause != null) {
			gen.printCode	("jmp " + labels.labelCondEnd);
			gen.printLabel	(labels.labelFalse);
			this.elseClause.cgen(gen);
		}
		
		gen.printLabel	(labels.labelCondEnd);
		
		return; 
	}
	

	/**
	 *
	 */
	/**
	 *
	 */
	public void preprocessGlobalVariables(CodeGenerator gen)
	{
		return;
	}
	
	/**
	 *
	 */
	public ExprType checkTypes(CodeGenerator gen) throws CompileErrorException
	{
		etype = expr.checkTypes(gen);
		if (etype != ExprType.BOOLEAN) {
			throw new CompileErrorException("the condtional expression for the if statement must be boolean.: " + t);
		}
		
		this.thenClause.checkTypes(gen);
		if (this.elseClause != null) {
			this.elseClause.checkTypes(gen);
		}
		
		return ExprType.VOID;
	}
}
