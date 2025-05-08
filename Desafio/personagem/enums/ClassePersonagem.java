package Desafio.personagem.enums;

/**
 * Enum para definir as classes de personagens jogáveis.
 * Cada classe pode ter atributos base ou modificadores específicos no futuro.
 */
public enum ClassePersonagem {
    GUERREIRO("Guerreiro", "Forte em combate corpo a corpo, alta vida e defesa."),
    MAGO("Mago", "Mestre das artes arcanas, alto dano mágico e mana."),
    ARQUEIRO("Arqueiro", "Ágil e preciso, especialista em ataques à distância.");

    private final String nomeDisplay;
    private final String descricao;

    ClassePersonagem(String nomeDisplay, String descricao) {
        this.nomeDisplay = nomeDisplay;
        this.descricao = descricao;
    }

    public String getNomeDisplay() {
        return nomeDisplay;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return nomeDisplay;
    }
}
