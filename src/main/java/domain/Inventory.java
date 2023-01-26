package domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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

    public Inventory(String id,String itemName, int itemQuantity,String itemCategoryId,String itemLocationId)
    {
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
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

    public ItemLocation getItemLocationId()
    {
        return itemLocation;
    }
    public void setItemLocationId(ItemLocation itemLocation)
    {
        this.itemLocation = itemLocation;
    }

    @Override
    public String toString()
    {
        return id + "," + itemName + " ," + itemQuantity + "," + itemCategory.getCategoryName() + "," + itemLocation.getLocationName();
    }
}
