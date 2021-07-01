/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmaciasaplicacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pogbom
 */
public class modelo extends conexion {

    public farmaceutico farm = new farmaceutico();
    public administrador admin = new administrador();
    public trabajadores trab = new trabajadores();

    public modelo() {

    }

    public boolean ingresarfarm(String usu, String pw) {
        Object[][] res = this.select("Trabajadores", " usuario,dni , contra , nombre, telefono", " usuario='" + usu + "' AND contra='" + pw + "' ");
        if (res.length > 0) {
            farm.setDni(res[0][0].toString());
            farm.setPassword(res[0][1].toString());
            farm.setNombre(res[0][2].toString());
            farm.setTelefono(res[0][3].toString());
            return true;
        } else {
            return false;
        }
    }

    public boolean ingresaradmin(String usu, String pw) {

        Object[][] res = this.select("Trabajadores", " usuario, dni , contra , nombre, telefono", " contra='" + pw + "' AND usuario='" + usu + "' ");
        if (res.length > 0) {
            admin.setNombre(res[0][0].toString());
            admin.setDni(res[0][1].toString());
            admin.setPassword(res[0][2].toString());
            admin.setTel(res[0][3].toString());
            return true;
        } else {
            return false;
        }
    }

    public boolean ingresartrabajador(String usu, String pw) {
        Object[][] res = this.select("Trabajadores", "usuario, dni , contra , nombre, telefono", "usuario='" + usu + "' AND dni='" + pw + "'");
        if (res.length > 0) {
            trab.setNombre(res[0][0].toString());
            trab.setDni(res[0][1].toString());
            trab.setPassword(res[0][2].toString());
            trab.setTel(res[0][3].toString());
            return true;
        } else {
            return false;
        }
    }

    public Object[][] getTrabajadores() {
        Object[][] res = this.select("Trabajadores", "usuario, dni, contra, nombre, telefono", null);
        if (res.length > 0) {
            return res;
        } else {
            return null;
        }
    }

    public Object[][] getCiudades() {
        Object[][] res = this.select("Ciudades", "nombre, provincia, dnitrabajadorfarmaceutico, añotitulacionfamaceutico", null);
        if (res.length > 0) {
            return res;
        } else {
            return null;
        }
    }

    public Object[][] getFarmacias() {
        Object[][] res = this.select("Farmacias", " nombre, direccion,telefono, nombreciudad", null);
        if (res.length > 0) {
            return res;
        } else {
            return null;
        }
    }

    public Object[][] getMedicamentos() {
        Object[][] res = this.select("Medicamentos", "nombre,prospecto,nombrelaboratorio", null);
        if (res.length > 0) {
            return res;
        } else {
            return null;
        }
    }

    public Object[][] getIngredientes() {
        Object[][] res = this.select("Ingredientes", "nombre,caracteristicas", null);
        if (res.length > 0) {
            return res;
        } else {
            return null;
        }
    }

    public Object[][] getDatosPresentacion() {
        Object[][] res = this.select("Datos_Presentacion", " nombremedicamento, nombrepresentacion,cantidadstock, precio", null);
        if (res.length > 0) {
            return res;
        } else {
            return null;
        }
    }

    public Object[][] getAccionesTerapeuticas() {
        Object[][] res = this.select("Acciones_Terapeuticas", "denominacion", null);
        if (res.length > 0) {
            return res;
        } else {
            return null;
        }
    }

    public Object[][] getPresentaciones() {
        Object[][] res = this.select("Presentaciones", "nombre", null);
        if (res.length > 0) {
            return res;
        } else {
            return null;
        }
    }

    /**
     *
     * @param dni
     * @return
     */
    public Object[][] getContratos(String dni) {
        Object[][] res = this.select(" Contratos inner join Trabajadores on dnitrabajador = dni ",
                " nombrefarmacia,dnitrabajador,usuario,contra,nombre,telefono ", "dni='" + dni + "'"
        );
        if (res.length > 0) {
            return res;
        } else {
            return null;
        }
    }

