package com.example.restaurante;

public class Usuario {
    String uid;
    String nombreCompleto;
    String email;
    String telefono;
    String photoUrl;
    String clave;
    public Usuario(String uid, String nombreCompleto, String email, String telefono, String photoUrl, String clave) {
        this.uid = uid;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.telefono = telefono;
        this.photoUrl = photoUrl;
        this.clave = clave;
    }

    public String getUid() {
        return uid;
    }

    public String getClave() {
        return clave;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

}
