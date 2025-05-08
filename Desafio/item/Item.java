package Desafio.item;

import Desafio.personagem.Personagem;
import Desafio.item.enums.TipoItem; // Importando o Enum TipoItem

/**
 * Interface para itens que podem ser usados por personagens.
 * Define o contrato básico para todos os itens do jogo.
 * Movido para o pacote Desafio.item
 */
public interface Item {
    /**
     * Retorna o nome do item.
     * @return O nome do item.
     */
    String getNome();

    /**
     * Retorna a descrição do item.
     * @return A descrição do item.
     */
    String getDescricao();

    /**
     * Retorna o tipo do item.
     * @return O TipoItem do item.
     */
    TipoItem getTipo(); // Novo método

    /**
     * Executa a ação do item quando usado pelo personagem.
     * @param usuario O personagem que está usando o item.
     */
    void usar(Personagem usuario);

    /**
     * Indica se o item é consumível após o uso.
     * @return true se o item deve ser removido do inventário após o uso, false caso contrário.
     */
    boolean isConsumivel();
}
