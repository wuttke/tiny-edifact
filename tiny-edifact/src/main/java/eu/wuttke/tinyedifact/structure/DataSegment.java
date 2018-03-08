package eu.wuttke.tinyedifact.structure;

import java.util.LinkedList;
import java.util.List;

public class DataSegment {

	private String code;
	private String value;
	private List<SegmentElement> elements = new LinkedList<SegmentElement>();

	public String getValue(int group, int key) {
		if (key < 0 || group < 0 || group >= getElements().size())
			return null;
		SegmentElement element = getElements().get(group);
		if (key > 0 && element instanceof SimpleSegmentElement)
			return null;
		if (element instanceof SimpleSegmentElement)
			return ((SimpleSegmentElement)element).getValue();
		else {
			CompositeSegmentElement cse = (CompositeSegmentElement)element;
			return cse.getValue(key);
		}
	}
	
	public void setValue(int group, int key, String value) {
		if (key < 0 || group < 0)
			throw new IllegalArgumentException("need positive key/group");
		while (group >= elements.size())
			elements.add(new SimpleSegmentElement(null));
		
		SegmentElement element = elements.get(group);
		if (key == 0) {
			if (element instanceof SimpleSegmentElement)
				((SimpleSegmentElement)element).setValue(value);
			else 
				((CompositeSegmentElement)element).setValue(key, value);
		} else {
			if (element instanceof SimpleSegmentElement) {
				// need to convert from Simple to Composite
				int index = elements.indexOf(element);
				element = new CompositeSegmentElement(((SimpleSegmentElement)element).getValue());
				elements.set(index, element);
            }

			CompositeSegmentElement composite = (CompositeSegmentElement)element;
			composite.setValue(key, value);
		}
	}

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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getCode());
		if (value != null) {
			sb.append(":" + value);
		}
		sb.append("> ");
		
		for (int i = 0; i < elements.size(); i++) {
			if (i > 0)
				sb.append("; ");
			sb.append(elements.get(i).toString());
		}
		return sb.toString();
	}
	
}
