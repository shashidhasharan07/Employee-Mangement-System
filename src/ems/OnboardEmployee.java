package ems;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OnboardEmployee extends JFrame {
    private JFrame parent;
    public OnboardEmployee(JFrame parent) {
        this.parent = parent;
        setTitle("Employee Onboarding");
        setSize(420,300);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6,6,6,6);
        c.anchor = GridBagConstraints.WEST;

        c.gridx=0; c.gridy=0; add(new JLabel("Name:"), c);
        JTextField name = new JTextField(20); c.gridx=1; add(name,c);

        c.gridx=0; c.gridy=1; add(new JLabel("Salary:"), c);
        JTextField salary = new JTextField(10); c.gridx=1; add(salary,c);

        c.gridx=0; c.gridy=2; add(new JLabel("Department:"), c);
        JTextField dept = new JTextField(15); c.gridx=1; add(dept,c);

        c.gridx=0; c.gridy=3; add(new JLabel("Position:"), c);
        JTextField pos = new JTextField(15); c.gridx=1; add(pos,c);

        JButton submit = new JButton("Submit");
        JButton back = new JButton("Back");
        JPanel p = new JPanel(); p.add(submit); p.add(back);
        c.gridx=0; c.gridy=4; c.gridwidth=2; add(p,c);

        back.addActionListener(e -> { this.dispose(); parent.setVisible(true); });

        submit.addActionListener(e -> {
            String n = name.getText().trim();
            String s = salary.getText().trim();
            String d = dept.getText().trim();
            String pa = pos.getText().trim();
            if (n.isEmpty()) { JOptionPane.showMessageDialog(this, "Provide name"); return; }

            double sal = 0;
            try { sal = Double.parseDouble(s); } catch (Exception ex) { JOptionPane.showMessageDialog(this,"Enter numeric salary"); return; }

            String sql = "INSERT INTO employee(name,salary,department,position) VALUES(?,?,?,?)";
            try (Connection con = DBUtil.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, n);
                ps.setDouble(2, sal);
                ps.setString(3, d);
                ps.setString(4, pa);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "ONBOARD SUCCESSFul");
                // clear fields
                name.setText(""); salary.setText(""); dept.setText(""); pos.setText("");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }
}
