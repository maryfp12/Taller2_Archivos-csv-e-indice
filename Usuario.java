public class Usuario{
int id;
String name;
String lastname;
int cel;
 
 public  Usuario(int id, String name, String lastname, int cel){
    this.id = id;
    this.name = name;
    this.lastname = lastname;
    this.cel = cel;
 }
 @Override
  public String toString(){
        return "Usuario:" + this.id + " ," + this.name + " ," + this.lastname + " ,"+ this.cel;
    }



}


