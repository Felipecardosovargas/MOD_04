package exception_exercicios.exercicio_01_try_catch_basico;

import java.util.Scanner;

public class Exercicio1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Digite o primeiro número inteiro: ");
            int a = scanner.nextInt();
            System.out.print("Digite o segundo número inteiro: ");
            int b = scanner.nextInt();

            int resultado = a / b;
            System.out.println("Resultado da divisão: " + resultado);
        } catch (ArithmeticException e) {
            System.out.println("Erro: Divisão por zero não é permitida.");
        }
    }
}
