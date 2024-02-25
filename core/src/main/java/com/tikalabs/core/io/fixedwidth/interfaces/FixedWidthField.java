package com.tikalabs.core.io.fixedwidth.interfaces;

public class FixedWidthField {
	private final int start;
	private final int length;
	private final FieldType type;

	public FixedWidthField(int start, int length, FieldType type) {
		this.start = start;
		this.length = length;
		this.type = type;
	}

	public int getStart() {
		return start;
	}

	public int getLength() {
		return length;
	}

	public FieldType getType() {
		return type;
	}

	public enum FieldType {
		STRING, INTEGER, DOUBLE, DATE
	}
}
