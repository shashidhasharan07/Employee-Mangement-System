package ems;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class SingleEmployee extends JFrame {
    private JFrame parent;
    public SingleEmployee(JFrame parent) {
        this.parent = parent;
        setTitle("EMPLOYEE DETAILS");
        setSize(420, 220);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8,8,8,8);

        c.gridx=0; c.gridy=0; add(new JLabel("EMPLOYEE ID:"), c);
        JTextField idField = new JTextField(10);
        c.gridx=1; add(idField, c);

        JButton btnShow = new JButton("Show");
        c.gridx=0; c.gridy=1; c.gridwidth=2; add(btnShow, c);

        JTextArea result = new JTextArea(5,30);
        result.setEditable(false);
        c.gridy=2; add(new JScrollPane(result), c);

        JButton back = new JButton("Back");
        c.gridy=3; add(back, c);

        back.addActionListener(e -> {
            this.dispose(); parent.setVisible(true);
        });

        btnShow.addActionListener(e -> {
            String sId = idField.getText().trim();
            if (sId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter an ID");
                return;
            }
            try {
                int id = Integer.parseInt(sId);
                String sql = "SELECT id,name,salary,department,position FROM employee WHERE id=?";
                try (Connection con = DBUtil.getConnection();
                     PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, id);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            String out = String.format("ID: %d\nName: %s\nSalary: %.2f\nDepartment: %s\nPosition: %s",
                                    rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getDouble("salary"),
                                    rs.getString("department"),
                                    rs.getString("position"));
                            result.setText(out);
                        } else {
                            result.setText("Employee not found.");
                        }
                    }
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Enter a numeric ID");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }
}
