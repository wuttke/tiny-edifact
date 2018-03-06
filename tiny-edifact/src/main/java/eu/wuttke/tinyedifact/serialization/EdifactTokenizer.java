package eu.wuttke.tinyedifact.serialization;

import java.util.LinkedList;
import java.util.List;

// inspired by https://github.com/metroplex-systems/edifact/blob/master/src/Tokenizer.php
public class EdifactTokenizer {
	
	private StringBuffer message;
	private EdifactSeparators separators;
	private Character currentChar;
	private StringBuilder string;
	private boolean isEscaped;
	
	public List<EdifactToken> getTokens(String message, EdifactSeparators separators) {
		this.message = new StringBuffer(message);
		this.separators = separators;
		this.currentChar = null;
		this.string = new StringBuilder();
		
		readNextChar();
		
		List<EdifactToken> tokens = new LinkedList<EdifactToken>();
		EdifactToken token;
		while ((token = getNextToken()) != null) {
			tokens.add(token);
		}
		return tokens;
	}

	public EdifactToken getNextToken() {
		if (endOfMessage())
			return null;
		
		if (!isEscaped) {
			if (currentChar.charValue() == separators.getComponentDataElementSeparator()) {
				storeCurrentCharAndReadNext();
				return new EdifactToken(EdifactTokenType.ComponentDataElementSeparator,
						extractStoredChars());
			}

			if (currentChar.charValue() == separators.getDataElementSeparator()) {
				storeCurrentCharAndReadNext();
				return new EdifactToken(EdifactTokenType.DataElementSeparator,
						extractStoredChars());
			}
			
			if (currentChar.charValue() == separators.getSegmentTerminator()) {
				storeCurrentCharAndReadNext();
				EdifactToken token = new EdifactToken(EdifactTokenType.SegmentTerminator,
						extractStoredChars());
				
				// ignore trailing space after segment end
				while (currentChar != null && 
					(currentChar.charValue() == '\r' || currentChar.charValue() == '\n')) {
					readNextChar();
				}
				
				return token;
			}
		}
		
		while (!isControlCharacter()) {
			if (endOfMessage())
				throw new RuntimeException("unexpected end of EDI message");
			storeCurrentCharAndReadNext();
		}
		
		return new EdifactToken(EdifactTokenType.Content, extractStoredChars());
	}

	private boolean endOfMessage() {
		return currentChar == null;
	}

	private String extractStoredChars() {
		String s = string.toString();
		string = new StringBuilder();
		return s;
	}

	private void storeCurrentCharAndReadNext() {
		string.append(currentChar);
		readNextChar();
	}

	private boolean isControlCharacter() {
		if (isEscaped)
			return false;
		if (currentChar.charValue() == separators.getComponentDataElementSeparator())
			return true;
		if (currentChar.charValue() == separators.getDataElementSeparator())
			return true;
		if (currentChar.charValue() == separators.getSegmentTerminator())
			return true;
		return false;
	}

	private void readNextChar() {
		currentChar = getNextChar();
		
		if (isEscaped) {
			// if the last character was escaped, this one can't possibly be
			isEscaped = false;
		}
		
		if (currentChar != null && currentChar.charValue() == separators.getReleaseCharacter()) {
			currentChar = getNextChar();
			isEscaped = true;
		}
	}

	private Character getNextChar() {
		if (message.length() == 0)
			return null;
		Character c = message.charAt(0);
		message.deleteCharAt(0);
		return c;
	}
	
}
