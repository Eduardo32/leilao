package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioDaoTest {

    private UsuarioDao usuarioDao;
    private EntityManager em;

    @BeforeEach
    void setUp() {
        em = JPAUtil.getEntityManager();
        usuarioDao = new UsuarioDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    void tearDown() {
        em.getTransaction().rollback();
    }

    @Test
    void buscarPorUsernameTeste() {
        Usuario usuario = mockUsuario();
        Usuario usuarioEncontrado = usuarioDao.buscarPorUsername(usuario.getNome());
        assertNotNull(usuarioEncontrado);
    }

    @Test
    void buscarPorUsernameNaoCadastradoTeste() {
        assertThrows(NoResultException.class, () -> usuarioDao.buscarPorUsername("Beltrano"));
    }

    @Test
    void deletarUsuarioTeste() {
        Usuario usuario = mockUsuario();
        Usuario usuarioEncontrado = usuarioDao.buscarPorUsername("Fulano");
        usuarioDao.deletar(usuarioEncontrado);
        assertThrows(NoResultException.class, () -> usuarioDao.buscarPorUsername(usuario.getNome()));
    }

    private Usuario mockUsuario() {
        Usuario usuario = new Usuario("Fulano", "fulano@email.com", "123456");
        em.persist(usuario);
        return usuario;
    }
}