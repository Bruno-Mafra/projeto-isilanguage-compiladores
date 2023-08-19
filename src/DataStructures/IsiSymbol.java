package DataStructures;

public class IsiSymbol {
	protected String name;
	protected boolean isInitialized;
	protected boolean isUsed;
	
	public IsiSymbol(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void markAsInitialized() {
		this.isInitialized = true;
	}
	
	public void markAsUsed() {
		this.isUsed = true;
	}

	public boolean getIsInitialized() {
		return this.isInitialized;
	}
	
	public boolean getIsUsed() {
		return this.isUsed;
	}
	
}
