/**
 * @authors : Rui Manuel Mota Carneiro, Ylli Fazlija
 * @brief   : Générateur de labyrinthe utilisant l'algorithme de Kruskal
 */

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

        //Vérification que le sommet de départ fasse partie du graphe
        if (builder.topology().vertexExists(from)) {
            //Récupération des arêtes du graphe
            List<Edge> edges = builder.topology().edges();
            //Mélange aléatoire pour créer un labyrinthe
            Collections.shuffle(edges);
            //Union des sommets s'ils ne forment pas un cycle
            UnionFind uf = new UnionFind(builder.topology().nbVertices());
            for (Edge edge : edges) {
                int u = edge.u(), v = edge.v();
                if (uf.union(u, v)) {
                    //Mise à jour de l'affichage
                    builder.progressions().setLabel(u, Progression.PROCESSED);
                    builder.progressions().setLabel(v, Progression.PROCESSED);
                    //Suppression du mur séparant les 2 sommets
                    builder.removeWall(u, v);
                }
            }
        }
    }
}
