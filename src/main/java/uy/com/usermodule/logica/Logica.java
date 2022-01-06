package uy.com.usermodule.logica;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import uy.com.usermodule.datos.usuarios.IRepositorio;
import uy.com.usermodule.logica.errores.BadRequestException;
import uy.com.usermodule.logica.errores.InternalServerErrorException;
import uy.com.usermodule.logica.errores.NotFoundException;
import uy.com.usermodule.logica.responses.usuario.ResponseAPIModuloUsuario;
import uy.com.usermodule.logica.responses.usuario.ResponseUsuario;

import java.io.IOException;
import java.util.List;

@Service("LUC")
public class Logica implements ILogica<ResponseUsuario, String>{
    @Qualifier("RUC")
    @Autowired
    IRepositorio repositorio;

    @Override
    public ResponseUsuario obtenerUsuarioPorID(String idUsuario)
            throws BadRequestException, NotFoundException, InternalServerErrorException, IOException {
        // ir al repositorio necesario y pedirle que busque el usuario con el id enviado
        ResponseAPIModuloUsuario responseAPIModuloUsuario =
                (ResponseAPIModuloUsuario) repositorio.obtenerUsuarioPorID(idUsuario);
        // generar nuestro token
        String tokenGenerado = "12345LKJSKLAKLDS$@#";
        // transformar el response de la api, en un response nuestro, sumandole nuestro token
        ResponseUsuario response = ResponseUsuario.crearResponseDesdeAPI(responseAPIModuloUsuario, tokenGenerado);
        // devolver el response
        return response;
    }

    @Override
    public ResponseUsuario crearUsuario(int codigo_deposito, int codigo_empresa,
                                                 String email, String apellido, String nombre,
                                                 List roles, List depositos_accesibles) {
        return null;
    }
}
