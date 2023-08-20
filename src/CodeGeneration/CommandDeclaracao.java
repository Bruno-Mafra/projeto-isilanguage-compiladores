package CodeGeneration;

public class CommandDeclaracao extends AbstractCommand {
	private String tipo;
	private String id;
	private String expr;
	
	public CommandDeclaracao(String tipo, String id, String expr) {
		this.tipo = tipo;
		this.id = id;
		this.expr = expr;
	}

	@Override
	public String generateJavaCode() {
		// TODO Auto-generated method stub
		String javaType;
		String javaExpr;
		String outputCode;
		
		
		if (this.tipo.equals("str")) {
			javaType = "String";
		} else if (this.tipo.equals("bool")) {
			javaType = "boolean";
		} else if (this.tipo.equals("float")) {
			javaType = "double";
		} else {
			javaType = this.tipo;
		}
		
		outputCode = javaType + " " + this.id;
		
		if (!(this.expr == null)) {
			javaExpr = this.expr;
			if (this.tipo.equals("bool")) {
				javaExpr = javaExpr.replaceAll("True", "true").replaceAll("False", "false");
				javaExpr = javaExpr.replace("&", "&&").replace("|", "||");
			}
			outputCode = outputCode + " = " + javaExpr;
		}
		
		outputCode = outputCode + ";";
		
		return outputCode;
	}

	@Override
	public String generateCCode() {
		// TODO Auto-generated method stub
		String outputCode;
		
		if (this.tipo.equals("str")) {
			outputCode = "char "+this.id+"[1024]";
		} else {
			outputCode = this.tipo + " " + this.id;
		}
		
		if (!(this.expr == null)) {
			outputCode = outputCode + " = " + this.expr;
			if (this.tipo.equals("bool")) {
				outputCode = outputCode.replaceAll("False", "false").replaceAll("True", "true");
				outputCode = outputCode.replace("&", "&&").replace("|", "||");
			}
		}
		
		outputCode = outputCode + ";";
		
		return outputCode;
	}

	@Override
	public String generatePythonCode() {
		// TODO Auto-generated method stub
		String pythonExpr;
		String outputCode;
		
		outputCode = this.id + ":" + this.tipo;
		
		if (!(this.expr == null)) {
			outputCode = outputCode + " = " + this.expr;
			if (this.tipo.equals("bool")) {
				outputCode = outputCode.replace("&", " and ").replace("|", " or ").replace("!", "not ");
			}
		} else {
			outputCode = outputCode + " = None";
		}
		
		return outputCode;
	}

}
