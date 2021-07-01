/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmaciasaplicacion;

/**
 *
 * @author pogbom
 */
public class FarmaciasAplicacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        modelo Modelo = new modelo();
        Aplicacion vista = new Aplicacion();
        new controlador(vista, Modelo).go();
    }

}
