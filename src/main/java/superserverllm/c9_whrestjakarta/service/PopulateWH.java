package superserverllm.c9_whrestjakarta.service;
import entities.Category;
import entities.Product;
import service.Warehouse;
import service.*;

import java.util.List;

public class PopulateWH {
  public void populate(List warehouseProducts) {

    // PANTS (minst 5 produkter)
    warehouseProducts.add(new Product("Blue Jeans", Category.PANTS, 8));
    warehouseProducts.add(new Product("Cargo Pants", Category.PANTS, 7));
    warehouseProducts.add(new Product("Khaki Shorts", Category.PANTS, 7));
    warehouseProducts.add(new Product("Black Trousers", Category.PANTS, 6));
    warehouseProducts.add(new Product("Skinny Jeans", Category.PANTS, 9));

    // SHIRTS (minst 5 produkter)
    warehouseProducts.add(new Product("Red T-shirt", Category.SHIRTS, 7));
    warehouseProducts.add(new Product("White Shirt", Category.SHIRTS, 8));
    warehouseProducts.add(new Product("Blue Polo", Category.SHIRTS, 9));
    warehouseProducts.add(new Product("Black Turtleneck", Category.SHIRTS, 6));
    warehouseProducts.add(new Product("Green Hoodie", Category.SHIRTS, 8));

    // SHOES (minst 5 produkter)
    warehouseProducts.add(new Product("Running Shoes", Category.SHOES, 9));
    warehouseProducts.add(new Product("Sneakers", Category.SHOES, 6));
    warehouseProducts.add(new Product("Formal Shoes", Category.SHOES, 8));
    warehouseProducts.add(new Product("Boots", Category.SHOES, 7));
    warehouseProducts.add(new Product("Sandals", Category.SHOES, 5));

    // JACKETS (minst 5 produkter)
    warehouseProducts.add(new Product("Winter Jacket", Category.JACKETS, 10));
    warehouseProducts.add(new Product("Leather Jacket", Category.JACKETS, 9));
    warehouseProducts.add(new Product("Rain Jacket", Category.JACKETS, 7));
    warehouseProducts.add(new Product("Bomber Jacket", Category.JACKETS, 8));
    warehouseProducts.add(new Product("Windbreaker", Category.JACKETS, 6));

    // BACKPACKS (minst 5 produkter)
    warehouseProducts.add(new Product("Hiking Backpack", Category.BACKPACKS, 6));
    warehouseProducts.add(new Product("Travel Backpack", Category.BACKPACKS, 8));
    warehouseProducts.add(new Product("School Backpack", Category.BACKPACKS, 7));
    warehouseProducts.add(new Product("Daypack", Category.BACKPACKS, 7));
    warehouseProducts.add(new Product("Laptop Backpack", Category.BACKPACKS, 8));

    // COOKING (minst 5 produkter)
    warehouseProducts.add(new Product("Camping Stove", Category.COOKING, 8));
    warehouseProducts.add(new Product("Portable Grill", Category.COOKING, 6));
    warehouseProducts.add(new Product("Cooking Pot", Category.COOKING, 7));
    warehouseProducts.add(new Product("Frying Pan", Category.COOKING, 8));
    warehouseProducts.add(new Product("BBQ Tongs", Category.COOKING, 5));

    // CARVING (minst 5 produkter)
    warehouseProducts.add(new Product("Swiss Knife", Category.CARVING, 9));
    warehouseProducts.add(new Product("Hunting Knife", Category.CARVING, 9));
    warehouseProducts.add(new Product("Wood Carving Set", Category.CARVING, 8));
    warehouseProducts.add(new Product("Carving Fork", Category.CARVING, 6));
    warehouseProducts.add(new Product("Carving Knife", Category.CARVING, 7));

    // MEDICAL (minst 5 produkter)
    warehouseProducts.add(new Product("First Aid Kit", Category.MEDICAL, 7));
    warehouseProducts.add(new Product("Bandages", Category.MEDICAL, 5));
    warehouseProducts.add(new Product("Antiseptic Wipes", Category.MEDICAL, 6));
    warehouseProducts.add(new Product("Pain Relief Spray", Category.MEDICAL, 8));
    warehouseProducts.add(new Product("Medical Tape", Category.MEDICAL, 7));

  }
}
