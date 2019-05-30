/**
 * Título.
 * Insira aqui uma pequena descrição.
 *
 * @author Cláudio Henrique <https://github.com/01ch01>
 * @since May 22, 2019 at 12:42:03 AM
 * @version 0.1
 */
package view;

public interface View {

    /**
     * Busca determinado objeto dentro de um ArrayList com base no parâmetro.
     *
     * @param termo : o termo que será usado como referência de pesquisa.
     * @return : o índice do ArrayList, ou qualquer outra estrutura de dados
     * indexada.
     */
    public int buscar(String termo);

    /**
     * Verifica se determinado objeto existe dentro de uma estrutura de dados
     * indexada.
     *
     * @param obj : o Objeto cuja existência será verificada.
     * @return : true caso o objeto exista.
     */
//    public boolean existe(Object obj);
    
    /**
     * Verifica se algum campo da GUI não foi preenchido.
     *
     * @return : true caso nenhum campo esteja em branco.
     */
    public boolean verificarCamposVazios();

    /**
     * Cria e desenha a tabela (AbstractTableModel), adicionando linha a linha.
     */
    public void mostrarTabela();

    /**
     * Copia os atributos de determinado objeto para os campos da GUI.
     *
     * @param obj : o objeto cujos atributos estão sendo copiados.
     */
//    public void copiarClasseParaCampos(Object obj);
    /**
     * Copia as informações dos campos da GUI para os atributos de determinado
     * objeto.
     *
     * @param obj : o objeto cujos atributos preenchidos.
     */
//    public void copiarCamposParaClasse(Object obj);
    /**
     * Carrega determinado arquivo com base no caminho (path) referenciado.
     *
     * @param caminho : o caminho em questão. Ex. "files/myfile.txt".
     */
    public void carregar(String caminho);

    /**
     * Salva as alterações de determinado arquivo.
     *
     * @param caminho : o caminho em questão. Ex. "files/myfile.txt".
     */
    public void salvar(String caminho);

    /**
     * Salva as informações (e alterações) de dados nas tabelas.
     */
    public void salvar();

    /**
     * Ativa/desativa os campos de texto (e botões de rádio) da interface, de
     * acordo com o parâmetro.
     *
     * @param flag : se false, além de desabilitar os campos, também limpa todo
     * o conteúdo dentro dos mesmos.
     */
    public void resetarCampos(boolean flag);

}
