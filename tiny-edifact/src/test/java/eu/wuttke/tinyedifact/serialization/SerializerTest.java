package eu.wuttke.tinyedifact.serialization;

import java.util.Date;
import java.util.LinkedList;

import eu.wuttke.tinyedifact.segments.InterchangeHeaderSegment;
import eu.wuttke.tinyedifact.segments.InterchangeTrailerSegment;
import eu.wuttke.tinyedifact.structure.Interchange;
import eu.wuttke.tinyedifact.structure.Message;
import junit.framework.TestCase;

public class SerializerTest 
extends TestCase {

	public void testSerialize() {
		Interchange i = new Interchange();
		Date d = new Date();
		i.setInterchangeHeader(
			new InterchangeHeaderSegment(
				"104027544", "999999999", d, "00078", null, "KOTR0000078"));
		i.setInterchangeTrailer(new InterchangeTrailerSegment(1, "00078"));
		i.setMessages(new LinkedList<Message>());
		EdifactSeparators sep = new EdifactSeparators();
		EdifactSerializer ser = new EdifactSerializer();
		String str = ser.serializeInterchange(i, sep, true);
		assertTrue(str.length() > 0);
	}
	
}