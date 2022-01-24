package edu.uniciencia.proyectofinal.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import edu.uniciencia.proyectofinal.R;
import edu.uniciencia.proyectofinal.entity.Usuario;

public class MenuPrincipalActivity extends AppCompatActivity {
    private TextView txtNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        init();
    }

    private void init() {
        txtNombre = findViewById(R.id.txtNombre);

        Usuario usuario = Usuario.getInstance();
        txtNombre.setText(usuario.getNombre());
    }

    public void GenerarPago(View view) {
        startActivity(new Intent(this, PayActivity.class));
    }

    public void UserList(View view) {
        startActivity(new Intent(this,ListaUsuariosActivity.class));
    }

    public void Registrar(View view) {
        startActivity(new Intent(this, RegistrarUsuarioActivity.class));
    }

    public void CerrarSesion(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Cerrar Sesión");
        builder.setMessage("¿Está seguro que desea finalizar sesión?");
        builder.setCancelable(false);

        builder.setPositiveButton("Sí", (dialog, which) -> finishSesion());

        builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());

        Dialog dialog = builder.create();
        dialog.show();
    }

    private void finishSesion() {
        finish();
        Toast.makeText(this,"Cierre de sesión exitoso", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this,"Por favor cierre sesión",Toast.LENGTH_SHORT).show();
    }
}