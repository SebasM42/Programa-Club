package club;

import java.util.Scanner;
import club.Socio.Tipo;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int op = 0;
        Club c = new Club();

        do {
            System.out.println("\n1. Afiliar un socio al club.");
            System.out.println("2. Registrar una persona autorizada por un socio.");
            System.out.println("3. Pagar una factura.");
            System.out.println("4. Registrar un consumo en la cuenta de un socio");
            System.out.println("5. Aumentar fondos de la cuenta de un socio");
            System.out.println("6. Salir");
            System.out.print("Ingrese una opcion: ");
            try {
                op = Integer.parseInt(sc.next());
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida. Intente de nuevo.");
                continue;
            }

            switch (op) {
                case 1: {
                    String cedula;
                    while (true) {
                        while (true) {
                            System.out.print("Ingrese cédula: ");
                            try {
                                cedula = sc.next();
                                Long.parseLong(cedula);
                                break;
                            } catch (Exception e) {
                                System.out.println("La cédula solo debe contener números. Intente de nuevo.");
                            }
                        }
                        if (!c.cedulaDisponible(cedula)) {
                            System.out.println("El socio ya se encuentra registrado. Ingrese otra cédula.");
                        } else {
                            break;
                        }
                    }
                    String nombre;
                    while (true) {
                        System.out.print("Ingrese nombre: ");
                        try {
                            nombre = sc.next();
                            if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) throw new Exception();
                            break;
                        } catch (Exception e) {
                            System.out.println("El nombre no debe contener números ni caracteres especiales. Intente de nuevo.");
                        }
                    }
                    int tipo = 1;
                    while (true) {
                        System.out.print("Tipo (1: REGULAR, 2: VIP): ");
                        try {
                            tipo = Integer.parseInt(sc.next());
                            if (tipo == 1 || tipo == 2) break;
                        } catch (Exception e) {}
                        System.out.println("Tipo inválido. Intente de nuevo.");
                    }
                    Tipo t = (tipo == 2) ? Tipo.VIP : Tipo.REGULAR;
                    boolean exito = c.afiliarSocio(cedula, nombre, t);
                    System.out.println(exito ? "Socio afiliado correctamente." : "No se pudo afiliar el socio.");
                } break;
                case 2: {
                    String cedula;
                    while (true) {
                        System.out.print("Ingrese cédula del socio: ");
                        try {
                            cedula = sc.next();
                            Long.parseLong(cedula);
                            break;
                        } catch (Exception e) {
                            System.out.println("La cédula solo debe contener números. Intente de nuevo.");
                        }
                    }
                    String nombreAut;
                    while (true) {
                        System.out.print("Ingrese nombre del autorizado: ");
                        try {
                            nombreAut = sc.next();
                            if (!nombreAut.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) throw new Exception();
                            break;
                        } catch (Exception e) {
                            System.out.println("El nombre no debe contener números ni caracteres especiales. Intente de nuevo.");
                        }
                    }
                    c.agregarAutorizado(cedula, nombreAut);
                    System.out.println("Autorizado registrado.");
                } break;
                case 3: {
                    String cedula;
                    while (true) {
                        System.out.print("Ingrese cédula del socio: ");
                        try {
                            cedula = sc.next();
                            Long.parseLong(cedula);
                            break;
                        } catch (Exception e) {
                            System.out.println("La cédula solo debe contener números. Intente de nuevo.");
                        }
                    }
                    int idx = -1;
                    while (true) {
                        System.out.print("Ingrese índice de la factura a pagar: ");
                        try {
                            idx = Integer.parseInt(sc.next());
                            if (idx >= 0) break;
                        } catch (Exception e) {}
                        System.out.println("Índice inválido. Intente de nuevo.");
                    }
                    // Obtener el monto de la factura y los fondos disponibles
                    double monto = c.getFactura(cedula, idx); // Debe retornar el monto de la factura o -1 si no existe
                    double fondos = c.getFondos(cedula); // Debe retornar los fondos actuales del socio
                    if (monto < 0) {
                        System.out.println("La factura no existe.");
                    } else {
                        System.out.println("Monto a pagar: $" + monto);
                        System.out.println("Fondos disponibles: $" + fondos);
                        if (fondos >= monto) {
                            c.pagarFactura(cedula, idx);
                            System.out.println("Factura pagada correctamente.");
                        } else {
                            System.out.println("Fondos insuficientes para pagar la factura.");
                        }
                    }
                } break;
                case 4: {
                    String cedula;
                    while (true) {
                        System.out.print("Ingrese cédula del socio: ");
                        try {
                            cedula = sc.next();
                            Long.parseLong(cedula);
                            break;
                        } catch (Exception e) {
                            System.out.println("La cédula solo debe contener números. Intente de nuevo.");
                        }
                    }
                    String nombre;
                    while (true) {
                        System.out.print("Ingrese nombre (socio o autorizado): ");
                        try {
                            nombre = sc.next();
                            if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) throw new Exception();
                            break;
                        } catch (Exception e) {
                            System.out.println("El nombre no debe contener números ni caracteres especiales. Intente de nuevo.");
                        }
                    }
                    System.out.print("Ingrese concepto: ");
                    String concepto = sc.next();
                    double valor = 0;
                    while (true) {
                        System.out.print("Ingrese valor: ");
                        try {
                            valor = Double.parseDouble(sc.next());
                            if (valor > 0) break;
                        } catch (Exception e) {}
                        System.out.println("Valor inválido. Debe ser un número positivo. Intente de nuevo.");
                    }
                    c.registrarConsumo(cedula, nombre, concepto, valor);
                    System.out.println("Consumo registrado.");
                } break;
                case 5: {
                    String cedula;
                    while (true) {
                        System.out.print("Ingrese cédula del socio: ");
                        try {
                            cedula = sc.next();
                            Long.parseLong(cedula);
                            break;
                        } catch (Exception e) {
                            System.out.println("La cédula solo debe contener números. Intente de nuevo.");
                        }
                    }
                    double valor = 0;
                    while (true) {
                        System.out.print("Ingrese valor a aumentar: ");
                        try {
                            valor = Double.parseDouble(sc.next());
                            if (valor > 0) break;
                        } catch (Exception e) {}
                        System.out.println("Valor inválido. Debe ser un número positivo. Intente de nuevo.");
                    }
                    c.aumentarFondos(cedula, valor);
                    System.out.println("Fondos aumentados.");
                } break;
                case 6: {
                    System.out.println("Gracias!");
                } break;
                default:
                    System.out.println("Opción inválida");
            }
        } while (op != 6);

        sc.close();
    }
}