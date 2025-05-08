package Desafio.batalha.enums;

/**
 * Enum para definir os diferentes estados do jogo.
 * Ajuda a controlar o fluxo principal e as ações permitidas.
 */
public enum EstadoJogo {
    INICIALIZANDO("Inicializando", "O jogo está sendo configurado."),
    MENU_PRINCIPAL("Menu Principal", "O jogador está no menu principal."),
    CRIACAO_PERSONAGEM("Criação de Personagem", "O jogador está criando seu personagem."),
    EM_BATALHA("Em Batalha", "O jogador está em combate."),
    EXPLORANDO("Explorando", "O jogador está explorando o mundo do jogo (para futuras expansões)."),
    PAUSADO("Pausado", "O jogo está pausado."),
    FIM_DE_JOGO_VITORIA("Vitória", "O jogador venceu o jogo."),
    FIM_DE_JOGO_DERROTA("Derrota", "O jogador foi derrotado.");

    private final String nomeDisplay;
    private final String descricao;

    EstadoJogo(String nomeDisplay, String descricao) {
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
