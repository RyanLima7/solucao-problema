package exercicio;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livroo> livros;
    private List<Emprestimo> emprestimos;

    public Biblioteca() {
        this.livros = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    public void adicionarLivro(Livroo livro) {
        livros.add(livro);
    }

    public List<Livroo> getLivros() {
        return livros;
    }

    public void emprestarLivro(Usuario usuario, String codigo) {
        Livroo livro = buscarLivroPorCodigo(codigo);
        if (livro != null && livro.getStatus().equals("DISPONÍVEL")) {
            if (quantidadeEmprestimos(usuario) < usuario.getLimiteEmprestimos()) {
                livro.emprestar(usuario);
                emprestimos.add(new Emprestimo(livro, usuario));
                System.out.println("Livro emprestado com sucesso.");
            } else {
                System.out.println("Limite de empréstimos atingido.");
            }
        } else {
            System.out.println("Livro indisponível.");
        }
    }

    public void devolverLivro(Usuario usuario, String codigo) {
        Livroo livro = buscarLivroPorCodigo(codigo);
        if (livro != null && livro.getStatus().equals("EMPRESTADO")) {
            livro.devolver();
            Emprestimo e = encontrarEmprestimo(livro, usuario);
            if (e != null) {
                emprestimos.remove(e);
                System.out.println("Livro devolvido com sucesso.");
            }
        } else {
            System.out.println("Esse livro não está emprestado.");
        }
    }

    private Livroo buscarLivroPorCodigo(String codigo) {
        for (Livroo l : livros) {
            if (l.getCodigo().equals(codigo)) {
                return l;
            }
        }
        return null;
    }

    private int quantidadeEmprestimos(Usuario usuario) {
        int count = 0;
        for (Emprestimo e : emprestimos) {
            if (e.getUsuario().getId() == usuario.getId()) {
                count++;
            }
        }
        return count;
    }

    private Emprestimo encontrarEmprestimo(Livroo livro, Usuario usuario) {
        for (Emprestimo e : emprestimos) {
            if (e.getLivro().getCodigo().equals(livro.getCodigo()) &&
                e.getUsuario().getId() == usuario.getId()) {
                return e;
            }
        }
        return null;
    }
}