    /*public void imprimir() {
     new reporte(this.getConnection()).ver_reporte(this.farm.getDni(), this.farm.getPassword(), this.farm.getNombre(), this.farm.getTelefono());
     }*/
//--------REGISTRAR------//
    //insertar farmacias
    public boolean insertarFarmacia(String nomF, String direF, String telF, String ciudadF) {
        Object[][] res = this.select("Farmacias", "nombre,direccion,telefono,nombreciudad", "nombre='" + nomF + "'");
        if (res.length > 0) {
            JOptionPane.showMessageDialog(null, "Lo siento esa Farmacia ya esta registrada");
        } else {
            if (res.length == 0) {
                this.insert("Farmacias", " nombre, direccion, telefono , nombreciudad", " '" + nomF + "', '"
                        + direF + "' , '" + telF + "' , '" + ciudadF + "' ");
                JOptionPane.showMessageDialog(null, "La farmacia " + nomF + " fue agregada correctamente");
            }
        }
        return true;
    }

    //insertar medicamento
    public boolean insertarMedicamento(String nomM, String proM, String nombrelabM) {
        String nom="";
        String pro="";
        String noml="";
        if (nom==nomM||pro==proM||noml==nombrelabM) {
            JOptionPane.showMessageDialog(null, "Dejo campos en blancos");
        }
        else{
        Object[][] res = this.select("Medicamentos", "nombre,prospecto,nombrelaboratorio", "nombre='" + nomM + "'");
        if (res.length > 0) {
            JOptionPane.showMessageDialog(null, "Lo siento ese Medicamento ya esta registrado");
        } else {
            if (res.length == 0) {
                this.insert("Medicamentos", " nombre , prospecto, nombrelaboratorio", " '" + nomM
                        + "', '" + proM + "' ,'" + nombrelabM + "' ");
                JOptionPane.showMessageDialog(null, "El medicamento " + nomM + " fue agregado correctamente");
            }
        }
        }
        return true;
    }

    public boolean insertarTrabajadores(String Us, String DNI, String Contra, String Nm, String tel) {
        Object[][] res = this.select("Trabajadores", "dni,usuario,contra,nombre,telefono", "dni='" + DNI + "' or nombre='" + Nm + "'");
        if (res.length > 0) {
            JOptionPane.showMessageDialog(null, "Lo siento ese Usuario ya esta registrado");
        } else {
            if (res.length == 0) {
                this.insert("Trabajadores", "usuario,dni , contra , nombre , telefono", " ' " + Us + " ', '" + DNI+ " ', '" + Contra + "' , '" + Nm + "' , '" + tel + "' ");
                JOptionPane.showMessageDialog(null, "El " + Us + " " + Nm + " fue agregado correctamente");
            }
        }
        return true;
    }

    public boolean insertarPresentaciones(String nomP, String preP, String stoP, String precP) {
        Object[][] res = this.select("Medicamentos", "nombre,prospecto,"
                + "nombrelaboratorio", "nombre='" + nomP + "'");
        if (res.length > 0) {
            Object[][] res1 = this.select("Datos_Presentacion", "nombremedicamento,nombrepresentacion,"
                    + "cantidadstock,precio", "nombremedicamento='" + nomP + "' and nombrepresentacion='" + preP+"'");
            if (res1.length > 0) {
                JOptionPane.showMessageDialog(null, "El medicamento " + nomP + " ya existe en esa presentacion");
                return false;
            } else {
                if (res1.length == 0) {
                    this.insert("Datos_Presentacion", " nombremedicamento , "
                            + "nombrepresentacion, cantidadstock, precio", " '"
                            + nomP + "', '" + preP + "' ,'" + stoP + "','" + precP + "' ");
                    JOptionPane.showMessageDialog(null, "Agregado al inventario");
                    return true;
                }
            }

        } else {
            if (res.length == 0) {
                JOptionPane.showMessageDialog(null, "Lo siento no existe el medicamento " + nomP);
                return false;
            }
        }
        return true;
    }

