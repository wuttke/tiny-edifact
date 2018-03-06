package eu.wuttke.tinyedifact.structure;

import java.util.Arrays;
import java.util.List;

public class CompositeSegmentElement extends SegmentElement {

	private List<String> values;
	
	public CompositeSegmentElement(String... values) {
		setValues(Arrays.asList(values));
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}
	
}
