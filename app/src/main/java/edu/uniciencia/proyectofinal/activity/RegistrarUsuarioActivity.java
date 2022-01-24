package edu.uniciencia.proyectofinal.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import edu.uniciencia.proyectofinal.R;
import edu.uniciencia.proyectofinal.database.ConexionSQLHelper;
import static edu.uniciencia.proyectofinal.database.Constants.DATABASE;
import static edu.uniciencia.proyectofinal.database.Constants.TABLA_USUARIO;

public class RegistrarUsuarioActivity extends AppCompatActivity {
    private EditText txtDocumento, txtNombre, txtUsuario, txtClave, txtClaveConfirm, txtCelular;
    private ConexionSQLHelper con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        con = new ConexionSQLHelper(this, DATABASE,null,1);

        init();
    }

    private void init() {
        txtDocumento = findViewById(R.id.edt_document);
        txtNombre = findViewById(R.id.edt_name);
        txtUsuario = findViewById(R.id.edt_register_user);
        txtClave = findViewById(R.id.edt_register_clave);
        txtClaveConfirm = findViewById(R.id.edt_register_clave_confirm);
        txtCelular = findViewById(R.id.edt_cell);
    }

    public void registrar(View view) {
        if (validarCampos())
            registrarUsuario();
    }

    private boolean validarCampos() {
        if (txtDocumento.getText().toString().length() <= 0 || txtNombre.getText().toString().length() <= 0 ||
                txtUsuario.getText().toString().length() <= 0 || txtClave.getText().toString().length() <= 0 ||
                txtClaveConfirm.getText().toString().length() <= 0 || txtCelular.getText().toString().length() <= 0){

            Toast.makeText(this, "No puede haber ningún campo vacío. Verifique!",Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!validarClave(txtClave.getText().toString(), txtClaveConfirm.getText().toString())){
            Toast.makeText(this, "Claves diferentes. Verifique!",Toast.LENGTH_SHORT).show();
            return false;
        }

        if (consultarUsuario(txtUsuario.getText().toString())){
            Toast.makeText(this, "Usuario ya existe!",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean validarClave(String clave, String claveConfirm) {
        if (clave.equals(claveConfirm))
            return true;
        else
            return false;
    }

    private boolean consultarUsuario(String user) {
        boolean ret;

        SQLiteDatabase bd = con.getWritableDatabase();
        Cursor c= bd.rawQuery("select * from " + TABLA_USUARIO + " a where a.usuario=?",new String[]{user});
        if (c.getCount() != 0)
            ret = true;
        else
            ret = false;

        bd.close();

        return ret;
    }

    private void registrarUsuario() {
        SQLiteDatabase bd = con.getWritableDatabase();
        try {
            bd.beginTransaction();

            ContentValues mapa= new ContentValues();
            mapa.put("documento",txtDocumento.getText().toString());
            mapa.put("nombre",txtNombre.getText().toString());
            mapa.put("usuario",txtUsuario.getText().toString());
            mapa.put("clave", txtClave.getText().toString());
            mapa.put("celular",txtCelular.getText().toString());

            bd.insertOrThrow(TABLA_USUARIO,null,mapa);
            bd.setTransactionSuccessful();
            successRegister();
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

    private void successRegister() {
        Toast.makeText(this,"Usuario registrado satisfactoriamente!",Toast.LENGTH_SHORT).show();
        finish();
    }
}