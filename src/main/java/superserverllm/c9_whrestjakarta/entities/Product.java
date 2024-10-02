package entities;


import service.UID;

import java.time.LocalDateTime;

public class Product{
  private  String id;
  private String name;
  private Category category;
  private int rating;
  private final LocalDateTime createdDate;
  private LocalDateTime lastModifiedDate;

  public Product(String name, Category category, int rating) {
    LocalDateTime thisIsNow = LocalDateTime.now();
    this.id = new UID(1,4).getUid();
    this.name = name;
    this.category = category;
    this.rating = rating;
    this.createdDate = thisIsNow;
    this.lastModifiedDate = thisIsNow;
  }
  public Product(String name, Category category, int rating, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
    this.id = new UID(1,4).getUid();
    this.name = name;
    this.category = category;
    this.rating = rating;
    this.createdDate = createdDate;
    this.lastModifiedDate = lastModifiedDate;
  }

  @Override
  public String toString() {
    return
        "Id: " + id + ",\t" +
        "Name: " + name + ",\t" +
        "Category: " + category + ",\t" +
        "Rating: " + rating;
  }

  
  public String getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public Category getCategory() {
    return category;
  }
  public int getRating() {
    return rating;
  }
  public LocalDateTime getCreatedDate() {
    return createdDate;
  }
  public LocalDateTime getLastModifiedDate() {
    return lastModifiedDate;
  }
  public void setLastModifiedDate() {
    this.lastModifiedDate = LocalDateTime.now();
  }

  public void editProduct(String newName){
    this.name = newName;
    setLastModifiedDate();
  }

  public void editProduct(Category newCategory){
    this.category = newCategory;
    setLastModifiedDate();
  }

  public void editProduct(int newRating){
    this.rating = newRating;
    setLastModifiedDate();
  }

}