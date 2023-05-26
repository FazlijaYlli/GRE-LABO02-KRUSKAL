package gre.lab2.groupe12;

import gre.lab2.gui.MazeBuilder;
import gre.lab2.gui.MazeGenerator;

public final class KruskalMazeGenerator implements MazeGenerator {
  @Override
  public void generate(MazeBuilder builder, int from) {
    // Affichage : après union, marquer les deux extrémités de l'arête traitée comme PROCESSED puis supprimer le mur
  }
}

// TODO
//  - Implémentation des classes KruskalMazeGenerator, UnionFind et BfsSolver ;
//  - Documentation abondante des trois classes comprenant :
//    - la javadoc, avec auteurs et description des implémentations ;
//    - des commentaires sur les différentes parties de vos algorithmes.
