package co.edu.udea.refactorizacionreto3;

public class PesoException extends Exception{
    
    public PesoException(){
        super("El peso no puede ser menor que cero");
    }
    
}
