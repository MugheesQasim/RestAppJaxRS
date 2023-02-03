package service;

import domain.Inventory;
import domain.sql.SqlQuery;

import java.sql.SQLException;
import java.util.List;

public interface InventoryService {

    //todo add only sql exception
    //todo remove warnings
    //todo move if else in list to service domain
    //todo move validate user to authenticate service
    //todo change one line for decoding the header

    Inventory getInventoryById(String id) throws SQLException;

    List<Inventory> getInventory(String category,String location) throws SQLException;

    List<Inventory> getInventoryAll() throws SQLException;

    List<Inventory> getInventoryByCategory(String categoryId) throws SQLException;

    List<Inventory> getInventoryByLocation(String locationId) throws SQLException;

    List<Inventory> getInventoryByCategoryAndLocation(String categoryId,String locationId) throws SQLException;

    Inventory addItem(Inventory inventory) throws SQLException;

    Inventory updateInventory(Inventory inventory,String inventoryId) throws Exception;

    void deleteInventory(String id) throws Exception;
}
