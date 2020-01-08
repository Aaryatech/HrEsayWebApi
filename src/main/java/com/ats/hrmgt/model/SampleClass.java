package com.ats.hrmgt.model;

 public class SampleClass {
	
	int value;
	String name;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SampleClass [value=" + value + ", name=" + name + "]";
	}
	
	

}
