package eu.wuttke.tinyedifact.serialization;

import java.util.List;

import junit.framework.TestCase;

public class TokenizerTest 
extends TestCase {

	public void testTokenizer() {
		String message = "UNA:+,? 'UNB+UNOC:3+104027544+999999999+171129:1030+00078++KOTR0000078'UNH+00001+KOTR:01:001:KV'IDK+100323099+02+DRÄGER & HANSE BKK'VDT+19930101+20110331'FKT+01'VKG+04+104529255'NAM+01+DRÄGER & HANSE BKK'ANS+1+18057+Rostock+Doberaner Str. 114'UNT+000008+00001'UNH+00002+KOTR:01:001:KV'IDK+100820488+02+Brandenburgische BKK'VDT+19930101'FKT+01'VKG+03+104027544+4++08'NAM+01+Brandenburgische+Betriebskrankenkasse'ANS+1+15890+Eisenhüttenstadt+Werkstr. 10'UNT+000008+00002'";
		EdifactSeparators es = new EdifactSeparators();
		EdifactTokenizer et = new EdifactTokenizer();
		List<EdifactToken> tokens = et.getTokens(message, es);
		assertTrue(tokens.size() > 0);
	}
	
}
