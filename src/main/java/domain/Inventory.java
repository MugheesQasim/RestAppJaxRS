package domain;

public class Inventory {
    private String id;
    private String itemName;
    private int itemQuantity;
    private ItemCategory itemCategory;
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
}
