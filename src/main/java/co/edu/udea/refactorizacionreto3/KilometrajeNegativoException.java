package co.edu.udea.refactorizacionreto3;

public class KilometrajeNegativoException extends Exception{
    
    public KilometrajeNegativoException(){
        super("El automovil no puede tener kilometraje negativo");
    }
    
}
