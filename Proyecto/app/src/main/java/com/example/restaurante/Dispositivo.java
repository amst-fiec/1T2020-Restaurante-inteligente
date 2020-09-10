package com.example.restaurante;

public class Dispositivo {
    public String estado;
    public String bateria;

    public Dispositivo(String bateria, String estado) {

        this.estado = estado;
        this.bateria = bateria;
    }

    public Dispositivo() {
    }

    public String getEstado() {
        return estado;
    }

    public String getBateria() {
        return bateria;
    }


    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Dispositivo{" +
                "estado='" + estado + '\'' +
                ", bateria='" + bateria + '\'' +
                '}';
    }

    public void setBateria(String bateria) {
        this.bateria = bateria;
    }
}
