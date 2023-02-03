package service;

import domain.Inventory;
import domain.ItemCategory;
import domain.ItemLocation;
import org.junit.Test;

public class InventoryServiceImplTest{
    InventoryServiceImpl inventoryServiceImpl = InventoryServiceImpl.getInstance();
    AuthenticationService authenticationService = new AuthenticationService();

    @Test
    public void testGetInventoryById() {
        System.out.println("Inside test get inventoryById");

        String id = "-25";
        String id2 = "test";

        try
        {
            assert(inventoryServiceImpl.getInventoryById("7")==null);
            assert(inventoryServiceImpl.getInventoryById(id)!=null);
            assert(inventoryServiceImpl.getInventoryById(id2)!=null);
        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());
        }
    }

    @Test
    public void testGetInventoryAll() {
        System.out.println("Inside test get inventory all");

        try
        {
            assert ( inventoryServiceImpl.getInventoryAll() != null);
        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());
        }
    }

    @Test
    public void testGetInventoryByCategory() {
        System.out.println("Inside test get inventory by category");

        String category = "99";
        String category2 = null;
        String category3 = "24";

        try
        {
            assert(inventoryServiceImpl.getInventoryByCategory(category3)==null);
            assert (inventoryServiceImpl.getInventoryByCategory(category)==null);
            assert (inventoryServiceImpl.getInventoryByCategory(category2) == null);
        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());
        }
    }

    @Test
    public void testGetInventoryByLocation() {
        System.out.println("Inside test get inventory by location");

        String location = "99";
        String location2 = "location";
        String location3 = "7";

        try
        {
            assert(inventoryServiceImpl.getInventoryByLocation(location3)!=null);
            assert(inventoryServiceImpl.getInventoryByCategory(location)!=null);
            assert(inventoryServiceImpl.getInventoryByCategory(location2)!=null);
        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());
        }
    }

    @Test
    public void testGetInventoryByCategoryAndLocation() {
        System.out.println("Inside test get inventory by category and location");

        String category = "99";
        String location = "99";
        String location2 = "location";
        String category2 = "category";

        try
        {
            assert(inventoryServiceImpl.getInventoryByCategoryAndLocation("24","7")!=null);
            assert(inventoryServiceImpl.getInventoryByCategoryAndLocation(null,null)!=null);
            assert(inventoryServiceImpl.getInventoryByCategoryAndLocation(category,location)!=null);
            assert(inventoryServiceImpl.getInventoryByCategoryAndLocation(category2,location2)!=null);
        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());
        }
    }

    @Test
    public void testAddItem() {
        System.out.println("Inside test add item");

        ItemCategory itemCategory = new ItemCategory("31","Phone");
        ItemLocation itemLocation = new ItemLocation("29","Phoenix");

        Inventory inventory = new Inventory("five","iPhone 13",10,itemCategory,itemLocation);
        Inventory inventory1 = new Inventory("5","iPhone 13",10,null,null);


        try
        {
            String header = "Basic TXVnaGVlczoxMjM=";
            if(authenticationService.validateUser(header))
                System.out.println("User is validated");
            else
                System.out.println("Invalid User");
            assert(inventoryServiceImpl.addItem(inventory)!=null);
            assert(inventoryServiceImpl.addItem(inventory1)!=null);
        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());
        }
    }

    @Test
    public void testUpdateInventory() {
        System.out.println("Inside test update item");

        ItemCategory itemCategory = new ItemCategory("24","Phone");
        ItemLocation itemLocation = new ItemLocation("7","Phoenix");

        Inventory inventory1 = new Inventory("-20","iPhone 11",2,itemCategory,itemLocation);


        try
        {
            assert(inventoryServiceImpl.updateInventory(inventory1,"6")!=null);
            assert(inventoryServiceImpl.updateInventory(inventory1,"6")==inventory1);
        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());
        }
    }

    @Test
    public void testDeleteInventory() {
        System.out.println("Inside test delete item");


        try
        {
            inventoryServiceImpl.deleteInventory("6");
            assert(inventoryServiceImpl.getInventoryById("6")==null);

            inventoryServiceImpl.deleteInventory("5");
            assert(inventoryServiceImpl.getInventoryById("5")!=null);
        }
        catch(Exception exc)
        {
            System.out.println(exc.getMessage());
        }
    }

    @Test
    public void testValidateUser()
    {
        String header = "Basic TXVnaGVlczoxMjM=";
        try
        {
            authenticationService.validateUser(header);
        }
        catch (Exception exc)
        {
            System.out.println("Invalid User");
        }

    }
}
