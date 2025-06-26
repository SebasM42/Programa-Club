package club;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Club {
    private Map<String, Socio> socios = new HashMap<>();

    public boolean afiliarSocio(String cedula, String nombre, Socio.Tipo tipo) {
        if (socios.containsKey(cedula)) {
            return false;
        }
        socios.put(cedula, new Socio(cedula, nombre, tipo));
        return true;
    }

    public boolean cedulaDisponible(String cedula) {
        return !socios.containsKey(cedula);
    }

    public void agregarAutorizado(String cedula, String nombreAut) {
        Socio socio = socios.get(cedula);
        if (socio != null) {
            socio.agregarAutorizado(nombreAut);
        }
    }

    public void pagarFactura(String cedula, int idx) {
        Socio socio = socios.get(cedula);
        if (socio != null) {
            socio.pagarFactura(idx);
        }
    }

    public void registrarConsumo(String cedula, String nombre, String concepto, double valor) {
        Socio socio = socios.get(cedula);
        if (socio != null && (socio.getNombre().equals(nombre) || socio.estaAutorizado(nombre))) {
            socio.registrarConsumo(concepto, valor);
            System.out.println("Factura generada. Concepto: " + concepto);
        }
    }

    public void aumentarFondos(String cedula, double valor) {
        Socio socio = socios.get(cedula);
        if (socio != null) {
            socio.aumentarFondos(valor);
        }
    }
    public double getFactura(String cedula, int idx) {
        Socio socio = socios.get(cedula);
        if (socio != null) {
            return socio.getMontoFactura(idx);
        }
        return -1;
    }

    public double getFondos(String cedula) {
        Socio socio = socios.get(cedula);
        if (socio != null) {
            return socio.getFondos();
        }
        return -1;
    }
}