    //insertar ciudades
    public void insertarCiudades(String nomC, String provC, String dnitrabajadorC, String anotituC) {
        this.insert("Ciudades", " nombre , provincia, dnitrabajadorfarmaceutico, añotitulacionfamaceutico", " '"
                + nomC + "', '" + provC + "' ,'" + dnitrabajadorC + "','" + anotituC + "' ");
        JOptionPane.showMessageDialog(null, "Agregado correctamente");
    }

//-------ELIMINAR------//
    //Eliminar Farmacias
    public void EliminarFarmacia(String condicion) {
        this.eliminar("Farmacias", "nombre='" + condicion + "' ");
        JOptionPane.showMessageDialog(null, "Eliminado correctamente");
    }

    //Eliminar Medicamentos
    public void EliminarMedicamentos(String condicion) {
        this.eliminar("Medicamentos", "nombre='" + condicion + "' ");
        JOptionPane.showMessageDialog(null, "Eliminado correctamente");
    }

    //Eliminar Ciudades
    public void EliminarCiudades(String condicion) {
        this.eliminar("Ciudades", "nombre='" + condicion + "' ");
        JOptionPane.showMessageDialog(null, "Eliminado correctamente");
    }

    // Eliminar Trabajadores
    public void EliminarTrabajadores(String condicion) {
        this.eliminar("Trabajadores", "usuario='" + condicion + "' ");
        JOptionPane.showMessageDialog(null, "Eliminado correctamente");
    }

    public void EliminarPresentaciones(String condicion) {
        this.eliminar("Datos_Presentacion", "nombremedicamento='" + condicion + "' ");
        JOptionPane.showMessageDialog(null, "Eliminado correctamente");
    }

    //-----------Modificar--------//
    //Modificar Farmacias
    public void ModificarFarmacias(String nomF, String dirF, String telF, String nomcdF) {
        try {
            PreparedStatement pstm = this.getConnection().prepareStatement("UPDATE Farmacias SET nombre='" + nomF
                    + "', direccion= '" + dirF + "', telefono='" + telF + "', nombreciudad='" + nomcdF + "'  WHERE nombre='" + nomF + "'");

            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Los datos de la Farmacia " + nomF + "han sido modificados correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

//Modificar Medicamentos
    public void ModificarMedicamentos(String nomM, String prosM, String nomlM) {
        try {
            PreparedStatement pstm = this.getConnection().prepareStatement("UPDATE Medicamentos SET nombre='" + nomM
                    + "', prospecto= '" + prosM + "', nombrelaboratorio='" + nomlM + "' WHERE nombre='" + nomM + "'");

            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Los datos del Medicamento " + nomM + "han sido modificados correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Modificar Ciudades
    public void ModificarCiudades(String nomC, String provC, String dniTC, String anoTituC) {
        try {
            PreparedStatement pstm = this.getConnection().prepareStatement("UPDATE Ciudades SET nombre='" + nomC
                    + "', provincia='" + provC + "', dnitrabajadorfarmaceutico='" + dniTC
                    + "', añotitulacionfamaceutico='" + anoTituC + "' WHERE nombre='" + nomC + "'");

            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Los datos de la Ciudad " + nomC + " han sido modificados correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Modificar Trabajadores
    public void ModificarTrabajadores(String usuT, String dniT, String pwT, String nomT, String telT) {
        try {
            PreparedStatement pstm = this.getConnection().prepareStatement("UPDATE Trabajadores SET usuario='" + usuT
                    + "', dni='" + dniT + "', contra='" + pwT
                    + "', nombre='" + nomT + "',telefono='" + telT + "' WHERE dni='" + dniT + "'");

            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Los datos del usuario " + usuT + " han sido modificados correctamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void ModificarPresentaciones(String nomP, String preP, String stockP, String precioP) {
        try {
            PreparedStatement pstm = this.getConnection().prepareStatement("UPDATE Datos_Presentacion SET nombremedicamento='" + nomP
                    + "', nombrepresentacion='" + preP + "', cantidadstock='" + stockP
                    + "', precio='" + precioP + "' WHERE nombremedicamento='" + nomP + "'");

            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Se modifico la presentacion del medicamento " + nomP);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

}
