package br.com.asac.userapi.service;

import br.com.asac.userapi.dto.UserDTO;
import br.com.asac.userapi.model.User;
import br.com.asac.userapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserDTO::convert).collect(Collectors.toList());
    }

    public UserDTO findById(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found."));
        return UserDTO.convert(user);
    }

    public UserDTO findByCpf(String cpf) {
        return UserDTO.convert(userRepository.findByCpf(cpf));
    }

    public UserDTO save(UserDTO dto) {
        dto.setDataDeCadastro(LocalDateTime.now());
        return UserDTO.convert(userRepository.save(User.convert(dto)));
    }

    public UserDTO delete(Long id) {
        User u = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found."));
        userRepository.delete(u);
        return UserDTO.convert(u);
    }

    public List<UserDTO> queryByNome(String nome) {
        List<User> users = userRepository.queryByNomeLike(nome);
        return users.stream().map(UserDTO::convert).toList();
    }

    public UserDTO editUser(Long id, UserDTO dto) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            return new RuntimeException("User not found.");
        });

        if(dto.getNome() != null && !dto.getNome().equals(user.getNome())) {
            user.setNome(dto.getNome());
        }

        if(dto.getCpf() != null && !dto.getCpf().equals(user.getCpf())) {
            user.setCpf(dto.getCpf());
        }

        if(dto.getEndereco() != null && dto.getEndereco().equals(user.getEndereco())) {
            user.setEndereco(dto.getEndereco());
        }

        if(dto.getEmail() != null && !dto.getEmail().equals(user.getEmail())) {
            user.setEmail(dto.getEmail());
        }

        if(dto.getTelefone() != null && !dto.getTelefone().equals(user.getTelefone())) {
            user.setTelefone(dto.getTelefone());
        }

        user = userRepository.save(user);
        return UserDTO.convert(user);
    }

    public Page<UserDTO> getAllPage(Pageable page) {
        Page<User> users = userRepository.findAll(page);
        return users.map(UserDTO::convert);
    }
}
