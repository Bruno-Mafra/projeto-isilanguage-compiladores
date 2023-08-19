package CodeGeneration;

import DataStructures.DataType;

public class CommandLeitura extends AbstractCommand {
	private String id;
	private String type;
	
	public CommandLeitura(String id, DataType id_type) {
		this.id = id;
		
		if (id_type == DataType.STR) {
			this.type = "str";
		} else if (id_type == DataType.INT) {
			this.type = "int";
		} else if (id_type == DataType.FLOAT) {
			this.type = "float";
		} else if (id_type == DataType.BOOL) {
			this.type = "bool";
		} else {
			throw new RuntimeException("Erro verificando tipo de id "+id+" durante geração do comando de leitura.");
		}
	}
	
	@Override
	public String generateJavaCode() {
		// TODO Auto-generated method stub
		String output_code;
		if (this.type.equals("str")) {
			output_code = this.id+" = reader.nextLine();";
		} else if (this.type.equals("float")) {
			output_code = this.id+" = reader.nextDouble();";
		} else if (this.type.equals("int")) {
			output_code = this.id+" = reader.nextInt();";
		} else {
			output_code = this.id+" = reader.hasNextInt();";
		}
		
		return output_code;
	}

	@Override
	public String generateCCode() {
		// TODO Auto-generated method stub
		String output_code;
		if (this.type.equals("str")) {
			output_code = "scanf(\"%s\", &"+this.id+");";
		} else if (this.type.equals("float")) {
			output_code = "scanf(\"%f\", &"+this.id+");";
		} else if (this.type.equals("int")) {
			output_code = "scanf(\"%d\", &"+this.id+");"; 
		} else {
			output_code = "scanf(\"%d\", &"+this.id+");";
		}
		
		return output_code;
	}

	@Override
	public String generatePythonCode() {
		// TODO Auto-generated method stub
		String output_code;
		if (this.type.equals("str")) {
			output_code = this.id+" = input()";
		} else if (this.type.equals("float")) {
			output_code = this.id+" = float(input())";
		} else if (this.type.equals("int")) {
			output_code = this.id+" = int(input())";
		} else {
			output_code = this.id+" = bool(input())";
		}
		
		return output_code;
	}
	
}
