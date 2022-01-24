package edu.uniciencia.proyectofinal.entity;

public class Usuario {
    private String documento;
    private String nombre;
    private String usuario;
    private String clave;
    private String celular;
    private static Usuario mInstance = null;

    public static Usuario getInstance(){
        if (mInstance == null){
            mInstance = new Usuario();
        }
        return mInstance;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
