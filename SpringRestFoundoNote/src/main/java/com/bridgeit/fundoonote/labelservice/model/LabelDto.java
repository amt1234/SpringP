package com.bridgeit.fundoonote.labelservice.model;

public class LabelDto {
	private long labelId;
	private String labelName;

	public long getLabelId() {
		return labelId;
	}

	public void setLabelId(long labelId) {
		this.labelId = labelId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public LabelDto() {

	}

	public LabelDto(long labelId, String labelName) {

		this.labelId = labelId;
		this.labelName = labelName;
	}

}
