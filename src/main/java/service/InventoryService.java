package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import domain.*;
import resource.InventoryResource;


public class InventoryService {
    public static InventoryService getInstance()
    {
        return new InventoryService();
    }

    public Inventory getInventoryById(String id)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment","root","root");
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM inventory JOIN itemcategory join itemlocation ON inventory.item_category_id=itemcategory.id AND inventory.item_location_id=itemlocation.id WHERE inventory.id =" + id);
            rs.next();
            Inventory inventory = new Inventory();

            inventory.setId(rs.getString(1));
            inventory.setItemName(rs.getString(2));
            inventory.setItemQuantity(rs.getInt(3));

            ItemCategory itemCategory = new ItemCategory(rs.getString(6),rs.getString(7));
            ItemLocation itemLocation = new ItemLocation(rs.getString(8),rs.getString(9));
            inventory.setItemCategoryId(itemCategory);
            inventory.setItemLocationId(itemLocation);

            return inventory;
        }
        catch(Exception exc)
        {
            System.out.println(exc);
        }
        return null;
    }

    public List<Inventory> getInventoryAll()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment","root","root");
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM inventory");

            List<Inventory> inventoryList = new ArrayList<>();

            while(rs.next())
            {
                Inventory inventory = new Inventory();

                inventory.setId(rs.getString(1));
                inventory.setItemName(rs.getString(2));
                inventory.setItemQuantity(rs.getInt(3));

                ItemCategory itemCategory = new ItemCategory(rs.getString(6),rs.getString(7));
                ItemLocation itemLocation = new ItemLocation(rs.getString(8),rs.getString(9));
                inventory.setItemCategoryId(itemCategory);
                inventory.setItemLocationId(itemLocation);

                inventoryList.add(inventory);
            }

            return inventoryList;
        }
        catch(Exception exc)
        {
            System.out.println(exc);
        }
        return null;
    }

    public List<Inventory> getInventoryByCategory(String categoryId)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment","root","root");
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from inventory JOIN itemcategory JOIN itemlocation ON inventory.item_category_id = itemcategory.id AND inventory.item_location_id = itemlocation.id WHERE itemcategory.id=" + categoryId);

            List<Inventory> inventoryList = new ArrayList<>();

            while(rs.next())
            {
                Inventory inventory = new Inventory();

                inventory.setId(rs.getString(1));
                inventory.setItemName(rs.getString(2));
                inventory.setItemQuantity(rs.getInt(3));

                ItemCategory itemCategory = new ItemCategory(rs.getString(6),rs.getString(7));
                ItemLocation itemLocation = new ItemLocation(rs.getString(8),rs.getString(9));
                inventory.setItemCategoryId(itemCategory);
                inventory.setItemLocationId(itemLocation);

                inventoryList.add(inventory);
            }

            return inventoryList;
        }
        catch(Exception exc)
        {
            System.out.println(exc);
        }
        return null;
    }
    public List<Inventory> getInventoryByLocation(String locationId)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment","root","root");
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from inventory JOIN itemcategory JOIN itemlocation ON inventory.item_category_id = itemcategory.id AND inventory.item_location_id = itemlocation.id WHERE itemlocation.id=" + locationId);

            List<Inventory> inventoryList = new ArrayList<>();

            while(rs.next())
            {
                Inventory inventory = new Inventory();

                inventory.setId(rs.getString(1));
                inventory.setItemName(rs.getString(2));
                inventory.setItemQuantity(rs.getInt(3));

                ItemCategory itemCategory = new ItemCategory(rs.getString(6),rs.getString(7));
                ItemLocation itemLocation = new ItemLocation(rs.getString(8),rs.getString(9));
                inventory.setItemCategoryId(itemCategory);
                inventory.setItemLocationId(itemLocation);

                inventoryList.add(inventory);
            }

            return inventoryList;
        }
        catch(Exception exc)
        {
            System.out.println(exc);
        }
        return null;
    }

    public List<Inventory> getInventoryByCategoryAndLocation(String categoryId,String locationId)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment","root","root");
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select * from inventory JOIN itemcategory JOIN itemlocation ON inventory.item_category_id = itemcategory.id AND inventory.item_location_id = itemlocation.id WHERE itemcategory.id=" + categoryId +
                    "AND itemlocation.id = " + locationId);

            List<Inventory> inventoryList = new ArrayList<>();

            while(rs.next())
            {
                Inventory inventory = new Inventory();

                inventory.setId(rs.getString(1));
                inventory.setItemName(rs.getString(2));
                inventory.setItemQuantity(rs.getInt(3));

                ItemCategory itemCategory = new ItemCategory(rs.getString(6),rs.getString(7));
                ItemLocation itemLocation = new ItemLocation(rs.getString(8),rs.getString(9));
                inventory.setItemCategoryId(itemCategory);
                inventory.setItemLocationId(itemLocation);

                inventoryList.add(inventory);
            }

            return inventoryList;
        }
        catch(Exception exc)
        {
            System.out.println(exc);
        }
        return null;
    }

    public void addItem(Inventory inventory)
    {
        try
        {
            System.out.println(inventory);
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment","root","root");
            Statement st = con.createStatement();

            int rs = st.executeUpdate("insert into inventory (id, item_name, item_quantity, item_category_id, item_location_id)"
                            + " values (" + "'"+inventory.getId() +"'" + "," + "'" + inventory.getItemName()+ "'" +","+ "'" + inventory.getItemQuantity() + "'"
                            + "," + "'" + inventory.getItemCategory().getId() + "'" + "," + "'" + inventory.getItemLocationId().getId() +"'" + ")");
            int rs2 = st.executeUpdate("insert into itemcategory (id, categoryname)" + "values(" + "'"+inventory.getItemCategory().getId()+"'" +
                    "," + "'" + inventory.getItemCategory().getCategoryName() + "'" + ")");
            int rs3 = st.executeUpdate("insert into itemlocation (id, locationname)" + "values(" + "'" + inventory.getItemLocationId().getId() +"'" +
                    "," + "'" + inventory.getItemLocationId().getLocationName() +"'" + ")");

            System.out.println(rs);
            System.out.println(rs2);
            System.out.println(rs3);;
        }
        catch(Exception exc)
        {
            System.out.println(exc);
        }
    }

    public void updateInventory(Inventory inventory,String inventoryId)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment","root","root");
            Statement st = con.createStatement();

            st.executeQuery("UPDATE inventory SET item_name = " +inventory.getItemName() +"WHERE id=" + inventoryId);
            st.executeQuery("UPDATE inventory SET item_quantity = " + inventory.getItemQuantity() +"WHERE id=" + inventoryId);
            st.executeQuery("UPDATE inventory SET item_category_id = "+inventory.getItemCategory().getId() +"WHERE id=" + inventoryId);
            st.executeQuery("UPDATE inventory SET item_location_id = " + inventory.getItemLocationId().getId() +"WHERE id=" + inventoryId);
            st.executeQuery("UPDATE itemCategory SET id = " + inventory.getItemCategory().getId() +"WHERE id=" + inventoryId);
            st.executeQuery("UPDATE itemCategory SET category_name = " + inventory.getItemCategory().getCategoryName() +"WHERE id=" + inventoryId);
            st.executeQuery("UPDATE itemlocation SET id = " + inventory.getItemLocationId().getId() +"WHERE id=" + inventoryId);
            st.executeQuery("UPDATE itemLocation SET location_name = " + inventory.getItemLocationId().getLocationName() +"WHERE id=" + inventoryId);
        }
        catch(Exception exc)
        {
            System.out.println(exc);
        }
    }

    public void deleteInventory(String id)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment", "root", "root");
            Statement st = con.createStatement();

            st.executeQuery("DELETE FROM inventory WHERE id = " + id);

        }
        catch(Exception exc)
        {
            System.out.println(exc);
        }
    }
}
