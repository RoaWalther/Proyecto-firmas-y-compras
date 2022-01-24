package edu.uniciencia.proyectofinal.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import edu.uniciencia.proyectofinal.R;
import edu.uniciencia.proyectofinal.entity.Usuario;
import edu.uniciencia.proyectofinal.entity.Venta;

public class ShowResultActivity extends AppCompatActivity {
    private TextView txtVendedor, txtDocumento, txtCelular, txtValor, txtIva, txtTotal;
    private ImageView firma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);

        init();
        initComponent();
    }

    private void init() {
        firma = findViewById(R.id.firma);
        txtVendedor = findViewById(R.id.txtNombre);
        txtDocumento = findViewById(R.id.txtDocumento);
        txtCelular = findViewById(R.id.txtCelular);
        txtValor = findViewById(R.id.txtValor);
        txtIva = findViewById(R.id.txtIva);
        txtTotal = findViewById(R.id.txtTotal);
    }

    private void initComponent() {
        Usuario usuario = Usuario.getInstance();
        Venta venta = Venta.getInstance();

        firma.setImageBitmap(venta.getBitmap());
        txtVendedor.setText(usuario.getNombre());
        txtDocumento.setText(usuario.getDocumento());
        txtCelular.setText(usuario.getCelular());
        txtValor.setText(venta.getNeto());
        txtIva.setText(venta.getIva());
        txtTotal.setText(venta.getTotal());
    }

    public void finalizar(View view) {
        finish();
    }
}