package club;

import java.util.ArrayList;

public class Socio {
    public enum Tipo { VIP, REGULAR }

    private String cedula;
    private String nombre;
    private double fondos;
    private Tipo tipo;
    private ArrayList<Factura> facturas;
    private ArrayList<String> autorizados;

    // Límites y fondos iniciales según el tipo de socio
    private static final double FONDO_INICIAL_REGULAR = 50;
    private static final double FONDO_INICIAL_VIP = 100;
    private static final double FONDO_MAX_REGULAR = 1000;
    private static final double FONDO_MAX_VIP = 5000;

    public Socio(String cedula, String nombre, Tipo tipo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.tipo = tipo;
        this.fondos = (tipo == Tipo.VIP) ? FONDO_INICIAL_VIP : FONDO_INICIAL_REGULAR;
        this.facturas = new ArrayList<>();
        this.autorizados = new ArrayList<>();
    }

    public String darCedula() { return cedula; }
    public String darNombre() { return nombre; }
    public double darFondos() { return fondos; }
    public Tipo darTipo() { return tipo; }
    public ArrayList<Factura> darFacturas() { return facturas; }
    public ArrayList<String> darAutorizados() { return autorizados; }

    public boolean agregarAutorizado(String nombre) {
        if (fondos > 0 && !autorizados.contains(nombre)) {
            autorizados.add(nombre);
            return true;
        }
        return false;
    }

    public boolean eliminarAutorizado(String nombre) {
        // Solo se puede eliminar si no tiene facturas pendientes
        for (Factura f : facturas) {
            if (f.darNombre().equals(nombre)) {
                return false;
            }
        }
        return autorizados.remove(nombre);
    }

    public boolean registrarConsumo(String nombreCliente, String concepto, double valor) {
        if (fondos >= valor && (nombreCliente.equals(this.nombre) || autorizados.contains(nombreCliente))) {
            facturas.add(new Factura(nombreCliente, concepto, valor));
            return true;
        }
        return false;
    }

    public boolean pagarFactura(int indice) {
        if (indice >= 0 && indice < facturas.size()) {
            Factura f = facturas.get(indice);
            if (fondos >= f.darValor()) {
                fondos -= f.darValor();
                facturas.remove(indice);
                return true;
            }
        }
        return false;
    }

    public boolean aumentarFondos(double valor) {
        double max = (tipo == Tipo.VIP) ? FONDO_MAX_VIP : FONDO_MAX_REGULAR;
        if (valor > 0 && (fondos + valor) <= max) {
            fondos += valor;
            return true;
        }
        return false;
    }
}