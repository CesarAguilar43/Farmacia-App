/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmaciasaplicacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author pogbom
 */
public class controlador implements ActionListener {

    private Aplicacion view;
    private modelo model;

    logueo frmlogin = new logueo(view, true);
    Buscador frmbuscador = new Buscador(view, true);
    Buscador frmimpresion = new Buscador(view, false);
    acerca frmacerca = new acerca(view, true);
    trabajador frmtra = new trabajador(view, true);

    /**
     *
     * @param vista
     * @param modelo
     */
    public controlador(Aplicacion vista, modelo modl) {
        this.view = vista;
        this.model = modl;
        iniciar();
    }

    public void go() {
        this.view.setVisible(true);
    }

    private void iniciar() {

        //se añade las acciones a los controles del formulario padre
        this.view.botonIniciar.setActionCommand("Abrir Sesion");
        this.view.botonSalir.setActionCommand("Cerrar Sesion");
        this.view.botonBuscar.setActionCommand("Buscador");
        this.view.botonImprimir.setActionCommand("Imprimir");
        this.view.botonAcerca.setActionCommand("Acerca de");
        this.frmlogin.botonsesionAceptar.setActionCommand("Iniciar Sesion");

        //Se pone a escuchar las acciones del usuario
        view.botonIniciar.addActionListener(this);
        view.botonSalir.addActionListener(this);
        view.botonBuscar.addActionListener(this);
        view.botonImprimir.addActionListener(this);
        view.botonAcerca.addActionListener(this);
        frmlogin.botonsesionAceptar.addActionListener(this);

        //Hacemos funcional sólo el botón de programar y acerca de 
        this.view.botonIniciar.setEnabled(true);
        this.view.botonSalir.setEnabled(false);
        this.view.botonBuscar.setEnabled(false);
        this.view.botonImprimir.setEnabled(false);
        this.view.botonAdmin.setEnabled(false);
        this.view.botonAcerca.setEnabled(true);
    }
    /* ATENTO A LAS ACCIONES DEL USUARIO */

    @Override
    public void actionPerformed(ActionEvent e) {
        //Captura en String el comando accionado por el usuario
        String comando = e.getActionCommand();
        //..........................................................................................
        /* Acciones del formulario padre */
        if (comando.equals("Abrir Sesion")) {
            frmlogin.setVisible(true);

        } else if (comando.equals("Cerrar Sesion")) {
            //deshabilita/habilita controles según sea necesario
            this.view.botonIniciar.setEnabled(true);
            this.view.botonSalir.setEnabled(false);
            this.view.botonBuscar.setEnabled(false);
            this.view.botonImprimir.setEnabled(false);
            this.frmlogin.txtUsuario.setText("");
            this.frmlogin.txtPassword.setText("");
        }
        /* Acciones formulario Iniciar Sesion */
        if (comando.equals("Iniciar Sesion")) {
            if (this.model.ingresarfarm(this.frmlogin.txtUsuario.getText(), this.frmlogin.txtPassword.getText())) {
                //si los datos de acceso son correctos
                frmlogin.dispose();//cierra frmlogin
                //habilita/deshabilita controles segun corresponda
                this.view.botonIniciar.setEnabled(false);
                this.view.botonSalir.setEnabled(true);
                this.view.botonBuscar.setEnabled(true);
                this.view.botonImprimir.setEnabled(false);
                this.view.botonAdmin.setEnabled(false);
                this.frmbuscador.btnBuscarMedicamentos.setEnabled(true);
            } else {
                if (this.model.ingresaradmin(frmlogin.txtPassword.getText(), frmlogin.txtUsuario.getText())) {
                    this.view.botonIniciar.setEnabled(false);
                    this.view.botonSalir.setEnabled(true);
                    this.view.botonBuscar.setEnabled(true);
                    this.view.botonImprimir.setEnabled(false);
                    this.view.botonAdmin.setEnabled(true);
                    frmlogin.dispose();
                } else {
                    if (this.model.ingresartrabajador(frmlogin.txtUsuario.getText(), frmlogin.txtPassword.getText())) {

                        this.view.botonIniciar.setEnabled(false);
                        this.view.botonSalir.setEnabled(true);
                        this.view.botonBuscar.setEnabled(false);
                        this.view.botonImprimir.setEnabled(true);
                        this.view.botonAdmin.setEnabled(false);
                        frmlogin.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this.view, "Lo siento, no esta Registrado o dejo campos vacios");
                    }
                }
            }

        }

    }

}
