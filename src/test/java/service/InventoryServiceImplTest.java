package service;

import domain.Inventory;
import domain.ItemCategory;
import domain.ItemLocation;

import junit.framework.*;

public class InventoryServiceImplTest extends TestCase{
    InventoryServiceImpl inventoryServiceImpl = InventoryServiceImpl.getInstance();

    public void testGetInventoryById() {
        System.out.println("Inside test get inventoryById");

        String id = "-25";
        String id2 = "test";

        try
        {
            Assert.assertNotNull(inventoryServiceImpl.getInventoryById("7"));
            Assert.assertEquals(inventoryServiceImpl.getInventoryById(id),null);
            Assert.assertEquals(inventoryServiceImpl.getInventoryById(id2),null);
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
            Assert.assertNotNull(inventoryServiceImpl.getInventoryAll());
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
            Assert.assertNotNull(inventoryServiceImpl.getInventoryByCategory(category3));
            Assert.assertNotNull(inventoryServiceImpl.getInventoryByCategory(category));
            Assert.assertNotNull(inventoryServiceImpl.getInventoryByCategory(category2));
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
            Assert.assertNotNull(inventoryServiceImpl.getInventoryByLocation(location3));
            Assert.assertNotNull(inventoryServiceImpl.getInventoryByCategory(location));
            Assert.assertNotNull(inventoryServiceImpl.getInventoryByCategory(location2));
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
            Assert.assertNotNull(inventoryServiceImpl.getInventoryByCategoryAndLocation("24","7"));
            Assert.assertNull(inventoryServiceImpl.getInventoryByCategoryAndLocation(null,null));
            Assert.assertNotNull(inventoryServiceImpl.getInventoryByCategoryAndLocation(category,location));
            Assert.assertNotNull(inventoryServiceImpl.getInventoryByCategoryAndLocation(category2,location2));
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
            if(inventoryServiceImpl.validateUser(header))
                System.out.println("User is validated");
            else
                System.out.println("Invalid User");
            Assert.assertNotNull(inventoryServiceImpl.addItem(inventory));
            Assert.assertNotNull(inventoryServiceImpl.addItem(inventory1));
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
            Assert.assertNotNull(inventoryServiceImpl.updateInventory(inventory1,"6"));
            Assert.assertSame(inventoryServiceImpl.updateInventory(inventory1,"6"),inventory1);
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
            inventoryServiceImpl.deleteInventory("6");
            Assert.assertNotNull(inventoryServiceImpl.getInventoryById("6"));

            inventoryServiceImpl.deleteInventory("5");
            Assert.assertNotNull(inventoryServiceImpl.getInventoryById("5"));
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
            inventoryServiceImpl.validateUser(header);
        }
        catch (Exception exc)
        {
            System.out.println("Invalid User");
        }

    }
}
