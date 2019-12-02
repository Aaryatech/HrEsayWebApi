package com.ats.hrmgt.model;

public class TypeListWithAmt {
	private float amt;
	private int typeId;
	private String typeName;
	
	
	public float getAmt() {
		return amt;
	}
	public void setAmt(float amt) {
		this.amt = amt;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	@Override
	public String toString() {
		return "TypeListWithAmt [amt=" + amt + ", typeId=" + typeId + ", typeName=" + typeName + "]";
	}
	
	
	
}
