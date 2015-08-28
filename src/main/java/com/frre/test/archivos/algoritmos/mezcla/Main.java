package com.frre.test.archivos.algoritmos.mezcla;

import com.frre.programacion.Generador;
import com.frre.programacion.Utils;
import com.frre.programacion.archivos.GeneradorArchivos;
import com.frre.test.archivos.algoritmos.Empleado;

import java.io.File;

import static com.frre.programacion.archivos.FuncionesDeArchivos.*;
import static com.frre.programacion.archivos.FuncionesDeArchivos.FDA;

/**
 * Created by jvargas on 8/21/15.
 */
public class Main {


    //ambiente

    //registros
    static Empleado registroPersonal;
    static Empleado registroPersonalExterno;

    //archivoPersonal
    private static File archivoPersonal;
    private static File archivoPersonalExterno;
    private static File archivoFinal;


    //algoritmo
    public static void main(String[] args){
        //muestra de funciones con archivos
        //aqui creamos un archivoPersonal
        registroPersonal = new Empleado();
        registroPersonalExterno = new Empleado();

        GeneradorArchivos.generarArchivo("personal", registroPersonal, Generador.generarEnteroAleatorio(100,1000));
        GeneradorArchivos.generarArchivo("personalExterno", registroPersonalExterno, Generador.generarEnteroAleatorio(100,1000));

        //con mi archivoPersonal creado cmoienzo a utilizarlo
        archivoPersonal = abrir("personal");
        archivoPersonalExterno = abrir("personalExterno");

        archivoFinal = abrir("nuevoPersonal", true);

        registroPersonal = leer(archivoPersonal, registroPersonal);
        registroPersonalExterno = leer(archivoPersonalExterno, registroPersonalExterno);

        while (!FDA(archivoPersonal) && !FDA(archivoPersonalExterno)){
            if (Utils.esMenor(registroPersonal, registroPersonalExterno)){
                grabar(archivoFinal, registroPersonal);
                registroPersonal = leer(archivoPersonal, registroPersonal);
            } else if (Utils.esMayor(registroPersonal, registroPersonalExterno)){
                grabar(archivoFinal, registroPersonalExterno);
                registroPersonalExterno = leer(archivoPersonalExterno, registroPersonalExterno);
            }  else {
                grabar(archivoFinal, registroPersonalExterno);
                grabar(archivoFinal, registroPersonal);
                registroPersonalExterno = leer(archivoPersonalExterno, registroPersonalExterno);
                registroPersonal = leer(archivoPersonal, registroPersonal);
            }
        }

        while (!FDA(archivoPersonal)){
            grabar(archivoFinal, registroPersonal);
            registroPersonal = leer(archivoPersonal, registroPersonal);
        }

        while (!FDA(archivoPersonalExterno)){
            grabar(archivoFinal, registroPersonalExterno);
            registroPersonalExterno = leer(archivoPersonalExterno, registroPersonalExterno);
        }

        cerrar(archivoPersonalExterno);
        cerrar(archivoPersonal);
        cerrar(archivoFinal);
    }

}
