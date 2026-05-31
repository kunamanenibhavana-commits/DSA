import java.util.*;

public class KruskalMST {

    static class Edge {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    static class UnionFind {
        int[] parent;

        UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++)
                parent[i] = i;
        }

        int find(int x) {
            if (parent[x] == x)
                return x;
            return parent[x] = find(parent[x]);
        }

        boolean union(int x, int y) {
            int rx = find(x);
            int ry = find(y);

            if (rx == ry)
                return false;

            parent[rx] = ry;
            return true;
        }
    }

    public static void main(String[] args) {

        Edge[] edges = {
            new Edge(0,1,4),
            new Edge(1,2,5),
            new Edge(2,3,6),
            new Edge(4,5,7),
            new Edge(3,4,8),
            new Edge(0,3,8),
            new Edge(5,6,9),
            new Edge(4,0,9),
            new Edge(6,1,10),
            new Edge(6,3,11),
            new Edge(6,2,12),
            new Edge(5,1,14)
        };

        Arrays.sort(edges, Comparator.comparingInt(e -> e.w));

        UnionFind uf = new UnionFind(7);

        int totalCost = 0;

        System.out.println("MST Edges:");

        for (Edge e : edges) {

            if (uf.union(e.u, e.v)) {
                totalCost += e.w;

                System.out.println(
                    e.u + " - " + e.v +
                    " : " + e.w + " crore");
            }
        }

        System.out.println("\nTotal MST Cost = "
                + totalCost + " crore");
    }
}