package exception_exercicios.exercicio_03_try_catch_finally;

public class Exercicio3 {
    public static void main(String[] args) {
        String numeroStr = "123a"; // teste com valores válidos ou inválidos

        try {
            int numero = Integer.parseInt(numeroStr);
            System.out.println("Número lido: " + numero);
        } catch (NumberFormatException e) {
            System.out.println("Erro: A string não é um número válido.");
        } finally {
            System.out.println("Bloco finally: Esse código sempre será executado.");
        }
    }
}