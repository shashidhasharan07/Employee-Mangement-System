package ems;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class DisplayAll extends JFrame {
    private JFrame parent;

    public DisplayAll(JFrame parent) {
        this.parent = parent;
        setTitle("LIST OF ALL EMPLOYEES");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        String[] columns = {"ID","Name","Salary","Department","Position"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        add(sp, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        JButton back = new JButton("BACK");
        bottom.add(back);
        add(bottom, BorderLayout.SOUTH);

        back.addActionListener(e -> {
            this.dispose();
            parent.setVisible(true);
        });

        loadData(model);
    }

    private void loadData(DefaultTableModel model) {
        String sql = "SELECT id,name,salary,department,position FROM employee";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Object[] row = {
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("salary"),
                        rs.getString("department"),
                        rs.getString("position")
                };
                model.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading employees: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

