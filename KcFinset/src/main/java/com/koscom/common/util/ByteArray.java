package com.koscom.common.util;

import java.nio.charset.Charset;

public class ByteArray {
	private transient byte[] array;
	private transient int offset;
	private transient int length;
	
	public ByteArray(byte[] array, int offset, int length) {
		this.array = array;
		this.offset = offset;
		this.length = length;
	}
	
	public ByteArray(byte[] array) {
		this(array, 0, array.length);
	}
	
	public ByteArray() {
	}
	
	public void setBytes(byte[] array) {
		setBytes(array, 0, array.length);
	}

	public void setBytes(byte[] array, int length) {
		setBytes(array, 0, length);
	}

	public void setBytes(byte[] array, int offset, int length) {
		this.array = array;
		this.offset = offset;
		this.length = length;
	}
	
	public boolean equals(Object other) {
		if (other instanceof ByteArray) {
			ByteArray ob = (ByteArray) other;
			return ByteArray.equals(array, offset, length, ob.array, ob.offset, ob.length);
		}
		return false;
	}
	
	public int hashCode() {
		byte[] larray = array;
		int hash = length;
		for (int i = 0; i < length; i++) {
			hash += larray[i + offset];
		}
		return hash;
	}

	public final byte[] getArray() {
		return this.array;
	}
	
	public final int getOffset() {
		return this.offset;
	}
	
	public final int getLength() {
		return this.length;
	}
	
	public final void setLength(int newLength) {
		this.length = newLength;
	}
	
	public String toString() {
		return new String(this.array, this.offset, this.length);
	}
	
	public String toString(Charset charset) {
		return new String(this.array, this.offset, this.length, charset);
	}
	
	public String toString(int offset, int length, Charset charset) {
		return new String(this.array, offset, length, charset);
	}

	private static boolean equals(byte[] a, int aOffset, int aLength, byte[] b, int bOffset, int bLength) {
		if (aLength != bLength)
			return false;
		
		for (int i = 0; i < aLength; i++) {
			if (a[i + aOffset] != b[i + bOffset])
				return false;
		}
		return true;
	}
}
