package balancemath;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.math.BigInteger;

public class BalanceMath extends JFrame {
    private final JTextField aField = new JTextField("12");
    private final JTextField bField = new JTextField("8");
    private final JTextField cField = new JTextField("5");
    private final JTextArea output = new JTextArea();

    public BalanceMath() {
        setTitle("BalanceMath");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(760, 560);
        setLocationRelativeTo(null);
        JPanel root = new JPanel(new BorderLayout(12, 12));
        root.setBorder(new EmptyBorder(18,18,18,18));
        setContentPane(root);

        JLabel title = new JLabel("BalanceMath");
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        root.add(title, BorderLayout.NORTH);

        JPanel inputs = new JPanel(new GridLayout(2, 6, 8, 8));
        inputs.add(new JLabel("A")); inputs.add(new JLabel("B")); inputs.add(new JLabel("C"));
        inputs.add(new JLabel("")); inputs.add(new JLabel("")); inputs.add(new JLabel(""));
        inputs.add(aField); inputs.add(bField); inputs.add(cField);

        JButton calculate = new JButton("Calculate");
        JButton test = new JButton("Test Properties");
        JButton clear = new JButton("Clear");
        inputs.add(calculate); inputs.add(test); inputs.add(clear);

        JPanel holder = new JPanel(new BorderLayout(8,8));
        holder.add(inputs, BorderLayout.NORTH);
        output.setEditable(false);
        output.setFont(new Font("Monospaced", Font.PLAIN, 15));
        output.setLineWrap(true);
        output.setWrapStyleWord(true);
        holder.add(new JScrollPane(output), BorderLayout.CENTER);
        root.add(holder, BorderLayout.CENTER);

        calculate.addActionListener(e -> calculate());
        test.addActionListener(e -> testProperties());
        clear.addActionListener(e -> output.setText(""));

        output.setText("BalanceMath uses:\n\nA ◇ B = A + B + |A - B| / gcd(A, B)\n\nEnter numbers and press Calculate.");
    }

    private BigInteger balance(BigInteger a, BigInteger b) {
        BigInteger g = a.abs().gcd(b.abs());
        if (g.equals(BigInteger.ZERO)) return a.add(b);
        return a.add(b).add(a.subtract(b).abs().divide(g));
    }

    private BigInteger read(JTextField f) { return new BigInteger(f.getText().trim()); }

    private void calculate() {
        try {
            BigInteger a = read(aField), b = read(bField);
            BigInteger ab = balance(a,b), ba = balance(b,a);
            output.setText("BALANCE OPERATION\n\n" +
                a + " ◇ " + b + " = " + ab + "\n" +
                b + " ◇ " + a + " = " + ba + "\n\n" +
                "Commutative: " + ab.equals(ba) + "\n\n" +
                "Formula:\nA ◇ B = A + B + |A - B| / gcd(A, B)");
        } catch (Exception ex) { showError(); }
    }

    private void testProperties() {
        try {
            BigInteger a = read(aField), b = read(bField), c = read(cField);
            BigInteger ab = balance(a,b), ba = balance(b,a);
            BigInteger left = balance(ab,c), right = balance(a,balance(b,c));
            output.setText("PROPERTY TEST\n\n" +
                "A = " + a + ", B = " + b + ", C = " + c + "\n\n" +
                "A ◇ B = " + ab + "\nB ◇ A = " + ba + "\n\n" +
                "Commutative? " + ab.equals(ba) + "\n\n" +
                "(A ◇ B) ◇ C = " + left + "\n" +
                "A ◇ (B ◇ C) = " + right + "\n\n" +
                "Associative for these numbers? " + left.equals(right));
        } catch (Exception ex) { showError(); }
    }

    private void showError() {
        JOptionPane.showMessageDialog(this, "Please enter valid whole numbers.", "Input error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BalanceMath().setVisible(true));
    }
}
