package whjakarta.service;



import whjakarta.entities.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.concurrent.*;

public class Warehouse {
  private List<Product> warehouseProducts = new CopyOnWriteArrayList<>();

  public void populateWarehouseProducts() {
    PopulateWH populateWH = new PopulateWH();
    populateWH.populate(warehouseProducts);
  }

  public List<Product> getWarehouesList() {
    return warehouseProducts;
  }

  public boolean isPopulated() {
    return !warehouseProducts.isEmpty();
  }

  public void addProduct(Product product) {
    System.out.println("addproduct function: " + warehouseProducts.size());
    if(warehouseProducts.contains(product.getName())) {
      System.out.println("Varan finns redan");
    } else{
      warehouseProducts.add(product);
      System.out.println("Produkten tillagd");
//      callbackOnAddedProduct(product);

    }
  }
public String callbackOnAddedProduct(Product product) {
    return product.toString();
}
  public List<String> getAllNames(){
    List<String> names = new ArrayList<>();
    for(Product product : warehouseProducts){
      names.add(product.getName());
    }
    return Collections.unmodifiableList(names);
  }

  //NEW NAME
  public void modifyProduct(String id, String newName) {
    Product product = search(id);
    if (product != null) {
      product.editProduct(newName);
    }
  }

//NEW CATEGORY
  public void modifyProduct( String id, Category newCategory) {
    Product product = search(id);
    if (product != null) {
      product.editProduct(newCategory);
    }
  }

  //NEW RATING
  public void modifyProduct(String id, int newRating) {
    Product product = search(id);
    if (product != null) {
      product.editProduct(newRating);
    }
  }

  public Product search(String uid) {
    try {
      for (Product product : warehouseProducts) {
        if (product.getId().equals(uid)) {
//          product.toString();
          return product;
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return null;
  }

  public void searchToString(String uid) {
    try {
      for (Product product : warehouseProducts) {
        if (product.getId().equals(uid)) {
          product.toString();
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public Product searchProductByID(String id) {
    for (Product product : warehouseProducts) {
      if (product.getId().equals(id)) {
        return product;
      }
    }
    // Returning null if no product is found
    return null;
  }


    public void printAll() {
    for (Product product : warehouseProducts) {
      System.out.println(product.toString());
    }
  }

//  public List<String> printAllJSON() {
//    return warehouseProducts.stream()
//            .map(product -> {
//              StringBuilder sb = new StringBuilder();
//              sb.append(product.getId())
//                      .append(" ")
//                      .append(product.getName())
//                      .append(" ")
//                      .append(product.getRating())
//                      .append(" ")
//                      .append(product.getCategory());
//              return sb.toString();  // Return the concatenated string
//            })
//            .collect(Collectors.toList());
//  }

  public List<String> printAllJSON() {
    return warehouseProducts.stream()
            .map(Product::toString)  // Use the existing toString() method
            .collect(Collectors.toList());
  }


  public void printCategory(Category inputCategory) {
    List<Product> sortedList = getProductsByCategoryAndSortByName(inputCategory);
    printGivenList(sortedList);
  }

  public List<Product> getProductsByCategoryAndSortByName(Category category) {
    return warehouseProducts.stream()
        .filter(product -> product.getCategory().equals(category))
        .sorted(Comparator.comparing(Product::getName))
        .collect(Collectors.toList());
  }

  public List<Product> fetchListByDate(LocalDateTime date) {
    return warehouseProducts.stream()
        .filter(product -> product.getCreatedDate().isAfter(date))
        .sorted(Comparator.comparing(Product::getCreatedDate))
        .collect(Collectors.toList());
  }
  public void printProductsByDate(LocalDateTime date){
    List<Product> productsByDate = fetchListByDate(date);
    printGivenList(productsByDate);
  }

  private static void printGivenList(List<Product> listOfProducts) {
    for (Product product : listOfProducts) {
      System.out.println(product.toString());
    }
  }



  public void isModified() {
    List<Product> productsIsModified = getModifiedList();
    printGivenList(productsIsModified);
  }

  public List<Product> getModifiedList() {
    return warehouseProducts.stream()
        .filter(product -> !product.getCreatedDate().equals(product.getLastModifiedDate()))
        .sorted(Comparator.comparing(Product::getLastModifiedDate))
        .collect(Collectors.toList());
  }
}//endpoint
