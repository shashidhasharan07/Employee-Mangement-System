package ems;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Home extends JFrame {
    public Home() {
        setTitle("EMS - Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 360);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(12, 12, 12, 12);

        JLabel title = new JLabel("EMS");
        title.setFont(new Font("Arial", Font.BOLD, 36));
        c.gridwidth = 2;
        c.gridx = 0; c.gridy = 0;
        add(title, c);

        c.gridwidth = 1;
        JButton btnViewAll = new JButton("VIEW ALL Employees");
        JButton btnViewSingle = new JButton("VIEW Employee");
        JButton btnAdd = new JButton("ADD Employee");
        JButton btnDelete = new JButton("Delete Employee");
        JButton btnUpdate = new JButton("Update Employee");

        c.gridx = 0; c.gridy = 1;
        add(btnViewAll, c);
        c.gridx = 1; c.gridy = 1;
        add(btnViewSingle, c);

        c.gridx = 0; c.gridy = 2;
        add(btnAdd, c);
        c.gridx = 1; c.gridy = 2;
        add(btnDelete, c);

        c.gridx = 0; c.gridy = 3; c.gridwidth = 2;
        add(btnUpdate, c);

        // actions
        btnViewAll.addActionListener((ActionEvent e) -> {
            new DisplayAll(this).setVisible(true);
            this.setVisible(false);
        });
        btnViewSingle.addActionListener(e -> {
            new SingleEmployee(this).setVisible(true);
            this.setVisible(false);
        });
        btnAdd.addActionListener(e -> {
            new OnboardEmployee(this).setVisible(true);
            this.setVisible(false);
        });
        btnDelete.addActionListener(e -> {
            new DeboardEmployee(this).setVisible(true);
            this.setVisible(false);
        });
        btnUpdate.addActionListener(e -> {
            new UpdateEmployee(this).setVisible(true);
            this.setVisible(false);
        });


        setVisible(true);
    }

    public static void main(String[] args) {
        // set simple look & feel
        SwingUtilities.invokeLater(() -> new Home());
    }
}
