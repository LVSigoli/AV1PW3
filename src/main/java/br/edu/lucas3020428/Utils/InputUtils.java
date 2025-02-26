package br.edu.lucas3020428.Utils;

import java.math.BigDecimal;
import java.util.Scanner;

public class InputUtils {


    public static BigDecimal normalizeInputToBigDecimal(Scanner scanner, String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                String entrada = scanner.next().replace(",", ".");
                BigDecimal valor = new BigDecimal(entrada);


                if (valor.compareTo(BigDecimal.ZERO) >= 0 && valor.compareTo(BigDecimal.TEN) <= 0) {
                    return valor;
                } else {
                    System.out.println("Erro: Digite um número entre 0 e 10!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número válido!");
            }
        }
    }
}
