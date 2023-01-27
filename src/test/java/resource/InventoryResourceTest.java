package resource;

import domain.Inventory;
import junit.framework.Assert;
import junit.framework.TestCase;
import resource.InventoryResource;

import javax.ws.rs.core.Response;

public class InventoryResourceTest extends TestCase {

    InventoryResource inventoryResource = InventoryResource.getInstance();

    public void testGetInventory() {
        String test1 = "1";
        String test2 = "testing";

        Assert.assertEquals(inventoryResource.getInventory(test1), Response.status(Response.Status.BAD_REQUEST).build());
        Assert.assertEquals(inventoryResource.getInventory(test2),Response.status(Response.Status.BAD_REQUEST).build());
    }

    public void testGetAllInventory() {
        Assert.assertEquals(inventoryResource.getAllInventory("99","99"),Response.status(Response.Status.BAD_REQUEST));
        Assert.assertEquals(inventoryResource.getAllInventory("-30","-20"),Response.status(Response.Status.BAD_REQUEST));
    }

    public void testAddNewInventoryItem() {
        Inventory inventory1 = new Inventory("1","iPhone",5,null,null);
        Inventory inventory2 = new Inventory(null,"iphone 12",5,null,null);
        Assert.assertEquals(inventoryResource.addNewInventoryItem(inventory1),Response.status(Response.Status.BAD_REQUEST));
        Assert.assertEquals(inventoryResource.addNewInventoryItem(inventory2),Response.status(Response.Status.BAD_REQUEST));
    }

    public void testUpdateItem() {
        Inventory inventory1 = new Inventory("1","iPhone",5,null,null);
        Inventory inventory2 = new Inventory(null,"iphone 12",5,null,null);
        Assert.assertEquals(inventoryResource.updateItem(inventory1,"5"),Response.status(Response.Status.BAD_REQUEST));
        Assert.assertEquals(inventoryResource.updateItem(inventory2,"6"),Response.status(Response.Status.BAD_REQUEST));
    }

    public void testDeleteItem() {
        Assert.assertEquals(inventoryResource.deleteItem("99"),Response.status(Response.Status.BAD_REQUEST));
        Assert.assertEquals(inventoryResource.deleteItem("-30"),Response.status(Response.Status.BAD_REQUEST));
    }
}
