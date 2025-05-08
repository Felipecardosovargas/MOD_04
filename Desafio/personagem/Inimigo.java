package Desafio.personagem;

import Desafio.habilidade.Habilidade;

// ClassePersonagem pode não ser diretamente aplicável a inimigos da mesma forma que para jogadores.
// Inimigos podem ter seus próprios "tipos" ou categorias no futuro.
// Por enquanto, o construtor de Personagem exige uma ClassePersonagem, então passaremos null ou um valor genérico.
// Ou melhor, criamos uma categoria "MONSTRO" em ClassePersonagem ou removemos a obrigatoriedade para inimigos.
// Para simplificar agora, vamos assumir que inimigos não têm uma "ClassePersonagem" no mesmo sentido que o jogador.
// Vamos ajustar o construtor de Personagem ou criar um construtor específico para Inimigos se necessário.
// *** REVISÃO: O construtor de Personagem FOI alterado para aceitar ClassePersonagem.
// Para inimigos, podemos passar null ou criar uma entrada específica no enum.
// Vamos adicionar uma entrada NEUTRO ou MONSTRO no enum ClassePersonagem para eles.
// Ou, mais simples, o Inimigo não precisa ter uma `ClassePersonagem` se ela for só para jogadores.
// Por ora, o construtor de `Personagem` pede `ClassePersonagem`.
// Vamos criar um construtor em `Personagem` que não peça `ClassePersonagem` ou passar um valor default.
// A melhor abordagem seria o Inimigo não usar o enum ClassePersonagem se ele for específico para classes jogáveis.
// Para manter a estrutura atual sem grandes refatorações no construtor de Personagem,
// vamos passar null para a classe do inimigo, e tratar isso nos getters ou lógica específica se necessário.
// No entanto, uma solução mais limpa seria ter um construtor diferente ou um tipo base que não exija ClassePersonagem.

// Alternativa: Adicionar uma classe "MONSTRO" ao enum ClassePersonagem.
// Vamos fazer isso para manter a consistência do construtor.

import Desafio.personagem.enums.ClassePersonagem;


public class Inimigo extends Personagem {

    /**
     * Construtor da classe Inimigo.
     * @param nome Nome do inimigo.
     * @param vida Vida do inimigo.
     * @param mana Mana do inimigo.
     * @param ataque Ataque do inimigo.
     * @param defesa Defesa do inimigo.
     */
    public Inimigo(String nome, int vida, int mana, int ataque, int defesa) {
        // Passando ClassePersonagem.GUERREIRO como placeholder, idealmente seria um tipo "MONSTRO" ou similar.
        // Se ClassePersonagem for estritamente para jogadores, este design precisaria de ajuste.
        // Por ora, usaremos GUERREIRO como um tipo base genérico para não quebrar o construtor.
        // Uma melhoria seria ter um enum TipoInimigo.
        super(nome, ClassePersonagem.GUERREIRO, vida, mana, ataque, defesa); // Placeholder para classe
        this.classe = null; // Inimigos não têm uma "classe de jogador"
        inicializarHabilidadesInimigo();
    }

    // Sobrescrevemos o getter para retornar algo mais apropriado para inimigos
    @Override
    public ClassePersonagem getClasse() {
        // Inimigos não têm uma classe de jogador, então podemos retornar null
        // ou um valor especial se tivéssemos um enum TipoInimigo.
        return null; // Ou um valor padrão/específico para inimigos
    }


    private void inicializarHabilidadesInimigo() {
        if (this.nome.contains("Orc Brutal")) {
            Habilidade golpeEsmagador = new Habilidade(
                    "Golpe Esmagador",
                    "Um ataque poderoso.",
                    8,
                    true,
                    (caster, alvo) -> {
                        if (alvo == null || !alvo.estaVivo()) return false;
                        int danoBaseHabilidade = caster.getAtaqueBase() + 10;
                        int danoEfetivo = Math.max(0, danoBaseHabilidade - alvo.getDefesaAtual() + random.nextInt(6));
                        System.out.println(caster.getNome() + " usa Golpe Esmagador em " + alvo.getNome() + " causando " + danoEfetivo + " de dano!");
                        alvo.receberDano(danoEfetivo);
                        return true;
                    }
            );
            aprenderHabilidade(golpeEsmagador);
        }
        if (this.nome.contains("Feiticeiro Negro")) {
            Habilidade setaSombria = new Habilidade(
                    "Seta Sombria",
                    "Dispara energia negra.",
                    10,
                    true,
                    (caster, alvo) -> {
                        if (alvo == null || !alvo.estaVivo()) return false;
                        int danoMagico = 18 + caster.getManaMaxima() / 5 + random.nextInt(7);
                        int danoEfetivo = Math.max(0, danoMagico - (alvo.getDefesaBase() / 2));
                        System.out.println(caster.getNome() + " lança uma Seta Sombria em " + alvo.getNome() + " causando " + danoEfetivo + " de dano necrótico!");
                        alvo.receberDano(danoEfetivo);
                        return true;
                    }
            );
            aprenderHabilidade(setaSombria);
        }
    }

    @Override
    public String getMensagemAtaquePadrao() {
        String[] mensagens = {
                this.nome + " ataca com fúria!",
                this.nome + " investe brutalmente!",
                this.nome + " tenta esmagar " + (jogador != null ? jogador.getNome() : "seu oponente") + "!"
        };
        return mensagens[random.nextInt(mensagens.length)];
    }

    // Referência ao jogador para mensagens personalizadas (opcional)
    private Personagem jogador;
    public void setAlvoAtual(Personagem jogador) { this.jogador = jogador; }


    @Override
    public void usarItemDoInventario(int indiceItem) {
        System.out.println(this.nome + " não usa itens dessa forma!");
    }

    @Override
    public void exibirInventarioCompleto() {
        System.out.println(this.nome + " não carrega um inventário que possa ser inspecionado.");
    }

    @Override
    protected void inicializarItensPadrao() {
        // Inimigos não recebem itens padrão como os jogadores.
    }
}
