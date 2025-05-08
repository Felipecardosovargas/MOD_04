package Desafio.batalha;

// Personagens e suas classes específicas
import Desafio.personagem.Personagem;
import Desafio.personagem.Guerreiro;
import Desafio.personagem.Mago;
import Desafio.personagem.Arqueiro;
import Desafio.personagem.Inimigo;
// Enums relacionados a personagem e jogo
import Desafio.personagem.enums.ClassePersonagem;
import Desafio.batalha.enums.EstadoJogo;
// Habilidades e Itens nos seus novos pacotes
import Desafio.habilidade.Habilidade;
// Não precisamos importar Item diretamente aqui se Jogo não o manipula diretamente,
// mas Personagem sim.

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Jogo {
    private Personagem jogador;
    private List<Inimigo> listaDeInimigos;
    private int inimigoAtualIndex;
    private EstadoJogo estadoAtual; // Novo atributo para o estado do jogo

    private static final int MAX_TURNOS_POR_BATALHA = 25;
    private static final Random random = new Random();
    private Scanner scanner; // Scanner como atributo da classe

    public Jogo() {
        this.listaDeInimigos = new ArrayList<>();
        this.inimigoAtualIndex = 0;
        this.estadoAtual = EstadoJogo.INICIALIZANDO;
        this.scanner = new Scanner(System.in);
        prepararInimigos();
    }

    private void prepararInimigos() {
        // Nome, Vida, Mana, Ataque, Defesa
        listaDeInimigos.add(new Inimigo("Goblin Verde Nóia", 45, 0, 9, 3));
        listaDeInimigos.add(new Inimigo("Zargor, o Orc Brutal", 110, 25, 17, 10));
        listaDeInimigos.add(new Inimigo("Malakor, o Feiticeiro Negro", 130, 70, 14, 8));
        listaDeInimigos.add(new Inimigo("Grognak, o Gigante de Pedra", 220, 15, 22, 18));
        listaDeInimigos.add(new Inimigo("Dragão Ancião das Cinzas", 400, 100, 30, 25));
    }

    public void iniciarJogo() {
        this.estadoAtual = EstadoJogo.CRIACAO_PERSONAGEM;
        System.out.println("========================================");
        System.out.println("Bem-vindo à Arena dos Campeões!");
        System.out.println("Estado do Jogo: " + estadoAtual.getNomeDisplay());
        System.out.println("========================================");

        System.out.print("Digite seu nome, bravo herói: ");
        String nome = scanner.nextLine();

        System.out.println("\nEscolha sua classe, " + nome + ":");
        // Apresentando as classes a partir do Enum
        for (ClassePersonagem classe : ClassePersonagem.values()) {
            // Excluindo MONSTRO ou outros tipos não jogáveis se adicionados ao enum ClassePersonagem
            // Para este exemplo, assumimos que todos os valores em ClassePersonagem são jogáveis.
            System.out.println((classe.ordinal() + 1) + ". " + classe.getNomeDisplay() + " (" + classe.getDescricao() + ")");
        }
        System.out.println("----------------------------------------");
        System.out.println(ClassePersonagem.GUERREIRO.getNomeDisplay() + " (Vida: 150, Mana: 30, Atk: 18, Def: 10)");
        System.out.println(ClassePersonagem.MAGO.getNomeDisplay() + " (Vida: 100, Mana: 80, Atk: 10, Def: 6)");
        System.out.println(ClassePersonagem.ARQUEIRO.getNomeDisplay() + " (Vida: 120, Mana: 50, Atk: 16, Def: 8)");
        System.out.print("Sua escolha (número): ");


        int escolha = -1;
        try {
            escolha = scanner.nextInt();
        } catch (InputMismatchException e) {
            // Tratamento de erro se não for número
        }
        scanner.nextLine();

        switch (escolha) {
            case 1: jogador = new Guerreiro(nome); break;
            case 2: jogador = new Mago(nome); break;
            case 3: jogador = new Arqueiro(nome); break;
            default:
                System.out.println("Opção inválida. Você será um " + ClassePersonagem.GUERREIRO.getNomeDisplay() + " por padrão.");
                jogador = new Guerreiro(nome);
                break;
        }

        System.out.println("\nVocê escolheu: " + jogador.getNome() + " - " + jogador.getClasse().getNomeDisplay());
        System.out.println("Vida: " + jogador.getVidaMaxima() + ", Mana: " + jogador.getManaMaxima() + ", Ataque Base: " + jogador.getAtaqueBase() + ", Defesa Base: " + jogador.getDefesaBase());
        jogador.exibirInventarioCompleto();
        jogador.listarHabilidadesParaUso();
        System.out.println("========================================");


        // Loop principal do jogo (sequência de batalhas)
        while (jogador.estaVivo() && inimigoAtualIndex < listaDeInimigos.size() && (estadoAtual != EstadoJogo.FIM_DE_JOGO_DERROTA && estadoAtual != EstadoJogo.FIM_DE_JOGO_VITORIA)) {
            Inimigo inimigoAtual = listaDeInimigos.get(inimigoAtualIndex);
            inimigoAtual.curar(inimigoAtual.getVidaMaxima());
            inimigoAtual.restaurarMana(inimigoAtual.getManaMaxima());
            inimigoAtual.setAlvoAtual(jogador); // Para mensagens personalizadas do inimigo

            this.estadoAtual = EstadoJogo.EM_BATALHA;
            iniciarBatalha(inimigoAtual); // Scanner já é um atributo da classe

            if (jogador.estaVivo() && !inimigoAtual.estaVivo()) { // Jogador venceu a batalha
                System.out.println("\nVocê derrotou " + inimigoAtual.getNome() + "!");
                inimigoAtualIndex++;
                if (inimigoAtualIndex < listaDeInimigos.size()) {
                    System.out.println("Prepare-se para o próximo desafio...");
                    // Recuperação pós-batalha
                    int curaPosBatalha = jogador.getVidaMaxima() / 4;
                    int manaPosBatalha = jogador.getManaMaxima() / 3;
                    jogador.curar(curaPosBatalha);
                    jogador.restaurarMana(manaPosBatalha);
                    System.out.println(jogador.getNome() + " recuperou um pouco de vida e mana.");
                    System.out.println("Pressione ENTER para continuar...");
                    scanner.nextLine();
                }
            } else if (!jogador.estaVivo()) { // Jogador foi derrotado
                this.estadoAtual = EstadoJogo.FIM_DE_JOGO_DERROTA;
                break;
            } else { // Limite de turnos ou outra condição de saída (jogador não venceu nem perdeu diretamente)
                System.out.println("A batalha com " + inimigoAtual.getNome() + " terminou. Você segue em frente, mas com cautela.");
                inimigoAtualIndex++;
                if (inimigoAtualIndex < listaDeInimigos.size()) {
                    System.out.println("Pressione ENTER para continuar...");
                    scanner.nextLine();
                }
            }
            System.out.println("========================================");
        }

        if (jogador.estaVivo() && inimigoAtualIndex >= listaDeInimigos.size()) {
            this.estadoAtual = EstadoJogo.FIM_DE_JOGO_VITORIA;
        } else if (!jogador.estaVivo()){
            this.estadoAtual = EstadoJogo.FIM_DE_JOGO_DERROTA;
        }

        fimDeJogo();
        scanner.close();
    }

    private void iniciarBatalha(Personagem inimigo) {
        System.out.println("\n--- INÍCIO DA BATALHA ---");
        System.out.println("Estado do Jogo: " + estadoAtual.getNomeDisplay());
        System.out.println("Você enfrenta: " + inimigo.getNome() + " (Vida: " + inimigo.getVida() + ", Mana: " + inimigo.getMana() +", Atk: " + inimigo.getAtaqueBase() + ", Def: " + inimigo.getDefesaBase() + ")");
        if(!inimigo.getHabilidades().isEmpty()){
            System.out.print(inimigo.getNome() + " parece conhecer as seguintes habilidades: ");
            for(Habilidade h : inimigo.getHabilidades()){ System.out.print(h.getNome() + " ");}
            System.out.println();
        }
        System.out.println("----------------------------------------");


        int turno = 1;
        while (jogador.estaVivo() && inimigo.estaVivo() && turno <= MAX_TURNOS_POR_BATALHA) {
            System.out.println("\n--- Turno " + turno + "/" + MAX_TURNOS_POR_BATALHA + " ---");
            System.out.println(jogador.getNome() + " ("+jogador.getClasse().getNomeDisplay()+"): Vida: " + jogador.getVida() + "/" + jogador.getVidaMaxima() + " | Mana: " + jogador.getMana() + "/" + jogador.getManaMaxima() + (jogador.isDefendendo() ? " (DEFENDENDO)" : ""));
            System.out.println(inimigo.getNome() + ": Vida: " + inimigo.getVida() + "/" + inimigo.getVidaMaxima() + " | Mana: " + inimigo.getMana() + "/" + inimigo.getManaMaxima() + (inimigo.isDefendendo() ? " (DEFENDENDO)" : ""));
            System.out.println("--------------------");

            jogador.resetarEstadoDefesa();

            System.out.println("Escolha sua ação, " + jogador.getNome() + ":");
            System.out.println("1. Atacar");
            System.out.println("2. Defender");
            System.out.println("3. Usar Habilidade");
            System.out.println("4. Usar Item");
            System.out.println("5. Ver Status Detalhado");
            System.out.print("Sua escolha: ");

            int acao = -1;
            try {
                acao = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Tente um número.");
            }
            scanner.nextLine();

            switch (acao) {
                case 1: jogador.atacar(inimigo); break;
                case 2: jogador.defender(); break;
                case 3:
                    jogador.listarHabilidadesParaUso();
                    if (!jogador.getHabilidades().isEmpty()) {
                        System.out.print("Escolha a habilidade (número) ou 0 para cancelar: ");
                        int escolhaHab = -1;
                        try { escolhaHab = scanner.nextInt(); } catch (InputMismatchException e) {}
                        scanner.nextLine();
                        if (escolhaHab > 0 && escolhaHab <= jogador.getHabilidades().size()) {
                            Habilidade hab = jogador.getHabilidades().get(escolhaHab - 1);
                            Personagem alvoHabilidade = hab.isRequerAlvoOponente() ? inimigo : jogador;
                            jogador.lancarHabilidade(escolhaHab - 1, alvoHabilidade);
                        } else if (escolhaHab == 0) { System.out.println("Uso de habilidade cancelado."); }
                        else { System.out.println("Seleção de habilidade inválida."); }
                    } else { System.out.println("Você não possui habilidades para usar.");}
                    break;
                case 4:
                    jogador.listarItensParaUso();
                    if (!jogador.getInventario().isEmpty()) {
                        System.out.print("Escolha o item (número) ou 0 para cancelar: ");
                        int escolhaItem = -1;
                        try { escolhaItem = scanner.nextInt(); } catch (InputMismatchException e) {}
                        scanner.nextLine();
                        if (escolhaItem > 0 && escolhaItem <= jogador.getInventario().size()) {
                            jogador.usarItemDoInventario(escolhaItem - 1);
                        } else if (escolhaItem == 0) { System.out.println("Uso de item cancelado."); }
                        else { System.out.println("Seleção de item inválida."); }
                    } else { System.out.println("Seu inventário está vazio."); }
                    break;
                case 5:
                    System.out.println("\n--- Status de " + jogador.getNome() + " ---");
                    System.out.println("Classe: " + jogador.getClasse().getNomeDisplay());
                    System.out.println("Vida: " + jogador.getVida() + "/" + jogador.getVidaMaxima());
                    System.out.println("Mana: " + jogador.getMana() + "/" + jogador.getManaMaxima());
                    System.out.println("Ataque Base: " + jogador.getAtaqueBase());
                    System.out.println("Defesa Base: " + jogador.getDefesaBase());
                    jogador.exibirInventarioCompleto();
                    jogador.listarHabilidadesParaUso();
                    System.out.println("Pressione ENTER para voltar à batalha...");
                    scanner.nextLine();
                    turno--; // Não conta como uma ação que passa o turno
                    continue;
                default:
                    System.out.println("Ação inválida. Você hesitou e perdeu sua vez!");
                    break;
            }

            if (!inimigo.estaVivo() || !jogador.estaVivo()) break; // Sai do loop se alguém morreu

            // Ação do Inimigo
            if (inimigo.estaVivo()) {
                turnoDoInimigo(inimigo, jogador);
            }

            inimigo.resetarEstadoDefesa();

            if (!jogador.estaVivo()) break; // Sai se o jogador morreu após o turno do inimigo

            if (turno == MAX_TURNOS_POR_BATALHA && inimigo.estaVivo() && jogador.estaVivo()) {
                System.out.println("\nO limite de " + MAX_TURNOS_POR_BATALHA + " turnos foi atingido! A batalha termina em empate.");
                break;
            }
            turno++;
            System.out.println("----------------------------------------");
            System.out.println("Pressione ENTER para o próximo turno...");
            scanner.nextLine();
        }
        System.out.println("--- FIM DA BATALHA ---");
    }

    private void turnoDoInimigo(Personagem inimigo, Personagem jogadorAlvo) {
        System.out.println("\n--- Turno de " + inimigo.getNome() + " ---");
        inimigo.resetarEstadoDefesa();
        double chance = random.nextDouble();

        // IA um pouco melhorada:
        // 1. Se tiver pouca vida e habilidade de cura (não implementado para inimigos ainda), tenta curar.
        // 2. Se tiver mana e habilidade ofensiva, chance de usar.
        // 3. Se jogador com pouca vida, mais agressivo.
        // 4. Chance de defender se com pouca vida.
        // 5. Ataque padrão.

        boolean usouHabilidade = false;
        if (!inimigo.getHabilidades().isEmpty() && inimigo.getMana() > 0) {
            // Tenta usar a primeira habilidade ofensiva que tiver mana para
            for (int i = 0; i < inimigo.getHabilidades().size(); i++) {
                Habilidade habInimigo = inimigo.getHabilidades().get(i);
                if (habInimigo.isRequerAlvoOponente() && inimigo.getMana() >= habInimigo.getCustoMana()) {
                    if (chance < 0.5) { // 50% de chance de usar a habilidade se puder
                        inimigo.lancarHabilidade(i, jogadorAlvo);
                        usouHabilidade = true;
                    }
                    break; // Tenta usar apenas uma habilidade por turno
                }
            }
        }

        if (!usouHabilidade) {
            if (inimigo.getVida() < inimigo.getVidaMaxima() * 0.30 && chance < 0.4) { // 40% de chance de defender se com <30% vida
                inimigo.defender();
            } else {
                inimigo.atacar(jogadorAlvo);
            }
        }
    }

    private void fimDeJogo() {
        System.out.println("\n================ FIM DE JOGO ================");
        System.out.println("Estado Final: " + estadoAtual.getNomeDisplay());
        if (estadoAtual == EstadoJogo.FIM_DE_JOGO_VITORIA) {
            System.out.println("Parabéns, " + jogador.getNome() + "! Você derrotou todos os inimigos e se tornou o Lendário Campeão da Arena!");
            System.out.println("Sua bravura será cantada por eras!");
        } else if (estadoAtual == EstadoJogo.FIM_DE_JOGO_DERROTA) {
            System.out.println(jogador.getNome() + ", você lutou com honra, mas foi superado...");
            System.out.println("A Arena clama por heróis mais fortes. Tente novamente!");
        } else {
            System.out.println(jogador.getNome() + ", sua jornada na arena termina aqui. Estado: " + estadoAtual.getNomeDisplay());
        }
        System.out.println("Obrigado por jogar!");
        System.out.println("============================================");
    }
}
