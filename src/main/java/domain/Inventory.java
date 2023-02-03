package domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.BadRequestException;

public class Inventory {

    @JsonProperty("id")
    private String id;
    @JsonProperty("item_name")
    private String item_name;
    @JsonProperty("item_quantity")
    private int item_quantity;
    @JsonProperty("item_category")
    private ItemCategory item_category;
    @JsonProperty("item_location")
    private ItemLocation item_location;

    public Inventory(String id, String item_name, int item_quantity, ItemCategory item_category, ItemLocation item_location)
    {
        this.id = id;
        this.item_name = item_name;
        this.item_quantity = item_quantity;
        this.item_category = item_category;
        this.item_location = item_location;
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
    public int getItem_quantity()
    {
        return item_quantity;
    }
    public void setItem_quantity(int quantity) throws BadRequestException
    {
        if(quantity<0)
            throw new BadRequestException();
        item_quantity = quantity;
    }

    public String getItem_name()
    {
        return item_name;
    }
    public void setItem_name(String name) throws BadRequestException
    {
        if(name==null)
            throw new BadRequestException();
        item_name = name;
    }
    public ItemCategory getItem_category()
    {
        return item_category;
    }
    public void setItemCategoryId(ItemCategory itemCategory) throws BadRequestException
    {
        if(itemCategory==null)
            throw new BadRequestException();
        this.item_category = itemCategory;
    }

    public ItemLocation getItem_location()
    {
        return item_location;
    }
    public void setItem_location(ItemLocation item_location) throws BadRequestException
    {
        if(item_location ==null)
            throw new BadRequestException();
        this.item_location = item_location;
    }

    public void validateObject() throws BadRequestException
    {
        if(item_name ==null)
            throw new BadRequestException();
        if(item_category ==null)
            throw new BadRequestException();
        if(item_location ==null)
            throw new BadRequestException();
        if(item_quantity <0)
            throw new BadRequestException();
    }

    @Override
    public String toString()
    {
        return "{" + id + "," + item_name + "," + item_quantity + "," + "{" + item_category.getId() + "," + item_category.getcategory_name() + "}" + "," + "{"+ item_location.getId() + "," + item_location.getLocation_name() + "}"+"}";
    }


}
