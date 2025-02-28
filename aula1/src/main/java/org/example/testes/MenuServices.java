package org.example.testes;

import jakarta.persistence.EntityManager;
import org.example.dao.AlunoDao;
import org.example.modelo.Aluno;
import org.example.util.JPAUtil;

import java.math.BigDecimal;
import java.util.Scanner;

public class MenuServices {

    public MenuServices() {
    }

    public static void addUserToDB(int option) {
        Scanner scanner = new Scanner(System.in);
        String nome, ra, email;
        BigDecimal n1,n2,n3;
        Aluno a;

        EntityManager em = JPAUtil.getEntityManager();
        AlunoDao dao = new AlunoDao(em);

        System.out.println("CADASTRO DE ALUNO: ");
        System.out.println("Digite o nome: ");
        nome = scanner.nextLine();

        System.out.println("Digite o RA: ");
        ra = scanner.nextLine();

        System.out.println("Digite o email: ");
        email = scanner.nextLine();

        System.out.println("Digite a nota 1: ");
        n1 = scanner.nextBigDecimal();
        scanner.nextLine();

        System.out.println("Digite a nota 2: ");
        n2 = scanner.nextBigDecimal();


        System.out.println("Digite a nota 3: ");
        n3 = scanner.nextBigDecimal();

        a = new Aluno(nome, ra, email, n1,n2,n3);

        register(a, dao);
    }

    //funcoes auxiliares
    private static void register(Aluno aluno, AlunoDao dao) {
        dao.register(aluno);
    }


}
