import java.util.ArrayList;
import java.util.List;

public class ProfessorController {
    private List<Professor> professores;

    public ProfessorController() {
        this.professores = new ArrayList<>();
    }

    public void adicionarProfessor(Professor professor) {
        professores.add(professor);
    }

    public List<Professor> listarProfessores() {
        return professores;
    }

    public Professor buscarProfessor(String matricula) {
        for (Professor professor : professores) {
            if (professor.getMatricula().equals(matricula)) {
                return professor;
            }
        }
        return null; // Retorna null se não encontrar
    }

    public void atualizarProfessor(String matricula, Professor professorAtualizado) {
        Professor professor = buscarProfessor(matricula);
        if (professor != null) {
            professor.setNome(professorAtualizado.getNome());
            professor.setDisciplinas(professorAtualizado.getDisciplinas());
            professor.setSeriesAtendidas(professorAtualizado.getSeriesAtendidas());
        }
    }

    public void removerProfessor(String matricula) {
        Professor professor = buscarProfessor(matricula);
        if (professor != null) {
            professores.remove(professor);
        }
    }
}
