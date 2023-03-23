import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGUI extends JFrame implements ActionListener, ChangeListener {
    private JLabel welcomeLabel;
    private JTextArea textArea;
    private JTextField textField;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JMenu menu2;
    private JMenu menu1;
    private JMenuItem menuItem1;
    private JMenuItem menuItem2;
    private JMenuItem helpItem1;
    private JMenuItem helpItem2;
    private JSlider slider;

    public SimpleGUI() {
        super("Frame title");
        init();
    }

    private void init() {
        // setting up the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocation(300, 50);

        // create the MenuBar and menu components
        JMenuBar menuBar = new JMenuBar();
        menu1 = new JMenu("File");
        menuItem1 = new JMenuItem("Open");
        menuItem2 = new JMenuItem("Save as");
        menu1.add(menuItem1);
        menu1.add(menuItem2);
        menu2 = new JMenu("Help");
        helpItem1 = new JMenuItem("FAQ");
        helpItem2 = new JMenuItem("About");
        menu2.add(helpItem1);
        menu2.add(helpItem2);

        // add "File" and "Help" menus to the MenuBar
        menuBar.add(menu1);
        menuBar.add(menu2);

        // create the big text area located in the middle
        textArea = new JTextArea();

        // create welcome label
        welcomeLabel = new JLabel("Welcome to my GUI!");
        welcomeLabel.setFont(new Font("Helvetica", Font.BOLD, 20));

        // create slider and adjust settings
        slider = new JSlider(0, 40, 20);
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        // create a panel for organizing the label and slider
        JPanel sliderPanel = new JPanel();

        // add label and slider, in left-to-right order
        sliderPanel.add(welcomeLabel);
        sliderPanel.add(slider);

        // create the components at the bottom
        JLabel label = new JLabel("Enter Text");
        // accepts upto 10 characters
        textField = new JTextField(10);
        JButton sendButton = new JButton("Send");
        JButton resetButton = new JButton("Reset");

        // create checkboxes
        checkBox1 = new JCheckBox("Yes");
        checkBox1.setBounds(100, 100, 50, 50);
        checkBox2 = new JCheckBox("No", true);
        checkBox2.setBounds(100, 150, 50, 50);

        // create a panel for organizing the components at the bottom
        JPanel panel = new JPanel(); // a "panel" is not visible

        // add bottom components to the panel, in left-to-right order
        panel.add(label);
        panel.add(textField);
        panel.add(sendButton);
        panel.add(resetButton);
        panel.add(checkBox1);
        panel.add(checkBox2);

        // creating a third panel to place slider and bottom panels vertically
        // (allows two rows of UI elements to be displayed)
        JPanel combinedPanels = new JPanel();
        combinedPanels.setLayout(new GridLayout(2, 1));
        combinedPanels.add(sliderPanel, BorderLayout.NORTH);
        combinedPanels.add(panel, BorderLayout.SOUTH);

        // add the menu bar to the TOP of the frame, the big white text area
        // to the MIDDLE of the frame, and the "combinedPanels" (which has
        // the label, slider, text box, buttons, and checkboxes) at the BOTTOM
        add(menuBar, BorderLayout.NORTH);
        add(textArea, BorderLayout.CENTER);
        add(combinedPanels, BorderLayout.SOUTH);

        // Event Handling:
        menuItem1.addActionListener(this);
        menuItem2.addActionListener(this);
        helpItem1.addActionListener(this);
        helpItem2.addActionListener(this);
        sendButton.addActionListener(this);
        resetButton.addActionListener(this);

        slider.addChangeListener(this);

        // display the frame!
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();
        String text;
        if (source instanceof JButton) {
            text = ((JButton) source).getText();
        } else if (source instanceof JMenuItem) {
            text = ((JMenuItem) source).getText();
        } else {
            text = "ERROR!";
        }

        System.out.println(text);

        if (text.equals("Send")) {
            welcomeLabel.setText("Sent!");
            textArea.append(textField.getText());
            //textField.setText("");
        } else if (text.equals("Reset")) {
            welcomeLabel.setText("Reset!");
            textArea.setText("");
            textField.setText("");

            checkBox1.setSelected(false);
            checkBox2.setSelected(false);
        } else if (text.equals("Open") || text.equals("Save as")
                || text.equals("FAQ") || text.equals("About")) {

            textField.setText(text);
        }
    }

    public void stateChanged(ChangeEvent e) {
        String num = String.valueOf(((JSlider) e.getSource()).getValue());

        textArea.setText(num);
    }
}