package org.example.dao;

import jakarta.persistence.EntityManager;
import org.example.modelo.Aluno;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class AlunoDao {
    private EntityManager em;

    public AlunoDao(EntityManager em) {this.em = em;}

    public void register(Aluno aluno){
        em.getTransaction().begin();
        em.persist(aluno);
        em.getTransaction().commit();
    }

    public void remove(Aluno aluno){
        em.getTransaction().begin();
        em.remove(aluno);
        em.getTransaction().commit();
    }

    public Optional<Aluno> findByName(String name){
        Objects.requireNonNull(name, "Nome cannot be null");

        try {
            String jpql = "SELECT a FROM Aluno a WHERE a.nome = :n";
            Aluno aluno = em.createQuery(jpql, Aluno.class)
                    .setParameter("n", name)
                    .setMaxResults(1)
                    .getSingleResult();
            return Optional.of(aluno);
        } catch (jakarta.persistence.NoResultException e) {
            return Optional.empty();
        }
    }

    public void update(Aluno a){

        Objects.requireNonNull(a, "Aluno cannot be null");

        String jpql = "UPDATE Aluno a SET a.nome = :nome, a.ra = :ra, a.email = :email, a.nota1 = :n1, a.nota2 = :n2, a.nota3 = :n3 WHERE a.id = :id";
        em.createQuery(jpql, Aluno.class)
                .setParameter("id", a.getId())
                .setParameter("email", a.getEmail())
                .setParameter("nome", a.getNome())
                .setParameter("ra", a.getRa())
                .setParameter("n1", a.getNota1())
                .setParameter("n2", a.getNota2())
                .setParameter("n3", a.getNota3())
                .executeUpdate();
    }

    public Stream<Aluno> findAll(){
        String jpql = "SELECT a FROM Aluno a";
        return em.createQuery(jpql, Aluno.class).getResultStream();
    }
}


