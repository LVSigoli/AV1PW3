package br.edu.lucas3020428.Utils;

import java.math.BigDecimal;
import java.util.Scanner;

public class InputUtils {


    public static BigDecimal normalizeInputToBigDecimal(Scanner scanner, String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                String entrada = scanner.next().replace(",", ".");

                return new BigDecimal(entrada);

            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número válido!");
            }
        }
    }
}
