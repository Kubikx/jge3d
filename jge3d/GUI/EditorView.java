package jge3d.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditorView extends JPanel{
	//EditorView
	private JButton editorLayerNext;
	private JButton editorLayerPrev;
	private JTextField editorLayerField;
	private JLabel editorLabel;
	private int current_layer=0;
	
	public EditorView() {
		//EditorView
		editorLayerNext = new JButton("=>");
		editorLayerPrev = new JButton("<=");
		editorLayerField = new JTextField(String.valueOf(current_layer));
		editorLabel = new JLabel("Editor");
		
		//layout the editor panel
		setupEditorView();
	}
	
	//Contains all current editor commands (currently layer)
	private void setupEditorView()
	{
		this.setBorder(BorderFactory.createLineBorder(Color.green));
		this.setPreferredSize(new Dimension(super.getWidth()-2, 25));
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(editorLabel);
		this.add(editorLayerPrev);
		this.add(editorLayerField);
		this.add(editorLayerNext);

		editorLayerPrev.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                current_layer--;
                editorLayerField.setText(String.valueOf(current_layer));
            }
        });  
        
		editorLayerNext.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	current_layer++;
            	editorLayerField.setText(String.valueOf(current_layer));
            }
        });  
	}
	
	public int getLayer() {
		return current_layer;
	}
}
