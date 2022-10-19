package domain;

import java.util.ArrayList;
import java.util.List;

import test.ProductList;

public abstract class Person {
    public String name;
    int type;
    private ProductList boughtProducts;

    ProductMenu productMenu;

    public abstract ProductMenu createProductMenu(int productType);
    public abstract void showProductMenu();

    public ProductList getBoughtProducts() {
        return boughtProducts;
    }

    public Person(String name, int type) {
        this.name = name;
        this.type = type;
    }

    public boolean isBuyer() {
        return type == 0;
    }

    public void buyProducts(List<Product> products) {

        if(boughtProducts == null)
            boughtProducts = new ProductList(products);
        else

            boughtProducts.addAll(products);
    }

}
