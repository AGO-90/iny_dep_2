package uy.com.usermodule.logica.responses.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class ResponseUsuario {
    private int id_deposito, id_empresa;
    private String email, apellido, nombre, id, token, nuestro_token;
    private List<String> roles, permisos;
    private List<Integer> depositos_accesibles;

    public static ResponseUsuario crearResponseDesdeAPI(ResponseAPIModuloUsuario responseAPIModuloUsuario,
                                                        String nuestroToken){
        return ResponseUsuario.builder()
                .id(responseAPIModuloUsuario.get_id())
                .id_deposito(responseAPIModuloUsuario.getWarehouse_id())
                .id_empresa(responseAPIModuloUsuario.getCompany_id())
                .email(responseAPIModuloUsuario.getEmail())
                .apellido(responseAPIModuloUsuario.getLast_name())
                .nombre(responseAPIModuloUsuario.getName())
                .token(responseAPIModuloUsuario.getToken())
                .nuestro_token(nuestroToken)
                .roles(responseAPIModuloUsuario.getRoles())
                .permisos(responseAPIModuloUsuario.getPermissions())
                .depositos_accesibles(responseAPIModuloUsuario.getAccessible_deposits())
                .build();
    }
}
