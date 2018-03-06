package eu.wuttke.tinyedifact.structure;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CompositeSegmentElement extends SegmentElement {

	private List<String> values;
	
	public CompositeSegmentElement(String... values) {
		setValues(new LinkedList<String>(
				Arrays.asList(values)));
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < values.size(); i++) {
			if (i > 0)
				sb.append(", ");
			String value = values.get(i);
			sb.append(value != null ? "'" + value + "'" : "NULL");
		}
		sb.append("]");
		return sb.toString();
	}
	
}
