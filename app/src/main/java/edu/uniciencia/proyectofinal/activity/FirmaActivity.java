package edu.uniciencia.proyectofinal.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import edu.uniciencia.proyectofinal.R;
import edu.uniciencia.proyectofinal.entity.Venta;
import edu.uniciencia.proyectofinal.firma.Lienzo;

public class FirmaActivity extends AppCompatActivity {
    private static Lienzo lienzo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firma);

        lienzo = findViewById(R.id.lienzo);
        initLienzo();
    }

    private void initLienzo() {
        lienzo.setColor("#FF000000");
        Lienzo.setTamanyoPunto(15);
    }

    public void btnNuevaFirma(View view) {
        AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
        newDialog.setTitle("Nueva Firma");
        newDialog.setMessage("¿Comenzar nueva firma (perderás la firma actual)?");
        newDialog.setPositiveButton("Aceptar", (dialog, which) -> {
            lienzo.NuevoDibujo();
            dialog.dismiss();
        });

        newDialog.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());
        newDialog.show();
    }

    public void btnGuardar(View view) {
        lienzo.setDrawingCacheEnabled(true);

        Bitmap image = getBitmapFromView(lienzo);

        if (image != null) {
            Venta venta = Venta.getInstance();
            venta.setBitmap(image);
            finish();
            startActivity(new Intent(this, ShowResultActivity.class));
        } else {
            Toast.makeText(this, "¡Error! La forma no se ha guardado correctamente.", Toast.LENGTH_SHORT).show();

        }

        lienzo.destroyDrawingCache();
    }

    public static Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            bgDrawable.draw(canvas);
        else
            canvas.drawColor(Color.WHITE);

        view.draw(canvas);

        return returnedBitmap;
    }

}