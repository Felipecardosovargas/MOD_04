package collections_exercicios.exercicio_04_conversao;

import java.util.ArrayList;
import java.util.HashSet;

public class Exercicio4 {
    public static void main(String[] args) {

        ArrayList<String> carros = new ArrayList<>();
        carros.add("Mini Cooper");
        carros.add("Mini Cooper");
        carros.add("Ferrari");
        carros.add("Camaro");
        carros.add("Mustang");

        System.out.println(carros);

        HashSet<String> conjunto = new HashSet<>(carros);
        System.out.println(conjunto);

        ArrayList<String> conjuntoDeCarros = new ArrayList<>(conjunto);
        System.out.println(conjuntoDeCarros);
    }
}
