import java.util.List;

public class Professor {
    private String nome;
    private String matricula;
    private List<String> disciplinas;
    private List<String> seriesAtendidas;

    public Professor(String nome, String matricula, List<String> disciplinas, List<String> seriesAtendidas) {
        this.nome = nome;
        this.matricula = matricula;
        this.disciplinas = disciplinas;
        this.seriesAtendidas = seriesAtendidas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public List<String> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<String> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public List<String> getSeriesAtendidas() {
        return seriesAtendidas;
    }

    public void setSeriesAtendidas(List<String> seriesAtendidas) {
        this.seriesAtendidas = seriesAtendidas;
    }
}
