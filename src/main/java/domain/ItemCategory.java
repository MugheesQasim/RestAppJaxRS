package domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemCategory {
    @JsonProperty("id")
    private String id;
    @JsonProperty("category_name")
    private String category_name;

    public ItemCategory(String id,String category_name)
    {
        this.setId(id);
        this.setCategory_name(category_name);
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getcategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
