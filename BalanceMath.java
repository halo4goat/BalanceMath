package balancemath;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.math.BigInteger;

public class BalanceMath extends JFrame {
    private final JTextField a = new JTextField("12");
    private final JTextField b = new JTextField("8");
    private final JLabel answer = new JLabel("—", SwingConstants.CENTER);

    public BalanceMath() {
        super("BalanceMath");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 320);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBorder(new EmptyBorder(25, 25, 25, 25));
        setContentPane(panel);

        JLabel title = new JLabel("BalanceMath", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        panel.add(title, BorderLayout.NORTH);

        JPanel inputs = new JPanel(new GridLayout(2, 2, 12, 10));
        inputs.add(new JLabel("A"));
        inputs.add(new JLabel("B"));
        inputs.add(a);
        inputs.add(b);
        panel.add(inputs, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout(10, 10));

        JButton calculate = new JButton("Calculate");
        bottom.add(calculate, BorderLayout.NORTH);

        answer.setFont(new Font("SansSerif", Font.BOLD, 34));
        bottom.add(answer, BorderLayout.CENTER);

        panel.add(bottom, BorderLayout.SOUTH);

        calculate.addActionListener(e -> calculate());
        a.addActionListener(e -> calculate());
        b.addActionListener(e -> calculate());
    }

    private BigInteger balance(BigInteger x, BigInteger y) {
        BigInteger gcd = x.abs().gcd(y.abs());
        if (gcd.equals(BigInteger.ZERO)) return x.add(y);
        return x.add(y).add(x.subtract(y).abs().divide(gcd));
    }

    private BigInteger read(JTextField field) {
        return new BigInteger(field.getText().trim());
    }

    private void calculate() {
        try {
            BigInteger x = read(a);
            BigInteger y = read(b);
            answer.setText(balance(x, y).toString());
        } catch (NumberFormatException ex) {
            answer.setText("?");
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter whole numbers.",
                    "Input error",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BalanceMath().setVisible(true));
    }
}
