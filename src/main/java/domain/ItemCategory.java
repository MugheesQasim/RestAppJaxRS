package domain;

public class ItemCategory {
    private String id;
    private String categoryName;

    public ItemCategory(String id,String categoryName)
    {
        this.setId(id);
        this.setCategoryName(categoryName);
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
