package GUI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import Compiler.Addresses.AddressProvider;
import Compiler.Register.Register;
import Compiler.Register.RegisterProvider;

public class GalaxyCompilerV2 extends JFrame {

    private JTextArea editPanel;
    private JTextArea registerPanel;
    private JTextArea outputConsole;
    private JTable registerTable;
    private AddressProvider addressProvider;
    private RegisterProvider registerProvider;

    public GalaxyCompilerV2(AddressProvider addressProvider, RegisterProvider registerProvider) {
        this.addressProvider = new AddressProvider();
        this.registerProvider = new RegisterProvider(addressProvider);
        setTitle("MIPS GALAXY V.0.0.1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Main Panel with border layout and stuff
        JPanel mainPanel = new JPanel(new BorderLayout());
        //Assembly code panel and register value panel
        JPanel editorPanel = new JPanel(new GridLayout(1, 2));

        // Edit Panel for the code or simply code panel
        editPanel = new JTextArea();
        editPanel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
        JScrollPane editPanelScroll = new JScrollPane(editPanel);
        editorPanel.add(editPanelScroll);

        //Register value panel to enter the register values etc
        registerPanel = new JTextArea();
        registerPanel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
        JScrollPane registerPanelScroll = new JScrollPane(registerPanel);
        editorPanel.add(registerPanelScroll);

        mainPanel.add(editorPanel, BorderLayout.CENTER);

        // Register table displaying registers, their numbers and values
        String[] columnNames = {"Index", "Register", "Value"};
        Object[][] data = new Object[32][3];

        refreshDataTable(data);

        registerTable = new JTable(data, columnNames);
        JScrollPane registerTableScroll = new JScrollPane(registerTable);
        mainPanel.add(registerTableScroll, BorderLayout.EAST);

        // Console output for the .asm code
        outputConsole = new JTextArea(10, 40); // Increase dimensions
        outputConsole.setEditable(false);
        outputConsole.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane outputConsoleScroll = new JScrollPane(outputConsole);
        mainPanel.add(outputConsoleScroll, BorderLayout.SOUTH);

        // Menu Bar and Buttons
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        menuBar.add(fileMenu);

        JButton executeButton = new JButton("Execute");
        executeButton.addActionListener(e -> executeCode());

        JButton clearOutputButton = new JButton("Clear Output");
        clearOutputButton.addActionListener(e -> outputConsole.setText(""));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(executeButton);
        buttonPanel.add(clearOutputButton);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(menuBar, BorderLayout.NORTH);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        // File chooser for open and save operations
        openItem.addActionListener(e -> openFile());
        saveItem.addActionListener(e -> saveFile());
    }

    public static class PopupWindow extends JWindow {
        private JProgressBar progressBar;
        private JLabel label;
        private Timer timer;
        private int progress = 0;
        private GalaxyCompilerV2 GUI;

        public PopupWindow(GalaxyCompilerV2 GUI) {
            this.GUI = GUI;
            setSize(400, 200);
            setLocationRelativeTo(null);
            setVisible(true);
            initComponents();
            startTimer();
        }

        private void initComponents() {
            //Panel to hold the stuff
            JPanel popupPanel = new JPanel(new BorderLayout());

            label = new JLabel("Loading GALAXY V.0.0.1......", SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 18)); // Change the font and size here
            label.setForeground(Color.BLUE); // Change the font color here
            popupPanel.add(label, BorderLayout.NORTH);

            //Le Progress Bar
            progressBar = new JProgressBar();
            progressBar.setStringPainted(true);
            popupPanel.add(progressBar, BorderLayout.CENTER);

            ImageIcon imageIcon = new ImageIcon("galaxy-stars-space-digital-art-4k-wallpaper-pixground.jpg");
            Image image = imageIcon.getImage().getScaledInstance(400, 200, Image.SCALE_SMOOTH); // Scale the image to fit the popup window dimensions
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            popupPanel.add(imageLabel, BorderLayout.SOUTH);

            setContentPane(popupPanel);
        }

        private void startTimer() {
            timer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    progress += 10;
                    progressBar.setValue(progress);
                    label.setText("Loading GALAXY..." + progress + "%");
                    if (progress >= 100) {
                        timer.stop();
                        dispose();
                        showMainGUI();
                    }
                }
            });

            timer.start();
        }

        private void showMainGUI() {
            GUI.setVisible(true);
        }
    }

    public void refreshDataTable(Object[][] data) {
        Register[] registerArray = registerProvider.getRegisterArray();

        for (int i = 0; i < registerArray.length; i += 4) {
            data[i / 4][0] = i / 4;
            data[i / 4][1] = registerArray[i].getRegisterHumanName();
            data[i / 4][2] = registerArray[i].getValue();
            System.out.println(registerArray[i].getRegisterHumanName());
        }
    }

    public static void main(String[] args) {
        AddressProvider addressProvider = new AddressProvider();
        RegisterProvider registerProvider = new RegisterProvider(addressProvider);
        GalaxyCompilerV2 GUI = new GalaxyCompilerV2(addressProvider, registerProvider);

        SwingUtilities.invokeLater(() -> {
            new PopupWindow(GUI);
        });
    }

    //Function to open a .asm file
    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Assembly File", "asm");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                StringBuilder contents = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    contents.append(line).append("\n");
                }
                reader.close();
                editPanel.setText(contents.toString());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    //Function to save file to .asm
    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Assembly File", "asm");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (!selectedFile.getName().endsWith(".asm")) {
                selectedFile = new File(selectedFile.getAbsolutePath() + ".asm");
            }
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
                writer.write(editPanel.getText());
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void executeCode() {
        String code = editPanel.getText();
        try {
            Process process = Runtime.getRuntime().exec("mips-compiler");
            BufferedReader inputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            // Write code to process's input stream
            process.getOutputStream().write(code.getBytes());
            process.getOutputStream().flush();

            // Read output and error streams
            String line;
            StringBuilder output = new StringBuilder();
            StringBuilder error = new StringBuilder();
            while ((line = inputReader.readLine()) != null) {
                output.append(line).append("\n");
                updateRegisterTable(line);
            }
            while ((line = errorReader.readLine()) != null) {
                error.append(line).append("\n");
            }

            // Update console output
            outputConsole.setText(output.toString());

            // Wait for the process to complete
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                outputConsole.append("Execution failed with exit code " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void updateRegisterTable(String line) {
        if (line.startsWith("$")) {
            String[] parts = line.split("[=,\\s]+");
            String register = parts[0];
            int index = Integer.parseInt(parts[1]);
            int value = Integer.parseInt(parts[3], 16);

            for (int i = 0; i < registerTable.getRowCount(); i++) {
                if (registerTable.getValueAt(i, 1).equals(register) && (int) registerTable.getValueAt(i, 0) == index) {
                    registerTable.setValueAt(value, i, 2);
                    break;
                }
            }
        }
    }
}
