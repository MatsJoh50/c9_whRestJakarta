package whjakarta.entities;

import whjakarta.service.UID;

import java.time.LocalDateTime;

public class Product{
  private  String id;
  private String name;
  private Category category;
  private int rating;
  private LocalDateTime createdDate = LocalDateTime.now();
  private LocalDateTime lastModifiedDate;


  public Product(){} //Empty construct to work with jakarta.

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
            "Id: " + id + ", " +  // Replace \t with a space
            "Name: " + name + ", " +
            "Category: " + category + ", " +
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

  // Setters for deserialization
  public void setName(String name) { this.name = name; }
  public void setCategory(Category category) { this.category = category; }
  public void setRating(int rating) { this.rating = rating; }

  // Optionally, if needed, setters for createdDate and lastModifiedDate can be added
  public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }
  public void setLastModifiedDate(LocalDateTime lastModifiedDate) { this.lastModifiedDate = lastModifiedDate; }

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