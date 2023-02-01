package service;

import domain.Inventory;

import java.util.List;

public interface InventoryService {
    Inventory getInventoryById(String id) throws Exception;

    List<Inventory> getInventoryAll() throws Exception;

    List<Inventory> getInventoryByCategory(String categoryId) throws Exception;

    List<Inventory> getInventoryByLocation(String locationId) throws Exception;

    List<Inventory> getInventoryByCategoryAndLocation(String categoryId,String locationId) throws Exception;

    Inventory addItem(Inventory inventory) throws Exception;

    Inventory updateInventory(Inventory inventory,String inventoryId) throws Exception;

    void deleteInventory(String id) throws Exception;

    boolean validateUser(String user) throws Exception;
}
