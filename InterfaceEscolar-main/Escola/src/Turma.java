import java.util.List;

public class Turma {
    private String nome;
    private List<Aluno> alunos;
    private List<Professor> professores;

    public Turma(String nome, List<Aluno> alunos, List<Professor> professores) {
        this.nome = nome;
        this.alunos = alunos;
        this.professores = professores;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public List<Professor> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Professor> professores) {
        this.professores = professores;
    }
}
