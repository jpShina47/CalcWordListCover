import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class MakeWinFrame{
	/*---------------------------------- Make Instance ----------------------------------*/
	JFrame frame = new JFrame("単語帳カバー率算出アプリ");
	static JTextField FileNameTextField = new JTextField();
	static JTextField ResultTextField = new JTextField();
	static JTextArea ScriptTextArea = new JTextArea();
	static JTextArea MatchedWrdTextArea = new JTextArea();
	static JTextArea AnalizedResultTextArea = new JTextArea();
	JPanel Panel1 = new JPanel();	//Scriptファイル名，Scriptのセット
	JPanel Panel2 = new JPanel();	//単語帳のボタンセット
	JPanel Panel3 = new JPanel();	//結果を表示するセット
	static JCheckBox WordList_Target1900 = new JCheckBox();
	static JCheckBox WordList_Target1400 = new JCheckBox();
	static JCheckBox WordList_SysTan = new JCheckBox();
	static JCheckBox LEAP = new JCheckBox();
	JButton OpenExpBt = new JButton();
	JButton GetRationBt = new JButton();
	
	JScrollPane Script_scrollpane = new JScrollPane(ScriptTextArea);
	JScrollPane Matched_scrollpane = new JScrollPane(MatchedWrdTextArea);
	GridBagLayout grdbglt = new GridBagLayout();
	BoxLayout boxlayout_panel2 = new BoxLayout(Panel2,BoxLayout.Y_AXIS);
	
	public MakeWinFrame(){
		/*---------------------------------- Frame Setting ----------------------------------*/
		frame.setSize(1000,700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(grdbglt);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		
		/*---------------------------------- Panel1 Setting ----------------------------------*/
//		Panel1.setBackground(Color.BLACK);
//		LineBorder border_panel1 = new LineBorder(Color.BLACK,2,true);
//		Panel1.setBorder(border_panel1);
		Panel1.setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 3;
		gbc.weightx = 0.6;
		gbc.weighty = 1.0; // Panel1に十分な縦のスペースを確保する
		grdbglt.setConstraints(Panel1, gbc);
		
		/*---------------------------------- Panel2 Setting ----------------------------------*/
//		Panel2.setBackground(Color.ORANGE);
//		LineBorder border_panel2 = new LineBorder(Color.BLACK,2,true);
//		Panel2.setBorder(border_panel2);
		Panel2.setLayout(boxlayout_panel2);
	    gbc.gridx = 1;
	    gbc.gridy = 0;
	    gbc.gridheight = 1;
	    gbc.weightx = 0.4;
	    gbc.weighty = 0.2;
		grdbglt.setConstraints(Panel2, gbc);
		
		/*---------------------------------- Panel3 Setting ----------------------------------*/
//		Panel3.setBackground(Color.BLUE);
//		LineBorder border_panel3 = new LineBorder(Color.BLACK,2,true);
//		Panel3.setBorder(border_panel3);
		Panel3.setLayout(new GridBagLayout());
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.weighty = 0.8;
		grdbglt.setConstraints(Panel3, gbc);
		
		/*---------------------------------- Panel1_Content Setting ----------------------------------*/
        GridBagConstraints gbcPanel1 = new GridBagConstraints();
        gbcPanel1.fill = GridBagConstraints.BOTH;
        
        gbcPanel1.gridx = 0;
        gbcPanel1.gridy = 0;
        gbcPanel1.weightx = 1.0;
        gbcPanel1.weighty = 0.02;
        Panel1.add(FileNameTextField, gbcPanel1);
        FileNameTextField.setMargin(new Insets(0, 10, 0, 0));
        
        gbcPanel1.gridx = 0;
        gbcPanel1.gridy = 1;
        gbcPanel1.weighty = 0.98;
        Panel1.add(Script_scrollpane, gbcPanel1);
		ScriptTextArea.setWrapStyleWord(false);
		ScriptTextArea.setMargin(new Insets(10, 10, 10, 10));
		
		/*---------------------------------- Panel2_Content Setting ----------------------------------*/
		Panel2.add(WordList_Target1900);
		Panel2.add(WordList_Target1400);
		Panel2.add(WordList_SysTan);
		Panel2.add(LEAP);
		WordList_Target1900.setText("ターゲット1900");
		WordList_Target1400.setText("ターゲット1400");
		WordList_SysTan.setText("システム英単語");
		LEAP.setText("LEAP");
		WordList_Target1900.addItemListener(new CheckBoxBanMultiSelect());
		WordList_Target1400.addItemListener(new CheckBoxBanMultiSelect());
		WordList_SysTan.addItemListener(new CheckBoxBanMultiSelect());
		LEAP.addItemListener(new CheckBoxBanMultiSelect());
		WordList_Target1900.setAlignmentX(Component.LEFT_ALIGNMENT);
		WordList_Target1400.setAlignmentX(Component.LEFT_ALIGNMENT);
		WordList_SysTan.setAlignmentX(Component.LEFT_ALIGNMENT);
		LEAP.setAlignmentX(Component.LEFT_ALIGNMENT);
		WordList_Target1900.setAlignmentY(Component.TOP_ALIGNMENT);
		WordList_Target1400.setAlignmentY(Component.TOP_ALIGNMENT);
		WordList_SysTan.setAlignmentY(Component.TOP_ALIGNMENT);
		LEAP.setAlignmentY(Component.TOP_ALIGNMENT);
		
		/*---------------------------------- Panel3_Content Setting ----------------------------------*/
		OpenExpBt.setText("開く");
		GetRationBt.setText("結果表示");
		OpenExpBt.setBackground(Color.ORANGE);
		GetRationBt.setBackground(Color.ORANGE);
		OpenExpBt.addActionListener(new TXTFileReader());
		GetRationBt.addActionListener(new Comparing());
		ResultTextField.setMargin(new Insets(0, 5, 0, 0));
		MatchedWrdTextArea.setMargin(new Insets(10, 10, 10, 10));
		AnalizedResultTextArea.setMargin(new Insets(10, 10, 10, 10));
		
		GridBagConstraints gbcPanel3 = new GridBagConstraints();
        gbcPanel3.fill = GridBagConstraints.HORIZONTAL;
        gbcPanel3.gridx = 0;
        gbcPanel3.gridy = 0;
        gbcPanel3.weightx = 1.0;
        Panel3.add(OpenExpBt, gbcPanel3);

        gbcPanel3.gridy = 1;
        Panel3.add(GetRationBt, gbcPanel3);
        
        gbcPanel3.gridy = 2;
        Panel3.add(ResultTextField, gbcPanel3);

        gbcPanel3.gridy = 3;
        gbcPanel3.fill = GridBagConstraints.BOTH;
        gbcPanel3.weighty = 1.0;
        Panel3.add(Matched_scrollpane, gbcPanel3);
        
        gbcPanel3.gridx = 1;
        Panel3.add(AnalizedResultTextArea,gbcPanel3);
		/*---------------------------------- Content Add ----------------------------------*/
		frame.add(Panel1);
		frame.add(Panel2);
		frame.add(Panel3);
		
		/*---------------------------------- Frame Visible ----------------------------------*/
		frame.setVisible(true);
    }
	
}