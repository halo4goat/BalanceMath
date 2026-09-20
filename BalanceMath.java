package balancemath;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.math.BigInteger;

public class BalanceMath extends JFrame {
    private final JTextField a = new JTextField("12");
    private final JTextField b = new JTextField("8");
    private final JTextField c = new JTextField("5");
    private final JTextArea result = new JTextArea();

    public BalanceMath() {
        super("BalanceMath");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 520);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout(12, 12));
        panel.setBorder(new EmptyBorder(18, 18, 18, 18));
        setContentPane(panel);

        JLabel title = new JLabel("BalanceMath");
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        panel.add(title, BorderLayout.NORTH);

        JPanel top = new JPanel(new GridLayout(2, 3, 10, 8));
        top.add(new JLabel("A"));
        top.add(new JLabel("B"));
        top.add(new JLabel("C"));
        top.add(a);
        top.add(b);
        top.add(c);

        JButton calculate = new JButton("Calculate");
        JButton properties = new JButton("Test properties");
        JButton clear = new JButton("Clear");

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        buttons.add(calculate);
        buttons.add(properties);
        buttons.add(clear);

        JPanel controls = new JPanel(new BorderLayout(8, 8));
        controls.add(top, BorderLayout.CENTER);
        controls.add(buttons, BorderLayout.SOUTH);
        panel.add(controls, BorderLayout.CENTER);

        result.setEditable(false);
        result.setFont(new Font("Monospaced", Font.PLAIN, 14));
        result.setLineWrap(true);
        result.setWrapStyleWord(true);
        result.setText("Enter some numbers and press Calculate.\n\n" +
                "Rule: A ◇ B = A + B + |A - B| / gcd(A, B)");
        panel.add(new JScrollPane(result), BorderLayout.SOUTH);

        calculate.addActionListener(e -> calculate());
        properties.addActionListener(e -> testProperties());
        clear.addActionListener(e -> result.setText(""));
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
            BigInteger xy = balance(x, y);
            BigInteger yx = balance(y, x);

            result.setText(
                    x + " ◇ " + y + " = " + xy + "\n" +
                    y + " ◇ " + x + " = " + yx + "\n\n" +
                    "Same result both ways: " + xy.equals(yx) + "\n\n" +
                    "Rule:\nA ◇ B = A + B + |A - B| / gcd(A, B)"
            );
        } catch (NumberFormatException ex) {
            showError();
        }
    }

    private void testProperties() {
        try {
            BigInteger x = read(a);
            BigInteger y = read(b);
            BigInteger z = read(c);

            BigInteger xy = balance(x, y);
            BigInteger left = balance(xy, z);
            BigInteger right = balance(x, balance(y, z));

            result.setText(
                    "A = " + x + ", B = " + y + ", C = " + z + "\n\n" +
                    "A ◇ B = " + xy + "\n\n" +
                    "(A ◇ B) ◇ C = " + left + "\n" +
                    "A ◇ (B ◇ C) = " + right + "\n\n" +
                    "Associative for these numbers: " + left.equals(right)
            );
        } catch (NumberFormatException ex) {
            showError();
        }
    }

    private void showError() {
        JOptionPane.showMessageDialog(
                this,
                "Please enter whole numbers.",
                "Input error",
                JOptionPane.WARNING_MESSAGE
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BalanceMath().setVisible(true));
    }
}
