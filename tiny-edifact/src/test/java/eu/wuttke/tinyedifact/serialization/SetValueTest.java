package eu.wuttke.tinyedifact.serialization;

import eu.wuttke.tinyedifact.structure.DataSegment;
import junit.framework.TestCase;

public class SetValueTest extends TestCase {

	public void testExpandSimple() {
		DataSegment ds = new DataSegment();
		ds.setValue(0, 0, "Hello");
		ds.setValue(3, 0, "World");
		assertEquals("null> 'Hello'; NULL; NULL; 'World'", ds.toString());
	}
	
	public void testExpandComposite() {
		DataSegment ds = new DataSegment();
		ds.setValue(3, 2, "Hi");
		assertEquals("null> NULL; NULL; NULL; [NULL, NULL, 'Hi']", ds.toString());
	}
	
	public void testSwitchToComposite() {
		DataSegment ds = new DataSegment();
		ds.setValue(0, 0, "Hi");
		assertEquals("null> 'Hi'", ds.toString());
		ds.setValue(0, 1, "There");
		assertEquals("null> ['Hi', 'There']", ds.toString());
	}
}
