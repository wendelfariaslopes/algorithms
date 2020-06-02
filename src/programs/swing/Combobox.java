package programs.swing;
import java.awt.BorderLayout;
import javax.swing.*;

public class Combobox extends JFrame {

	 public static void main(String[] args)
	    {
	        new Combobox ();
	    }


	    public Combobox()
	    {   

	        //comboBox creation
	        final String[] name = {"Aziz", "masum", "sakib", "shaon"};
	        final Wendel[] itemsWendel = {new Wendel(1,"wendel_1"),new Wendel(2,"wendel_2")};

	        JComboBox<String> comboBox = new JComboBox<>(name);
	        JComboBox<Wendel> comboBoxWendel = new JComboBox<>(itemsWendel);

	        //transparent JPanel creation
	        JPanel mainPanel = new JPanel(new BorderLayout()); // transparent frame to add comboBox
	        mainPanel.add(comboBox, BorderLayout.WEST); // comboBox added to transparent frame
	        mainPanel.add(comboBoxWendel, BorderLayout.EAST); // comboBox added to transparent frame

	        //now dealing with the creation of the JFrame to display it all
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        this.setLocationRelativeTo(null);
	        this.setLayout(new BorderLayout());

	        //adding everything to the frame

	        this.add(mainPanel, BorderLayout.CENTER);
	        this.pack();
	        this.setVisible(true);
	    }
	    
	    
	    
	    
	class Wendel{
		private int id;
		private String name;
		
		public Wendel(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	}
	
	class ComboBoxModel extends DefaultComboBoxModel<Wendel> {
	    public ComboBoxModel(Wendel[] items) {
	        super(items);
	    }
	 
	    @Override
	    public Wendel getSelectedItem() {
	        Wendel selectedWendel = (Wendel) super.getSelectedItem();
	 
	        // do something with this job before returning...
	 
	        return selectedWendel;
	    }
	}

}
