package uy.com.usermodule.controladores.requests.usuarios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RequestUsuario {
    private int codigo_deposito, codigo_empresa;
    private String email, apellido, nombre, password;
    private List<String> roles;
    private List<Integer> depositos_accesibles;
}
