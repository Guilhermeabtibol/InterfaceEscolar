import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

// colocar combobox nas disciplinas do professor
//colocar cpf pro professor

public class EscolaSystem extends JFrame {

    private AlunoController alunoController;
    private ProfessorController professorController;
    private DisciplinaController disciplinaController;


    // Componentes para alunos
    private DefaultTableModel modeloTabelaAlunos;
    private JTable tabelaAlunos;

    // Componentes para professores
    private DefaultTableModel modeloTabelaProfessores;
    private JTable tabelaProfessores;

    public EscolaSystem() {
        // Exibe o diálogo de login antes de inicializar a interface
        if (!exibirLogin()) {
            System.exit(0); // Encerra o programa se o login falhar
        }

        alunoController = new AlunoController();
        professorController = new ProfessorController();
        disciplinaController = new DisciplinaController();

        setTitle("Sistema de Gerenciamento Escolar");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //Criando a barra de menu
        JMenuBar menuBar = new JMenuBar();

        //criando o menu
        JMenu cadastro = new JMenu("Cadastrar");

        // criando os itens do menu
        JMenuItem cadastrarAluno = new JMenuItem("Cadastrar Aluno");
        JMenuItem cadastrarProfessor = new JMenuItem("Cadastrar Professor");

        // criando açoes do menu
        cadastrarAluno.addActionListener(e -> adicionarAluno());
        cadastrarProfessor.addActionListener(e -> adicionarProfessor());

        cadastro.add(cadastrarAluno);
        cadastro.add(cadastrarProfessor);

        //adicionando menus a barra de menu
        menuBar.add(cadastro);

        //definindo a barra de menu na janela
        setJMenuBar(menuBar);

         JTabbedPane tabbedPane = new JTabbedPane();

       /* public void abrirJanelaListaAlunos() {
            JFrame janelaAlunos = new JFrame("Lista de Alunos");
            janelaAlunos.setSize(800, 600);
        }*/ // ideia de abrir outra janela apenas pra listar o
        // s alunos ou professores
        // Painel Alunos
        JPanel painelAlunos = new JPanel(new BorderLayout());

        modeloTabelaAlunos = new DefaultTableModel(
                new String[]{"Matrícula", "Nome", "Idade", "Série", "Turma"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaAlunos = new JTable(modeloTabelaAlunos);
        JScrollPane scrollAlunos = new JScrollPane(tabelaAlunos);
        painelAlunos.add(scrollAlunos, BorderLayout.CENTER);

        JPanel painelBotoesAlunos = new JPanel();
        JButton btnAdicionarAluno = new JButton("Adicionar");
        JButton btnEditarAluno = new JButton("Editar");
        JButton btnExcluirAluno = new JButton("Excluir");
        painelBotoesAlunos.add(btnAdicionarAluno);
        painelBotoesAlunos.add(btnEditarAluno);
        painelBotoesAlunos.add(btnExcluirAluno);
        painelAlunos.add(painelBotoesAlunos, BorderLayout.SOUTH);

         tabbedPane.addTab("Alunos", painelAlunos);

        // Painel Professores
        JPanel painelProfessores = new JPanel(new BorderLayout());

        modeloTabelaProfessores = new DefaultTableModel(
                new String[]{"Matrícula", "Nome", "Disciplinas", "Séries Atendidas"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaProfessores = new JTable(modeloTabelaProfessores);
        JScrollPane scrollProfessores = new JScrollPane(tabelaProfessores);
        painelProfessores.add(scrollProfessores, BorderLayout.CENTER);

        JPanel painelBotoesProfessores = new JPanel();
        JButton btnAdicionarProfessor = new JButton("Adicionar");
        JButton btnEditarProfessor = new JButton("Editar");
        JButton btnExcluirProfessor = new JButton("Excluir");
        painelBotoesProfessores.add(btnAdicionarProfessor);
        painelBotoesProfessores.add(btnEditarProfessor);
        painelBotoesProfessores.add(btnExcluirProfessor);
        painelProfessores.add(painelBotoesProfessores, BorderLayout.SOUTH);

        tabbedPane.addTab("Professores", painelProfessores);

         add(tabbedPane, BorderLayout.CENTER);

        // Ações dos botões Alunos
        btnAdicionarAluno.addActionListener(e -> adicionarAluno());
        btnEditarAluno.addActionListener(e -> editarAluno());
        btnExcluirAluno.addActionListener(e -> excluirAluno());

        // Ações dos botões Professores
        btnAdicionarProfessor.addActionListener(e -> adicionarProfessor());
        btnEditarProfessor.addActionListener(e -> editarProfessor());
        btnExcluirProfessor.addActionListener(e -> excluirProfessor());

        atualizaTabelaAlunos();
        atualizaTabelaProfessores();

        setVisible(true);
    }

    private boolean exibirLogin() {
        LoginDialog loginDialog = new LoginDialog(this);
        loginDialog.setVisible(true);
        return loginDialog.isAutenticado();
    }

    private void atualizaTabelaAlunos() {
        modeloTabelaAlunos.setRowCount(0);
        List<Aluno> alunos = alunoController.listarAlunos();
        for (Aluno a : alunos) {
            modeloTabelaAlunos.addRow(new Object[]{
                    a.getMatricula(), a.getNome(), a.getIdade(), a.getSerie(), a.getTurma()
            });
        }
    }

    private void adicionarAluno() {
        JTextField tfMatricula = new JTextField();
        JTextField tfNome = new JTextField();
        JTextField tfIdade = new JTextField();
        JComboBox cbSerie = new JComboBox(new String[]{"1ºA", "1º B", "1º C", "2ºA","2ºB","2ºC", "3ºA", "3ºB", "3ºC"});
        JTextField tfTurma = new JTextField();
        JComboBox<String> cbGenero = new JComboBox<>(new String[]{"Masculino", "Feminino", "Outro"});

        Object[] campos = {
                "Matrícula:", tfMatricula,
                "Nome:", tfNome,
                "Idade:", tfIdade,
                "Série:", cbSerie,
                "Turma:", tfTurma,
                "Gênero: ", cbGenero
        };

        int resultado = JOptionPane.showConfirmDialog(this, campos, "Adicionar Aluno", JOptionPane.OK_CANCEL_OPTION);
        if (resultado == JOptionPane.OK_OPTION) {
            String matricula = tfMatricula.getText().trim();
            String nome = tfNome.getText().trim();
            String idadeStr = tfIdade.getText().trim();
            String serie = (String) cbSerie.getSelectedItem();
            String turma = tfTurma.getText().trim();
            String genero = (String) cbGenero.getSelectedItem();

            if (matricula.isEmpty() || nome.isEmpty() || idadeStr.isEmpty() || serie.isEmpty() || turma.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int idade;
            try {
                idade = Integer.parseInt(idadeStr);
                if (idade < 5 || idade > 20) {
                    JOptionPane.showMessageDialog(this, "Idade deve ser entre 5 e 20 anos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Idade inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // delimitando a matricula em 7 digitos
            if (!matricula.matches("\\d{7}")) {
                JOptionPane.showMessageDialog(this, "A matrícula deve contar 7 digitos numéricos", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (alunoController.buscarAluno(matricula) != null) {
                JOptionPane.showMessageDialog(this, "Matrícula já cadastrada.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Aluno aluno = new Aluno(nome, matricula, idade, serie, turma, genero);
            alunoController.adicionarAluno(aluno);
            atualizaTabelaAlunos();
        }
    }

    private void editarAluno() {
        int linha = tabelaAlunos.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String matriculaOriginal = (String) modeloTabelaAlunos.getValueAt(linha, 0);
        Aluno aluno = alunoController.buscarAluno(matriculaOriginal);
        if (aluno == null) {
            JOptionPane.showMessageDialog(this, "Aluno não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JTextField tfMatricula = new JTextField(aluno.getMatricula());
        JTextField tfNome = new JTextField(aluno.getNome());
        JTextField tfIdade = new JTextField(String.valueOf(aluno.getIdade()));
        JComboBox cbSerie = new JComboBox(new String[]{"1ºA", "1º B", "1º C", "2ºA","2ºB","2ºC", "3ºA", "3ºB", "3ºC"});
        JTextField tfTurma = new JTextField(aluno.getTurma());
        JComboBox<String> cbGenero = new JComboBox<>(new String[]{"Masculino", "Feminino", "Outro"});
        cbGenero.setSelectedItem(aluno.getGenero());

        Object[] campos = {
                "Matrícula:", tfMatricula,
                "Nome:", tfNome,
                "Idade:", tfIdade,
                "Série:", cbSerie,
                "Turma:", tfTurma,
                "Genero", cbGenero
        };

        int resultado = JOptionPane.showConfirmDialog(this, campos, "Editar Aluno", JOptionPane.OK_CANCEL_OPTION);
        if (resultado == JOptionPane.OK_OPTION) {
            String matricula = tfMatricula.getText().trim();
            String nome = tfNome.getText().trim();
            String idadeStr = tfIdade.getText().trim();
            String serie = (String) cbSerie.getSelectedItem();
            String turma = tfTurma.getText().trim();
            String genero = (String) cbGenero.getSelectedItem();

            if (matricula.isEmpty() || nome.isEmpty() || idadeStr.isEmpty() || serie.isEmpty() || turma.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int idade;
            try {
                idade = Integer.parseInt(idadeStr);
                if (idade < 5 || idade > 25) {
                    JOptionPane.showMessageDialog(this, "Idade deve ser entre 5 e 25 anos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Idade inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // delimitando a matricula em 7 digitos
            if (!matricula.matches("\\d{7}")) {
                JOptionPane.showMessageDialog(this, "A matrícula deve contar 7 digitos numéricos", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!matricula.equals(matriculaOriginal) && alunoController.buscarAluno(matricula) != null) {
                JOptionPane.showMessageDialog(this, "M atrícula já cadastrada.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Aluno alunoAtualizado = new Aluno(nome, matricula, idade, serie, turma, genero);
            alunoController.atualizarAluno(matriculaOriginal, alunoAtualizado);
            atualizaTabelaAlunos();
        }
    }

    private void excluirAluno() {
        int linha = tabelaAlunos.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um aluno para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String matricula = (String) modeloTabelaAlunos.getValueAt(linha, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Confirma exclusão do aluno?", "Excluir", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            alunoController.removerAluno(matricula);
            atualizaTabelaAlunos();
        }
    }

    private void atualizaTabelaProfessores() {
        modeloTabelaProfessores.setRowCount(0);
        List<Professor> professores = professorController.listarProfessores();
        for (Professor p : professores) {
            modeloTabelaProfessores.addRow(new Object[]{
                    p.getMatricula(),
                    p.getNome(),
                    p.getDisciplinas(),
                    p.getSeriesAtendidas()
            });
        }
    }

    private void adicionarProfessor() {
        JTextField tfMatricula = new JTextField();
        JTextField tfNome = new JTextField();
        JComboBox cbDisciplinas = new JComboBox(new String[]{"Lingua Portuguesa", "Matemática", "Biologia", "Física", "Ed. Física"});
        JComboBox cbSeriesAtendidas = new JComboBox(new String[]{"1ºA", "1º B", "1º C", "2ºA","2ºB","2ºC", "3ºA", "3ºB", "3ºC"});

        Object[] campos = {
                "Matrícula:", tfMatricula,
                "Nome:", tfNome,
                "Disciplinas:", cbDisciplinas,
                "Séries Atendidas (separadas por vírgula):", cbSeriesAtendidas
        };

        int resultado = JOptionPane.showConfirmDialog(this, campos, "Adicionar Professor", JOptionPane.OK_CANCEL_OPTION);
        if (resultado == JOptionPane.OK_OPTION) {
            String matricula = tfMatricula.getText().trim();
            String nome = tfNome.getText().trim();
            String disciplinasStr = (String) cbDisciplinas.getSelectedItem();
            String seriesStr = (String) cbSeriesAtendidas.getSelectedItem();

            if (matricula.isEmpty() || nome.isEmpty() || disciplinasStr.isEmpty() || seriesStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (professorController.buscarProfessor(matricula) != null) {
                JOptionPane.showMessageDialog(this, "Matrícula já cadastrada.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            List<String> disciplinas = List.of(disciplinasStr.split("\\s*,\\s*"));
            List<String> series = List.of(seriesStr.split("\\s*,\\s*"));

            Professor professor = new Professor(nome, matricula, disciplinas, series);
            professorController.adicionarProfessor(professor);
            atualizaTabelaProfessores();
        }
    }

    private void editarProfessor() {
        int linha = tabelaProfessores.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um professor para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String matriculaOriginal = (String) modeloTabelaProfessores.getValueAt(linha, 0);
        Professor professor = professorController.buscarProfessor(matriculaOriginal);
        if (professor == null) {
            JOptionPane.showMessageDialog(this, "Professor não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTextField tfMatricula = new JTextField(professor.getMatricula());
        JTextField tfNome = new JTextField(professor.getNome());
        JComboBox cbDisciplinas = new JComboBox(new String[]{"Lingua Portuguesa", "Matemática", "Biologia", "Física", "Ed. Física"});
        JComboBox cbSeriesAtendidas = new JComboBox(new String[]{"1ºA", "1º B", "1º C", "2ºA","2ºB","2ºC", "3ºA", "3ºB", "3ºC"});

        Object[] campos = {
                "Matrícula:", tfMatricula,
                "Nome:", tfNome,
                "Disciplinas:", cbDisciplinas,
                "Séries Atendidas:", cbSeriesAtendidas
        };

        int resultado = JOptionPane.showConfirmDialog(this, campos, "Editar Professor", JOptionPane.OK_CANCEL_OPTION);
        if (resultado == JOptionPane.OK_OPTION) {
            String matricula = tfMatricula.getText().trim();
            String nome = tfNome.getText().trim();
            String disciplinas = (String) cbDisciplinas.getSelectedItem();
            String seriesStr = (String) cbSeriesAtendidas.getSelectedItem();

            if (matricula.isEmpty() || nome.isEmpty() || disciplinas.isEmpty() || seriesStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!matricula.equals(matriculaOriginal) && professorController.buscarProfessor(matricula) != null) {
                JOptionPane.showMessageDialog(this, "Matrícula já cadastrada.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            List<String> cbdisciplinas = List.of(disciplinas.split("\\s*,\\s*"));
            List<String> series = List.of(seriesStr.split("\\s*,\\s*"));

            Professor professorAtualizado = new Professor(nome, matricula, cbdisciplinas, series);
            professorController.atualizarProfessor(matriculaOriginal, professorAtualizado);
            atualizaTabelaProfessores();
        }
    }

    private void excluirProfessor() {
        int linha = tabelaProfessores.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um professor para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String matricula = (String) modeloTabelaProfessores.getValueAt(linha, 0);
        int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir este professor?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
        if (confirmacao == JOptionPane.YES_OPTION) {
            professorController.removerProfessor(matricula);
            atualizaTabelaProfessores();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EscolaSystem::new);
    }
}

class LoginDialog extends JDialog {

    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private boolean autenticado = false;

    public LoginDialog(Frame parent) {
        super(parent, "Gerenciamento Escolar", true);
        setLayout(null);
        setSize(600, 400);
        setLocationRelativeTo(null);// Centraliza o diálogo na tela


        JLabel lblUsuario = new JLabel("Usuário");
        lblUsuario.setBounds(200, 70, 80, 30); // Ajustei a posição vertical
        add(lblUsuario);
        txtUsuario = new JTextField();
        txtUsuario.setBounds(200, 100, 200, 30); // Ajustei a posição horizontal
        add(txtUsuario);
        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setBounds(200, 130, 80, 30); // Ajustei a posição vertical
        add(lblSenha);
        txtSenha = new JPasswordField();
        txtSenha.setBounds(200, 160, 200, 30); // Ajustei a posição horizontal
        add(txtSenha);
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(200, 200, 200, 30); // Ajustei a posição horizontal
        add(btnEntrar);

        btnEntrar.addActionListener(e -> {
            String usuario = txtUsuario.getText();

            String senha = new String(txtSenha.getPassword());

            if (validarLogin(usuario, senha)) {
                autenticado = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(LoginDialog.this, "Usuário ou senha inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private boolean validarLogin(String usuario, String senha) {
        // Validação Simples
        return "admin".equals(usuario) && "admin".equals(senha);
    }

    public boolean isAutenticado() {
        return autenticado;
    }
}