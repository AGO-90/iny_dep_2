package uy.com.usermodule.datos.usuarios;

import com.google.gson.Gson;
import org.springframework.stereotype.Repository;
import uy.com.usermodule.logica.errores.BadRequestException;
import uy.com.usermodule.logica.errores.InternalServerErrorException;
import uy.com.usermodule.logica.errores.NotFoundException;
import uy.com.usermodule.logica.responses.usuario.ResponseAPIModuloUsuario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Repository("RUC")
public class Repositorio_Comun implements IRepositorio<ResponseAPIModuloUsuario, String>{
    final String URL_MODULO = "https://users-module-go.herokuapp.com/api/v1/";
    final String CONTROLLER_USERS = "users/";
    final String METHOD_DISABLED_USERS = "disabled/";

    @Override
    public ResponseAPIModuloUsuario obtenerUsuarioPorID(String idUsuario)
            throws BadRequestException, NotFoundException, InternalServerErrorException, IOException {
        // ir a la api con el id y obtener json
        String urlAGolpear = URL_MODULO + CONTROLLER_USERS + METHOD_DISABLED_USERS + idUsuario;
        String resultado = consultarAPIPorURLParams(urlAGolpear, "GET");
        // convertir el json a ResponseAPIModuloUsuario
        Gson gson = new Gson(); // libreria de google que tranforma jsons a objetos entre otras cosas.
        ResponseAPIModuloUsuario responseAPIModuloUsuario =
                gson.fromJson(resultado, ResponseAPIModuloUsuario.class);
        return responseAPIModuloUsuario;
    }

    @Override
    public ResponseAPIModuloUsuario crearUsuario(int codigo_deposito, int codigo_empresa, String email, String apellido, String nombre, List roles, List depositos_accesibles) {
        return null;
    }

    private String consultarAPIPorURLParams(String urlAGolpear, String verbo)
            throws IOException, BadRequestException, NotFoundException, InternalServerErrorException {
        // URL = servidor + puerto + controlador + metodo + parametros
        URL url = new URL(urlAGolpear); // define la url a la que le va a pegar
        // hacemos una prueba para ver si anda, si anda se desconecta y sigue configurando, sino lanza error
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        // definimos el verbo que vamos a utilizar
        connection.setRequestMethod(verbo);
        // definimos el MIME TYPE que vamos a aceptar / rechazar
        // los MIME TYPE siempre se componen de "application/ALGO", ese algo puede ser:
        /* Video (media), JSON (json), IMAGEN (jpeg, gif) */
        connection.setRequestProperty("Accept", "application/json");
        // le pego al endpoint con toda la info cargada y valido que status code me arroja
        if(connection.getResponseCode() > 299){
            definirError(connection.getResponseCode());
        }
        //salio bien la consulta al endpoint
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        // lo que leyo (el bufferedReader) de la respuesta de la otra api, lo guarda en el siguiente string
        String resultado = br.readLine();
        connection.disconnect(); // muy impotante, sino dejo la conexion entre esta api y la otra abierta.
        return resultado;
    }

    private String definirError(int responseCode) throws BadRequestException, NotFoundException, InternalServerErrorException {
        switch (responseCode){
            case 400:
                throw new BadRequestException();
            case 404:
                throw new NotFoundException();
            default:
                throw new InternalServerErrorException();
        }
    }
}
