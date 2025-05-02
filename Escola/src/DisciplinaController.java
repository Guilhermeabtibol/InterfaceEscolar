import java.util.ArrayList;
import java.util.List;

public class DisciplinaController {
    private List<Disciplina> disciplinas;

    public DisciplinaController() {
        this.disciplinas = new ArrayList<>();
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
    }

    public List<Disciplina> listarDisciplinas() {
        return disciplinas;
    }

    public Disciplina buscarDisciplina(String codigo) {
        for (Disciplina disciplina : disciplinas) {
            if (disciplina.getCodigo().equals(codigo)) {
                return disciplina;
            }
        }
        return null; // Retorna null se não encontrar
    }

    public void atualizarDisciplina(String codigo, Disciplina disciplinaAtualizada) {
        Disciplina disciplina = buscarDisciplina(codigo);
        if (disciplina != null) {
            disciplina.setNome(disciplinaAtualizada.getNome());
            disciplina.setCodigo(disciplinaAtualizada.getCodigo());
        }
    }

    public void removerDisciplina(String codigo) {
        Disciplina disciplina = buscarDisciplina(codigo);
        if (disciplina != null) {
            disciplinas.remove(disciplina);
        }
    }
}
