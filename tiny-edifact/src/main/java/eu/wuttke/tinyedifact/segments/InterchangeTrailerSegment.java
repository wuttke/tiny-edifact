package eu.wuttke.tinyedifact.segments;

import java.util.LinkedList;

import eu.wuttke.tinyedifact.structure.DataSegment;
import eu.wuttke.tinyedifact.structure.SegmentElement;
import eu.wuttke.tinyedifact.structure.SimpleSegmentElement;

public class InterchangeTrailerSegment 
extends DataSegment {
	
	public InterchangeTrailerSegment(int interchangeControlCount, 
			String interchangeControlReference) {
		setCode("UNZ");
		setElements(new LinkedList<SegmentElement>());
		getElements().add(new SimpleSegmentElement(Integer.toString(interchangeControlCount)));
		getElements().add(new SimpleSegmentElement(interchangeControlReference));
	}
	
}
