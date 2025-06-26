package club;

public class Factura {
    private String nombre;      // Nombre del socio o autorizado que hizo el consumo
    private String concepto;    // Concepto del consumo
    private double valor;       // Valor del consumo

    public Factura(String nombre, String concepto, double valor) {
        this.nombre = nombre;
        this.concepto = concepto;
        this.valor = valor;
    }

    public String darNombre() {
        return nombre;
    }

    public String darConcepto() {
        return concepto;
    }

    public double darValor() {
        return valor;
    }
    public double getMonto() {
        return valor;
    }
}