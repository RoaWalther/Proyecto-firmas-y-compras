package edu.uniciencia.proyectofinal.entity;

import android.graphics.Bitmap;

public class Venta {
    private String neto;
    private String iva;
    private String total;
    private Bitmap bitmap;
    private static Venta mInstance = null;

    public static Venta getInstance(){
        if (mInstance == null){
            mInstance = new Venta();
        }

        return mInstance;
    }

    public String getNeto() {
        return neto;
    }

    public void setNeto(String neto) {
        this.neto = neto;
    }

    public String getIva() {
        return iva;
    }

    public void setIva(String iva) {
        this.iva = iva;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
