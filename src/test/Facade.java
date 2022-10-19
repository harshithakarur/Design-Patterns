package test;

import domain.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Facade implements VisitableItem {
    private Integer userType = null;
    private Product selectedProduct = null;
    private Integer productCategory = null;
    private ProductList productList = new ProductList();
    private Person currentUser = null;
    private DataManager dataManager = null;

    Scanner sc= new Scanner(System.in);
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public Facade() {
        dataManager = new DataManager();

        createProductList();
    }


    ProductList getProducts() {
        return productList;
    }

    public  void login(String name, String password) throws Exception {

        System.out.println("-------Facade Pattern-------");
        System.out.println("Handling Login using the Facade pattern");
//        System.out.println("Before try block");
        try{
            if(dataManager.fetchPassword(name).equals(password))
            {
//                System.out.println("Going to try block");
                currentUser = createUser(name);
                attachProductToUser(currentUser);

                this.selectedProduct = selectProduct();
                this.productCategory = selectProductCategory();
                this.displayMenu();
            } else {
                System.out.println("Incorrect userName or password");
                System.exit(1);
            }
        } catch(Exception ex) {
//            System.out.println("In catch block");
            System.out.println("Incorrect User Name or Password");
            System.exit(1);
        }

    }

    void addTrading(Product product, Trading trading) {

    }

    void viewOffering(Offering offering) {

    }

    void submitOffering(Offering offering) {

    }

    void markOffering(Offering offering) {

    }

    void displayMenu() {
        System.out.println("********* Choose an operation *********");
        System.out.println("1. Create product menu");
        System.out.println("2. Show product menu");
        System.out.println("3. Remind");
        System.out.println("4. Done");

        try {
            int selectionIndex = Integer.parseInt(br.readLine());
            switch(selectionIndex) {
                case 1: {
                    productOperation();
                    displayMenu();
                    break;
                }
                case 2: {
                }
                case 3: {
                    remind(); break;
                }
                default : {
                    System.out.println("Exiting...");
                    System.exit(1);
                    break;
                }
            }
            if(selectionIndex!=4) {
                displayMenu();
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    int selectProductCategory() {

        System.out.println("Enter product category:");
        System.out.println("0 : Meat");
        System.out.println("1 : Produce");

        try {
            int selectionIndex = Integer.parseInt(br.readLine());
            return selectionIndex;
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        return 0;
    }

    private Person createUser(String name) throws Exception {
        //print creating user
        return dataManager.initializePerson(name);
    }

    private void createProductList() {
        //Create product list for the whole system
        try {
            productList = new ProductList(dataManager.initializeAllProducts());
            for (Object product : productList) {
                ((Product) product).addTrading(new Trading(new Float(0)));
            }
        } catch(Exception ex) {
            ArrayList<Product> productList = new ArrayList<Product>();
            this.productList.addAll(productList);
        }

    }

    void attachProductToUser(Person person) throws Exception {
        //print attaching product to user
        //Read UserProduct.txt and add all products mapped to user object by calling User.buyProducts(Product)
        person.buyProducts(dataManager.fetchProductMappingForUser(person.name));
    }
    //To be changed from here
    Product selectProduct() {
        // show list of courses and return selected course
        //display currentUser.enrolledCourses - using iterator
        System.out.println("------Iterator pattern-------");
        System.out.println("Iterating the products for the user using iterator pattern");

        ProductIterator iterator = (ProductIterator) currentUser.getBoughtProducts().iterator();
        int selectionIndex = 0;
        while(iterator.hasNext()) {
            System.out.println(selectionIndex + ": " + iterator.next().id);
            selectionIndex++;
        }

        System.out.println("Enter product selection:");

        try {
            selectionIndex = Integer.parseInt(br.readLine());
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        // Dummy Product object
        Product selectedProduct = (Product) currentUser.getBoughtProducts().get(selectionIndex);
        return selectedProduct;
    }

    void productOperation() {
        if(currentUser!=null) {
            currentUser.createProductMenu(productCategory);
            //Create product menu for currentUser
            //Show menu for selecting product category - meat and produce
        }
    }

    void showProductMenuCreated() {
        currentUser.showProductMenu();
    }

    void remind() {
        accept(new ReminderVisitor());
    }

    @Override
    public void accept(NodeVisitor visitor) {
        //print accept in facade
        System.out.println("-------Visitor pattern-------");
        visitor.visit(this);
    }
}

class Offering{ }
