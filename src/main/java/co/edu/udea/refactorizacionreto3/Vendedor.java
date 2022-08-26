package co.edu.udea.refactorizacionreto3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Santiago Graciano
 */
public class Vendedor {

    private final double VENTAS_ESPERADAS_ANUALES = 80000;
    public static SimpleDateFormat formatoFechaSimple = new SimpleDateFormat("dd/MM/yyy");
    private int documento;
    private String nombres;
    private String apellidos;
    private int edad;
    private double totalVendido;
    private String descripcion;
    private Date fechaIngreso;

    public Vendedor() {
    }

    public Vendedor(int documento, String nombres, String apellidos, int edad, double totalVendido, String descripcion, Date fechaIngreso) throws EdadPersonaException, AnioIngresoException  {
        this.documento = documento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        if (edad >= 18) {
            this.edad = edad;
        } else {
            throw new EdadPersonaException();
        }
        this.totalVendido = totalVendido;
        this.descripcion = descripcion;
        if (validarAnio(fechaIngreso)) {
            this.fechaIngreso = fechaIngreso;
        } else {
            throw new AnioIngresoException();
        }
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) throws AnioIngresoException {
        boolean estadoAnio = validarAnio(fechaIngreso);

        if (estadoAnio) {
            this.fechaIngreso = fechaIngreso;
        } else {
            throw new AnioIngresoException();
        }
    }

    public boolean validarAnio(Date fecha) {
        boolean validador = false;
        Date fechaActual = new Date();
        SimpleDateFormat formatAnioActual = new SimpleDateFormat("yyyy");
        int anioActual = Integer.parseInt(formatAnioActual.format(fechaActual));
        SimpleDateFormat formatAnio = new SimpleDateFormat("yyyy");
        int anioEnFecha = Integer.parseInt(formatAnio.format(fecha));

        if (anioActual == anioEnFecha) {
            validador = true;
        }

        return validador;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) throws EdadPersonaException {
        if (edad >= 18) {
            this.edad = edad;
        } else {
            throw new EdadPersonaException();
        }
    }

    public double getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(double totalVendido) {
        this.totalVendido = totalVendido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String obtenerCategoriaVendedor() {
        String categoriaVendedor = "Avanzado";
        if (this.totalVendido == 0) {
            categoriaVendedor = "Novato";
        } else if (this.totalVendido > 0 && this.totalVendido <= 500000000) {
            categoriaVendedor = "principiante";
        } else if (this.totalVendido > 500000000 && this.totalVendido <= 2000000000) {
            categoriaVendedor = "Intermedio";
        }

        return categoriaVendedor;
    }

    public int consultarAntiguedad() throws ParseException {
        int anios = 0;
            Date fechaActual = new Date();
            fechaActual = formatoFechaSimple.parse(formatoFechaSimple.format(new Date()));

            long d = Math.abs(fechaActual.getTime() - getFechaIngreso().getTime());
            long g = TimeUnit.DAYS.convert(d, TimeUnit.MILLISECONDS);
            anios = (int) g / 365;
        
        return anios;
    }

    /**
     * Formula para definir la categoria donde se encuentra el vendedor de
     * acuerdo a su total de ventas en los años de antiguedad se va a medir en
     * porcentaje de la siguiente manera -malo: <45% -regular: entre 45% - 70%
     * -bueno: entre 70% - 90% excelente: mayor al 90%
     *
     * ((totalVentas/# de años) * 100) / ventasEsperadasAnual @return categ
     * @return
     */
    public String consultarEstadoDeVendedor() throws ParseException {
        String mensaje = "";
        double formula = ((getTotalVendido() / consultarAntiguedad()) * 100) / this.VENTAS_ESPERADAS_ANUALES;

        if (formula <= 45) {
            mensaje = "El vendedor " + this.getNombres() + " " + this.getApellidos() + " su estado es malo";
        } else if (formula > 45 && formula <= 70) {
            mensaje = "El vendedor " + this.getNombres() + " " + this.getApellidos() + " su estado es regular";
        } else if (formula > 70 && formula <= 90) {
            mensaje = "El vendedor " + this.getNombres() + " " + this.getApellidos() + " su estado es bueno";
        } else if (formula > 90) {
            mensaje = "El vendedor " + this.getNombres() + " " + this.getApellidos() + " su estado es excelente";
        }

        return mensaje;
    }

}
