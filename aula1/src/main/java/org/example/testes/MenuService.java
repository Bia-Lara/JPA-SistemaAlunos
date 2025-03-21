package org.example.testes;

import jakarta.persistence.EntityManager;
import org.example.dao.AlunoDao;
import org.example.modelo.Aluno;
import org.example.util.JPAUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class MenuService {
    public static Scanner scanner = new Scanner(System.in);
    public static EntityManager em = JPAUtil.getEntityManager();
    public static AlunoDao dao = new AlunoDao(em);

    public MenuService() {
    }

    public static void addUserToDB() {
        String nome, ra, email;
        BigDecimal n1,n2,n3;
        Aluno a;

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

    public static void excludeStudentFromDB() {
        System.out.println("insira o nome do aluno: ");
        String name = scanner.nextLine();
        exclude(name);
    }

    public static void listStudents() {
        dao.findAll().forEach(System.out::println);
    }

    public static void findByName() {
        System.out.println("insira o nome do aluno: ");
        String name = scanner.nextLine();
        try {
            Aluno student = dao.findByName(name).orElseThrow(() -> new IllegalArgumentException("Student with the provided name not found"));
            System.out.println(student);
        } catch (IllegalArgumentException e) {
            System.out.println("Aluno não encontrado!");
        }
    }

    public static void updateStudent() {
        System.out.println("nome do aluno: ");
        String name = scanner.nextLine();
        try {
            Aluno aluno = dao.findByName(name).orElseThrow(() -> new IllegalArgumentException("Student with the provided name not found"));
            String nome, ra, email;
            BigDecimal n1,n2,n3;

            System.out.println("NOVOS DADOS: ");
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

            Aluno newStudent = new Aluno(nome, ra, email, n1,n2,n3);

            dao.update(aluno, newStudent);
        } catch (IllegalArgumentException e) {
            System.out.println("Aluno não encontrado!");
        }
    }

    //funcoes auxiliares
    private static void register(Aluno aluno, AlunoDao dao) {
        dao.register(aluno);
    }

    private static void exclude(String name) {
        try {
            Aluno student = dao.findByName(name).orElseThrow(() -> new IllegalArgumentException("Student does not exists"));
            dao.remove(student);
        } catch (IllegalArgumentException e) {
            System.out.println("Aluno não encontrado!");
        }
    };

}
