package club;

import java.util.ArrayList;

public class Club {
    private ArrayList<Socio> socios;

    public Club() {
        socios = new ArrayList<>();
    }

    public boolean afiliarSocio(String cedula, String nombre, Socio.Tipo tipo) {
        if (buscarSocio(cedula) == null) {
            socios.add(new Socio(cedula, nombre, tipo));
            return true;
        }
        return false;
    }

    public boolean agregarAutorizado(String cedula, String nombreAutorizado) {
        Socio s = buscarSocio(cedula);
        if (s != null) {
            return s.agregarAutorizado(nombreAutorizado);
        }
        return false;
    }

    public boolean pagarFactura(String cedula, int indiceFactura) {
        Socio s = buscarSocio(cedula);
        if (s != null) {
            return s.pagarFactura(indiceFactura);
        }
        return false;
    }

    public boolean registrarConsumo(String cedula, String nombre, String concepto, double valor) {
        Socio s = buscarSocio(cedula);
        if (s != null) {
            return s.registrarConsumo(nombre, concepto, valor);
        }
        return false;
    }

    public boolean aumentarFondos(String cedula, double valor) {
        Socio s = buscarSocio(cedula);
        if (s != null) {
            return s.aumentarFondos(valor);
        }
        return false;
    }

    private Socio buscarSocio(String cedula) {
        for (Socio s : socios) {
            if (s.darCedula().equals(cedula)) {
                return s;
            }
        }
        return null;
    }
}