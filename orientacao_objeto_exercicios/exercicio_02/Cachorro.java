package orientacao_objeto_exercicios.exercicio_02;

public class Cachorro extends Animal {
    public Cachorro(String nome) {
        super(nome, "Latido Au-Au");
    }

    public void fazerSom() {
        System.out.println(nome + " faz: " + som);
    }
}