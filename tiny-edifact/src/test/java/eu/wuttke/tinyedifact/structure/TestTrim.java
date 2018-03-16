package eu.wuttke.tinyedifact.structure;

import junit.framework.TestCase;

public class TestTrim 
extends TestCase {

	public void testTrimComposite() {
		CompositeSegmentElement cse;
		
		cse = new CompositeSegmentElement("", "", "");
		assertEquals(3, cse.getValues().size());
		cse.trimEmptyTrailingValues();
		assertEquals(0, cse.getValues().size());

		cse = new CompositeSegmentElement("X", "", "");
		assertEquals(3, cse.getValues().size());
		cse.trimEmptyTrailingValues();
		assertEquals(1, cse.getValues().size());

		cse = new CompositeSegmentElement("", "X", "");
		assertEquals(3, cse.getValues().size());
		cse.trimEmptyTrailingValues();
		assertEquals(2, cse.getValues().size());
		
		cse = new CompositeSegmentElement("", "", "X");
		assertEquals(3, cse.getValues().size());
		cse.trimEmptyTrailingValues();
		assertEquals(3, cse.getValues().size());		
	}
	
	public void testTrimSegment() {
		DataSegment ds = new DataSegment();
		ds.setValue(3, 2, "1");
		ds.setValue(5, 2, "");
		assertEquals(6, ds.getElements().size());
		ds.trimEmptyTrailingElements();
		assertEquals(4, ds.getElements().size());
		ds.setValue(3, 4, "");
		
		CompositeSegmentElement cse = (CompositeSegmentElement)ds.getElements().get(3); 
		assertEquals(5, cse.getValues().size());
		ds.trimEmptyTrailingValuesInElements();
		assertEquals(3, cse.getValues().size());
	}
	
}
