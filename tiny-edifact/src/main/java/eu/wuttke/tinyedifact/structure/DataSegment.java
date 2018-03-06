package eu.wuttke.tinyedifact.structure;

import java.util.List;

public class DataSegment {

	private String code;
	private String value;
	private List<SegmentElement> elements;
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public List<SegmentElement> getElements() {
		return elements;
	}
	
	public void setElements(List<SegmentElement> elements) {
		this.elements = elements;
	}
	
}
