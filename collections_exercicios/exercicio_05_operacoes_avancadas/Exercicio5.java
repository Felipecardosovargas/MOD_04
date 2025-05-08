package collections_exercicios.exercicio_05_operacoes_avancadas;

import java.util.ArrayList;
import java.util.HashSet;

public class Exercicio5 {
    public static void main(String[] args) {
        ArrayList<Integer> numeros = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            numeros.add(i);
        }

        // Remove números pares
        numeros.removeIf(n -> n % 2 == 0);

        System.out.println("Números ímpares: " + numeros);

        // Criar HashSet com os ímpares
        HashSet<Integer> impares = new HashSet<>(numeros);

        int verificar = 5;
        if (impares.contains(verificar)) {
            System.out.println("O número " + verificar + " está no conjunto de ímpares.");
        }
    }
}