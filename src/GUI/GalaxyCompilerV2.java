package GUI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
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

    public JButton executeButton;

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
        registerPanel=new JTextArea();
        registerPanel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
        JScrollPane registerPanelScroll= new JScrollPane(registerPanel);
        editorPanel.add(registerPanelScroll);

        mainPanel.add(editorPanel,BorderLayout.CENTER);


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

        executeButton = new JButton("Execute");
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

    public void refreshDataTable(Object[][] data) {
        Register[] registerArray = registerProvider.getRegisterArray();

        for(int i = 0; i < registerArray.length; i+=4) {
            data[i / 4][0] = i / 4;
            data[i / 4][1] = registerArray[i].getRegisterHumanName();
            data[i / 4][2] = registerArray[i].getValue();
            System.out.println(registerArray[i].getRegisterHumanName());
        }
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

    public String[] executeCode() {

        String code = editPanel.getText();
        int numberOfCommands=editPanel.getLineCount();

            String[] instructionMemory=code.split("\n");

            return instructionMemory;




//            Process process = Runtime.getRuntime().exec("mips-compiler");
//            BufferedReader inputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
//
//            // Write code to process's input stream
//            process.getOutputStream().write(code.getBytes());
//            process.getOutputStream().flush();
//
//            // Read output and error streams
//            String line;
//            StringBuilder output = new StringBuilder();
//            StringBuilder error = new StringBuilder();
//            while ((line = inputReader.readLine()) != null) {
//                output.append(line).append("\n");
//                updateRegisterTable(line);
//            }
//            while ((line = errorReader.readLine()) != null) {
//                error.append(line).append("\n");
//            }
//
//            // Update console output
//            outputConsole.setText(output.toString());

            // Wait for the process to complete
//            int exitCode = process.waitFor();
//            if (exitCode != 0) {
//                outputConsole.append("Execution failed with exit code " + exitCode);
//            }

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