package Desafio.item; // Movido para o pacote Desafio.item

import Desafio.personagem.Personagem;
import Desafio.item.enums.TipoItem; // Importando o Enum TipoItem
import java.util.Random;

/**
 * Representa uma Poção de Cura, um item consumível que restaura vida.
 * Movido para o pacote Desafio.item
 */
public class PocaoDeCura implements Item {
    private static final Random random = new Random();
    private final String nome = "Poção de Cura";
    private final String descricao = "Restaura uma quantidade moderada de vida.";
    private final int curaBase = 35;
    private final int curaVariacao = 10; // Cura entre 35 e 44 (35 + 0 a 9)

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public TipoItem getTipo() {
        return TipoItem.POCAO; // Define o tipo do item
    }

    @Override
    public void usar(Personagem usuario) {
        if (usuario.getVida() < usuario.getVidaMaxima()) {
            int curaRealizada = curaBase + random.nextInt(curaVariacao);
            usuario.curar(curaRealizada);
            System.out.println(usuario.getNome() + " usou " + getNome() + " ("+ getTipo().getNomeDisplay() +") e recuperou " + curaRealizada + " de vida. Vida atual: " + usuario.getVida());
        } else {
            System.out.println(usuario.getNome() + " tentou usar " + getNome() + ", mas já está com a vida máxima!");
        }
    }

    @Override
    public boolean isConsumivel() {
        return true; // Poções de cura são consumíveis
    }
}
