package edu.uniciencia.proyectofinal.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import edu.uniciencia.proyectofinal.R;
import edu.uniciencia.proyectofinal.database.ConexionSQLHelper;
import edu.uniciencia.proyectofinal.entity.Usuario;
import static edu.uniciencia.proyectofinal.database.Constants.ADMIN;
import static edu.uniciencia.proyectofinal.database.Constants.DATABASE;
import static edu.uniciencia.proyectofinal.database.Constants.TABLA_USUARIO;

public class MainActivity extends AppCompatActivity {
    private EditText txtUsuario, txtClave;
    private ConexionSQLHelper con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        con = new ConexionSQLHelper(this, DATABASE,null,1);

        init();
        validateUserAdmin();
    }

    private void init() {
        txtUsuario = findViewById(R.id.edt_user);
        txtClave = findViewById(R.id.edt_clave);
    }

    private void validateUserAdmin() {
        if (!consultarUsuario(ADMIN))
            insertarUsuarioAdmin();
    }

    public void ingresar(View view) {
        if (validarCampos()){
            if (consultarUsuario(txtUsuario.getText().toString())){
                Usuario usuario = Usuario.getInstance();
                if (txtClave.getText().toString().equals(usuario.getClave())) {
                    limpiar();
                    startActivity(new Intent(this, MenuPrincipalActivity.class));
                }else {
                    Toast.makeText(this, "Usuario o clave incorrectos", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this,"Usuario no existe", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private boolean validarCampos() {
        if (txtUsuario.getText().toString().length() <= 0){
            Toast.makeText(this,"Debe digitar un usuario válido", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (txtClave.getText().toString().length() <= 0){
            Toast.makeText(this,"Debe digitar una clave válida", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean consultarUsuario(String user) {
        boolean ret;

        SQLiteDatabase bd = con.getWritableDatabase();
        Cursor c= bd.rawQuery("select * from " + TABLA_USUARIO + " a where a.usuario=?",new String[]{user});
        if (c.getCount() != 0) {
            while(c.moveToNext()) {
                Usuario usuario = Usuario.getInstance();
                usuario.setDocumento(c.getString(0));
                usuario.setNombre(c.getString(1));
                usuario.setUsuario(c.getString(2));
                usuario.setClave(c.getString(3));
                usuario.setCelular(c.getString(4));
            }
            ret = true;
        } else {
            ret = false;
        }

        bd.close();

        return ret;
    }

    private void insertarUsuarioAdmin(){
        SQLiteDatabase bd = con.getWritableDatabase();
        try {
            bd.beginTransaction();

            ContentValues mapa= new ContentValues();
            mapa.put("documento","0000");
            mapa.put("nombre","Administrador");
            mapa.put("usuario","admin");
            mapa.put("clave","1234");
            mapa.put("celular","0000");

            bd.insertOrThrow(TABLA_USUARIO,null,mapa);
            bd.setTransactionSuccessful();
        }catch (Exception ex){
            Toast.makeText(this,ex.getMessage(),Toast.LENGTH_SHORT).show();
        }finally {
            try {
                bd.endTransaction();
            }catch (Exception exx){
                // nothing
            }
            bd.close();
        }
    }

    private void limpiar(){
        txtUsuario.setText("");
        txtClave.setText("");
    }
}