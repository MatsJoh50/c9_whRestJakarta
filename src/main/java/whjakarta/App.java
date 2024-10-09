//package whjakarta;
//
//import whjakarta.entities.Category;
//import whjakarta.entities.Product;
//import whjakarta.service.UID;
//import whjakarta.service.Warehouse;
//
//import java.time.DateTimeException;
//import java.time.LocalDateTime;
//import java.util.Scanner;
//
//public  class App {
//
//    private static final Warehouse warehouse = new Warehouse();
//
//    public static void main(String[] args) {
//
//        warehouse.populateWarehouseProducts();
//        boolean isRunning= true;
//        while(isRunning) {
//            menu();
//
//            int input = validInput(1,7);
//
//            switch (input) {
//                case 1 -> warehouse.addProduct(createProduct(warehouse));
//                case 2 -> modifyProduct();
//                case 3 -> warehouse.printAll();
//                case 4 -> warehouse.searchToString(findProduct());
//                case 5 -> warehouse.printCategory(askForCategory());
//                case 6 -> warehouse.printProductsByDate(askForDate());
//                case 7 -> warehouse.isModified();
//                case 0 -> isRunning = false;
//                default -> System.out.println("Invalid input");
//            }
//        }
//    }
//
//
//
//    private static void menu(){
//        System.out.println("Lager meny:");
//        System.out.println("1: Lägg till produkt.");
//        System.out.println("2: Ändra en produkt.");
//        System.out.println("3: Skriv ut alla produkter.");
//        System.out.println("4: Hitta en produkt.");
//        System.out.println("5: Visa alla produkter i en kategori.");
//        System.out.println("6: Visa alla produkter skapade efter ett visst datum.");
//        System.out.println("7: Visa alla produkter som ändrats");
//        System.out.println("0: Avsluta.");
//        System.out.print("Välj ditt alternativ: ");
//    }
//
//    private static String findProduct() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Sök product via id: ");
//        String productId = scanner.nextLine();
//        if(productId.trim().isEmpty()) return null;
//        return productId;
//    }
//
//    private static int validInput(int min, int max) {
//        Scanner scanner = new Scanner(System.in);
//        boolean checkingInput = true;
//        while(checkingInput){
//            int input = scanner.nextInt();
//            if(input >= min || input <= max || input == 0) {
//                return input;
//            }
//            else{
//                System.out.println("Inget giltigt alternativ! Försök igen.");
//            }
//        }
//        return -1;
//    };
//
//    private static int validInput(int min, int max, boolean exit) {
//        Scanner scanner = new Scanner(System.in);
//        boolean checkingInput = true;
//        while(checkingInput){
//            int input = scanner.nextInt();
//            if(input >= min || input <= max || (input == 0 && exit)) {
//                return input;
//            }
//            else{
//                System.out.println("Inget giltigt alternativ! Försök igen.");
//            }
//        }
//        return -1;
//    };
//
//    public static Product createProduct(Warehouse warehouse) {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Produktens namn: ");
//        String name = inputName(warehouse);
//        System.out.print("Produktens kategori: ");
//        printAllCategories();
//        Category category = parseCategory();
//
//        System.out.print("Produktens ratingd(1-10): ");
//        int rating = validRating(1, 10);
//        UID uid = new UID(1,4);
//
//        return new Product(name, category, rating);
//    }
//
//
//    private static int validRating(int min, int max) {
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            System.out.print("Ange en rating: ");
//            int input = scanner.nextInt();
//
//            if (input >= min && input <= max) {
//                return input;
//            } else {
//                System.out.println("Vänligen ange en rating mellan " + min + " och " + max);
//            }
//        }
//    }
//
//
//    private static String inputName(Warehouse warehouse) {
//        Scanner scanner = new Scanner(System.in);
//        while (true) {
//            System.out.print("Ange ditt namn: ");
//            String input = scanner.nextLine().trim();
//            boolean productIsUnique = isProductUnique(warehouse, input);
//            if(!productIsUnique) {
//                System.out.println("Produktens namn finns redan");
//                continue;
//            }
//            if (!input.isEmpty()) {
//                return input;
//            } else {
//                System.out.println("Du måste skriva något. Försök igen.");
//            }
//        }
//    }
//
//    private static boolean isProductUnique(Warehouse warehouse, String input) {
//        for (String productName : warehouse.getAllNames()) {
//            if(input.toLowerCase().trim().equals(productName.toLowerCase().trim())) {
//                return false;
//            }
//        }
//        return true;
//    };
//
//
//    public static Category parseCategory(){
//        Scanner scanner = new Scanner(System.in);
//        while(true){
//            try{
//                String input = scanner.nextLine().trim().toUpperCase();
//                return Category.valueOf(input);
//            } catch (Exception e){
//                System.out.println("Måste vara en korrekt kattegori!");
//            }
//        }
//    }
//
//
//    private static void printAllCategories(){
//        System.out.println("\nCategories");
//        int counter = 0;
//        for (Category category : Category.values()){
//
//            System.out.print(category.toString());
//            if(counter != Category.values().length - 1){
//                System.out.print(", ");
//            }
//            if(counter % 3 == 0 && counter != 0 || counter == Category.values().length - 1){
//                System.out.println();
//            }
//            counter++;
//        }
//        System.out.print("Skriv: ");
//    }
//
//    private static Category askForCategory() {
//        System.out.println("Vilken kategori?");
//        printAllCategories();
//        return parseCategory();
//    }
//
//    public static void modifyProduct(){
//        System.out.println("Skriv ID på den produkt du vill ändra?");
//        Scanner scanner = new Scanner(System.in);
//        String editThisProduct = scanner.nextLine();
//        System.out.println("Vad vill du ändra med produkten?");
//        System.out.println("1: Namn");
//        System.out.println("2: Kategori");
//        System.out.println("3: Rating");
//        System.out.print("Välj:");
//        int option = scanner.nextInt();
//        switch (option) {
//            case 1 -> {
//                scanner.nextLine();
//                while(true) {
//                    System.out.println("Nytt namn: ");
//                    String editedName = scanner.nextLine();
//
//                    if (!isProductUnique(warehouse, editedName)) {
//                        System.out.println("En produkt med det namnet finns redan");
//                        continue;
//                    }
//
//                    warehouse.modifyProduct(editThisProduct, editedName);
//                    break;
//                }
//            }
//            case 2 -> {
//                printAllCategories();
//                System.out.println("Ny kategori: ");
//                Category category = parseCategory();
//                warehouse.modifyProduct(editThisProduct, category);
//            }
//            case 3 -> {
//                System.out.println("Ny rating(1-10): ");
//                int rating = validRating(1, 10);
//                warehouse.modifyProduct(editThisProduct, rating);
//            }
//        }
//    }
//    private static LocalDateTime askForDate() {
//        System.out.println("Vilket datum vill du söka från?: ");
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("År: ");
//        int year = scanner.nextInt();
//        System.out.print("Månad: ");
//        int month = scanner.nextInt();
//        System.out.print("Dag: ");
//        int day = scanner.nextInt();
//        return buildDate(year, month, day);
//    }
//    private static LocalDateTime buildDate(int year, int month, int day) {
//        try{
//            return LocalDateTime.of(year, month, day, 0, 0, 0);
//        } catch (DateTimeException e) {
//            System.out.println(e.getMessage());
//        }
//        return null;
//    }
//}
