package uy.com.usermodule.controladores.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uy.com.usermodule.controladores.requests.usuarios.RequestUsuario;
import uy.com.usermodule.logica.ILogica;
import uy.com.usermodule.logica.errores.BadRequestException;
import uy.com.usermodule.logica.errores.InternalServerErrorException;
import uy.com.usermodule.logica.errores.NotFoundException;
import uy.com.usermodule.logica.responses.usuario.ResponseUsuario;

import java.io.IOException;

@RestController // Le indico que es un controlador (caja con metodos que se exponen) y es del tipo REST
@RequestMapping("api/v1/usuarios") // Le indico la direccion o el mapeo, para acceder al controller o a la caja con metodos
public class Controller {
    @Qualifier("LUC")
    @Autowired
    ILogica logica;
    /* Metodos o Endpoints */

    /* Verbos HTTP: GET - traer info, POST = crear informacion nueva
    *               DELETE - eliminar info, PUT - modificar info existente (completamente)
    *               PATCH - modificar info existente (parcialmente) */

    /* Endpoints pueden recibir info por 3 lados diferentes:
    * 1) URL, se usa para metodos que reciben informacion basica (strings, ints, booleans, etc) - @PathVariable
    * 2) BODY, se usa para metodos que reciben informacion compleja (objetos, listas, hashtable, etc) - @RequestBody
    * 3) HEADER, se usa para recibir informacion delicada (tokens de seguridad, propiedades, etc)
    * */

    /* Usuario -> nombre, apellido, contrasena, habilitado, etc.
    * Request: nombre, apellido y contrasena. - CAPA CONTROLLER
    * Response: nombre, apellido, contrasena y habilitado - CAPA NEGOCIO
    * Objeto del dominio, nombre, apellido, etc - CAPA NEGOCIO
    * */

    /* ResponseEntity -> | ALGO QUE QUIERA DEVOLVER YO (int, string, hashtable, lista, obj) | STATUS CODE | */

    @GetMapping(value = "/obtenerPorID/{idUsuario}")
    public ResponseEntity obtenerUsuarioPorID(@PathVariable String idUsuario){
        ResponseUsuario usuarioESP = null;
        //validar datos basicos
        if (idUsuario == "") {
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }
        // llamar a mi capa de logica
        try {
            usuarioESP = (ResponseUsuario) logica.obtenerUsuarioPorID(idUsuario);
            return new ResponseEntity<>(usuarioESP, HttpStatus.OK);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (InternalServerErrorException | IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/crear")
    public void crearUsuario(@RequestBody RequestUsuario usuario){

    }

}
