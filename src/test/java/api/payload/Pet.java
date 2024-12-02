package api.payload;

import java.math.BigInteger;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pet {

	@JsonProperty("id")
	private BigInteger petId;
	
	private Category category;

	@JsonProperty("name")
	private String petName;
	
	private List<String> photoUrls;
	
	private List<Tag> tags;
	
	private String status;

	public BigInteger getPetId() {
		return petId;
	}

	public void setPetId(BigInteger petId) {
		this.petId = petId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public List<String> getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
