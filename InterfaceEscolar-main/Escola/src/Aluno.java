public class Aluno {
    private String nome;
    private String matricula;
    private int idade;
    private String serie;
    private String turma;

    public Aluno(String nome, String matricula, int idade, String serie, String turma) {
        this.nome = nome;
        this.matricula = matricula;
        this.idade = idade;
        this.serie = serie;
        this.turma = turma;
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

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }
}
