package api.payload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Category {
	
	@JsonProperty("id")
    private int categoryid;
	
	@JsonProperty("name")
    private String categoryName;

    public Category(int categoryid, String categoryName) {
        this.categoryid = categoryid;
        this.categoryName = categoryName;
    }

    public int getId() {
        return categoryid;
    }

    public void setId(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
