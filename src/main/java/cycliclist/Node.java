package cycliclist;

public class Node implements NodeInterface {
    private Object o;
    private NodeInterface nextNode;
    private int seen;

    public Node(Object o, NodeInterface nextNode) {
        this.o = o;
        this.nextNode = nextNode;
        this.seen = -1;
    }

    public NodeInterface next() {
        return this.nextNode;
    }

    public Object val() {
        return this.o;
    }

    public int seen() {
        this.seen++;
        return this.seen;
    }

    public void clear() {
        this.seen = -1;
    }

    public void reLink(NodeInterface n) {
        this.nextNode = n;
    }

    @Override
    public String toString() {
        return (String) o;
    }

}
