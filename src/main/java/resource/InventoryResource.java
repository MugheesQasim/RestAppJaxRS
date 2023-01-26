package resource;


import java.util.List;
import domain.Inventory;
import domain.ItemLocation;
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

}
