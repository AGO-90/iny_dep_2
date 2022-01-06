package uy.com.usermodule.logica.responses.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAPIModuloUsuario {
    private int warehouse_id, company_id;
    private String email, last_name, name, _id, token;
    private List<String> roles, permissions;
    private List<Integer> accessible_deposits;
}
