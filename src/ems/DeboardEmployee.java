package ems;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeboardEmployee extends JFrame {
    private JFrame parent;
    public DeboardEmployee(JFrame parent) {
        this.parent = parent;
        setTitle("Deboard Employee");
        setSize(360,180);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints(); c.insets = new Insets(6,6,6,6);

        c.gridx=0; c.gridy=0; add(new JLabel("Employee ID to remove:"), c);
        JTextField idField = new JTextField(8); c.gridx=1; add(idField,c);

        JButton confirm = new JButton("Confirm Deboard");
        JButton back = new JButton("Back");
        JPanel p = new JPanel(); p.add(confirm); p.add(back);
        c.gridx=0; c.gridy=1; c.gridwidth=2; add(p,c);

        back.addActionListener(e -> { this.dispose(); parent.setVisible(true); });

        confirm.addActionListener(e -> {
            String txt = idField.getText().trim();
            if (txt.isEmpty()) { JOptionPane.showMessageDialog(this, "Enter ID"); return; }
            try {
                int id = Integer.parseInt(txt);
                int option = JOptionPane.showConfirmDialog(this, "Are you sure to delete employee id=" + id + "?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (option != JOptionPane.YES_OPTION) return;

                String sql = "DELETE FROM employee WHERE id=?";
                try (Connection con = DBUtil.getConnection();
                     PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, id);
                    int rows = ps.executeUpdate();
                    if (rows > 0) {
                        JOptionPane.showMessageDialog(this, "DEBOARD SUCCESSFul");
                        idField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "Employee not found");
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
