package domain;

public class Trading implements VisitableItem{
    public Trading(Float price) {
        this.price = price;
    }

    public float price = 0;

    @Override
    public void accept(NodeVisitor visitor) {
        //print accept in assignment
        visitor.visit(this);
    }
}
