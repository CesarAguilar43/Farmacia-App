/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmaciasaplicacion;

import java.sql.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pogbom
 */
public class conexion {
    /* Conexion */

    ResultSet rsDatos;
    Statement stSentencias;
    private String BD = "Cadena_Farmacias";
    private String login = "root";
    private String password = "13071757";
    private String url = "jdbc:mysql://localhost/" + BD;
    private Connection conx = null;

    public conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conx = DriverManager.getConnection(url, login, password);
            stSentencias = conx.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            if (conx != null) {
                System.out.println("Base de datos : OK" + BD);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println("e");
        } catch(Exception e){
            System.out.println("Se ha encontrado el siguiente error: "+e.getMessage());
        }
    }

    /**
     *
     * @return
     */
    public Connection getConnection() {
        return this.conx;
    }

    /**
     *
     * @param table
     * @param fields
     * @param where
     * @return
     */
    public Object[][] select(String table, String fields, String where) {
        int registros = 0;
        String colname[] = fields.split(",");

        //Consultas SQL
        String q = "SELECT " + fields + " FROM " + table;
        String q2 = "SELECT count(*) as total FROM " + table;
        if (where != null) {
            q += " WHERE " + where;
            q2 += " WHERE " + where;
        }
        try {
            PreparedStatement pstm = conx.prepareStatement(q2);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }

        //se crea una matriz con tantas filas y columnas que necesite
        Object[][] data = new String[registros][fields.split(",").length];
        //realizamos la consulta sql y llenamos los datos en la matriz "Object"
        try {
            PreparedStatement pstm = conx.prepareStatement(q);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                for (int j = 0; j <= fields.split(",").length - 1; j++) {
                    data[i][j] = res.getString(colname[j].trim());
                }
                i++;
            }
            res.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return data;
    }
    /* METODO PARA INSERTAR UN REGISTRO EN LA BASE DE DATOS
     */

    /**
     *
     * @param table
     * @param fields
     * @param values
     * @return
     */
    public boolean insert(String table, String fields, String values) {
        boolean res = false;
        //Se arma la consulta
        String q = " INSERT INTO " + table + " ( " + fields + " ) VALUES ( " + values + " ) ";
        //se ejecuta la consulta
        try {
            PreparedStatement pstm = conx.prepareStatement(q);
            pstm.execute();
            pstm.close();
            res = true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return res;
    }

    /**
     *
     * @param tabla
     * @param valor
     * @param columna
     * @param condicion
     */
    public void Update(String tabla, String valor, String columna, String condicion) {
        String u = " UPADTE " + tabla
                + " SET " + columna + "=" + valor
                + " where " + condicion;
        //se ejecuta la consulta
        try {
            PreparedStatement pstm = conx.prepareStatement(u);
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     *
     */
    public void desconectar() {
        conx = null;
        System.out.println("La conexion a la  base de datos " + BD + " a terminado");
    }

    public void eliminar(String tabla, String condicion) {
        String d = " DELETE FROM " + tabla
                + " where " + condicion;
        //se ejecuta la consulta
        try {
            PreparedStatement pstm = conx.prepareStatement(d);
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
   public ResultSet consulta(String sql) throws SQLException{
    try{
    rsDatos = stSentencias.executeQuery(sql);
    }
    catch(SQLException ex){
    throw ex;
    }
    return rsDatos;
    }
   public DefaultTableModel retornarDatos(String sentencia) {

        DefaultTableModel ML = new DefaultTableModel();
        {
            try {

                ResultSet rsBuscador = consulta(sentencia);
                ResultSetMetaData metadatos = rsBuscador.getMetaData();
                int numeroColumnas = metadatos.getColumnCount();
                Object[] etiquetas = new Object[numeroColumnas];
                for (int i = 0; i < numeroColumnas; i++) {
                    etiquetas[i] = metadatos.getColumnLabel(i + 1);
                }
                ML.setColumnIdentifiers(etiquetas);
                while (rsBuscador.next()) {
                    Object[] datosFila = new Object[ML.getColumnCount()];
                    for (int i = 0; i < ML.getColumnCount(); i++) {
                        datosFila[i] = rsBuscador.getObject(i + 1);
                    }
                    ML.addRow(datosFila);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return ML;
    }
}
