package resource;

import java.util.List;
import domain.Inventory;
import domain.ItemLocation;
import service.InventoryService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/inventory")
public class InventoryResource {

        InventoryService inventoryService = InventoryService.getInstance();

        public static InventoryResource getInstance()
        {
                return new InventoryResource();
        }

        // Object to JSON
        @Path("/{inventoryId}")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getInventory(@PathParam("inventoryId") String inventoryId) {
                try
                {
                        Inventory inventory = inventoryService.getInventoryById(inventoryId);
                        return Response.status(Response.Status.OK).entity(inventory).build();
                }
                catch (Exception exc)
                {
                        String message = "{\"error_message\" : \"invalid id in request\"}";
                        return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
                }
        }

        @Path("/list")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getAllInventory(@QueryParam("category") String category,@QueryParam("location") String location)
        {
                try
                {
                        if(category==null && location==null)
                                return Response.status(Response.Status.OK).entity(inventoryService.getInventoryAll()).build();
                        else if(category!=null && location==null)
                                return Response.status(Response.Status.OK).entity(inventoryService.getInventoryByCategory(category)).build();
                        else if(category==null && location!=null)
                                return Response.status(Response.Status.OK).entity(inventoryService.getInventoryByLocation(location)).build();
                        else
                                return Response.status(Response.Status.OK).entity(inventoryService.getInventoryByCategoryAndLocation(category,location)).build();

                }
                catch(Exception exc)
                {
                        String message = "{\"error_message\" : \"invalid id in request\"}";
                        return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
                }
        }


        @POST
        @Path("/add")
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Response addNewInventoryItem(Inventory inventory,@HeaderParam("authorization") String header) {
                try
                {
                        if(inventoryService.validateUser(header))
                                return Response.status(Response.Status.OK).entity(inventoryService.addItem(inventory)).build();
                        else
                        {
                                String message = "{\"message\" : \"Unauthorized user\"}";
                                return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
                        }
                }
                catch(Exception exc)
                {
                        String message = "{\"error_message\" : \"invalid payload\"}";
                        return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
                }
        }

        @PUT
        @Path("/update/{inventoryId}")
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Response updateItem(Inventory inventory,@PathParam("inventoryId") String inventoryId,@HeaderParam("authorization")String header)
        {
                try
                {
                        if(inventoryService.validateUser(header))
                        {
                                return Response.status(Response.Status.OK).entity(inventoryService.updateInventory(inventory,inventoryId)).build();
                        }
                        else
                        {
                                String message = "{\"message\" : \"Unauthorized user\"}";
                                return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
                        }
                }
                catch(Exception exc)
                {
                        String message = "{\"error_message\" : \"invalid payload\"}";
                        return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
                }
        }

        @DELETE
        @Path("/{inventoryId}/")
        @Produces(MediaType.APPLICATION_JSON)
        public Response deleteItem(@PathParam("inventoryId") String inventoryId,@HeaderParam("authorization") String header)
        {
                try{
                        if(inventoryService.validateUser(header))
                        {
                                String message = "{\"message\" : \"OK\"}";
                                inventoryService.deleteInventory(inventoryId);
                                return Response.status(Response.Status.OK).entity(message).build();
                        }
                        else
                        {
                                String message = "{\"error_message\" : \"Unauthorized user\"}";
                                return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
                        }

                }
                catch(Exception exc)
                {
                        String message = "{\"error_message\" : \"invalid id in request\"}";
                        return Response.status(Response.Status.OK).entity(message).build();
                }
        }
}
