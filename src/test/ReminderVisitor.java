package test;

import domain.NodeVisitor;
import domain.Product;
import domain.Trading;

import java.util.Iterator;

public class ReminderVisitor extends NodeVisitor {
    @Override
    public void visit(Facade element) {
        System.out.println("Visiting Facade now");
        System.out.println("Iterating all products in Facade using iterator pattern");
        ProductIterator iterator = (ProductIterator) element.getProducts().iterator();
        while(iterator.hasNext()) {
            iterator.next().accept(this);
        }
    }

    @Override
    public void visit(Product product) {
        System.out.println("Seeing Product "+(product.id)+" ....");

        Iterator<Trading> iterator = product.getTradings().iterator();
        while(iterator.hasNext()) {
            iterator.next().accept(this);
        }
    }

    @Override
    public void visit(Trading trading) {

        //visiting assignment
        System.out.println("Seeing Trading with price:"+ trading.price);

    }
}
