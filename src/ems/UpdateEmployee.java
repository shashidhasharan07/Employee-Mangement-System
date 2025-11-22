package ems;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateEmployee extends JFrame {
    // UI fields (so they are visible to both load and update handlers)
    private JTextField idField = new JTextField(6);
    private JTextField name = new JTextField(20);
    private JTextField salary = new JTextField(10);
    private JTextField dept = new JTextField(15);
    private JTextField pos = new JTextField(15);

    public UpdateEmployee(JFrame parent) {
        super("Update Employee");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(420, 320);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints(); c.insets = new Insets(6,6,6,6);

        c.gridx = 0; c.gridy = 0; add(new JLabel("ID:"), c);
        c.gridx = 1; add(idField, c);
        JButton load = new JButton("Load");
        c.gridx = 2; add(load, c);

        c.gridx = 0; c.gridy = 1; add(new JLabel("Name:"), c);
        c.gridx = 1; c.gridwidth = 2; add(name, c);

        c.gridx = 0; c.gridy = 2; c.gridwidth = 1; add(new JLabel("Salary:"), c);
        c.gridx = 1; c.gridwidth = 2; add(salary, c);

        c.gridx = 0; c.gridy = 3; c.gridwidth = 1; add(new JLabel("Department:"), c);
        c.gridx = 1; c.gridwidth = 2; add(dept, c);

        c.gridx = 0; c.gridy = 4; c.gridwidth = 1; add(new JLabel("Position:"), c);
        c.gridx = 1; c.gridwidth = 2; add(pos, c);

        JButton update = new JButton("Update");
        JButton back = new JButton("Back");
        JPanel p = new JPanel(); p.add(update); p.add(back);
        c.gridx = 0; c.gridy = 5; c.gridwidth = 3; add(p, c);

        // Back button behaviour
        back.addActionListener(e -> {
            this.dispose();
            parent.setVisible(true);
        });

        // Load button: populate fields from DB
        load.addActionListener(e -> {
            String t = idField.getText().trim();
            if (t.isEmpty()) { JOptionPane.showMessageDialog(this, "Enter ID"); return; }
            int id;
            try {
                id = Integer.parseInt(t);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Enter numeric ID"); return;
            }

            String sql = "SELECT name,salary,department,position FROM employee WHERE id=?";
            try (Connection con = DBUtil.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        name.setText(rs.getString("name"));
                        salary.setText(String.valueOf(rs.getDouble("salary")));
                        dept.setText(rs.getString("department"));
                        pos.setText(rs.getString("position"));
                    } else {
                        JOptionPane.showMessageDialog(this, "Employee not found");
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        // UPDATE button: validated and parameterized update
        update.addActionListener(e -> {
            String t = idField.getText().trim();
            if (t.isEmpty()) { JOptionPane.showMessageDialog(this, "Enter ID"); return; }
            int id;
            try { id = Integer.parseInt(t); } catch (NumberFormatException nfe) { JOptionPane.showMessageDialog(this,"Enter numeric ID"); return; }

            String nm = name.getText().trim();
            String salTxt = salary.getText().trim();
            String d = dept.getText().trim();
            String po = pos.getText().trim();

            if (nm.isEmpty()) { JOptionPane.showMessageDialog(this, "Enter Name"); return; }
            if (salTxt.isEmpty()) { JOptionPane.showMessageDialog(this, "Enter Salary"); return; }

            double sal;
            try { sal = Double.parseDouble(salTxt); } catch (NumberFormatException nfe) { JOptionPane.showMessageDialog(this, "Enter numeric salary"); return; }

            String sql = "UPDATE employee SET name=?, salary=?, department=?, position=? WHERE id=?";
            // Debug (optional) - will print to Eclipse console
            System.out.println("SQL: " + sql);
            System.out.println("params => name=" + nm + ", salary=" + sal + ", dept=" + d + ", pos=" + po + ", id=" + id);

            try (Connection con = DBUtil.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, nm);
                ps.setDouble(2, sal);
                ps.setString(3, d);
                ps.setString(4, po);
                ps.setInt(5, id);

                int rows = ps.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(this, "UPDATE SUCCESSFul");
                } else {
                    JOptionPane.showMessageDialog(this, "No employee found with this ID");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "ERROR: " + ex.getMessage());
            }
        });
    }
}
