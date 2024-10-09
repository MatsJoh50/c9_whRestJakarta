package whjakarta;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;


import jakarta.ws.rs.core.Response;
import whjakarta.entities.Category;
import whjakarta.entities.Product;
import whjakarta.entities.ProductRecord;
import whjakarta.service.WarehouseInstance;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/products")
public class whonline {
    private final WarehouseInstance warehouse = WarehouseInstance.getInstance();
    public static final Logger logger = LoggerFactory.getLogger(whonline.class);
    
    //lägg till produkt med validering av värdern
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/addnewproduct")
    public Response addProductToWarehouseList(ProductRecord inputProduct) {
        try {
            // Input validation
            if (inputProduct.name() == null || inputProduct.name().isEmpty()) {
                logger.info("Faulty Category.Enum: {}", inputProduct.category());
                return Response.status(Response.Status.BAD_REQUEST).entity("Product name cannot be empty").build();
            }


            //Forced controll due to input in String.
            Category parsedInputCategory;
            try {
                parsedInputCategory = Category.valueOf(inputProduct.category().toUpperCase());
            } catch (IllegalArgumentException e) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid category").build();
            }

            Product newProductInput = new Product(
                    inputProduct.name(),
                    parsedInputCategory,
                    inputProduct.rating()
            );

            warehouse.addProduct(newProductInput);
            logger.info("Adding product: {}", newProductInput);
            return Response.status(Response.Status.CREATED).entity(warehouse.printAllJSON()).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid product category: " + inputProduct.category()).build();
        }
    }


    //hämta alla produkter
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListOfAllProductsFromWarehouseList() {
//        warehouse.populateWarehouseProducts();
        if (!warehouse.isPopulated()) {
            logger.info("Warehouse is not populated");
            return Response.status(Response.Status.NOT_FOUND).entity("No products found").build();
        }

        List<String> allProducts = warehouse.printAllJSON();
        logger.info("Fetched all {} products: {}", allProducts.size(), allProducts);
        return Response.ok(allProducts).build();
    }

    @GET
    @Path("/populate")
    public Response populateWarehouseListForTestingFunctionality() {
        warehouse.populateWarehouseProducts();
        if (!warehouse.isPopulated()) {
            logger.info("Warehouse populated: {}", LocalDateTime.now());
            int productCount = warehouse.printAllJSON().size();  // Assuming a method to get the product list
            return Response.ok().entity("Warehouse populated with " + productCount + " products. Task completed: " + LocalDateTime.now()).build();
        }
        logger.info("New try to populate a populated warehouse: {}", LocalDateTime.now());
        return Response.status(Response.Status.NOT_FOUND).entity("Warehouse not populated").build();
    }


    //hämta en produkt genom id
    //case 4 -> warehouse.searchToString(findProduct());
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/id/{id}")
    public Response getProductByIdFromWarehouseList(@PathParam("id") String id) {
        logger.info("Calling /id/{id} using: {}", id);
        Product returnThisProduct = warehouse.searchProductByID(id);
        if (returnThisProduct != null) {
            logger.info("Found product: {}", returnThisProduct);
            return Response.ok(returnThisProduct.toString()).build();
        }
        logger.info("No product found with id: {}", id);
        return Response.status(Response.Status.NOT_FOUND).build();
    }


    //hämta alla produkter från en kattegori
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/category/{category}")
    public Response getListOfProductsFromGivenCategory(@PathParam("category") Category category) {
//        warehouse.populateWarehouseProducts();
        List<Product> listOfCategory = warehouse.getProductsByCategoryAndSortByName(category);
        if (listOfCategory.isEmpty()) {
            logger.info("No products found for given category: {}", category);
            return Response.status(Response.Status.NOT_FOUND).entity("No products of: " + category + " was found").build();
        }
        logger.info("Found {} products: {}", listOfCategory.size(), listOfCategory);
        return Response.ok().entity(listOfCategory.stream().map(Product::toString).collect(Collectors.toList())).build();
    }
}
