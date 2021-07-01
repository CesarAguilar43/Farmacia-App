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
public class trabajadores {
    private String dni;
    private String usuario;
    private String password;
    private String nombre;
    private String tel;
    
    public trabajadores() {
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getUsuario(){
    return usuario;
    }
    
    public void setUsuario(String usu){
    this.usuario=usu;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getTel(){
    return tel;
    
    }
    
    public void setTel(String tel){
    this.tel=tel;
    }

}
