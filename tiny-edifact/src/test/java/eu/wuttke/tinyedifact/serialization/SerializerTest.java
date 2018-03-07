package eu.wuttke.tinyedifact.serialization;

import java.util.Date;
import java.util.LinkedList;

import eu.wuttke.tinyedifact.exchange.InterchangeFormatter;
import eu.wuttke.tinyedifact.messages.Interchange;
import eu.wuttke.tinyedifact.messages.Message;
import eu.wuttke.tinyedifact.segments.InterchangeHeaderSegment;
import eu.wuttke.tinyedifact.segments.InterchangeTrailerSegment;
import eu.wuttke.tinyedifact.segments.MessageHeaderSegment;
import eu.wuttke.tinyedifact.segments.MessageTrailerSegment;
import junit.framework.TestCase;

public class SerializerTest 
extends TestCase {

	public void testSerialize() {
		Message m = new Message();
		// UNH+00001+KOTR:01:001:KV'
		m.setMessageHeaderSegment(new MessageHeaderSegment("123", "KOTR", "01", "001", "KV"));
		m.setMessageTrailerSegment(new MessageTrailerSegment(2, "123"));
		Interchange i = new Interchange();
		Date d = new Date();
		i.setInterchangeHeader(
			new InterchangeHeaderSegment(
				"104027544", "999999999", d, "00078", null, "KOTR0000078"));
		i.setInterchangeTrailer(new InterchangeTrailerSegment(1, "00078"));
		i.setMessages(new LinkedList<Message>());
		i.getMessages().add(m);
		EdifactSeparators sep = new EdifactSeparators();
		InterchangeFormatter ser = new InterchangeFormatter();
		String str = ser.formatInterchange(i, sep, true);
		System.out.println(str);
		assertTrue(str.length() > 0);
	}
	
}
