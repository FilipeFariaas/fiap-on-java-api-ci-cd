package br.com.fiap.qualidade_agua.service;

import br.com.fiap.qualidade_agua.dto.UsuarioExibicaoDTO;
import br.com.fiap.qualidade_agua.dto.UsuarioCadastroDTO;
import br.com.fiap.qualidade_agua.exception.UsuarioNaoEncontradoException;
import br.com.fiap.qualidade_agua.model.Usuario;
import br.com.fiap.qualidade_agua.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioExibicaoDTO salvarUsuario(UsuarioCadastroDTO usuarioDTO) {
        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioDTO.senha());

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        usuario.setSenha(senhaCriptografada);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioExibicaoDTO(usuarioSalvo);
    }
    
    public UsuarioExibicaoDTO buscarPorId(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if(usuarioOptional.isPresent()) {
            return new UsuarioExibicaoDTO(usuarioOptional.get());
        } else {
            throw new UsuarioNaoEncontradoException("Usuario não existe");
        }
    }

    public Page<UsuarioExibicaoDTO> buscarTodosUsuarios(Pageable paginacao) {
        return usuarioRepository.findAll(paginacao)
                .map(UsuarioExibicaoDTO::new);
    }

    public void excluir(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if(usuarioOptional.isPresent()) {
            usuarioRepository.delete(usuarioOptional.get());
        } else {
            throw new RuntimeException("Usuario não encontrado");
        }
    }

    public Usuario atualizar(Usuario Usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(Usuario.getId());

        if(usuarioOptional.isPresent()) {
            return usuarioRepository.save(Usuario);
        } else {
            throw new RuntimeException("Usuario não encontrado");
        }
    }

//    public UserDetails buscarPorEmail(String email) {
//        Optional<UserDetails> usuarioOptional = usuarioRepository.findByEmail(email);
//
//        if(usuarioOptional.isPresent()) {
//            return usuarioOptional.get();
//        } else {
//            throw new RuntimeException("Usuario não encontrado");
//        }
//    }
}
