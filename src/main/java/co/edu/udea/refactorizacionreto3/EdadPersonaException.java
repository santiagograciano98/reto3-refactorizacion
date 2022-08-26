package co.edu.udea.refactorizacionreto3;

public class EdadPersonaException extends Exception{
    
    public EdadPersonaException(){
        super("La edad del vendedor no puede ser menor de 18");
    }
    
}
