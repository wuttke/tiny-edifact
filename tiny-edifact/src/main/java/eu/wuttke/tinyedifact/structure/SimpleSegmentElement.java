package eu.wuttke.tinyedifact.structure;

public class SimpleSegmentElement extends SegmentElement {

	private String value;

	public SimpleSegmentElement(String v) {
		setValue(v);
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		if (getValue() == null)
			return "NULL";
		else
			return "'" + getValue() + "'";
	}
	
	@Override
	public boolean isEmpty() {
		return value == null || value.length() == 0;
	}
	
}
