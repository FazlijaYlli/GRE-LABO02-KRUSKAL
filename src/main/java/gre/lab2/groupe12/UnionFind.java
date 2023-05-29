/**
 * @authors : Rui Manuel Mota Carneiro, Ylli Fazlija
 * @brief   : Structure de données UnionFind servant à l'implémentation de l'algorithme de Kruskal
 */

package gre.lab2.groupe12;

import java.util.LinkedList;

final class UnionFind {
    private final LinkedList<Integer> parents, size;

    /**
     * Création de la structure UnionFind
     * @param size Nombre de sommets du graphe
     */
    public UnionFind(int size) {
        //Initialise 2 listes représentant un arbre et la taille de l'arbre
        this.parents = new LinkedList<>();
        this.size = new LinkedList<>();

        for (int i = 0; i < size; ++i) {
            this.parents.add(-1); //Pas de parents
            this.size.add(1); //Singletons
        }
    }

    /**
     * Permet d'obtenir le représentant d'un ensemble
     * @param v Sommet du graphe
     * @return Sommet représentant l'ensemble
     */
    public int find(int v) {

        int leader = v;
        int parent = parents.get(leader);
        //Itération jusqu'à la racine de l'arbre
        while (parent != -1) {
            if (parents.get(parent) != -1) parents.set(leader, parents.get(parent)); //Remonte les éléments en utilisant le Path splitting
            leader = parent;
            parent = parents.get(leader);
        }
        return leader;
    }

    /**
     * Permet d'unir 2 ensembles
     * @param u Sommet du graphe
     * @param v Autre sommet du graphe
     * @return Vrai si les ensembles ont été unis, Faux si les 2 sommets font déjà partie du même ensemble
     */
    public boolean union(int u, int v) {
        //Récupération des représentants de chaque sommet
        int pu = find(u), pv = find(v);
        if (pu == pv) {
            //On n'unie pas s'ils font partie du même ensemble
            return false;
        }
        //Ajout d'un ensemble à l'autre selon leurs tailles et mise à jour de leurs parents et taille
        if (size.get(pu) >= size.get(pv)) {
            parents.set(pv, pu);
            size.set(pu, size.get(pu) + size.get(pv));
        } else {
            parents.set(pu, pv);
            size.set(pv, size.get(pv) + size.get(pu));
        }
        //Union effectuée
        return true;
    }
}
