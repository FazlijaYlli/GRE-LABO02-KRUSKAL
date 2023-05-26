package gre.lab2.groupe12;

import java.util.LinkedList;

final class UnionFind {
  class Node {
    private int rank;
    final private int vertex;
    private Node parent;
    final private LinkedList<Node> children;
    public Node(int vertex) {
      this.rank = 0;
      this.parent = null;
      this.vertex = vertex;
      this.children = new LinkedList<>();
    }

    private void updateRank() {
      ++rank;
      if (parent != null) { parent.updateRank(); }
    }

    public void addChildren(Node child) {
      if (this.children.isEmpty() || this.rank == child.rank) {
        updateRank();
      }
      child.setParent(this);
      this.children.add(child);
    }

    public int getVertex() {
      return vertex;
    }

    public int getRank() {
      return rank;
    }

    public Node getParent() {
      return parent;
    }

    public void setParent(Node parent) {
      this.parent = parent;
    }
  }

  private LinkedList<Node> nodes;

  public UnionFind(int size) {
    this.nodes = new LinkedList<>();
    for(int i = 0 ; i < size ; ++i) {
      this.nodes.add(new Node(i));
    }
  }

  public int find(int v) {
    Node current = nodes.get(v);
    while (current.getParent() != null) {
      current = current.getParent();
    }
    return current.getVertex();
  }

  public boolean union(int u, int v) {
    // Indication : retourne false si les deux sommets font partie de la même composante connexe
    if (find(u) == find(v)) return false;
    if (nodes.get(u).getRank() >= nodes.get(v).getRank()) {
      nodes.get(u).addChildren(nodes.get(v));
    } else {
      nodes.get(v).addChildren(nodes.get(u));
    }
    return true;
  }
}
