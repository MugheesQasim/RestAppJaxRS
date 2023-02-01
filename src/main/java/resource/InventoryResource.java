package resource;

import domain.Inventory;
import service.InventoryServiceImpl;
import service.InventoryService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/inventory")
public class InventoryResource {

        InventoryService inventoryServiceImpl = InventoryServiceImpl.getInstance();

        // Object to JSON
        @Path("/{inventoryId}")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getInventory(@PathParam("inventoryId") String inventoryId) {
                try
                {
                        Inventory inventory = inventoryServiceImpl.getInventoryById(inventoryId);
                        return Response.status(Response.Status.OK).entity(inventory).build();
                }
                catch (Exception exc)
                {
                        return Response.status(Response.Status.BAD_REQUEST).entity(exc.getMessage()).build();
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
                                return Response.status(Response.Status.OK).entity(inventoryServiceImpl.getInventoryAll()).build();
                        else if(category!=null && location==null)
                                return Response.status(Response.Status.OK).entity(inventoryServiceImpl.getInventoryByCategory(category)).build();
                        else if(category == null && location != null)
                                return Response.status(Response.Status.OK).entity(inventoryServiceImpl.getInventoryByLocation(location)).build();
                        else
                                return Response.status(Response.Status.OK).entity(inventoryServiceImpl.getInventoryByCategoryAndLocation(category,location)).build();

                }
                catch(Exception exc)
                {
                        String message = "{\"error_message\" : \"invalid id in request\"}";
                        return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
                }
        }


        @POST
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Response addNewInventoryItem(Inventory inventory,@HeaderParam("authorization") String header) {
                try
                {
                        if(inventoryServiceImpl.validateUser(header))
                                return Response.status(Response.Status.OK).entity(inventoryServiceImpl.addItem(inventory)).build();
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
        @Path("/{inventoryId}")
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Response updateItem(Inventory inventory,@PathParam("inventoryId") String inventoryId,@HeaderParam("authorization")String header)
        {
                try
                {
                        if(inventoryServiceImpl.validateUser(header))
                        {
                                return Response.status(Response.Status.OK).entity(inventoryServiceImpl.updateInventory(inventory,inventoryId)).build();
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
                        if(inventoryServiceImpl.validateUser(header))
                        {
                                String message = "{\"message\" : \"OK\"}";
                                inventoryServiceImpl.deleteInventory(inventoryId);
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
