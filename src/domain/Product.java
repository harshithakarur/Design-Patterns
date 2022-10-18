package domain;
import java.util.ArrayList;
import java.util.List;

public class Product implements VisitableItem{
    public String id;
    private ArrayList<Trading> tradingList = new ArrayList<Trading>();

    public Product(String id) {
        this.id = id;
    }

    public List<Trading> getTradings() {
        return tradingList;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        //print accept in course
        visitor.visit(this);
    }

    public void addTrading(Trading trading) {
        tradingList.add(trading);
    }

}
