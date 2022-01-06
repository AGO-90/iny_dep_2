package uy.com.usermodule.logica;

import uy.com.usermodule.logica.errores.BadRequestException;
import uy.com.usermodule.logica.errores.InternalServerErrorException;
import uy.com.usermodule.logica.errores.NotFoundException;

import java.io.IOException;
import java.util.List;

public interface ILogica<Q, T> {
    Q obtenerUsuarioPorID(T idUsuario)
            throws BadRequestException, NotFoundException, InternalServerErrorException, IOException;
    Q crearUsuario(int codigo_deposito, int codigo_empresa,
                   String email, String apellido, String nombre, List<String> roles,
                   List<Integer> depositos_accesibles);
}
