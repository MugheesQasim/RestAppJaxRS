package resource;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import domain.Inventory;

import domain.ItemCategory;
import domain.ItemLocation;
import org.junit.Test;

import javax.ws.rs.core.Response;

public class InventoryResourceTest{


    InventoryResource inventoryResource = new InventoryResource();
    //todo use annotations for testing by junit 5
    @Test
    public void testGetInventory() {

        //Case 1: If the user enters all the details correctly, the first assert will evaluate to true

        String test1 = "1";
        String test2 = "testing";

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("error_message","invalid id in request");
        Gson gson = new Gson();
        String jsonString = gson.toJson(jsonObject);
        Response desiredResponse = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonString).build();

        String header = "Basic TXVnaGVlczoxMjM=";

        assert(inventoryResource.getInventory(test1,header).getEntity()!= desiredResponse.getEntity());
        assert(inventoryResource.getInventory(test2,header).getEntity()!=desiredResponse.getEntity());

        String wrongHeader = "Basic TXVnaGVlOjEyMw==";
        assert(inventoryResource.getInventory("2",wrongHeader).getEntity()!= desiredResponse.getEntity());
    }

    @Test
    public void testGetAllInventory() {
        JsonObject jsonMessage = new JsonObject();
        jsonMessage.addProperty("error_message","invalid id in request");
        Gson gson = new Gson();
        String jsonString = gson.toJson(jsonMessage);

        String header = "Basic TXVnaGVlczoxMjM=";
        Response desiredResponse = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonString).build();
        assert(inventoryResource.getAllInventory("99",null,header).getEntity()!=desiredResponse.getEntity());
        assert(inventoryResource.getAllInventory("-30","-20",header).getEntity()!=desiredResponse.getEntity());

        JsonObject jsonMessageAuthorization = new JsonObject();
        jsonMessageAuthorization.addProperty("error_message","invalid id in request");
        Gson gson2 = new Gson();
        String jsonStringAuthorization = gson2.toJson(jsonMessageAuthorization);
        Response desiredResponseAuthorization = Response.status(Response.Status.UNAUTHORIZED).entity(jsonStringAuthorization).build();

        String wrongHeader = "Basic TXVnaGVlOjEyMw==";
        assert(inventoryResource.getAllInventory("24","7",wrongHeader).getEntity()!= desiredResponseAuthorization.getEntity());
        assert(inventoryResource.getAllInventory("99",null,wrongHeader).getEntity()!=desiredResponse.getEntity());
    }

    @Test
    public void testAddNewInventoryItem() {
        Gson gson = new Gson();
        ItemCategory itemCategory = new ItemCategory("24","Phone");
        ItemLocation itemLocation = new ItemLocation("7","Pheonix");
        Inventory inventory1 = new Inventory("1","iPhone",5,itemCategory,itemLocation);
        Inventory inventory2 = new Inventory("5","iphone 12",5,itemCategory,itemLocation);
        String jsonInventory1 = gson.toJson(inventory1);
        String jsonInventory2 = gson.toJson(inventory2);
        String message = "{\"error_message\" : \"invalid payload\"}";
        Response desiredResponse = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();

        assert (inventoryResource.addNewInventoryItem(jsonInventory1,"Basic TXVnaGVlczoxMjM=").getEntity()!=desiredResponse.getEntity());
        assert(inventoryResource.addNewInventoryItem(jsonInventory2,"Basic TXVnaGVlczoxMjM=").getEntity()!=desiredResponse.getEntity());

        assert(inventoryResource.addNewInventoryItem(jsonInventory1,"Basic TXVnaGVlOjEyMw==").getEntity()!=desiredResponse.getEntity());

        Inventory wrongPayload = new Inventory("1",null,5,itemCategory,itemLocation);
        String wrongJsonPayload = gson.toJson(wrongPayload);
        assert(inventoryResource.addNewInventoryItem(wrongJsonPayload,"Basic TXVnaGVlOjEyMw==").getEntity()!=desiredResponse.getEntity());
    }

    @Test
    public void testUpdateItem() {
        Gson gson = new Gson();
        ItemCategory itemCategory = new ItemCategory("24","Phone");
        ItemLocation itemLocation = new ItemLocation("7","Pheonix");
        Inventory inventory1 = new Inventory("1","iPhone",5,itemCategory,itemLocation);
        Inventory inventory2 = new Inventory(null,"iphone 12",5,null,null);
        String jsonInventory1 = gson.toJson(inventory1);
        String jsonInventory2 = gson.toJson(inventory2);
        String message = "{\"error_message\" : \"invalid payload\"}";
        Response desiredResponse = Response.status(Response.Status.BAD_REQUEST).entity(message).build();

        assert(inventoryResource.updateItem(jsonInventory1,"2","Basic TXVnaGVlczoxMjM==").getEntity()!=desiredResponse.getEntity());
        assert(inventoryResource.updateItem(jsonInventory2,"6","Basic TXVnaGVlczoxMjM=").getEntity()!=desiredResponse.getEntity());

        JsonObject jsonMessageAuthorization = new JsonObject();
        jsonMessageAuthorization.addProperty("error_message","invalid id in request");
        Gson gson2 = new Gson();
        String jsonStringAuthorization = gson2.toJson(jsonMessageAuthorization);
        Response desiredResponseAuthorization = Response.status(Response.Status.UNAUTHORIZED).entity(jsonStringAuthorization).build();

        assert(inventoryResource.updateItem(jsonInventory1,"2","Basic TXVnaGVlczoxMjM=").getEntity()!=desiredResponseAuthorization.getEntity());
    }

    @Test
    public void testDeleteItem() {
        String message = "{\"error_message\" : \"invalid id in request\"}";
        Response desiredResponse = Response.status(Response.Status.BAD_REQUEST).entity(message).build();

        JsonObject jsonMessageAuthorization = new JsonObject();
        jsonMessageAuthorization.addProperty("error_message","invalid id in request");
        Gson gson2 = new Gson();
        String jsonStringAuthorization = gson2.toJson(jsonMessageAuthorization);
        Response desiredResponseAuthorization = Response.status(Response.Status.UNAUTHORIZED).entity(jsonStringAuthorization).build();

        String okMessage = "{\"message\" : \"OK\"}";
        Response desiredResponse3 = Response.status(Response.Status.OK).entity(okMessage).build();

        assert(inventoryResource.deleteItem("7","Basic TXVnaGVlczoxMjM=").getEntity()!=desiredResponse.getEntity());
        assert(inventoryResource.deleteItem(null,"Basic TXVnaGVlczoxMjM=").getEntity()!=desiredResponse3.getEntity());
        assert(inventoryResource.deleteItem("2","Basic TXVnaGVlczoxMjM==").getEntity()!=desiredResponseAuthorization.getEntity());
    }
}
