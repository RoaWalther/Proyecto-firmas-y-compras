package edu.uniciencia.proyectofinal.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import edu.uniciencia.proyectofinal.R;
import edu.uniciencia.proyectofinal.entity.Venta;

public class PayActivity extends AppCompatActivity {
    private TextView txtResultado;
    double resultado;
    private String operador, mostrar , reserva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        txtResultado = findViewById(R.id.Etiqueta);

        reiniciarValores();
    }

    private void reiniciarValores() {
        resultado = 0;
        reserva = "";
        operador = "";
        mostrar = "";
    }

    public void btnUno(View view){
        mostrar = txtResultado.getText().toString();
        mostrar = mostrar + "1";
        txtResultado.setText(mostrar);
    }

    public void btnDos(View view){
        mostrar = txtResultado.getText().toString();
        mostrar = mostrar + "2";
        txtResultado.setText(mostrar);
    }

    public void btnTres(View view){
        mostrar = txtResultado.getText().toString();
        mostrar = mostrar + "3";
        txtResultado.setText(mostrar);
    }

    public void btnCuatro(View view){
        mostrar = txtResultado.getText().toString();
        mostrar = mostrar + "4";
        txtResultado.setText(mostrar);
    }

    public void btnCinco(View view){
        mostrar = txtResultado.getText().toString();
        mostrar = mostrar + "5";
        txtResultado.setText(mostrar);
    }

    public void btnSeis(View view){
        mostrar = txtResultado.getText().toString();
        mostrar = mostrar + "6";
        txtResultado.setText(mostrar);
    }

    public void btnSiete(View view){
        mostrar = txtResultado.getText().toString();
        mostrar = mostrar + "7";
        txtResultado.setText(mostrar);
    }

    public void btnOcho(View view){
        mostrar = txtResultado.getText().toString();
        mostrar = mostrar + "8";
        txtResultado.setText(mostrar);
    }

    public void btnNueve(View view){
        mostrar = txtResultado.getText().toString();
        mostrar = mostrar + "9";
        txtResultado.setText(mostrar);
    }

    public void btnCero(View view) {
        mostrar = txtResultado.getText().toString();
        if (!mostrar.equals("0")){
            mostrar = mostrar + "0";
            txtResultado.setText(mostrar);
        }
    }

    public void btnSuma(View view){
        reserva = txtResultado.getText().toString();
        operador = "+";
        txtResultado.setText("");
    }

    public void btnResta(View view){
        reserva = txtResultado.getText().toString();
        operador = "-";
        txtResultado.setText("");
    }

    public void btnMultiplicar(View view){
        reserva = txtResultado.getText().toString();
        operador = "*";
        txtResultado.setText("");
    }

    public void btnDividir(View view){
        reserva = txtResultado.getText().toString();
        operador = "/";
        txtResultado.setText("");
    }

    public void btnLimpiar(View view){
        limpiar();
    }

    private void limpiar() {
        mostrar = "";
        txtResultado.setText(mostrar);
        reserva = "";
        operador = "";
        resultado = 0;
    }

    public void btnBorrar(View view){
        mostrar = txtResultado.getText().toString();
        if (mostrar.length() > 0) {
            mostrar = mostrar.substring(0,mostrar.length()-1);
            txtResultado.setText(mostrar);
        }
    }

    public void btnPunto(View view){
        mostrar = txtResultado.getText().toString();

        if (!mostrar.contains(".") && mostrar.length() > 0){
            mostrar = mostrar + ".";
            txtResultado.setText(mostrar);
        }
    }

    public void btnIgual(View view){
        mostrar = txtResultado.getText().toString();
        mostrar = mostrar + "1";

        String obtenido = txtResultado.getText().toString();

        if (obtenido.length() == 0){
            obtenido = "0";
        }
        if (reserva.length() == 0){
            reserva = "0";
        }

        switch (operador){
            case "-":
                resultado = Double.parseDouble(reserva) - Double.parseDouble(obtenido);
                txtResultado.setText(String.valueOf(resultado));
                break;
            case "+":
                resultado = Double.parseDouble(reserva) + Double.parseDouble(obtenido);
                txtResultado.setText(String.valueOf(resultado));
                break;
            case "/":
                resultado = Double.parseDouble(reserva) / Double.parseDouble(obtenido);
                txtResultado.setText(String.valueOf(resultado));
                break;
            case "*":
                resultado = Double.parseDouble(reserva) * Double.parseDouble(obtenido);
                txtResultado.setText(String.valueOf(resultado));
                break;
            default:
                break;
        }
    }

    public void btnGenerarPago(View view) {
        long resultadoFinal;
        long maxVenta = 1000000;
        String cajaTexto = txtResultado.getText().toString();

        if (cajaTexto.length() != 0) {
            resultadoFinal = redondearMonto(cajaTexto);

            if (resultadoFinal > maxVenta || resultadoFinal <= 0) {
                Toast.makeText(this,  "Error\nValor de venta no permitido", Toast.LENGTH_SHORT).show();
            } else {
                dialogoConfirmacion(resultadoFinal);
            }
        } else {
            Toast.makeText(this, "Información\nDigite un valor para continuar", Toast.LENGTH_SHORT).show();
        }
    }

    private void dialogoConfirmacion(final long resultadoFinal) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Generar Recibo");
        builder.setMessage("Generar recibo con valor de \n$ " + resultadoFinal);
        builder.setCancelable(false);

        builder.setPositiveButton("Aceptar", (dialog, which) -> {
            String[] valores = getDataValores(resultadoFinal);
            Venta venta = Venta.getInstance();
            venta.setNeto(valores[0]);
            venta.setIva(valores[1]);
            venta.setTotal(valores[2]);

            finish();
            startActivity(new Intent(this,FirmaActivity.class));
        });
        builder.setNegativeButton("Cancelar", (dialog, id) -> {
            dialog.cancel();
        });

        Dialog dialog = builder.create();
        dialog.show();
    }

    private String[] getDataValores(long resultadoFinal){
        String iva = obtenerIva(resultadoFinal);
        String neto = obtenerNeto(resultadoFinal, Double.parseDouble(iva));
        String total = String.valueOf(resultadoFinal);

        String[] valores = new String[3];
        valores[0] = neto;
        valores[1] = iva;
        valores[2] = total;

        return valores;
    }

    private String obtenerNeto(double valor, double iva) {
        double neto = valor - iva;
        long netoLong = Math.round(neto);
        return String.valueOf( netoLong );
    }

    private String obtenerIva(double valor) {
        double iva = valor - (valor / 1.19);
        long ivaLong = Math.round(iva);
        return String.valueOf( ivaLong );
    }

    private long redondearMonto(String cajaTexto) {
        return Math.round( Double.parseDouble(cajaTexto) );
    }
}