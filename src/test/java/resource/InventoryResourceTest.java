package resource;

import domain.Inventory;
import junit.framework.Assert;
import junit.framework.TestCase;

import javax.ws.rs.core.Response;

public class InventoryResourceTest extends TestCase {


    InventoryResource inventoryResource = new InventoryResource();
    //todo use annotations for testing by junit 5
    public void testGetInventory() {
        String test1 = "1";
        String test2 = "testing";

        String message = "{\"error_message\" : \"invalid id in request\"}";
        Response desiredResponse = Response.status(Response.Status.BAD_REQUEST).entity(message).build();

        Assert.assertEquals(inventoryResource.getInventory(test1).getEntity(), desiredResponse.getEntity());
        Assert.assertEquals(inventoryResource.getInventory(test2).getEntity(),desiredResponse.getEntity());
    }

    public void testGetAllInventory() {
        String message = "{\"error_message\" : \"invalid id in request\"}";
        Response desiredResponse = Response.status(Response.Status.BAD_REQUEST).entity(message).build();

        Assert.assertEquals(inventoryResource.getAllInventory("99",null).getEntity(),desiredResponse.getEntity());
        Assert.assertEquals(inventoryResource.getAllInventory("-30","-20").getEntity(),desiredResponse.getEntity());
    }

    public void testAddNewInventoryItem() {
        Inventory inventory1 = new Inventory("1","iPhone",5,null,null);
        Inventory inventory2 = new Inventory(null,"iphone 12",5,null,null);

        String message = "{\"error_message\" : \"invalid payload\"}";
        Response desiredResponse = Response.status(Response.Status.BAD_REQUEST).entity(message).build();

        Assert.assertEquals(inventoryResource.addNewInventoryItem(inventory1,"Basic TXVnaGVlczoxMjM=").getEntity(),desiredResponse.getEntity());
        Assert.assertEquals(inventoryResource.addNewInventoryItem(inventory2,"Basic TXVnaGVlczoxMjM=").getEntity(),desiredResponse.getEntity());
    }

    public void testUpdateItem() {
        Inventory inventory1 = new Inventory("1","iPhone",5,null,null);
        Inventory inventory2 = new Inventory(null,"iphone 12",5,null,null);

        String message = "{\"error_message\" : \"invalid payload\"}";
        Response desiredResponse = Response.status(Response.Status.BAD_REQUEST).entity(message).build();

        Assert.assertEquals(inventoryResource.updateItem(inventory1,"5","Basic TXVnaGVlczoxMjM=").getEntity(),desiredResponse.getEntity());
        Assert.assertEquals(inventoryResource.updateItem(inventory2,"6","Basic TXVnaGVlczoxMjM=").getEntity(),desiredResponse.getEntity());
    }

    public void testDeleteItem() {
        String message = "{\"error_message\" : \"invalid id in request\"}";
        Response desiredResponse = Response.status(Response.Status.BAD_REQUEST).entity(message).build();

        String authenticationMessage = "{\"error_message\" : \"Unauthorized user\"}";
        Response desiredResponse2 = Response.status(Response.Status.BAD_REQUEST).entity(authenticationMessage).build();

        String okMessage = "{\"message\" : \"OK\"}";
        Response desiredResponse3 = Response.status(Response.Status.OK).entity(okMessage).build();

//        Assert.assertEquals(inventoryResource.deleteItem("7","Basic TXVaGVlc").getEntity(),desiredResponse.getEntity());
        Assert.assertEquals(inventoryResource.deleteItem("7","Basic TXVnaGVlczoxMjM=").getEntity(),desiredResponse.getEntity());
        Assert.assertEquals(inventoryResource.deleteItem("17","Basic TXVnaGVlczoxMjM=").getEntity(),desiredResponse3.getEntity());
    }
}
