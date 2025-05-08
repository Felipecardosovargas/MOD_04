package collections_exercicios.exercicio_03_comparacao_arraylist_hashset;

import java.util.ArrayList;
import java.util.HashSet;

public class Exercicio3 {
    public static void main(String[] args) {

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(4);
        arrayList.add(2);
        arrayList.add(1);
        arrayList.add(3);
        arrayList.add(5);

        System.out.println(arrayList);

        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(4);
        hashSet.add(2);
        hashSet.add(1);
        hashSet.add(3);
        hashSet.add(5);

        System.out.println(hashSet);

        /*
         * ArrayList e HashSet são classes que fazem parte do Java Collections Framework (JCF),
         * uma biblioteca de estruturas de dados que facilita o trabalho com coleções de objetos.
         *
         * A interface Collection é a interface raiz comum para estruturas como listas, conjuntos
         * e filas, mas não é um framework por si só — ela define comportamentos que as classes
         * do JCF devem seguir.
         *
         * O ArrayList implementa a interface List. Ele armazena elementos em ordem, permite
         * duplicatas e oferece acesso rápido por índice.
         *
         * O HashSet implementa a interface Set. Ele não permite elementos duplicados e não
         * garante a ordem dos elementos. Internamente, ele usa uma tabela de hash para
         * desempenho otimizado em operações de inserção, remoção e busca.
         *
         * Ambas as classes precisam ser instanciadas com `new`, e são úteis para finalidades
         * diferentes: ArrayList para listas ordenadas e HashSet para coleções únicas sem
         * preocupação com ordem.
         */
    }
}
