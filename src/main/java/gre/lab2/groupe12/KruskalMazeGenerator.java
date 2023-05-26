package gre.lab2.groupe12;

import gre.lab2.graph.Edge;
import gre.lab2.gui.MazeBuilder;
import gre.lab2.gui.MazeGenerator;
import gre.lab2.gui.Progression;

import java.util.Collections;
import java.util.List;

public final class KruskalMazeGenerator implements MazeGenerator {
    @Override
    public void generate(MazeBuilder builder, int from) {
        // Affichage : après union, marquer les deux extrémités de l'arête traitée comme PROCESSED puis supprimer le mur
        if (builder.topology().vertexExists(from)) {
            List<Edge> edges = builder.topology().edges();
            Collections.shuffle(edges);
            UnionFind uf = new UnionFind(builder.topology().nbVertices());
            for (Edge edge : edges) {
                int u = edge.u(), v = edge.v();
                if (uf.union(u, v)) {
                    builder.progressions().setLabel(u, Progression.PROCESSED);
                    builder.progressions().setLabel(v, Progression.PROCESSED);
                    builder.removeWall(u, v);
                }
            }
        }
    }
}

// TODO
//  - Implémentation des classes KruskalMazeGenerator, UnionFind et BfsSolver ;
//  - Documentation abondante des trois classes comprenant :
//    - la javadoc, avec auteurs et description des implémentations ;
//    - des commentaires sur les différentes parties de vos algorithmes.
