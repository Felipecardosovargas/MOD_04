package collections_exercicios.exercicio_01_arraylist;

import java.util.ArrayList;

public class Exercicio1 {
    public static void main(String[] args) {

        ArrayList<String> listaDeFrutas = new ArrayList<>();
        listaDeFrutas.add("Banana");
        listaDeFrutas.add("Maça");
        listaDeFrutas.add("Pera");
        listaDeFrutas.add("Mamão");
        listaDeFrutas.add("Abacaxi");

        System.out.println("Conteúdo do nosso arrayList: "+ listaDeFrutas);

        System.out.println("Tamanho do nosso arrayList: "+ listaDeFrutas.size());

        System.out.println("Terceiro elemento do nosso arrayList: " + listaDeFrutas.get(2));

        listaDeFrutas.remove(0); //Removendo elemento do nosso arrayList
        System.out.println("Nosso arrayList com primeiro elemento removido: " + listaDeFrutas);

        String itemProcurado = "Abacaxi";
        if (listaDeFrutas.contains(itemProcurado)) {
            System.out.println("O arrayList contem " + itemProcurado);
        } else {
            System.out.println("O arrayList não contem " + itemProcurado);
        }

        System.out.println("Lista de frutas:");
        for (String fruta : listaDeFrutas) {
            System.out.println(fruta);
        }
    }
}
