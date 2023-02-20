package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JPAUtil;
import br.com.alura.leilao.util.builder.LeilaoBuiler;
import br.com.alura.leilao.util.builder.UsuarioBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LeilaoDaoTest {

    private LeilaoDao leilaoDao;
    private EntityManager em;

    @BeforeEach
    void setUp() {
        em = JPAUtil.getEntityManager();
        leilaoDao = new LeilaoDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    void tearDown() {
        em.getTransaction().rollback();
    }

    @Test
    void salvarTest() {
        Usuario usuario = new UsuarioBuilder()
                .comNome("Fulano")
                .comEmail("fulano@email.com")
                .comSenha("123456")
                .build();
        em.persist(usuario);
        Leilao leilao = new LeilaoBuiler()
                .comNome("Mochila")
                .comValorInicial("50")
                .comUsuario(usuario)
                .comData(LocalDate.now())
                .build();
        leilao = leilaoDao.salvar(leilao);

        Leilao leilaoEncontrado = leilaoDao.buscarPorId(leilao.getId());
        assertNotNull(leilaoEncontrado);
        assertEquals(leilao.getNome(), leilaoEncontrado.getNome());
    }

    @Test
    void atualizarLeilaoTest() {
        Usuario usuario = mockUsuario();
        Leilao leilao = new Leilao("Mochila", new BigDecimal("70"), LocalDate.now(), usuario);
        leilao = leilaoDao.salvar(leilao);
        leilao.setNome("celular");
        leilao.setValorInicial(new BigDecimal("400"));
        leilao = leilaoDao.salvar(leilao);

        Leilao leilaoEncontrado = leilaoDao.buscarPorId(leilao.getId());
        assertNotNull(leilaoEncontrado);
        assertEquals(leilao.getNome(), leilaoEncontrado.getNome());
    }

    private Usuario mockUsuario() {
        Usuario usuario = new Usuario("Fulano", "fulano@email.com", "123456");
        em.persist(usuario);
        return usuario;
    }
}