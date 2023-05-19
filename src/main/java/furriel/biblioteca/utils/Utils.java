package furriel.biblioteca.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Classe de métodos utils
 */
public class Utils {

    /**
     * Verifica se a string de input contém apenas caractéres numéricos
     *
     * @param str String de input
     * @return verdadeiro para apenas números || falso caso contenha uma letra/símbolo
     */
    public static boolean verificaNumero(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        return str.matches("[0-9]+");
    }

    /**
     * Calcula a diferença de dias dada duas datas
     * @param dataInicial Data inicial
     * @param dataFinal Data final
     * @return Retorna a diferença
     */
    public static long calculaDiferencaDias(Date dataInicial, Date dataFinal) {

        long milissegundosInicial = dataInicial.getTime();
        long milissegundosFinal = dataFinal.getTime();

        long diferencaMilissegundos = milissegundosFinal - milissegundosInicial;

        return diferencaMilissegundos / (1000 * 60 * 60 * 24);
    }

    /**
     * Calcula uma data após 30 dias de uma data inicial
     * @param data Data inicial
     * @return Retorna a data um mês depois
     */
    public static Date calcularDiaDepoisDeUmMes(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    /**
     * Converte uma data para uma string no formato dd/MM/aaaa
     * @param data Data a ser transformada
     * @return String da data
     */
    public static String converteData(Date data) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(data);
    }
}
