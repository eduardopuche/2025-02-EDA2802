import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

import model.*;
import services.*;

public class App {
    private static final AccountService accountService = new AccountService();
    private static final BalanceService balanceService = new BalanceService();
    private static final LoansService loansService = new LoansService();
    private static final CardService cardService = new CardService();

    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                printMainMenu();
                String option = sc.nextLine().trim();
                switch (option) {
                    case "1":
                        runAccountMenu(sc);
                        break;
                    case "2":
                        runBalanceMenu(sc);
                        break;
                    case "3":
                        runLoansMenu(sc);
                        break;
                    case "4":
                        runCardsMenu(sc);
                        break;
                    case "0":
                        running = false;
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                }
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n=== Menú Principal ===");
        System.out.println("1. Account");
        System.out.println("2. Balance");
        System.out.println("3. Loans");
        System.out.println("4. Cards");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    
    private static void runAccountMenu(Scanner sc) {
        boolean back = false;
        while (!back) {
            printCrudMenu("Account");
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1":
                    System.out.println("[Account] Crear nuevo:");
                    String accNum = leerTextoSeguro(sc, "Número de cuenta: ");
                    String name = leerTextoSeguro(sc, "Nombre: ");
                    String email = leerTextoSeguro(sc, "Email: ");
                    String phone = leerTextoSeguro(sc, "Teléfono: ");
                    String type = leerTextoSeguro(sc, "Tipo de cuenta (Savings/Checking): ");
                    String address = leerTextoSeguro(sc, "Dirección: ");

                    Account newAcc = new Account(accNum, name, email, phone, type, address);
                    accountService.save(newAcc);
                    System.out.println(" Cuenta creada correctamente.");
                    pause(sc);
                    break;

                case "2":
                    String id = leerTextoSeguro(sc, "Ingrese ID de la cuenta: ");
                    accountService.findById(id)
                            .ifPresentOrElse(System.out::println,
                                    () -> System.out.println(" Cuenta no encontrada."));
                    pause(sc);
                    break;

                case "3":
                    System.out.println("Listado de cuentas:");
                    accountService.findAll().forEach(System.out::println);
                    pause(sc);
                    break;

                case "4":
                    String idUp = leerTextoSeguro(sc, "Ingrese número de cuenta a actualizar: ");
                    String newName = leerTextoSeguro(sc, "Nuevo nombre: ");
                    String newEmail = leerTextoSeguro(sc, "Nuevo email: ");
                    String newPhone = leerTextoSeguro(sc, "Nuevo teléfono: ");
                    String newType = leerTextoSeguro(sc, "Nuevo tipo: ");
                    String newAddr = leerTextoSeguro(sc, "Nueva dirección: ");

                    Account updated = new Account(idUp, newName, newEmail, newPhone, newType, newAddr);
                    accountService.save(updated);
                    System.out.println("Cuenta actualizada correctamente.");
                    pause(sc);
                    break;

                case "5":
                    String delId = leerTextoSeguro(sc, "Ingrese número de cuenta a eliminar: ");
                    boolean deleted = accountService.deleteById(delId);
                    System.out.println(deleted ? "Cuenta eliminada." : "No se encontró la cuenta.");
                    pause(sc);
                    break;

                case "0":
                    back = true;
                    break;
                default:
                    System.out.println(" Opción no válida.");
            }
        }
    }

    
    private static void runBalanceMenu(Scanner sc) {
        boolean back = false;
        while (!back) {
            printCrudMenu("Balance");
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1":
                    System.out.println("[Balance] Crear nuevo registro:");
                    Balance b = new Balance();
                    b.setDate(LocalDate.now());
                    b.setDescription(leerTextoSeguro(sc, "Descripción: "));
                    b.setCashIn(leerBigDecimalSeguro(sc, "Entrada (cashIn): "));
                    b.setCashOut(leerBigDecimalSeguro(sc, "Salida (cashOut): "));
                    b.setClosingBalance(leerBigDecimalSeguro(sc, "Balance final: "));
                    balanceService.save(b);
                    System.out.println("Balance registrado correctamente.");
                    pause(sc);
                    break;

                case "2":
                    String date = leerTextoSeguro(sc, "Ingrese fecha: ");
                    balanceService.findById(date, null)
                            .ifPresentOrElse(System.out::println,
                                    () -> System.out.println("Balance no encontrado."));
                    pause(sc);
                    break;

                case "3":
                    System.out.println("Listado de balances:");
                    balanceService.findAll().forEach(System.out::println);
                    pause(sc);
                    break;

                case "4":
                    String upDate = leerTextoSeguro(sc, "Ingrese fecha  del balance a actualizar: ");
                    Balance updateB = new Balance();
                    updateB.setDate(LocalDate.parse(upDate));
                    updateB.setDescription(leerTextoSeguro(sc, "Nueva descripción: "));
                    updateB.setCashIn(leerBigDecimalSeguro(sc, "Nuevo cashIn: "));
                    updateB.setCashOut(leerBigDecimalSeguro(sc, "Nuevo cashOut: "));
                    updateB.setClosingBalance(leerBigDecimalSeguro(sc, "Nuevo balance final: "));
                    balanceService.save(updateB);
                    System.out.println("Balance actualizado correctamente.");
                    pause(sc);
                    break;

                case "5":
                    String delDate = leerTextoSeguro(sc, "Ingrese fecha  del balance a eliminar: ");
                    boolean removed = balanceService.deleteById(delDate, null);
                    System.out.println(removed ? " Balance eliminado." : " No se encontró el balance.");
                    pause(sc);
                    break;

                case "0":
                    back = true;
                    break;
                default:
                    System.out.println(" Opción no válida.");
            }
        }
    }

    
    private static void runLoansMenu(Scanner sc) {
        boolean back = false;
        while (!back) {
            printCrudMenu("Loans");
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1":
                    System.out.println("[Loans] Crear nuevo préstamo:");
                    Loans l = new Loans();
                    l.setDate(LocalDate.now());
                    l.setType(leerTextoSeguro(sc, "Tipo (Home, Vehicle, University, Personal): "));
                    l.setTotalLoan(leerBigDecimalSeguro(sc, "Monto total: "));
                    l.setAmountPaid(leerBigDecimalSeguro(sc, "Monto pagado: "));
                    l.setOutstandingAmt(leerBigDecimalSeguro(sc, "Monto pendiente: "));
                    loansService.save(l);
                    System.out.println(" Préstamo registrado correctamente.");
                    pause(sc);
                    break;

                case "2":
                    String id = leerTextoSeguro(sc, "Ingrese fecha: ");
                    loansService.findById(id, null)
                            .ifPresentOrElse(System.out::println,
                                    () -> System.out.println(" Préstamo no encontrado."));
                    pause(sc);
                    break;

                case "3":
                    System.out.println("Listado de préstamos:");
                    loansService.findAll().forEach(System.out::println);
                    pause(sc);
                    break;

                case "4":
                    String upId = leerTextoSeguro(sc, "Ingrese fecha  del préstamo a actualizar: ");
                    Loans upLoan = new Loans();
                    upLoan.setDate(LocalDate.parse(upId));
                    upLoan.setType(leerTextoSeguro(sc, "Nuevo tipo: "));
                    upLoan.setTotalLoan(leerBigDecimalSeguro(sc, "Nuevo total: "));
                    upLoan.setAmountPaid(leerBigDecimalSeguro(sc, "Nuevo pagado: "));
                    upLoan.setOutstandingAmt(leerBigDecimalSeguro(sc, "Nuevo pendiente: "));
                    loansService.save(upLoan);
                    System.out.println(" Préstamo actualizado correctamente.");
                    pause(sc);
                    break;

                case "5":
                    String delId = leerTextoSeguro(sc, "Ingrese fecha del préstamo a eliminar: ");
                    boolean deleted = loansService.deleteById(delId, null);
                    System.out.println(deleted ? " Préstamo eliminado." : " No se encontró el préstamo.");
                    pause(sc);
                    break;

                case "0":
                    back = true;
                    break;
                default:
                    System.out.println(" Opción no válida.");
            }
        }
    }

    
    private static void runCardsMenu(Scanner sc) {
        boolean back = false;
        while (!back) {
            printCrudMenu("Cards");
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1":
                    System.out.println("[Cards] Crear nueva tarjeta:");
                    Cards c = new Cards();
                    c.setCardNumber(leerTextoSeguro(sc, "Número de tarjeta: "));
                    c.setType(leerTextoSeguro(sc, "Tipo (Credit/Debit): "));
                    BigDecimal total = leerBigDecimalSeguro(sc, "Límite total: ");
                    BigDecimal used = leerBigDecimalSeguro(sc, "Monto usado: ");
                    c.setTotalLimit(total);
                    c.setAmountUsed(used);
                    c.setAvailable(total.subtract(used));
                    cardService.save(c);
                    System.out.println("Tarjeta registrada correctamente.");
                    pause(sc);
                    break;

                case "2":
                    String id = leerTextoSeguro(sc, "Ingrese número de tarjeta: ");
                    cardService.findById(id)
                            .ifPresentOrElse(System.out::println,
                                    () -> System.out.println("Tarjeta no encontrada."));
                    pause(sc);
                    break;

                case "3":
                    System.out.println("Listado de tarjetas:");
                    cardService.findAll().forEach(System.out::println);
                    pause(sc);
                    break;

                case "4":
                    String upId = leerTextoSeguro(sc, "Ingrese número de tarjeta a actualizar: ");
                    Cards upCard = new Cards();
                    upCard.setCardNumber(upId);
                    upCard.setType(leerTextoSeguro(sc, "Nuevo tipo: "));
                    BigDecimal newTotal = leerBigDecimalSeguro(sc, "Nuevo límite total: ");
                    BigDecimal newUsed = leerBigDecimalSeguro(sc, "Nuevo monto usado: ");
                    upCard.setTotalLimit(newTotal);
                    upCard.setAmountUsed(newUsed);
                    upCard.setAvailable(newTotal.subtract(newUsed));
                    cardService.save(upCard);
                    System.out.println(" Tarjeta actualizada correctamente.");
                    pause(sc);
                    break;

                case "5":
                    String delId = leerTextoSeguro(sc, "Ingrese número de tarjeta a eliminar: ");
                    boolean deleted = cardService.deleteById(delId);
                    System.out.println(deleted ? " Tarjeta eliminada." : " No se encontró la tarjeta.");
                    pause(sc);
                    break;

                case "0":
                    back = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    
    private static void printCrudMenu(String entityName) {
        System.out.println("\n--- " + entityName + " CRUD ---");
        System.out.println("1. Create");
        System.out.println("2. Read by id");
        System.out.println("3. List all");
        System.out.println("4. Update");
        System.out.println("5. Delete");
        System.out.println("0. Back");
        System.out.print("Seleccione una opción: ");
    }

    private static BigDecimal leerBigDecimalSeguro(Scanner sc, String mensaje) {
        BigDecimal valor = null;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensaje);
            String input = sc.nextLine().trim();
            try {
                valor = new BigDecimal(input);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println(" Valor inválido. Ingrese un número válido .");
            }
        }
        return valor;
    }

    private static String leerTextoSeguro(Scanner sc, String mensaje) {
        String input = "";
        while (input.isBlank()) {
            System.out.print(mensaje);
            input = sc.nextLine().trim();
            if (input.isBlank()) System.out.println(" Este campo no puede estar vacío.");
        }
        return input;
    }

    private static void pause(Scanner sc) {
        System.out.print(" ENTER para continuar.");
        sc.nextLine();
    }
}
