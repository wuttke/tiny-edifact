package eu.wuttke.tinyedifact.serialization;

public class EdifactSeparators {

	private char componentDataElementSeparator = ':';
	private char dataElementSeparator = '+';
	private char decimalMark = '.';
	private char releaseCharacter = '?';
	private char segmentTerminator = '\'';
	
	public static EdifactSeparators parseServiceStringAdvice(String advice) {
		if (advice.length() != 6)
			throw new IllegalArgumentException("service advice must have 6 characters");
		
		EdifactSeparators s = new EdifactSeparators();
		s.setComponentDataElementSeparator(advice.charAt(0));
		s.setDataElementSeparator(advice.charAt(1));
		s.setDecimalMark(advice.charAt(2));
		s.setReleaseCharacter(advice.charAt(3));
		s.setSegmentTerminator(advice.charAt(5));
		
		return s;
	}
	
	public String generateServiceStringAdvice() {
		return Character.toString(getComponentDataElementSeparator()) +
				getDataElementSeparator() + 
				getDecimalMark() +
				getReleaseCharacter() +
				' ' +
				getSegmentTerminator();
	}
	
	public char getComponentDataElementSeparator() {
		return componentDataElementSeparator;
	}
	
	public void setComponentDataElementSeparator(char componentDataElementSeparator) {
		this.componentDataElementSeparator = componentDataElementSeparator;
	}
	
	public char getDataElementSeparator() {
		return dataElementSeparator;
	}
	
	public void setDataElementSeparator(char dataElementSeparator) {
		this.dataElementSeparator = dataElementSeparator;
	}
	
	public char getDecimalMark() {
		return decimalMark;
	}
	
	public void setDecimalMark(char decimalMark) {
		this.decimalMark = decimalMark;
	}
	
	public char getReleaseCharacter() {
		return releaseCharacter;
	}
	
	public void setReleaseCharacter(char releaseCharacter) {
		this.releaseCharacter = releaseCharacter;
	}
	
	public char getSegmentTerminator() {
		return segmentTerminator;
	}
	
	public void setSegmentTerminator(char segmentTerminator) {
		this.segmentTerminator = segmentTerminator;
	}

}
