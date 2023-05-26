package gre.lab2.groupe12;

import gre.lab2.graph.Graph;
import gre.lab2.graph.VertexLabelling;
import gre.lab2.gui.MazeSolver;

import java.util.*;

public final class BfsSolver implements MazeSolver {
    @Override
    public List<Integer> solve(Graph graph, int source, int destination, VertexLabelling<Integer> treatments) {
        if (graph.vertexExists(source) && graph.vertexExists(destination)) {
            LinkedList<Integer> predecessors = new LinkedList<>();
            for (int i = 0; i < graph.nbVertices(); ++i) {
                predecessors.add(-1);
            }
            Queue<Integer> vertexes = new LinkedList<>();
            vertexes.add(source);
            while (!vertexes.isEmpty()) {
                int current = vertexes.poll();
                treatments.setLabel(current, 1);
                if (current == destination) {
                    vertexes.clear();
                    break;
                }

                for (int i : graph.neighbors(current)) {
                    if (treatments.getLabel(i) != 1) {
                        vertexes.add(i);
                        predecessors.set(i, current);
                    }
                }
            }

            LinkedList<Integer> path = new LinkedList<>();
            path.add(destination);
            int current = predecessors.get(destination);
            while (current != -1) {
                path.add(current);
                current = predecessors.get(current);
            }

            return path;
        }
        return Collections.emptyList();
    }
}
