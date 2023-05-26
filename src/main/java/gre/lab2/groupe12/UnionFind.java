package gre.lab2.groupe12;

import java.util.LinkedList;

final class UnionFind {
    private final LinkedList<Integer> parents, ranks;

    public UnionFind(int size) {
        this.parents = new LinkedList<>();
        this.ranks = new LinkedList<>();
        for (int i = 0; i < size; ++i) {
            this.parents.add(-1);
            this.ranks.add(0);
        }
    }

    public int find(int v) {

        int leader = v;
        int parent = parents.get(leader);
        while (parent != -1) {
            if (parents.get(parent) != -1) parents.set(leader, parents.get(parent));
            leader = parent;
            parent = parents.get(leader);
        }
        return leader;
    }

    public boolean union(int u, int v) {
        // Indication : retourne false si les deux sommets font partie de la même composante connexe
        int pu = find(u), pv = find(v);
        if (pu == pv) {
            return false;
        }
        if (ranks.get(u) >= ranks.get(v)) {
            parents.set(pv, pu);
            if (ranks.get(u).equals(ranks.get(v))) {
                ranks.set(u, ranks.get(u) + 1);
            }
        } else {
            parents.set(pu, pv);
        }
        return true;
    }
}
