package domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Inventory {

    @JsonProperty("id")
    private String id;
    @JsonProperty("item_name")
    private String itemName;
    @JsonProperty("item_quantity")
    private int itemQuantity;
    @JsonProperty("item_category")
    private ItemCategory itemCategory;
    @JsonProperty("item_location")
    private ItemLocation itemLocation;

    public Inventory(String id,String itemName, int itemQuantity,ItemCategory itemCategoryId,ItemLocation itemLocationId)
    {
        this.id = id;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemCategory = itemCategoryId;
        this.itemLocation = itemLocationId;
    }

    public Inventory()
    {

    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }
    public int getItemQuantity()
    {
        return itemQuantity;
    }
    public void setItemQuantity(int quantity)
    {
        itemQuantity = quantity;
    }

    public String getItemName()
    {
        return itemName;
    }
    public void setItemName(String name)
    {
        itemName = name;
    }
    public ItemCategory getItemCategory()
    {
        return itemCategory;
    }
    public void setItemCategoryId(ItemCategory itemCategory)
    {
        this.itemCategory = itemCategory;
    }

    public ItemLocation getItemLocation()
    {
        return itemLocation;
    }
    public void setItemLocation(ItemLocation itemLocation)
    {
        this.itemLocation = itemLocation;
    }

    @Override
    public String toString()
    {
        return id + "," + itemName + " ," + itemQuantity + "," + itemCategory.getCategoryName() + "," + itemLocation.getLocationName();
    }
}
