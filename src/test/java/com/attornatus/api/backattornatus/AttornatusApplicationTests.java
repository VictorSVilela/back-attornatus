package com.attornatus.api.backattornatus;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.attornatus.api.backattornatus.model.Pessoa;
import com.attornatus.api.backattornatus.repository.PessoaRepository;

@RunWith(JUnit4.class)
@SpringBootTest(classes = AttornatusApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AttornatusApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private static  String getUrl() {
        return "http://localhost:8080";
    }

    private static List<Pessoa> pessoas;

    @Autowired
    private PessoaRepository pessoaRepository;

    @BeforeAll
    public static void init() {
        pessoas = Arrays.asList(new Pessoa[] {
           new Pessoa(1L, "Victor", "17/09/1990",null),
           new Pessoa(2L, "Adriane", "13/12/1993",null),
           new Pessoa(3L, "Júlia", "27/05/2020",null)
        });

        TestRestTemplate restTemplate = new TestRestTemplate();
        restTemplate.postForEntity(getUrl() + "/pessoas", pessoas.get(0), Pessoa.class);
        restTemplate.postForEntity(getUrl() + "/pessoas", pessoas.get(1), Pessoa.class);
        restTemplate.postForEntity(getUrl() + "/pessoas", pessoas.get(2), Pessoa.class);
    }

    @Test
    public void testListarPessoas() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = testRestTemplate.exchange(getUrl() + "/pessoas",
                HttpMethod.GET, entity, String.class);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertNotNull(response.getBody());
    }

    @Test
    public void testConsultarPessoa() {
        Long id = 1L;
        Pessoa pessoa = testRestTemplate.getForObject(getUrl() + "/pessoas/" + id, Pessoa.class);

        Assert.assertNotNull(pessoa);
        Assert.assertEquals(pessoas.get(0), pessoa);
    }

    @Test
    void testAdicionarPessoa() {
        Pessoa pessoa = Pessoa.builder()
                .nome("Roberto")
                .dataNascimento("07/08/1956")
                .enderecos(null)
                .build();

        pessoaRepository.save(pessoa);
        Assertions.assertThat(pessoa.getPessoaId()).isGreaterThan(0);
    }

    @Test
    public void testEditarPessoa() {
        Long id = 1L;
        Pessoa pessoa = testRestTemplate.getForObject(getUrl() + "/pessoas/" + id, Pessoa.class);
        pessoa.setNome("Victor Vilela");
        pessoa.setDataNascimento("28/08/1988");

        testRestTemplate.put(getUrl() + "/pessoas/" + id, pessoa);

        Pessoa pessoaAtualizada = testRestTemplate.getForObject(getUrl() + "/pessoas/" + id, Pessoa.class);
        Assert.assertNotNull(pessoaAtualizada);
        Assert.assertEquals(pessoa, pessoaAtualizada);
    }

}
