package whjakarta.service;

import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import whjakarta.entities.Category;
import whjakarta.entities.Product;

import java.util.List;
import static whjakarta.whonline.logger;


public class WarehouseInstance {
    private static final WarehouseInstance instance = new WarehouseInstance();
    private static final Logger log = LoggerFactory.getLogger(WarehouseInstance.class);
    private final Warehouse warehouse = new Warehouse();

    public static WarehouseInstance getInstance() {
        return instance;
    }

    public Response addProduct(Product newProductInput) {
        try {
            logger.info("Trying to add product " + newProductInput.getName());
            // Check if a product with the same name already exists
            boolean exists = warehouse.getWarehouesList().stream()
                    .map(Product::getName)
                    .anyMatch(name -> name.equals(newProductInput.getName()));

            if (exists) {
                return Response.status(Response.Status.CONFLICT)
                        .entity("En vara med det namnet finns redan.") // Product name conflict message
                        .build();
            } else {
                warehouse.addProduct(newProductInput); // Add the new product
                logger.info("Added product " + newProductInput.getName());
                return Response.status(Response.Status.CREATED)
                        .entity(newProductInput.toString() + " är nu tillagd") // Success message
                        .build();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR) // Change to 500 for internal errors
                    .entity("Aj då, något gick fel.") // General error message
                    .build();
        }
    }


    public List<String> printAllJSON() {
        return warehouse.printAllJSON();
    }

    public void populateWarehouseProducts() {
        warehouse.populateWarehouseProducts();
    }

    public boolean isPopulated() {
        return warehouse.isPopulated();
    }

    public Product searchProductByID(String id) {
        return warehouse.searchProductByID(id);
    }

    public List<Product> getProductsByCategoryAndSortByName(Category category) {
        return warehouse.getProductsByCategoryAndSortByName(category);
    }
}
