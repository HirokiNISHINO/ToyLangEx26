package kut.compiler.lexer;

public final class TokenClass 
{
	public static final int EOF		 		= -1;
	public static final int IntLiteral 		= -2;
	public static final int Identifier 		= -3;
	public static final int INT				= -4;
	public static final int GLOBAL			= -5;
	public static final int LOCAL			= -6;
	public static final int RETURN			= -7;
	public static final int PRINT			= -8;
	public static final int StringLiteral 	= -9;
	public static final int STRING			= -10;
	public static final int ERROR			= -11;
	public static final int DOUBLE			= -12;
	public static final int DoubleLiteral	= -13;
	public static final int BOOLEAN			= -14;
	public static final int BooleanLiteral	= -15;
	public static final int EQ				= -16;
	public static final int NEQ 			= -17;
	public static final int GTEQ			= -18;
	public static final int LTEQ			= -19;
	public static final int IF				= -20;
	public static final int ELSE 			= -21;
	public static final int WHILE			= -22;
	
	public static String getTokenClassString(int c) {
		switch(c){
		case EOF:
			return "EOF";
		
		case IntLiteral:
			return "IntLiteral";
		
		case Identifier:
			return "Identifier";
		
		case INT:
			return "INT";
		
		case GLOBAL:
			return "GLOBAL";
			
		case LOCAL:
			return "LOCAL";
			
		case RETURN:
			return "RETURN";
			
		case PRINT:
			return "PRINT";
			
		case StringLiteral:
			return "StringLiteral";

		case STRING:
			return "STRING";
					
		case DoubleLiteral:
			return "DoubleLiteral";

		case DOUBLE:
			return "double";
			
		case ERROR:
			return "ERROR";
			
		case BooleanLiteral:
			return "BooleanLiteral";
			
		case BOOLEAN:
			return "BOOLEAN";
			
		case EQ:
			return "EQ";
			
		case NEQ:
			return "NEQ";			
			
		case GTEQ:
			return "GTEQ";
			
		case LTEQ:
			return "LTEQ";
			
		case IF:
			return "IF";
			
		case ELSE:
			return "ELSE";
			
		case WHILE:
			return "WHILE";
			
		default:
			return "" + (char)c;
		}
		
	}
}
