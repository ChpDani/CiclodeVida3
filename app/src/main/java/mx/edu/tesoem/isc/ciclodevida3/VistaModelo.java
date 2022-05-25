package mx.edu.tesoem.isc.ciclodevida3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class VistaModelo extends ViewModel {
    MutableLiveData<List<Datos>> ListaDatos;
    List<Datos> LDatos;

    public LiveData<List<Datos>> getListaDatos(){
        if(ListaDatos == null){
            ListaDatos = new MutableLiveData<List<Datos>>();
            LDatos = new ArrayList<Datos>();
        }
        return ListaDatos;
    }

    public List<Datos> getDatos(){
        return LDatos;
    }

    public void Agregar(Datos datos){
        LDatos.add(datos);
        ListaDatos.setValue(LDatos);
    }
}
