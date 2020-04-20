package programs.swing;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Combobox2 {
	
	private String[] items = { "item1", "item2" };
	private JComboBox<String> combobox = new JComboBox<>(items);
	private JFrame frame = new JFrame();
	
	public static void main(String[] args){
		Combobox2 c = new Combobox2();
	}
	
	public Combobox2() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(300,300));
		frame.setLayout(new FlowLayout());
		
			combobox.addItemListener(new SelectorListener());
		
		frame.add(combobox);
		frame.pack();
		frame.setVisible(true);
	}
	
	
	private static JComboBox createCombobox(Object[] items) {
		JComboBox<Object> combobox = new JComboBox<>(items);
		return combobox;
	}
	
	static class Selector implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent evt) {
			//JComboBox cb = (JComboBox) evt.getSource();
		    Object item = evt.getItem();
		    
		    JOptionPane.showConfirmDialog(null, "Item was just selected "+item);
		    
		    if (evt.getStateChange() == ItemEvent.SELECTED) {
		    	JOptionPane.showConfirmDialog(null, "Item was just selected "+item);
		    } 
		    
		    if (evt.getStateChange() == ItemEvent.DESELECTED) {
		    	JOptionPane.showConfirmDialog(null, "IItem is no longer selected "+item);
		    } 
			
		}
		
	}
	
	class SelectorListener implements ItemListener {
		
		@Override
		public void itemStateChanged(ItemEvent evt) {
			//JComboBox cb = (JComboBox) evt.getSource();
		    Object item = evt.getItem();
		    
		    if (evt.getStateChange() == ItemEvent.SELECTED) {
		    	JOptionPane.showConfirmDialog(null, "Item was just selected "+item);
		    } 
			
		}
		
	}

}


