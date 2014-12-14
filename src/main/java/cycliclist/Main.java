package cycliclist;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        if (args.length == 2) {
            System.out.println("running mean cycle test...\r\n");

            int n, r;
            n = Integer.parseInt(args[0]);
            r = Integer.parseInt(args[1]);

            int[] cycles = new int[r];
            double mean = 0.0;


            String l = new String();

            String[] col = new String[4];
            col[0] = "Iteration";
            col[1] = "Cycles Required";
            col[2] = "Cycle Position";
            col[3] = "Original List Size";

            String lf = "%-" + (col[0].length() + 5) + "s%-" + (col[1].length() + 5) + "s%-" + (col[2].length() + 10) + "s%-" + (col[3].length() + 5) + "s";

            l = String.format(lf, col[0], col[1], col[2], col[3]);
            System.out.println(l);

            for (int i = 0; i < r; i++) {
                ListGenerator lg = new ListGenerator(n);
                NodeInterface no = lg.uniformRandomCyclic();
                cycles[i] = lg.isCyclic();
                l = String.format(lf, i, cycles[i], no + " -> " + no.next(), n);
                System.out.println(l);
            }

            for (int i = 0; i < cycles.length; i++) {
                mean += cycles[i];
            }

            mean = mean / cycles.length;

            System.out.println("\r\nAverage number of cycles taken: " + mean + " (" + mean / n + "n)");

        } else {
            usage();
        }
    }

    public static void usage() {
        System.out.println("<app> n r");
        System.out.println("where n is the size of the linked list to create");
        System.out.println("where r is the number of repetitions to calculate the mean cycle rate");
    }

}
