package utils;

import java.util.Scanner;

public class Utils {
    private static final Scanner scanner = new Scanner(System.in);

    public static int lerInteiro() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("❌ Entrada inválida. Digite um número: ");
            }
        }
    }

    public static Integer lerInteiroOpcional() {
        String entrada = scanner.nextLine().trim();
        if (entrada.isEmpty()) {
            return null; 
        }
        try {
            return Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada inválida. Mantendo valor atual.");
            return null;
        }
    }
    
    public static double lerDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("❌ Entrada inválida. Digite um número válido: ");
            }
        }
    }

    public static Double lerDoubleOpcional() {
        String entrada = scanner.nextLine().trim();
        if (entrada.isEmpty()) {
            return null; 
        }
        try {
            return Double.parseDouble(entrada);
        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada inválida. Mantendo valor atual.");
            return null;
        }
    }

    public static Integer converterParaInteiro(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void pausarTela() {
        System.out.print("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }
}
