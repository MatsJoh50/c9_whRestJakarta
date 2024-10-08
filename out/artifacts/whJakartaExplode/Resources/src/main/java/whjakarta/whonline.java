package whjakarta;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;


import jakarta.ws.rs.core.Response;
import whjakarta.entities.Product;
import whjakarta.service.Warehouse;

import java.util.List;

@Path("/products")
public class whonline {
    private final Warehouse warehouse = new Warehouse();

//lägg till produkt med validering av värdern
// case 1 -> warehouse.addProduct(createProduct(warehouse));
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/create")
    public Response addProductToWarehouseList(Product product) {
        try{
            Product newProductInput = new Product(product.getName(), product.getCategory(), product.getRating());
            warehouse.addProduct(newProductInput);
            return Response.status(Response.Status.CREATED).entity(newProductInput).build();
        } catch (IllegalArgumentException e){
            return Response.serverError().entity("Invalid product").build();
        }
    }


    //hämta alla produkter
//        case 3 -> warehouse.printAll();
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListOfAllProductsFromWarehouseList() {
        warehouse.populateWarehouseProducts();

        if (!warehouse.isPopulated()) {
            return Response.status(Response.Status.NOT_FOUND).entity("No products found").build();
        }

        List<String> allProducts = warehouse.printAllJSON();
        return Response.ok(allProducts).build();
    }

    @GET
    @Path("/populate")
    public Response populateWarehouseListForTestingFunctionality() {
        warehouse.populateWarehouseProducts();
        if (warehouse.isPopulated()) {
            int productCount = warehouse.printAllJSON().size();  // Assuming a method to get the product list
            return Response.ok().entity("Warehouse populated with " + productCount + " products").build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Warehouse not populated").build();
    }


    //hämta en produkt genom id
    //case 4 -> warehouse.searchToString(findProduct());
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getProductByIdFromWarehouseList(@PathParam("id") String id, Product product) {
     Product returnThisProduct = warehouse.searchProductByID(id);
     if (returnThisProduct != null) {
         return Response.ok(returnThisProduct).build();
     }
         return Response.status(Response.Status.NOT_FOUND).build();
    }

}








//hämta alla produkter från en kattegori
//    case 5 -> warehouse.printCategory(askForCategory());