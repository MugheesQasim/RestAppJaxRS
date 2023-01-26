package resource;


import java.util.List;
import domain.Inventory;
import domain.ItemLocation;
import netscape.javascript.JSObject;
import service.InventoryService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/inventory")
public class InventoryResource {

        InventoryService inventoryService = InventoryService.getInstance();

        @Path("/hello")
        @GET
        @Produces(MediaType.TEXT_PLAIN)
        public String sayPlainTextHello() {
            return "Hello Inventory";
        }

        // Object to JSON
        @Path("/{inventoryId}")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Inventory getInventory(@PathParam("inventoryId") String inventoryId) {
                return inventoryService.getInventoryById(inventoryId);
        }

        @Path("/list")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public List<Inventory> getAllInventory()
        {
                return inventoryService.getInventoryAll();
        }

        @GET
        @Path("/listByCategory/{category}")
        @Produces(MediaType.APPLICATION_JSON)
        public List<Inventory> fetchAllByCategory(@PathParam("category") String category)
        {
                return inventoryService.getInventoryByCategory(category);
        }

        @GET
        @Path("/listByLocation/{location}")
        @Produces(MediaType.APPLICATION_JSON)
        public List<Inventory> fetchAllByLocation(@PathParam("location") String location)
        {
                return inventoryService.getInventoryByCategory(location);
        }

        @GET
        @Path("/list/{location}&{category}")
        @Produces(MediaType.APPLICATION_JSON)
        public List<Inventory> fetchAllByCategoryAndLocation(@PathParam("location")String location,@PathParam("category") String category)
        {
                return inventoryService.getInventoryByCategoryAndLocation(location,category);
        }

        @POST
        @Path("/add")
        @Consumes(MediaType.APPLICATION_JSON)
        public void addNewInventoryItem(Inventory inventory)
        {
                System.out.println("");
                inventoryService.addItem(inventory);
        }

        @PUT
        @Path("/{inventoryId}/")
        @Consumes(MediaType.APPLICATION_JSON)
        public void updateItem(Inventory inventory,String inventoryId)
        {
                inventoryService.updateInventory(inventory,inventoryId);
        }

        @DELETE
        @Path("/{inventoryId}/")
        @Produces(MediaType.APPLICATION_JSON)
        public void deleteItem(String inventoryId)
        {
                inventoryService.deleteInventory(inventoryId);
        }
}
