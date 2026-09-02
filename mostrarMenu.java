import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class mostrarMenu{ 
   static ArrayList<String[]> mostrarMenu = new ArrayList<>();
    static Scanner sc = new Scanner(System.in); 

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("seleccione una opción: ");
            switch (opcion) {
                case 1 -> registrarUnlector();
                case 2 -> listarLector();
                case 3 -> eliminarLector();
                case 4 -> registrarPrestamo();
                case 5 -> listarPrestamo();
                case 6 -> salirdelMenu(); 
                System.out.println("Cerrando el sistema. Hasta pronto.");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
            System.out.println();
        } while (opcion != 6);
        sc.close();
    }
     static void mostrarMenu() {
        System.out.println("=== SISTEMA DE BIBLIOTECA ===");
        System.out.println("1. Registrar nuevo lector");
        System.out.println("2. Listar a un lector");
        System.out.println("3. Eliminar a un lector ");
        System.out.println("4. Registar un préstamo");
        System.out.println("5. Listar un préstamo");
        System.out.println("6. Salir");
    }

     static void registrarUnlector() 
     throws IOException{
        System.out.println("Ingrese nombre:");
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
     
     static void listarLector()
        throws IOException {
        System.out.println("ID\t Nombre \t Apellido \tTelefono");
        for (Lector l : Lector.leerLector()) {
            System.out.println(l.getId() + "\t" + l.getName() + "\t" + l.getLastname() + "\t" + l.getCel());
        }

     }
     static void eliminarLector(){
         int id = leerEntero("Ingrese el ID del lector a eliminar: ");
        Lector.eliminarLector(id);

     }
   
   

      static void registrarPrestamo()
       throws IOException {
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

    static void listarPrestamosPorLector() 
    throws IOException {
        int idLector = leerEntero("Ingrese el ID del lector: ");
        ConsultaPrestamo.listarPrestamosPorLector(idLector);
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

