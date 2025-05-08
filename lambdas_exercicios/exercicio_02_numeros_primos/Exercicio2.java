package lambdas_exercicios.exercicio_02_numeros_primos;

import java.util.function.IntPredicate;

public class Exercicio2 {
    public static boolean isPrimo(int numero) {
        IntPredicate isDivisor = n -> numero % n == 0;
        return numero > 1 &&
                java.util.stream.IntStream.range(2, numero)
                        .noneMatch(isDivisor);
    }
}