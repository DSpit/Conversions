package com.dSpit.conversions.mainGUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.dSpit.conversions.*;

@SuppressWarnings("serial")
public class MainFrame extends JApplet{
	
// Constants --------------------------------------------------------------- //
	
	private final String CONVERT_BUTTON = "Convert";
	private final String CONVERT_TO_CAN = "Convert from USA $ to Canadien $";
	private final String CONVERT_TO_USA = "convert from Canadien $ to USA $";
	private final String INPUT_PROMPT = "Enter Amount you wish to convert: ";
	private final String OUTPUT_PROMPT = "Result of Conversion: ";
	private final int PADDING = 5;

// Members ----------------------------------------------------------------- //

	private JButton mConvert;
	private JRadioButton mToCan;
	private JRadioButton mToUSA;
	private ButtonGroup mConvertSelectorGroup;
	private JTextField mInput;
	private JLabel mOutput;
	
// Overrides --------------------------------------------------------------- //
	
	public void init(){
		super.init();
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		mConvert = new JButton(CONVERT_BUTTON);
		mToCan = new JRadioButton(CONVERT_TO_CAN, true);
		mToUSA = new JRadioButton(CONVERT_TO_USA);
		mInput = new JTextField(10);
		mOutput = new JLabel();
		JLabel inputMessage = new JLabel(INPUT_PROMPT);
		JLabel outputMessage = new JLabel(OUTPUT_PROMPT);
		
		JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, PADDING, PADDING));
		inputPanel.add(inputMessage);
		inputPanel.add(mInput);
		
		JPanel outputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, PADDING, PADDING));
		outputPanel.add(outputMessage);
		outputPanel.add(mOutput);
		
		JPanel selectorPanel = new JPanel();
		selectorPanel.setLayout(new BoxLayout(selectorPanel, BoxLayout.Y_AXIS));
		selectorPanel.add(mToCan);
		selectorPanel.add(mToUSA);
		
		
		mConvertSelectorGroup = new ButtonGroup();
		mConvertSelectorGroup.add(mToCan);
		mConvertSelectorGroup.add(mToUSA);
		
		mConvert.addActionListener(new ConvertListener());
		
		mainPanel.add(inputPanel);
		mainPanel.add(selectorPanel);
		mainPanel.add(mConvert);
		mainPanel.add(outputPanel);
		
		this.add(mainPanel);
	}
	
	public void start(){
		super.start();
	}
	
	public void stop(){
		super.stop();
	}
	
	public void destroy(){
		super.destroy();
	}
	
	class ConvertListener implements ActionListener{
		
		private final String ERROR_MESSAGE = "Error in parsing. Input is invalid.";

		@Override
		public void actionPerformed(ActionEvent e) {
			
			Conversions conversion;
			double input;
			
			try{
				input = Double.parseDouble(mInput.getText());
			}catch(NumberFormatException exception){
				mOutput.setText(ERROR_MESSAGE);
				return;
			}
			
			if(mConvertSelectorGroup.getSelection().equals(mToCan)){
				conversion = new USAtoCAN();
			}else{
				conversion = new CANtoUSA();
			}
			
			
			mOutput.setText(String.valueOf(conversion.convert(input)));
		}
	}
}
