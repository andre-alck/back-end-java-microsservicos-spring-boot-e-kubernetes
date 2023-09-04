package br.com.asac.userapi.model;

import br.com.asac.userapi.dto.UserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cpf;
    private String endereco;
    private String email;
    private String telefone;
    private LocalDateTime dataDeCadastro;

    public static User convert(UserDTO dto) {
        User u = new User();
        u.setNome(dto.getNome());
        u.setCpf(dto.getCpf());
        u.setEndereco(dto.getEndereco());
        u.setEmail(dto.getEmail());
        u.setTelefone(dto.getTelefone());
        u.setDataDeCadastro(dto.getDataDeCadastro());
        return u;
    }
}
