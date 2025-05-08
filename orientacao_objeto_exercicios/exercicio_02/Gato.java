package orientacao_objeto_exercicios.exercicio_02;

public class Gato extends Animal {
    public Gato(String nome) {
        super(nome, "Miau");
    }

    public void fazerSom() {
        System.out.println(nome + " faz: " + som);
    }
}