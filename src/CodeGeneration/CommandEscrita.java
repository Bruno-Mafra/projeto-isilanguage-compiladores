package CodeGeneration;

import DataStructures.DataType;

public class CommandEscrita extends AbstractCommand {
	private String expr;
	private String tipo;
	
	public CommandEscrita(String expr, DataType tipo) {
		this.expr = expr;

		if (tipo == DataType.STR) {
			this.tipo = "str";
		} else if (tipo == DataType.INT) {
			this.tipo = "int";
		} else if (tipo == DataType.FLOAT) {
			this.tipo = "float";
		} else if (tipo == DataType.BOOL) {
			this.tipo = "bool";
		} else {
			throw new RuntimeException("Erro verificando tipo de expressão "+expr+" durante geração do comando de escrita.");
		}
	}
	
	@Override
	public String generateJavaCode() {
		// TODO Auto-generated method stub
		String outputCode = "System.out.println("+this.expr+");";
		
		outputCode = outputCode.replace("\n", "\\n");

		if (this.tipo.equals("bool")) {
			outputCode = outputCode.replace("&", "&&").replace("|", "||");
			outputCode = outputCode.replaceAll("True", "true").replaceAll("False", "false");
		}
		
		return outputCode;
	}

	@Override
	public String generateCCode() {
		// TODO Auto-generated method stub
		String cExpression = this.expr;
		
		if (this.tipo.equals("bool")) {
			cExpression = cExpression.replaceAll("False", "false").replaceAll("True", "true");
			cExpression = cExpression.replace("&", "&&").replace("|", "||");
		}
		
		String outputCode;
		
		if (this.tipo.equals("str")) {
			outputCode = "printf(\"%s\", "+cExpression+");";
		} else if (this.tipo.equals("int")) {
			outputCode = "printf(\"%d\", "+cExpression+");";
		} else if (this.tipo.equals("float")) {
			outputCode = "printf(\"%f\", "+cExpression+");";
		} else {
			outputCode = "printf(\"%d\", "+cExpression+");";
		}
		
		outputCode = outputCode.replace("\n", "\\n");
		
		return outputCode;
	}

	@Override
	public String generatePythonCode() {
		// TODO Auto-generated method stub
		String outputCode = "print("+this.expr+")";
		
		if (this.tipo.equals("bool")) {
			outputCode = outputCode.replace("&", " and ").replace("|", " or ").replace("!", " not ");
		}
		
		outputCode = outputCode.replace("\n", "\\n");
		
		return outputCode;
	}

}
