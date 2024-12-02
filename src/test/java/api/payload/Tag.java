package api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tag {
	@JsonProperty("id")
	private int tagId;
	
	@JsonProperty("name")
	private String tagName;

	public Tag(int tagId, String tagName) {
		this.tagId = tagId;
		this.tagName = tagName;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	@Override
	public String toString() {
		return "Tag{id=" + tagId + ", name='" + tagName + "'}";
	}
}
