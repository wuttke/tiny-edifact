package eu.wuttke.tinyedifact.segments;

import eu.wuttke.tinyedifact.structure.DataSegment;
import eu.wuttke.tinyedifact.structure.SimpleSegmentElement;

public class MessageGroupTrailerSegment extends DataSegment {

	// 	https://www.stylusstudio.com/edifact/40100/UNE_.htm
	public static final String CODE = "UNE";

	public MessageGroupTrailerSegment(int groupControlCount, String groupReference) {
		setCode(CODE);
		getElements().add(new SimpleSegmentElement(Integer.toString(groupControlCount)));
		getElements().add(new SimpleSegmentElement(groupReference));
	}
	
}
