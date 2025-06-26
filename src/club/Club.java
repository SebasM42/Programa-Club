package club;

import java.util.HashMap;
import java.util.Map;

public class Club {
    private Map<String, Socio> socios = new HashMap<>();

    public boolean afiliarSocio(String cedula, String nombre, Socio.Tipo tipo) {
        if (socios.containsKey(cedula)) {
            return false;
        }
        if (tipo == Socio.Tipo.VIP && contarVIP() >= 3) {
            return false;
        }
        socios.put(cedula, new Socio(cedula, nombre, tipo));
        return true;
    }

    private int contarVIP() {
        int count = 0;
        for (Socio s : socios.values()) {
            if (s.getTipo() == Socio.Tipo.VIP) count++;
        }
        return count;
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
        }
    }

    public void aumentarFondos(String cedula, double valor) {
        Socio socio = socios.get(cedula);
        if (socio != null) {
            socio.aumentarFondos(valor);
        }
    }

    public boolean eliminarSocio(String cedula) {
        return socios.remove(cedula) != null;
    }
}