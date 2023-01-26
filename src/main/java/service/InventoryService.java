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


}
