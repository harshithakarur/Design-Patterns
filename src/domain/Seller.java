package domain;

public class Seller extends Person{
    public Seller(String name) {
        super(name, 1);
    }

    @Override
    public ProductMenu createProductMenu(int productType) {

        if(productType == 0)
            productMenu = new MeatProductMenu() ;
        else productMenu = new ProduceProductMenu();
        System.out.println("Initialized" + (productType==0?" Meat product menu":" Produce product menu") + " product menu for Seller");

        //display - Instantiated product menu for buyer with product type - $productType
        return productMenu;
    }

    @Override
    public void showProductMenu() {
        productMenu.showMenuButtons();
        productMenu.showAddButtons();
        productMenu.showRadioButtons();
        productMenu.showComboBoxes();
        productMenu.showLabel();
    }
}
