package DataStructures;

public class IsiVariable extends IsiSymbol {
	
	private String type;
	private String value;
	
	public IsiVariable(String name, String type, String value) {
		super(name);
		this.type = type;
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "IsiVariable [name="+ name + ", type=" + type + ", value=" + value + "]";
	}
}
