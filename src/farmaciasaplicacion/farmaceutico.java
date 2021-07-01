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
public class farmaceutico {
    private String dni;
    private String usuario;
    private String password;
    private String nombre;
    private String telefono;
    
    
    public String getDni(){
    return dni;
    }
    
    /**
     * 
     * @param dni
     */
    public void setDni(String dni){
    this.dni=dni;
    }
     /**
     * 
     * @return
     */
    public String getUsuario(){
    return usuario;
    }
    
    public void setUsuario(String usu){
    this.usuario=usu;
    }
    
    
    public String getPassword(){
    return password;
    }
    /**
     * 
     * @param password
     */
    public void setPassword(String password){
    this.password=password;
    }
    /**
     * 
     * @return
     */
    public String getNombre(){
    return nombre;
    }
    
    /**
     * 
     * @return
     */

    /**
     * 
     * @param nombre
     */
    public void setNombre(String nombre){
    this.nombre=nombre;
    }
    
    /**
     * 
     * @return
     */
    public String getTelefono(){
    return telefono;
    }
    /**
     * 
     * @param telefono
     */
    public void setTelefono(String telefono){
    this.telefono=telefono;
    }
}
