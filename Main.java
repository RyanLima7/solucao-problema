package exercicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();
        List<Usuario> usuarios = new ArrayList<>();

        System.out.println("\n\t=== Bem-vindo à Biblioteca Virtual ===");

        int opcao = 0;
        while (opcao != 9) {
            System.out.println("\n\t Menu:");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Cadastrar livro");
            System.out.println("3 - Consultar livros disponíveis");
            System.out.println("4 - Consultar livros emprestados");
            System.out.println("5 - Emprestar livro");
            System.out.println("6 - Devolver livro");
            System.out.println("7 - Consultar todos os livros");
            System.out.println("8 - Consultar usuários cadastrados");
            System.out.println("9 - Sair");
            System.out.print("\n\tEscolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("\nÉ aluno (1) ou professor (2)? ");
                    int tipo = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    if (tipo == 1) {
                        usuarios.add(new Aluno(id, nome, email));
                        System.out.println("Aluno cadastrado com sucesso!");
                    } else if (tipo == 2) {
                        usuarios.add(new Professor(id, nome, email));
                        System.out.println("Professor cadastrado com sucesso!");
                    } else {
                        System.out.println("Tipo inválido.");
                    }
                    break;

                case 2:
                    Livroo livro = new Livroo();

                    System.out.print("Código do livro: ");
                    livro.setCodigo(scanner.nextLine());

                    System.out.print("Título do livro: ");
                    livro.setTitulo(scanner.nextLine());

                    System.out.print("Autor do livro: ");
                    livro.setAutor(scanner.nextLine());

                    livro.setStatus("DISPONÍVEL");
                    biblioteca.adicionarLivro(livro);
                    System.out.println("Livro cadastrado com sucesso!");
                    break;

                case 3:
                    System.out.println("\nLivros disponíveis:");
                    for (Livroo l : biblioteca.getLivros()) {
                        if (l.getStatus().equals("DISPONÍVEL")) {
                            System.out.println("Código: " + l.getCodigo() + " | Título: " + l.getTitulo());
                        }
                    }
                    break;

                case 4:
                    System.out.println("\nLivros emprestados:");
                    for (Livroo l : biblioteca.getLivros()) {
                        if (l.getStatus().equals("EMPRESTADO")) {
                            System.out.println("Código: " + l.getCodigo() + " | Título: " + l.getTitulo());
                        }
                    }
                    break;

                case 5:
                    System.out.print("Digite o ID do usuário: ");
                    int idEmprestar = scanner.nextInt();
                    scanner.nextLine();

                    Usuario usuarioEmprestimo = encontrarUsuarioPorId(usuarios, idEmprestar);
                    if (usuarioEmprestimo == null) {
                        System.out.println("Usuário não encontrado.");
                        break;
                    }

                    System.out.print("Digite o código do livro: ");
                    String codigoEmprestar = scanner.nextLine();

                    biblioteca.emprestarLivro(usuarioEmprestimo, codigoEmprestar);
                    break;

                case 6:
                    System.out.print("Digite o ID do usuário: ");
                    int idDevolver = scanner.nextInt();
                    scanner.nextLine();

                    Usuario usuarioDevolucao = encontrarUsuarioPorId(usuarios, idDevolver);
                    if (usuarioDevolucao == null) {
                        System.out.println("Usuário não encontrado.");
                        break;
                    }

                    System.out.print("Digite o código do livro: ");
                    String codigoDevolver = scanner.nextLine();

                    biblioteca.devolverLivro(usuarioDevolucao, codigoDevolver);
                    break;

                case 7:
                    System.out.println("\nTodos os livros:");
                    for (Livroo l : biblioteca.getLivros()) {
                        System.out.println("Código: " + l.getCodigo() + " | Título: " + l.getTitulo() + " | Status: " + l.getStatus());
                    }
                    break;

                case 8:
                    System.out.println("\nUsuários cadastrados:");
                    for (Usuario u : usuarios) {
                        String tipoUsuario = (u instanceof Aluno) ? "Aluno" : "Professor";
                        System.out.println("ID: " + u.getId() + " | Nome: " + u.getNome() + " | Email: " + u.getEmail() + " | Tipo: " + tipoUsuario);
                    }
                    break;

                case 9:
                    System.out.println("Encerrando a Biblioteca Virtual. Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    private static Usuario encontrarUsuarioPorId(List<Usuario> usuarios, int id) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }
}
