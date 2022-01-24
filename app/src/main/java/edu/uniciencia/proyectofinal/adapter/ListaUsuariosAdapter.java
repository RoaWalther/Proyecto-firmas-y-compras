package edu.uniciencia.proyectofinal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import edu.uniciencia.proyectofinal.R;
import edu.uniciencia.proyectofinal.entity.Usuario;

public class ListaUsuariosAdapter extends ArrayAdapter<Usuario> {
    private  ArrayList<Usuario> usuarios;

    public ListaUsuariosAdapter(Context context, ArrayList<Usuario> usuarios) {
        super(context,0,usuarios);
        this.usuarios = usuarios;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_listview_custom_line, parent,false);

            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Usuario usuario = usuarios.get(position);
        holder.documento.setText(usuario.getDocumento());
        holder.nombre.setText(usuario.getNombre());
        holder.usuario.setText(usuario.getUsuario());
        holder.celular.setText(usuario.getCelular());

        return convertView;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView documento, nombre, usuario, celular;

        public ViewHolder(View itemView) {
            super(itemView);

            documento = itemView.findViewById(R.id.txtDocumento);
            nombre = itemView.findViewById(R.id.txtNombre);
            usuario = itemView.findViewById(R.id.txtUsuario);
            celular = itemView.findViewById(R.id.txtCelular);
        }
    }
}
