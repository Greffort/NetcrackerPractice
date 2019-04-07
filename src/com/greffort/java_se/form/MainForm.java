package com.greffort.java_se.form;

import com.greffort.java_se.buildings.Buildings;
import com.greffort.java_se.factory.DwellingFactory;
import com.greffort.java_se.factory.OfficeFactory;
import com.greffort.java_se.interfaces.Building;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileReader;

public class MainForm extends JFrame {

    private JPanel rootPanel;
    private JPanel viewPane;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private Building building;
    private JMenuBar menuBar = new JMenuBar();

    private MainForm() {
        setLocationRelativeTo(null);
        setSize(1000, 500);
        setContentPane(rootPanel);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        viewPane.setLayout(new BoxLayout(viewPane, BoxLayout.PAGE_AXIS));
        setMaximumSize(new Dimension(800, 500));
        setMinimumSize(new Dimension(800, 500));
        setPreferredSize(new Dimension(800, 500));

        JMenu menu1 = new JMenu("File");
        JMenu menu2 = new JMenu("Look&Feel");
        menuBar.add(menu1);
        menuBar.add(menu2);
        JMenuItem jMenuItem1 = new JMenuItem("Open dwelling…");
        JMenuItem jMenuItem2 = new JMenuItem("Open office building…");
        menu1.add(jMenuItem1);
        menu1.add(jMenuItem2);

        setJMenuBar(menuBar);

//region MenuItem File Listener
        jMenuItem1.addActionListener(e -> {
            Buildings.setBuildingFactory(new DwellingFactory());
            loadBuilding();
            generateElement(building);
        });
        jMenuItem2.addActionListener(e -> {
            Buildings.setBuildingFactory(new OfficeFactory());
            loadBuilding();
            generateElement(building);
        });
//endregion

//region MenuItem Look&Feel
        JMenuItem jMenuItem21 = new JMenuItem("Metal");
        JMenuItem jMenuItem22 = new JMenuItem("Nimbus");
        JMenuItem jMenuItem23 = new JMenuItem("CDE/Motif");
        JMenuItem jMenuItem24 = new JMenuItem("Windows");
        JMenuItem jMenuItem25 = new JMenuItem("Windows Classic");
        menu2.add(jMenuItem21);
        menu2.add(jMenuItem22);
        menu2.add(jMenuItem23);
        menu2.add(jMenuItem24);
        menu2.add(jMenuItem25);

        jMenuItem21.addActionListener(e -> setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel"));
        jMenuItem22.addActionListener(e -> setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"));
        jMenuItem23.addActionListener(e -> setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel"));
        jMenuItem24.addActionListener(e -> setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"));
        jMenuItem25.addActionListener(e -> setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel"));
//endregion

    }

    private void loadBuilding() {
        JFileChooser fileOpen = new JFileChooser();
        int ret = fileOpen.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileOpen.getSelectedFile();
                FileReader fileReader = new FileReader(file);
                this.building = Buildings.readBuilding(fileReader);
                assert building != null;
                this.textArea1.setText("Тип здания: " + building.getClass().getSimpleName() +
                        "\n" + "Количество этажей: " + building.getCountFloors() +
                        "\n" + "Общая площадь помещений: " + building.getTotalSquare());

            } catch (Exception x) {
                JOptionPane.showMessageDialog(rootPanel,
                        "Error",
                        "Inane warning",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void generateElement(Building building) {
        int index = 0;
        for (int i = 0; i < building.getCountFloors(); i++) {
            int finalI = i;
            JPanel jPanel = createJPanel(i);
            jPanel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    textArea2.setText("Номер этажа: " + finalI +
                            "\n" + "Количество помещений: " + building.getFloor(finalI).getCountSpace() +
                            "\n" + "Общая площадь помещений: " + building.getFloor(finalI).getTotalSquare());
                }

                //region other event
                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
//endregion
            });
            viewPane.add(jPanel);
            for (int j = 0; j < building.getFloor(i).getCountSpace(); j++) {
                index++;
                int finalJ = j;
                int finalIndex = index - 1;
                JButton jButton = createJButton(j);
                jButton.addActionListener(e -> textArea3.setText("Номер в здании: " + finalIndex +
                        "\n" + "Количество комнат: " + building.getFloor(finalI).getSpace(finalJ).getRoomCount() +
                        "\n" + "Площадь: " + building.getFloor(finalI).getSpace(finalJ).getSquare()));
                jPanel.add(jButton);
            }
        }
    }

    private JPanel createJPanel(int index) {
        JLabel jLabel = new JLabel();
        jLabel.setText("Этаж номер: " + index + " ");

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        jPanel.add(jLabel);
        jPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        return jPanel;
    }

    private static JButton createJButton(int index) {
        JButton jButton = new JButton();
        jButton.setSize(10, 10);
        jButton.setMaximumSize(new Dimension(10, 10));
        jButton.setText("# " + index);
        return jButton;
    }

    private void getMessageBox() {
        JOptionPane.showMessageDialog(rootPanel,
                "Error",
                "Inane warning",
                JOptionPane.WARNING_MESSAGE);
    }

    private void setLookAndFeel(String className) {
        try {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(rootPanel);
            SwingUtilities.updateComponentTreeUI(menuBar);
        } catch (Exception x) {
            getMessageBox();
        }
    }

    public static void main(String[] args) {
        new MainForm();
    }
}





