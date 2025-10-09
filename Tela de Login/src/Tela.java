import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class Tela extends JFrame {
    // Componentes
    JLabel l1 = new JLabel("Usuário:");
    JTextField tf1 = new JTextField("");

    JLabel l2 = new JLabel("Senha:");
    JTextField tf2 = new JTextField("");

    JButton b1 = new JButton("Cadastrar");

    public static void main(String[] args) {
        Tela t1 = new Tela();
    }

    // Construtor da tela
    public Tela() {
        // Configurações da janela
        this.setBounds(200, 100, 330, 210); // posição e tamanho
        this.setLayout(null);               // layout manual
        this.setTitle("Tela de Login");     // título da janela
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // fechar ao clicar no X

        // Posições dos componentes
        l1.setBounds(40, 40, 80, 25);
        l2.setBounds(40, 70, 80, 25);
        tf1.setBounds(130, 40, 130, 25);
        tf2.setBounds(130, 70, 130, 25);
        b1.setBounds(100, 120, 100, 25);

        // Ação do botão
        b1.addActionListener(e -> {
            String usuario = tf1.getText();
            String senha = tf2.getText();

            // SQL para inserir no banco
            String sql = "INSERT INTO usuarios (usuario, senha) VALUES (?, ?)";

            try (Connection conn = Conexao.getConnection();
                 java.sql.PreparedStatement pst = conn.prepareStatement(sql)) {

                pst.setString(1, usuario);
                pst.setString(2, senha);

                int linhasAfetadas = pst.executeUpdate(); // executa o INSERT
                if (linhasAfetadas > 0) {
                    JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Falha ao cadastrar!");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
            }
        });

        // Adiciona os componentes à janela
        this.add(l1);
        this.add(l2);
        this.add(tf1);
        this.add(tf2);
        this.add(b1);

        // Deixa visível
        this.setVisible(true);
    }
}

