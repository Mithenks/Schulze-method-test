package it.tau2m;

public class Main {

    public static void main(String[] args) {

        final String[] candidates = {"a", "b", "c", "d", "e"};

        final String[][] votes = {
                {"a", "c", "b", "e", "d"},
                {"a", "c", "b", "e", "d"},
                {"a", "c", "b", "e", "d"},
                {"a", "c", "b", "e", "d"},
                {"a", "c", "b", "e", "d"},
                {"a", "d", "e", "c", "b"},
                {"a", "d", "e", "c", "b"},
                {"a", "d", "e", "c", "b"},
                {"a", "d", "e", "c", "b"},
                {"a", "d", "e", "c", "b"},
                {"b", "e", "d", "a", "c"},
                {"b", "e", "d", "a", "c"},
                {"b", "e", "d", "a", "c"},
                {"b", "e", "d", "a", "c"},
                {"b", "e", "d", "a", "c"},
                {"b", "e", "d", "a", "c"},
                {"b", "e", "d", "a", "c"},
                {"b", "e", "d", "a", "c"},
                {"c", "a", "b", "e", "d"},
                {"c", "a", "b", "e", "d"},
                {"c", "a", "b", "e", "d"},
                {"c", "a", "e", "b", "d"},
                {"c", "a", "e", "b", "d"},
                {"c", "a", "e", "b", "d"},
                {"c", "a", "e", "b", "d"},
                {"c", "a", "e", "b", "d"},
                {"c", "a", "e", "b", "d"},
                {"c", "a", "e", "b", "d"},
                {"c", "b", "a", "d", "e"},
                {"c", "b", "a", "d", "e"},
                {"d", "c", "e", "b", "a"},
                {"d", "c", "e", "b", "a"},
                {"d", "c", "e", "b", "a"},
                {"d", "c", "e", "b", "a"},
                {"d", "c", "e", "b", "a"},
                {"d", "c", "e", "b", "a"},
                {"d", "c", "e", "b", "a"},
                {"e", "b", "a", "d", "c"},
                {"e", "b", "a", "d", "c"},
                {"e", "b", "a", "d", "c"},
                {"e", "b", "a", "d", "c"},
                {"e", "b", "a", "d", "c"},
                {"e", "b", "a", "d", "c"},
                {"e", "b", "a", "d", "c"},
                {"e", "b", "a", "d", "c"}
              };


        final int C = candidates.length;
        Double[][] d = new Double[C][C];
        Double[][] p = new Double[C][C];

        for (int k=0; k < votes.length; k++) {
            for (int i=0; i < C; i++) {
                for (int j=0; j < C; j++) {
                    if (i != j) {
                        if ( d[i][j] == null ) {
                            d[i][j] = 0D;
                        }
                        d[i][j] += compare(votes[k], candidates[i], candidates[j]);
                    }
                }
            }
        }

        System.out.println("First array:");
        printArray(d, C);
        System.out.println();

        for (int i=0; i < C; i++) {
            for (int j=0; j < C; j++) {
                if ( i != j ) {
                    if (d[i][j] > d[j][i]) {
                        p[i][j] = d[i][j];
                    } else {
                        p[i][j] = 0D;
                    }
                }
            }
        }

        System.out.println("Second array:");
        printArray(p, C);
        System.out.println();

        for (int i=0; i < C; i++) {
            for (int j=0; j < C; j++) {
                if ( i != j ) {
                    for (int k=0; k < C; k++) {
                        if (i != k && j != k) {
                            p[j][k] = Math.max(p[j][k], Math.min(p[j][i],p[i][k] ) );
                        }
                    }
                }
            }
        }

        System.out.println("Third array:");
        printArray(p, C);
        System.out.println();

        String[] results = new String[C];

        for (int i=0; i < C; i++) {
            int win = 0;
            for (int j=0; j < C; j++) {
                if ( i != j ) {
                    if (p[i][j] > p[j][i]) {
                        win++;
                    }
                }
            }
            results[C - win - 1] = candidates[i];
        }

        System.out.println("Results: ");
        for (int i=0; i < C; i++) {
            System.out.print(results[i] + "\t");
        }
        System.out.println();
    }

    private static Double compare(String[] data, String x, String y) {
        int posX = data.length;
        int posY = data.length;

        for (int i=0; i < data.length; i++) {
            if ( data[i].equals(x) ) {
                posX = i;
            }
            if ( data[i].equals(y) ) {
                posY = i;
            }
        }

        return posX < posY ? 1D : 0D;
    }

    private static void printArray(Double[][] data, int C) {

        for (int i=0; i < C; i++) {
            for (int l=0; l < C; l++) {
                System.out.print(data[i][l] + " \t");
            }
            System.out.print("\n");
        }

    }

}
