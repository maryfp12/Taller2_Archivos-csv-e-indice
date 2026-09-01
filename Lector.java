
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class Lector{
int id;
String name;
String lastname;
int cel;

 
 public  Lector(int id, String name, String lastname, int cel){
    this.id = id;
    this.name = name;
    this.lastname = lastname;
    this.cel = cel;
 }
 

 public static void crearLector(Lector lector)
   throws IOException{
      FileWriter fw = new FileWriter("lectores.csv", true);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(lector.toString());
      bw.newLine();
      bw.close();
     }


  public  List<Lector> leerLector()
  throws IOException{
  List<Lector> lista = new ArrayList<>();
  Scanner sc= new Scanner (new File("lectores.csv"));
  
  while(sc.hasNextLine()){
   String [] datos =  sc.nextLine().split(",");
   lista.add( new Lector(
      Integer.parseInt(datos[0]),
      datos[1],
      datos[2],
      Integer.parseInt(datos[3])
   ));
  }
sc.close();
return lista;

  }







@Override
public String toString(){
   return id + "," + name + "," + lastname + "," + cel;
}

 }

 






