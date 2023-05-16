package furriel.biblioteca.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

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

    public static long calculaDiferencaDias(Date dataInicial, Date dataFinal) {

        long milissegundosInicial = dataInicial.getTime();
        long milissegundosFinal = dataFinal.getTime();

        long diferencaMilissegundos = milissegundosFinal - milissegundosInicial;

        return diferencaMilissegundos / (1000 * 60 * 60 * 24);
    }

    public static Date calcularDiaDepoisDeUmMes(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    public static String converteData(Date data) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(data);
    }

    public static Date lerData() {
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print("Insira a data de hoje <dd/mm/aaaa>: ");
                int dia = sc.nextInt();
                int mes = sc.nextInt();
                int ano = sc.nextInt();

                mes--; //ENUM
                ano -= 1900;

                return new Date(ano, mes, dia);
            } catch (Exception e) {
                System.out.println("ERRO! Por favor insira novamente");
            }
        } while (true);
    }
}
