package eu.wuttke.tinyedifact.serialization;

public class EdifactToken {

	private EdifactTokenType type;
	private String value;
	
	public EdifactToken(EdifactTokenType type, String value) {
		this.type = type;
		this.value = value;
	}

	public EdifactTokenType getType() {
		return type;
	}
	
	public void setType(EdifactTokenType type) {
		this.type = type;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
