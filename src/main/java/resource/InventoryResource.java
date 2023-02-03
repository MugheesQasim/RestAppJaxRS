package resource;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import domain.Inventory;

import service.AuthenticationService;
import service.InventoryServiceImpl;
import service.InventoryService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/inventory")
public class InventoryResource {
        Gson gson = new Gson();
        InventoryService inventoryServiceImpl = InventoryServiceImpl.getInstance();
        AuthenticationService authenticationService = new AuthenticationService();
        // Object to JSON
        @Path("/{inventoryId}")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getInventory(@PathParam("inventoryId") String inventoryId,@HeaderParam("authorization") String header) {
                try
                {
                        Inventory inventory = null;

                        if(authenticationService.validateUser(header))
                        {
                                inventory = inventoryServiceImpl.getInventoryById(inventoryId);
                                return Response.status(Response.Status.OK).entity(inventory).build();
                        }
                        else{
                                JsonObject jsonObject = new JsonObject();
                                jsonObject.addProperty("error_message","Unauthorized User");

                                String jsonString = gson.toJson(jsonObject);
                                return Response.status(Response.Status.UNAUTHORIZED).entity(jsonString).build();
                        }

                }
                catch (SQLException exc)
                {
                        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exc.getMessage()).build();
                }
        }

        @Path("/list")
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public Response getAllInventory(@QueryParam("category") String category,@QueryParam("location") String location,@HeaderParam("authorization") String header)
        {
                try
                {
                        List<Inventory> inventoryList;
                        if(authenticationService.validateUser(header))
                        {
                                inventoryList = inventoryServiceImpl.getInventory(category,location);
                                return Response.status(Response.Status.OK).entity(inventoryList).build();

                        }
                        else
                        {
                                JsonObject jsonMessage = new JsonObject();
                                jsonMessage.addProperty("error_message","Unauthorized access");

                                String jsonString = gson.toJson(jsonMessage);
                                return Response.status(Response.Status.UNAUTHORIZED).entity(jsonString).build();
                        }
                }
                catch(SQLException exc)
                {
                        JsonObject jsonMessage = new JsonObject();
                        jsonMessage.addProperty("error_message","invalid id in request");

                        String jsonString = gson.toJson(jsonMessage);
                        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonString).build();
                }
        }

        @POST
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Response addNewInventoryItem(String inventory,@HeaderParam("authorization") String header) {
                try
                {
                        Inventory inventory1 = gson.fromJson(inventory,Inventory.class);
                        inventory1.validateObject();

                        if(authenticationService.validateUser(header))
                                return Response.status(Response.Status.OK).entity(inventoryServiceImpl.addItem(inventory1)).build();
                        else
                        {
                                JsonObject jsonMessage = new JsonObject();
                                jsonMessage.addProperty("message","Unauthorized user");
                                String jsonString = gson.toJson(jsonMessage);
                                return Response.status(Response.Status.UNAUTHORIZED).entity(jsonString).build();
                        }
                }
                catch(SQLException sqlException)
                {
                        JsonObject jsonMessage = new JsonObject();
                        jsonMessage.addProperty("error_message","Sql exception");

                        String jsonString = gson.toJson(jsonMessage);
                        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonString).build();
                }
                catch(Exception exc)
                {
                        JsonObject jsonMessage = new JsonObject();
                        jsonMessage.addProperty("error_message","invalid payload");

                        String jsonString = gson.toJson(jsonMessage);
                        return Response.status(Response.Status.BAD_REQUEST).entity(jsonString).build();
                }
        }

        @PUT
        @Path("/{inventoryId}")
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        public Response updateItem(String inventory,@PathParam("inventoryId") String inventoryId,@HeaderParam("authorization")String header)
        {
                try
                {
                        Gson gson = new Gson();
                        Inventory inventory1 = gson.fromJson(inventory,Inventory.class);
                        if(authenticationService.validateUser(header))
                        {
                                return Response.status(Response.Status.OK).entity(inventoryServiceImpl.updateInventory(inventory1,inventoryId)).build();
                        }
                        else
                        {
                                JsonObject jsonMessage = new JsonObject();
                                jsonMessage.addProperty("message","Unauthorized user");
                                String jsonString = gson.toJson(jsonMessage);
                                return Response.status(Response.Status.UNAUTHORIZED).entity(jsonString).build();
                        }
                }
                catch(SQLException sqlException)
                {
                        JsonObject jsonMessage = new JsonObject();
                        jsonMessage.addProperty("error_message","Sql exception");

                        String jsonString = gson.toJson(jsonMessage);
                        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonString).build();
                }
                catch(Exception badRequestException)
                {
                        JsonObject jsonMessage = new JsonObject();
                        jsonMessage.addProperty("error_message","invalid payload");

                        String jsonString = gson.toJson(jsonMessage);
                        return Response.status(Response.Status.BAD_REQUEST).entity(jsonString).build();
                }
        }

        @DELETE
        @Path("/{inventoryId}")
        @Produces(MediaType.APPLICATION_JSON)
        public Response deleteItem(@PathParam("inventoryId") String inventoryId,@HeaderParam("authorization") String header)
        {
                try{
                        if(authenticationService.validateUser(header))
                        {
                                JsonObject jsonMessage = new JsonObject();
                                jsonMessage.addProperty("message","OK");

                                String jsonString = gson.toJson(jsonMessage);
                                inventoryServiceImpl.deleteInventory(inventoryId);
                                return Response.status(Response.Status.OK).entity(jsonString).build();
                        }
                        else
                        {
                                JsonObject jsonMessage = new JsonObject();
                                jsonMessage.addProperty("error_message","Unauthorized user");

                                String jsonString = gson.toJson(jsonMessage);
                                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(jsonString).build();
                        }
                }
                catch(Exception exc)
                {
                        JsonObject jsonMessage = new JsonObject();
                        jsonMessage.addProperty("error_message","invalid id in request");

                        String jsonString = gson.toJson(jsonMessage);
                        return Response.status(Response.Status.OK).entity(jsonString).build();
                }
        }
}
