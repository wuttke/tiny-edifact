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
	
}
