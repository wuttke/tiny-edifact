package eu.wuttke.tinyedifact.structure;

import java.util.List;

public class CompositeSegmentElement extends SegmentElement {

	private List<String> values;

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}
	
}
