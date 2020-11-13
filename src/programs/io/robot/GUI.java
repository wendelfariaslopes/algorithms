package programs.io.robot;




import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI extends JFrame
{

    private JMenuBar menuBar;
    private JButton btnCompute;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label6;
    private JLabel label7;
    private JTextField txtGrade;
    private JTextField txtGPA;
    private JTextField textfield11;
    private JTextField txtCourseCount;
    private JTextField txtUnits;
    private JTextField textfield9;
    private GPA studentGPA;
    //Constructor

    public GUI()
    {

        this.setTitle("GPA Calculator");
        this.setSize(317, 383);
        studentGPA = new GPA();
        //menu generate method
        generateMenu();
        this.setJMenuBar(menuBar);

        //pane with null layout
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(317, 383));
        contentPane.setBackground(new Color(192, 192, 192));


        btnCompute = new JButton();
        btnCompute.setBounds(144, 190, 90, 35);
        btnCompute.setBackground(new Color(214, 217, 223));
        btnCompute.setForeground(new Color(0, 0, 0));
        btnCompute.setEnabled(true);
        btnCompute.setFont(new Font("DejaVu Sans", 0, 12));
        btnCompute.setText("Compute");
        btnCompute.setVisible(true);
        //Set action for button click
        //Call defined method
        btnCompute.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent evt)
            {
                compute(evt);
            }
        });


        label1 = new JLabel();
        label1.setBounds(16, 51, 114, 32);
        label1.setBackground(new Color(214, 217, 223));
        label1.setForeground(new Color(0, 0, 0));
        label1.setEnabled(true);
        label1.setFont(new Font("DejaVu Sans", 0, 12));
        label1.setText("Letter Grade");
        label1.setVisible(true);

        label2 = new JLabel();
        label2.setBounds(18, 97, 110, 34);
        label2.setBackground(new Color(214, 217, 223));
        label2.setForeground(new Color(0, 0, 0));
        label2.setEnabled(true);
        label2.setFont(new Font("DejaVu Sans", 0, 12));
        label2.setText("Units");
        label2.setVisible(true);

        label4 = new JLabel();
        label4.setBounds(139, 9, 105, 32);
        label4.setBackground(new Color(214, 217, 223));
        label4.setForeground(new Color(0, 0, 0));
        label4.setEnabled(true);
        label4.setFont(new Font("DejaVu Sans", 0, 12));
        label4.setText("GPA Calculator");
        label4.setVisible(true);

        label6 = new JLabel();
        label6.setBounds(43, 258, 90, 35);
        label6.setBackground(new Color(214, 217, 223));
        label6.setForeground(new Color(0, 0, 0));
        label6.setEnabled(true);
        label6.setFont(new Font("DejaVu Sans", 0, 12));
        label6.setText("GPA");
        label6.setVisible(true);

        label7 = new JLabel();
        label7.setBounds(13, 310, 109, 23);
        label7.setBackground(new Color(214, 217, 223));
        label7.setForeground(new Color(0, 0, 0));
        label7.setEnabled(true);
        label7.setFont(new Font("DejaVu Sans", 0, 12));
        label7.setText("Course count");
        label7.setVisible(true);

        txtGrade = new JTextField();
        txtGrade.setBounds(152, 56, 90, 35);
        txtGrade.setBackground(new Color(255, 255, 255));
        txtGrade.setForeground(new Color(0, 0, 0));
        txtGrade.setEnabled(true);
        txtGrade.setFont(new Font("DejaVu Sans", 0, 12));
        txtGrade.setText("");
        txtGrade.setVisible(true);
        txtGrade.setName("txtGrade");

        txtGPA = new JTextField();
        txtGPA.setBounds(144, 251, 90, 35);
        txtGPA.setBackground(new Color(255, 255, 255));
        txtGPA.setForeground(new Color(0, 0, 0));
        txtGPA.setEnabled(true);
        txtGPA.setFont(new Font("DejaVu Sans", 0, 12));
        txtGPA.setText("");
        txtGPA.setVisible(true);
        txtGPA.setName("txtGPA");

        textfield11 = new JTextField();
        textfield11.setBounds(147, 305, 90, 35);
        textfield11.setBackground(new Color(255, 255, 255));
        textfield11.setForeground(new Color(0, 0, 0));
        textfield11.setEnabled(true);
        textfield11.setFont(new Font("DejaVu Sans", 0, 12));
        textfield11.setText("");
        textfield11.setVisible(false);

        txtCourseCount = new JTextField();
        txtCourseCount.setBounds(142, 299, 90, 35);
        txtCourseCount.setBackground(new Color(255, 255, 255));
        txtCourseCount.setForeground(new Color(0, 0, 0));
        txtCourseCount.setEnabled(true);
        txtCourseCount.setFont(new Font("DejaVu Sans", 0, 12));
        txtCourseCount.setText("");
        txtCourseCount.setVisible(true);

        txtUnits = new JTextField();
        txtUnits.setBounds(150, 100, 90, 35);
        txtUnits.setBackground(new Color(255, 255, 255));
        txtUnits.setForeground(new Color(0, 0, 0));
        txtUnits.setEnabled(true);
        txtUnits.setFont(new Font("DejaVu Sans", 0, 12));
        txtUnits.setText("");
        txtUnits.setVisible(true);
        txtUnits.setName("txtUnits");

        textfield9 = new JTextField();
        textfield9.setBounds(148, 141, 90, 35);
        textfield9.setBackground(new Color(255, 255, 255));
        textfield9.setForeground(new Color(0, 0, 0));
        textfield9.setEnabled(true);
        textfield9.setFont(new Font("DejaVu Sans", 0, 12));
        textfield9.setText("");
        textfield9.setVisible(true);

        //adding components to contentPane panel
        contentPane.add(btnCompute);
        contentPane.add(label1);
        contentPane.add(label2);
        contentPane.add(label4);
        contentPane.add(label6);
        contentPane.add(label7);
        contentPane.add(txtGrade);
        contentPane.add(txtGPA);
        contentPane.add(txtCourseCount);
        contentPane.add(txtUnits);

        //adding panel to JFrame and seting of window position and close operation
        this.add(contentPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
    }

    //Method actionPerformed for button2
    private void compute(ActionEvent evt)
    {
        // Retrieve input fields, compute results
        String grade = txtGrade.getText();
        double units = Float.parseFloat(txtUnits.getText());
        studentGPA.addCourse(grade, units);
        // Display results
        txtGPA.setText("" + String.format("%2.2f", studentGPA.getGPA()));
        txtCourseCount.setText("" + studentGPA.getCourseCount());
    }

    //method for generate menu
    public void generateMenu()
    {
        menuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenu tools = new JMenu("Tools");
        JMenu help = new JMenu("Help");

        JMenuItem open = new JMenuItem("Open   ");
        JMenuItem save = new JMenuItem("Save   ");
        JMenuItem exit = new JMenuItem("Exit   ");
        JMenuItem preferences = new JMenuItem("Preferences   ");
        JMenuItem about = new JMenuItem("About   ");


        file.add(open);
        file.add(save);
        file.addSeparator();
        file.add(exit);
        tools.add(preferences);
        help.add(about);

        menuBar.add(file);
        menuBar.add(tools);
        menuBar.add(help);
    }

    public static void main(String[] args)
    {
        System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new GUI().setVisible(true);
                ;
            }
        });
    }
}
