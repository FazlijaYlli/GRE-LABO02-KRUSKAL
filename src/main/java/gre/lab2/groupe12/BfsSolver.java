/**
 * @authors : Rui Manuel Mota Carneiro, Ylli Fazlija
 * @brief   : Résolveur de labyrinthe utilisant une simple exploration en profondeur (BFS)
 */

package gre.lab2.groupe12;

import gre.lab2.graph.Graph;
import gre.lab2.graph.VertexLabelling;
import gre.lab2.gui.MazeSolver;

import java.util.*;

public final class BfsSolver implements MazeSolver {
    @Override
    public List<Integer> solve(Graph graph, int source, int destination, VertexLabelling<Integer> treatments) {
        //Vérification que les sommets font partie du graphe
        if (graph.vertexExists(source) && graph.vertexExists(destination)) {
            //Initialisation d'une liste de prédécesseurs
            LinkedList<Integer> predecessors = new LinkedList<>();
            for (int i = 0; i < graph.nbVertices(); ++i) {
                predecessors.add(-1);
            }
            //Mise en place d'une pile de traitement des sommets
            Queue<Integer> vertexes = new LinkedList<>();
            vertexes.add(source);
            while (!vertexes.isEmpty()) {
                //Récupération du sommet de la pile
                int current = vertexes.poll();
                treatments.setLabel(current, 1);
                if (current == destination) {
                    //On vide la pile si on a atteint la destination
                    vertexes.clear();
                    break;
                }

                //Ajout des sommet adjacents à la pile et mise à jour de la liste des prédécesseurs
                for (int i : graph.neighbors(current)) {
                    if (treatments.getLabel(i) != 1) {
                        vertexes.add(i);
                        predecessors.set(i, current);
                    }
                }
            }

            //On dessine le chemin depuis la destination jusqu'à la source en utilisant la liste de prédécesseurs
            LinkedList<Integer> path = new LinkedList<>();
            path.add(destination);
            int current = predecessors.get(destination);
            while (current != -1) {
                path.add(current);
                current = predecessors.get(current);
            }

            return path;
        }
        //On retourne un chemin vide si on a pas réussi à atteindre la destination depuis la source
        return Collections.emptyList();
    }
}
