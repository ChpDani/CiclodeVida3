package mx.edu.tesoem.isc.ciclodevida3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText tnombre, tedad;
    GridView gvdatos;
    Button btnagregar;

    VistaModelo vistaModelo;
    List<Datos> listadatos = new ArrayList<>();
    LiveData<List<Datos>> lista;
    ArrayList<String> listagrid = new ArrayList<String>();

    private Observer<List<Datos>> observar = new Observer<List<Datos>>() {
        @Override
        public void onChanged(List<Datos> datosList) {
            listagrid.clear();
            cargarGrid(datosList);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tnombre = findViewById(R.id.txtNombre);
        tedad = findViewById(R.id.txtEdad);
        gvdatos = findViewById(R.id.gvdatos);
        btnagregar = findViewById(R.id.btnAgregar);

        vistaModelo = new ViewModelProvider(this).get(VistaModelo.class);
        vistaModelo.getListaDatos().observe(this, observar);

        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Datos datos = new Datos(tnombre.getText().toString(), Integer.valueOf(tedad.getText().toString()));
                vistaModelo.Agregar(datos);
            }
        });
    }

    private void cargarGrid(List<Datos> datosList) {
        listadatos = datosList;
        listagrid.add("Nombre");
        listagrid.add("Edad");
        ArrayAdapter<String> adapter;

        for (Datos dato: listadatos){
            listagrid.add(dato.getNombre());
            listagrid.add(String.valueOf(dato.getEdad()));
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listagrid);
        gvdatos.setAdapter(adapter);
    }
}