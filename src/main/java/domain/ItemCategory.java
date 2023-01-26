package domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemCategory {
    @JsonProperty("id")
    private String id;
    @JsonProperty("category_name")
    private String categoryName;

    public ItemCategory(String id,String categoryName)
    {
        this.setId(id);
        this.setCategoryName(categoryName);
    }
    public ItemCategory()
    {

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
