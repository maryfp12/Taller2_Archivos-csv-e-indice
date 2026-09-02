
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class Lector {
    private int id;
    private String name;
    private String lastname;
    private String cel;

    public Lector(){

    }

    public Lector(int id, String name, String lastname, String cel) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.cel = cel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public static void crearLector(Lector lector)
            throws IOException {
        FileWriter fw = new FileWriter("lectores.csv", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(lector.toString());
        bw.newLine();
        bw.close();
    }

    public static List<Lector> leerLector()
            throws IOException {
        List<Lector> lista = new ArrayList<>();
        Scanner sc = new Scanner(new File("lectores.csv"));

        while (sc.hasNextLine()) {
            String[] datos = sc.nextLine().split(",");
            lista.add(new Lector(
                    Integer.parseInt(datos[0]),
                    datos[1],
                    datos[2],
                    datos[3]));
        }
        sc.close();
        return lista;

    }

    public int generarNuevoId() throws IOException {
        List<Lector> lista = leerLector();
        int id2 = 0;
        for (Lector l : lista) {
            if (l.getId() > id2) {
                id2 = l.getId();
            }
        }
        return id2 + 1;
    }



    public static void actualizarLector(int id, String newName, String newLastname, String newCel)
            throws IOException {
        List<Lector> lista = leerLector();
        BufferedWriter bw = new BufferedWriter(new FileWriter("lectores.csv"));
        for (Lector l : lista) {
            if (l.getId() == id) {
                l.setName(newName);
                l.setLastname(newLastname);
                l.setCel(newCel);
            }
            bw.write(l.toString());
            bw.newLine();
        }
        bw.close();
    }

    public boolean existeLector(int id)
            throws IOException {
        List<Lector> lista = leerLector();

        for (Lector l : lista) {
            if (l.getId() == id) {
                return true;
            }
        }
        return false;
    }
    public static boolean tienePrestamos(int idLector)
     throws IOException {
    List<Prestamo> prestamos = Prestamo.leerPrestamo();
    for (Prestamo p : prestamos) {
      if (p.getId_lector() == idLector && (p.getFecha_dev() == null || p.getFecha_dev().isEmpty())) {
            return true;
        }
    }

    return false;
}

    public static void eliminarLector(int id)
            throws IOException {
                 if (tienePrestamos(id)) {
        System.out.println("No se puede eliminar: el lector tiene préstamos registrados ");
        return;
    }
        List<Lector> lista = leerLector();
        BufferedWriter bw = new BufferedWriter(new FileWriter("lectores.csv"));

        for (Lector l : lista) {
            if (l.getId() != id) {
                bw.write(l.toString());
                bw.newLine();
            }
        }
        bw.close();

    System.out.println("Lector eliminado exitosamente");

    }

    @Override
    public String toString() {
        return id + "," + name + "," + lastname + "," + cel;
    }

}
