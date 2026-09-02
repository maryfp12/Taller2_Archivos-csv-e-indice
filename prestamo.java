import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Prestamo {
    private int id_prestamo;
    private int id_lector;
    private String n_libro;
    private String fecha_pres;
    private String fecha_dev;

    public Prestamo(int id_prestamo, int id_lector, String n_libro, String fecha_pres, String fecha_dev) {
        this.id_prestamo = id_prestamo;
        this.id_lector = id_lector;
        this.n_libro = n_libro;
        this.fecha_dev = fecha_dev;
        this.fecha_pres = fecha_pres;
    }

    public int getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    public int getId_lector() {
        return id_lector;
    }

    public void setId_lector(int id_lector) {
        this.id_lector = id_lector;
    }

    public String getN_libro() {
        return n_libro;
    }

    public void setN_libro(String n_libro) {
        this.n_libro = n_libro;
    }

    public String getFecha_pres() {
        return fecha_pres;
    }

    public void setFecha_pres(String fecha_pres) {
        this.fecha_pres = fecha_pres;
    }

    public String getFecha_dev() {
        return fecha_dev;
    }

    public void setFecha_dev(String fecha_dev) {
        this.fecha_dev = fecha_dev;
    }

    @Override
    public String toString() {
        return id_prestamo + "," + id_lector + "," + n_libro + "," + fecha_pres + "," + fecha_dev;
    }

    public static void crearPrestamo(Prestamo prestamo) throws IOException {
        FileWriter fw = new FileWriter("prestamos.csv", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(prestamo.toString());
        bw.newLine();
        bw.close();
    }

    public static List<Prestamo> leerPrestamo() throws IOException {
        List<Prestamo> lista = new ArrayList<>();
        Scanner sc = new Scanner(new File("prestamos.csv"));

        while (sc.hasNextLine()) {
            String[] datos = sc.nextLine().split(",");
            lista.add(new Prestamo(
                    Integer.parseInt(datos[0]),
                    Integer.parseInt(datos[1]),
                    datos[2],
                    datos[3],datos[4]));
        }
        sc.close();
        return lista;
    }
}

public static boolean existeIdPrestamo(int id_prestamo) throws IOException {
    List<Prestamo> lista = leerPrestamo();
    for (prestamo p : lista) {
        if (p.getId_prestamo() == id_prestamo) {
            return true;
        }
    }
    return false;
}

public static int generarID() throws IOException {
    List<Prestamo> lista = leerPrestamo();
    int Idsig = 0;
    for (Prestamo p : lista) {
        if (p.getId_prestamo() > Idsig) {
            Idsig = p.getId_prestamo();
        }
    }
    return Idsig +1;
}
public class listarPrestamo{
    public static void listap_lector( int id_lector) throws IOException{
         Lector lectorObj = new Lector();
        if(!lector.existeLector(id_lector)){
            System.out.println("el lector con id"+ id_lector+ "no existe");
         return;
    }
    List<Prestamo> todos=Prestamo.leerPrestamo();
    List<Prestamo> prestamoLector=new ArrayList<>();
    for (Prestamo p: todos){
        if(p.getId_lector()== id_lector){
            prestamoLector.add(p);
        }
    }
    if(prestamoLector.isEmpty()){
        System.out.println("el  lector no  tiene préstamos");
        return;
    }
    System.out.println("préstamos del lector"+ id_lector+ ":" );
    for(Prestamo p: prestamoLector){
        System.out.println("id préstamo:"+ p.getId_prestamo()+"id lector:"+ p.getId_lector()+"nombre del libro"+p.getN_libro()+"fecha de préstamo:"+p.getFecha_pres()+"fecha de devolución"+p.getFecha_dev());
    }
    }

}