import DAO.AutorDAO;
import DAO.LivroDAO;
import Entity.Autor;
import Entity.Livro;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try{
            AutorDAO autorDAO = new AutorDAO();
            LivroDAO livroDAO = new LivroDAO();

            autorDAO.criarAutor();
            livroDAO.criarLivro();
            // Inserir o primeiro autor
            Autor autor1 = new Autor();
            autor1.setIdAutor(1);
            autor1.setNomeAutor("Stephen King");
            autor1.setNacionalidade("Americano");
            autorDAO.inserirAutor(autor1);

            // Inserir o segundo autor
            Autor autor2 = new Autor();
            autor2.setIdAutor(2);
            autor2.setNomeAutor("Jules Verne");
            autor2.setNacionalidade("Francês");
            autorDAO.inserirAutor(autor2);

            // Inserir 3 livros para o primeiro autor
            Livro livro1 = new Livro();
            livro1.setIdLivro(1);
            livro1.setTitulo("Carrie ");
            livro1.setAnoPublicado(1974);
            livro1.setAutor(autor1);
            livroDAO.inserirLivro(livro1);

            Livro livro2 = new Livro();
            livro2.setIdLivro(2);
            livro2.setTitulo("O Iluminado");
            livro2.setAnoPublicado(1977);
            livro2.setAutor(autor1);
            livroDAO.inserirLivro(livro2);

            Livro livro3 = new Livro();
            livro3.setIdLivro(3);
            livro3.setTitulo("It: A Coisa ");
            livro3.setAnoPublicado(1986);
            livro3.setAutor(autor1);
            livroDAO.inserirLivro(livro3);

            // Inserir 3 livros para o segundo autor
            Livro livro4 = new Livro();
            livro4.setIdLivro(4);
            livro4.setTitulo("Vinte Mil Léguas Submarinas ");
            livro4.setAnoPublicado(1870);
            livro4.setAutor(autor2);
            livroDAO.inserirLivro(livro4);

            Livro livro5 = new Livro();
            livro5.setIdLivro(5);
            livro5.setTitulo("A Volta ao Mundo em Oitenta Dias ");
            livro5.setAnoPublicado(1873);
            livro5.setAutor(autor2);
            livroDAO.inserirLivro(livro5);

            Livro livro6 = new Livro();
            livro6.setIdLivro(6);
            livro6.setTitulo("A Ilha Misteriosa");
            livro6.setAnoPublicado(1874);
            livro6.setAutor(autor2);
            livroDAO.inserirLivro(livro6);

            //Aualiza

            autor1.setNomeAutor("J.K");
            autor2.setNomeAutor("Lucas");
            autorDAO.atualizarAutor(autor1);
            autorDAO.atualizarAutor(autor2);
            livro1.setAnoPublicado(2000);
            livro2.setAnoPublicado(1996);
            livro3.setAnoPublicado(1980);
            livro4.setAnoPublicado(1956);
            livro5.setAnoPublicado(1967);
            livro6.setAnoPublicado(1955);
            livroDAO.atualizarLivro(livro1);
            livroDAO.atualizarLivro(livro2);
            livroDAO.atualizarLivro(livro3);
            livroDAO.atualizarLivro(livro4);
            livroDAO.atualizarLivro(livro5);
            livroDAO.atualizarLivro(livro6);

            livroDAO.excluirLivro(livro1.getIdLivro());
            autorDAO.excluirAutor(autor1.getIdAutor());


            // Listar todos os autores
            List<Autor> autores = autorDAO.listarAutores();
            System.out.println("\nAutores:");
            for (Autor a : autores) {
                System.out.println(a.getIdAutor() + ": " + a.getNomeAutor() + " (" + a.getNacionalidade() + ")");
            }

            // Listar todos os livros
            List<Livro> livros = livroDAO.listarLivros();
            System.out.println("\nTodos os livros:");
            for (Livro l : livros) {
                System.out.println(l.getIdLivro() + ": " + l.getTitulo() + " (" + l.getAnoPublicado() + ")");
            }


            // Listar todos os autores e seus respectivos livros
            autores = autorDAO.listarAutores();
            System.out.println("\nAutores e seus livros:");
            for (Autor autor : autores) {
                System.out.println("\n" + autor.getIdAutor() + ": " + autor.getNomeAutor() + " (" + autor.getNacionalidade() + ")");

                // Listar livros do autor atual
                List<Livro> livrosDeAutor = livroDAO.listarLivrosDeAutor(autor.getIdAutor());

                if (livrosDeAutor.isEmpty()) {
                    System.out.println("  Nenhum livro encontrado para este autor.");
                } else {
                    for (Livro livro : livrosDeAutor) {
                        System.out.println("  - " + livro.getIdLivro() + ": " + livro.getTitulo() + " (" + livro.getAnoPublicado() + ")");
                    }
                }
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
