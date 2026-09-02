import java.io.IOException;
import java.util.Scanner;

import javax.crypto.spec.PBEKeySpec;

public class mostrarMenu { 
    static Scanner sc = new Scanner(System.in); 

    public static void main(String[] args) throws IOException {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            switch (opcion) {
                case 1 : registrarUnlector(); 
                break;
                case 2 : listarLectores();
                break;
                case 3 : eliminarLector();
                break;
                case 4 : registrarPrestamo();
                break;
                case 5 : listarPrestamo();
                break;
                case 6 : salirdelMenu();
                break;
                default: System.out.println("Opción inválida. Intente de nuevo.");
                break;
            }
            System.out.println();
        } while (opcion != 6);
        sc.close();
    }

    static void mostrarMenu() {
        System.out.println("=== SISTEMA DE BIBLIOTECA ===");
        System.out.println("1. Registrar nuevo lector");
        System.out.println("2. Listar lectores");
        System.out.println("3. Eliminar lector");
        System.out.println("4. Registrar préstamo");
        System.out.println("5. Listar préstamos de un lector");
        System.out.println("6. Salir");
    }

    static void registrarUnlector() throws IOException {
        System.out.print("Ingrese nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Ingrese teléfono: ");
        String telefono = sc.nextLine();

        Lector lector = new Lector();
        int nuevoId = lector.generarNuevoId();
        lector.setId(nuevoId);
        lector.setName(nombre);
        lector.setLastname(apellido);
        lector.setCel(telefono);

        Lector.crearLector(lector);
        System.out.println("Lector registrado correctamente. ID asignado: " + nuevoId);
    }

    static void listarLectores() throws IOException {
        System.out.println("ID\tNombre\tApellido\tTeléfono");
        for (Lector l : Lector.leerLector()) {
            System.out.println(l.getId() + "\t" + l.getName() + "\t" + l.getLastname() + "\t" + l.getCel());
        }
    }

    static void eliminarLector() throws IOException {
        int id = leerEntero("Ingrese el ID del lector a eliminar: ");
        Lector.eliminarLector(id);
    }

    static void registrarPrestamo() throws IOException {
        int idLector = leerEntero("Ingrese el ID del lector: ");
        if (!new Lector().existeLector(idLector)) {
            System.out.println("El lector no existe");
            return;
        }
        System.out.print("Ingrese nombre del libro: ");
        String libro = sc.nextLine();
        System.out.print("Ingrese fecha del préstamo (YYYY-MM-DD): ");
        String fechaPres = sc.nextLine();

        int nuevoId = Prestamo.generarID();
        Prestamo prestamo = new Prestamo(nuevoId, idLector, libro, fechaPres, "");
        Prestamo.crearPrestamo(prestamo);
        System.out.println("Préstamo registrado correctamente. ID asignado: " + nuevoId);
    }

    static void listarPrestamosPorLector() throws IOException {
        int idLector = leerEntero("Ingrese el ID del lector: ");
        listarPrestamo.listap_lector(idLector);
    }

    static void salirdelMenu() {
        System.out.println("Cerrando el sistema. Hasta pronto.");
    }

    static int leerEntero(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Ingrese un número entero válido.");
            }
        }
    }
}
