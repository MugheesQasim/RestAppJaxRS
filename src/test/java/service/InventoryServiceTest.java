package service;

import domain.Inventory;
import domain.ItemCategory;
import domain.ItemLocation;

import junit.framework.*;

public class InventoryServiceTest extends TestCase{
    InventoryService inventoryService = InventoryService.getInstance();

    public void testGetInventoryById() {
        System.out.println("Inside test get inventoryById");

        String id = "-25";
        String id2 = "test";

        try
        {
            Assert.assertNotNull(inventoryService.getInventoryById("7"));
            Assert.assertEquals(inventoryService.getInventoryById(id),null);
            Assert.assertEquals(inventoryService.getInventoryById(id2),null);
        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());
        }
    }

    public void testGetInventoryAll() {
        System.out.println("Inside test get inventory all");

        try
        {
            Assert.assertNotNull(inventoryService.getInventoryAll());
        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());
        }
    }

    public void testGetInventoryByCategory() {
        System.out.println("Inside test get inventory by category");

        String category = "99";
        String category2 = null;
        String category3 = "24";

        try
        {
            Assert.assertNotNull(inventoryService.getInventoryByCategory(category3));
            Assert.assertNotNull(inventoryService.getInventoryByCategory(category));
            Assert.assertNotNull(inventoryService.getInventoryByCategory(category2));
        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());
        }
    }

    public void testGetInventoryByLocation() {
        System.out.println("Inside test get inventory by location");

        String location = "99";
        String location2 = "location";
        String location3 = "7";

        try
        {
            Assert.assertNotNull(inventoryService.getInventoryByLocation(location3));
            Assert.assertNotNull(inventoryService.getInventoryByCategory(location));
            Assert.assertNotNull(inventoryService.getInventoryByCategory(location2));
        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());
        }
    }

    public void testGetInventoryByCategoryAndLocation() {
        System.out.println("Inside test get inventory by category and location");

        String category = "99";
        String location = "99";
        String location2 = "location";
        String category2 = "category";

        try
        {
            Assert.assertNotNull(inventoryService.getInventoryByCategoryAndLocation("24","7"));
            Assert.assertNull(inventoryService.getInventoryByCategoryAndLocation(null,null));
            Assert.assertNotNull(inventoryService.getInventoryByCategoryAndLocation(category,location));
            Assert.assertNotNull(inventoryService.getInventoryByCategoryAndLocation(category2,location2));
        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());
        }
    }

    public void testAddItem() {
        System.out.println("Inside test add item");

        ItemCategory itemCategory = new ItemCategory("31","Phone");
        ItemLocation itemLocation = new ItemLocation("29","Phoenix");

        Inventory inventory = new Inventory("five","iPhone 13",10,itemCategory,itemLocation);
        Inventory inventory1 = new Inventory("5","iPhone 13",10,null,null);


        try
        {
            String header = "Basic TXVnaGVlczoxMjM=";
            if(inventoryService.validateUser(header))
                System.out.println("User is validated");
            else
                System.out.println("Invalid User");
            Assert.assertNotNull(inventoryService.addItem(inventory));
            Assert.assertNotNull(inventoryService.addItem(inventory1));
        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());
        }
    }

    public void testUpdateInventory() {
        System.out.println("Inside test update item");

        ItemCategory itemCategory = new ItemCategory("24","Phone");
        ItemLocation itemLocation = new ItemLocation("7","Phoenix");

        Inventory inventory = new Inventory("6","iPhone 13",10,itemCategory,itemLocation);
        Inventory inventory1 = new Inventory("-20","iPhone 11",2,itemCategory,itemLocation);


        try
        {
            Assert.assertNotNull(inventoryService.updateInventory(inventory1,"6"));
            Assert.assertSame(inventoryService.updateInventory(inventory1,"6"),inventory1);
        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());
        }
    }

    public void testDeleteInventory() {
        System.out.println("Inside test delete item");

        ItemCategory itemCategory = new ItemCategory("24","Phone");
        ItemLocation itemLocation = new ItemLocation("7","Phoenix");

        Inventory inventory = new Inventory("5","iPhone 13",10,itemCategory,itemLocation);
        Inventory inventory1 = new Inventory("6","iPhone 11",2,itemCategory,itemLocation);


        try
        {
            inventoryService.deleteInventory("6");
            Assert.assertNotNull(inventoryService.getInventoryById("6"));

            inventoryService.deleteInventory("5");
            Assert.assertNotNull(inventoryService.getInventoryById("5"));
        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());
        }
    }
    public void testValidateUser()
    {
        String header = "Basic TXVnaGVlczoxMjM=";
        try
        {
            inventoryService.validateUser(header);
        }
        catch (Exception exc)
        {
            System.out.println("Invalid User");
        }

    }
}
