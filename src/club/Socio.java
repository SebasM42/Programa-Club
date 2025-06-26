package club;

import java.util.ArrayList;
import java.util.List;

public class Socio {
    public enum Tipo { REGULAR, VIP }

    private String cedula;
    private String nombre;
    private Tipo tipo;
    private double fondos;
    private List<String> autorizados;
    private List<Factura> facturas;

    public Socio(String cedula, String nombre, Tipo tipo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.tipo = tipo;
        this.fondos = 0;
        this.autorizados = new ArrayList<>();
        this.facturas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarAutorizado(String nombreAut) {
        if (!autorizados.contains(nombreAut)) {
            autorizados.add(nombreAut);
        }
    }

    public boolean estaAutorizado(String nombreAut) {
        return autorizados.contains(nombreAut);
    }

    public void registrarConsumo(String concepto, double valor) {
        facturas.add(new Factura(concepto, valor));
    }

    public void pagarFactura(int idx) {
        if (idx >= 0 && idx < facturas.size()) {
            Factura f = facturas.get(idx);
            if (!f.pagada && fondos >= f.valor) {
                fondos -= f.valor;
                f.pagada = true;
            }
        }
    }

    public void aumentarFondos(double valor) {
        if (valor > 0) {
            fondos += valor;
        }
    }

    // Clase interna para Factura
    private static class Factura {
        String concepto;
        double valor;
        boolean pagada;

        Factura(String concepto, double valor) {
            this.concepto = concepto;
            this.valor = valor;
            this.pagada = false;
        }
    }
}