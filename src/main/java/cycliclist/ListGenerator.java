package cycliclist;

import java.util.Random;

public class ListGenerator {

    private int n;
    private NodeInterface head, tail;
    private Random r;
    private int cycleCount;

    public ListGenerator(int n) {
        this.n = n;
        this.head = tail = null;
        this.populate();
        this.r = new Random();
        this.cycleCount = 0;
    }

    private void populate() {
        NodeInterface toLink;

        toLink = this.tail = new Node("Node[" + (n - 1) + "]", null);

        for (int i = 1; i < n; i++) {
            head = new Node("Node[" + ((n - i) - 1) + "]", toLink);
            toLink = head;
        }

    }

    public NodeInterface uniformRandomCyclic() // descrete uniform distribution
    {
        int n = this.r.nextInt(this.getSize());
        int m = n != 0 ? this.r.nextInt(n + 1) : 0;
        return this.set(n, m);
    }

    // sets the nth element tol link to the mth
    public NodeInterface set(int n, int m) {
        NodeInterface c, N, M;
        int saw = 0;

        c = this.head;
        N = M = null;

        do {
            if (saw == n) {
                N = c;
            } // m == n is pos.
            if (saw == m) {
                M = c;
            }
            saw++;
        } while ((c = c.next()) != null && saw != this.getSize());

        if (N == null || M == null) {
            return null;
        }

        N.reLink(M); // expect horrendous garbage collection

        return N;
    }

    public int getSize() {
        return this.n;
    }

    private String asString() {

        // be careful here
        // as a cyclic list will want to loop forever

        String r = new String();
        int saw = 0;
        NodeInterface c;
        c = this.head;

        do {
            r += String.format("%-10s %s\r\n", c.hashCode(), (String) c.val());
            saw++;
        } while ((c = c.next()) != null && saw != this.getSize());

        if (c != null) {
            r += "Warning: List appears to have too many elements. Stopped.\r\n";
        } else if (saw != this.getSize()) {
            r += "Warning: List appears to have too few elements. Reached tail.\r\n";
        }

        return r;
    }


    public int isCyclic() {
        this.cycleCount = 0;
        if (isCyclic(this.head)) {
            return this.cycleCount;
        } else {
            return -1;
        }
    }


    // recursion is slow
    // but is cleaner
    private boolean isCyclic(NodeInterface n) {
        this.cycleCount++;
        if (n != null) {
            if (n.seen() > 0) {
                return true;
            } else {
                if (isCyclic(n.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.asString();
    }

}
