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

	public String getValue(int key) {
		if (key < 0)
			throw new IllegalArgumentException("key must be positive");
		if (key >= getValues().size())
			return null;
		else
			return getValues().get(key);
	}

	public void setValue(int key, String value) {
		if (key < 0)
			throw new IllegalArgumentException("key must be positive");
		while (key >= values.size())
			values.add(null);
		values.set(key, value);
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

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}
	
}
