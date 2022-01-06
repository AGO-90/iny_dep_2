package uy.com.usermodule.datos.usuarios;

import uy.com.usermodule.logica.errores.BadRequestException;
import uy.com.usermodule.logica.errores.InternalServerErrorException;
import uy.com.usermodule.logica.errores.NotFoundException;

import java.io.IOException;
import java.util.List;

public interface IRepositorio<R, M> {
    R obtenerUsuarioPorID(M idUsuario)
            throws BadRequestException, NotFoundException, InternalServerErrorException, IOException;
    R crearUsuario(int codigo_deposito, int codigo_empresa,
                                 String email, String apellido, String nombre,
                                 List roles, List depositos_accesibles);
}
