package br.com.asac.userapi.dto;

import br.com.asac.userapi.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    @NotBlank(message = "O preenchimento do nome é obrigatório.")
    private String nome;

    @NotBlank(message = "O preenchimento do CPF é obrigatório.")
    @CPF(message = "O CPF foi preenchido incorretamente.")
    private String cpf;
    private String endereco;
    @Email(message = "O e-mail foi preenchido incorretamente.")
    private String email;
    private String telefone;
    private LocalDateTime dataDeCadastro;

    public static UserDTO convert(User u) {
        return new UserDTO(u.getNome(), u.getCpf(), u.getEndereco(), u.getEmail(), u.getTelefone(), u.getDataDeCadastro());
    }
}
