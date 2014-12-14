package cycliclist;

public interface NodeInterface {
    public int seen();

    public NodeInterface next();

    public Object val();

    public void reLink(NodeInterface n);

    @Override
    public String toString();
}
