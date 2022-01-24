package edu.uniciencia.proyectofinal.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import edu.uniciencia.proyectofinal.R;
import edu.uniciencia.proyectofinal.adapter.ListaUsuariosAdapter;
import edu.uniciencia.proyectofinal.database.ConexionSQLHelper;
import edu.uniciencia.proyectofinal.entity.Usuario;
import static edu.uniciencia.proyectofinal.database.Constants.DATABASE;
import static edu.uniciencia.proyectofinal.database.Constants.TABLA_USUARIO;

public class ListaUsuariosActivity extends AppCompatActivity {
    private ListView listView;
    private ConexionSQLHelper con;
    private ArrayList<Usuario> arrayTemp = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_usuarios);

        con = new ConexionSQLHelper(this, DATABASE,null,1);

        init();
        showData();
    }

    private void init() {
        listView =  findViewById(R.id.lista_usuarios);
    }

    private void showData() {
        arrayTemp = new ArrayList<>();

        SQLiteDatabase db = con.getReadableDatabase();

        try{
            Cursor c = db.rawQuery("SELECT * FROM " + TABLA_USUARIO,null);

            if (c.moveToFirst()){
                do {
                    Usuario usuario = new Usuario();
                    usuario.setDocumento(c.getString(0));
                    usuario.setNombre(c.getString(1));
                    usuario.setUsuario(c.getString(2));
                    usuario.setClave(c.getString(3));
                    usuario.setCelular(c.getString(4));

                    arrayTemp.add(usuario);
                } while (c.moveToNext());
            }

            if (!arrayTemp.isEmpty()){
                llenarListView();
            }

            c.close();
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void llenarListView() {
        listView.setAdapter(new ListaUsuariosAdapter(this, arrayTemp));
    }
}