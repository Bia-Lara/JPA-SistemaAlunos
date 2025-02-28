package org.example.testes;

import jakarta.persistence.EntityManager;
import org.example.dao.AlunoDao;
import org.example.modelo.Aluno;
import org.example.util.JPAUtil;

import java.math.BigDecimal;
import java.util.Scanner;

public class MainAluno {
    static EntityManager em = JPAUtil.getEntityManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String nome, ra, email;
        BigDecimal n1,n2,n3;
        int opcao;

        Aluno a;
        AlunoDao dao = new AlunoDao(em);

        do {
            System.out.println("\nMenu:");
            System.out.println("1 - Cadastrar Aluno");
            System.out.println("2 - Excluir Aluno");
            System.out.println("3 - Alterar Aluno");
            System.out.println("4 - Buscar Aluno pelo Nome");
            System.out.println("5 - Listar Alunos");
            System.out.println("6 - FIM");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao){
                case 1: MenuService.addUserToDB();
                case 2: MenuService.excludeStudentFromDB();
                case 5: MenuService.listStudents();
            }

        }while (opcao !=6);


    }
}
