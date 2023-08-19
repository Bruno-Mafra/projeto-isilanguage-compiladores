package CodeGeneration;

import DataStructures.DataType;

public class CommandAttrib extends AbstractCommand {
	private String id;
	private String expr;
	private String exprTipo;
	
	public CommandAttrib(String id, String expr, DataType exprTipo) {
		this.id = id;
		this.expr = expr;

		if (exprTipo == DataType.STR) {
			this.exprTipo = "str";
		} else if (exprTipo == DataType.INT) {
			this.exprTipo = "int";
		} else if (exprTipo == DataType.FLOAT) {
			this.exprTipo = "float";
		} else if (exprTipo == DataType.BOOL) {
			this.exprTipo = "bool";
		} else {
			throw new RuntimeException("Erro verificando tipo de expressão "+expr+" durante geração do comando de escrita.");
		}
	}
	
	@Override
	public String generateJavaCode() {
		// TODO Auto-generated method stub
		String outputCode = this.id + " = " + this.expr + ";";
		if (this.exprTipo.equals("bool")) {
			outputCode = outputCode.replace("&", "&&").replace("|", "||");
			outputCode = outputCode.replaceAll("True", "true").replaceAll("False", "false");
		}
		
		return outputCode;
	}

	@Override
	public String generateCCode() {
		// TODO Auto-generated method stub
		String outputCode = this.id + " = " + this.expr + ";";
		
		if (this.exprTipo.equals("bool")) {
			outputCode = outputCode.replaceAll("False", "false").replaceAll("True", "true");
			outputCode = outputCode.replace("&", "&&").replace("|", "||");
		}
		
		return outputCode;
	}

	@Override
	public String generatePythonCode() {
		// TODO Auto-generated method stub
		String outputCode = this.id + " = " + this.expr;
		
		if (this.exprTipo.equals("bool")) {
			outputCode = outputCode.replace("&", " and ").replace("|", " or ").replace("!", "not ");
		}
		
		return outputCode;
	}

}
