package whjakarta.service;

import whjakarta.entities.Category;
import whjakarta.entities.Product;

import java.util.Collection;
import java.util.List;

public class WarehouseInstance {
    private static final WarehouseInstance instance = new WarehouseInstance();
    private final Warehouse warehouse = new Warehouse();

    public static WarehouseInstance getInstance() {
        return instance;
    }

    public void addProduct(Product newProductInput) {
        warehouse.addProduct(newProductInput);
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
