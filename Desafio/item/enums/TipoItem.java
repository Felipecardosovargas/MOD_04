package Desafio.item.enums;

/**
 * Enum para definir os tipos de itens no jogo.
 * Isso ajudará a categorizar e gerenciar diferentes comportamentos de itens.
 */
public enum TipoItem {
    POCAO("Poção", "Item consumível, geralmente para cura ou buffs."),
    ARMA("Arma", "Equipamento para aumentar o poder de ataque."),
    ARMADURA("Armadura", "Equipamento para aumentar a defesa."),
    ESCUDO("Escudo", "Equipamento para aumentar a defesa ou chance de bloqueio."),
    ACESSORIO("Acessório", "Item que fornece bônus diversos."),
    MISSAO("Item de Missão", "Item relacionado a objetivos específicos.");

    private final String nomeDisplay;
    private final String descricao;

    TipoItem(String nomeDisplay, String descricao) {
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
