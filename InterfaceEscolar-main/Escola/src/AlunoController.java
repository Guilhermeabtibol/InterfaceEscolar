import java.util.ArrayList;
import java.util.List;

public class AlunoController {
    private List<Aluno> alunos;

    public AlunoController() {
        this.alunos = new ArrayList<>();
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public List<Aluno> listarAlunos() {
        return alunos;
    }

    public Aluno buscarAluno(String matricula) {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        return null;
    }
    public void atualizarAluno(String matricula, Aluno alunoAtualizado) {
        Aluno aluno = buscarAluno(matricula);
        if (aluno != null) {
            aluno.setNome(alunoAtualizado.getNome());
            aluno.setIdade(alunoAtualizado.getIdade());
            aluno.setSerie(alunoAtualizado.getSerie());
            aluno.setTurma(alunoAtualizado.getTurma());
        }
    }

    public void removerAluno(String matricula) {
        Aluno aluno = buscarAluno(matricula);
        if (aluno != null) {
            alunos.remove(aluno);
        }
    }
}

