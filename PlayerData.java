package othello;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class PlayerData extends JFrame implements ActionListener{
	String PlayerName;
	private static final long serialVersionUID = 1L;
	JPanel Player1Dialogue=new JPanel();
	JPanel extra[]=new JPanel[12];
	JPanel Player2Dialogue=new JPanel();
	int[] dimW = {150,50,100,90};
	int[] dimH = {35, 50};
	Dimension displayDimension = new Dimension(dimW[0], dimH[0]);
	JButton SubmitButton=new JButton();

	JTextField Player1display = new JTextField(500);
	JTextField Player2display = new JTextField(500);
	JTextField Player1name = new JTextField(30);
	JTextField Player2name = new JTextField(30);
	Font font = new Font("ColorFont.font", Font.BOLD, 12);

	Color bg=new Color(47, 73, 110);
	Color whiteColor=new Color(255, 255, 255)	;
	public PlayerData()
	{
		super("Set Player Names");
		setDesign();
		setSize(500, 500);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		GridLayout grid = new GridLayout(14,10);
		setLayout(grid);
		FlowLayout f4 = new FlowLayout(FlowLayout.CENTER,0,0);
		Player1Dialogue.setLayout(f4);
		Player2Dialogue.setLayout(f4);


		for(int i=0;i<extra.length;i++)
		{
			extra[i]=new JPanel();
			extra[i].setBackground(bg);
			extra[i].setBorder(null);
		}
		Player1Dialogue.setBorder(null);
		Player1Dialogue.setBackground(new Color(255,255,255));
		Player1Dialogue.add(Player1display);

		SubmitButton.setText("Submit");
		SubmitButton.setFont(font);
		SubmitButton.setPreferredSize(displayDimension);
		SubmitButton.addActionListener(this);

		Player2Dialogue.setBorder(null);
		Player2Dialogue.setBackground(new Color(255,255,255));
		Player2Dialogue.add(Player2display);

		String p1="Enter Player 1 name below";
		setTextAre(Player1display,p1 );
		String p2="Enter Player 2 name below";
		setTextAre(Player2display, p2);

		setTextAre(Player1name, "");
		Player1name.setEditable(true);
		Player1name.setBackground(Color.white);
		Player1name.setForeground(Color.black);
		setTextAre(Player2name, "");
		Player2name.setEditable(true);
		Player2name.setBackground(Color.white);
		Player2name.setForeground(Color.black);
		
		add(extra[0]);
		add(extra[1]);
		add(Player1Dialogue);
		extra[2].add(Player1name);
		add(extra[2]);
		for(int i=3;i<=5;i++)
		{
			add(extra[i]);
		}
		add(extra[6]);
		add(Player2Dialogue);
		extra[7].add(Player2name);
		add(extra[7]);
		for(int i=8;i<extra.length;i++)
		{
			add(extra[i]);
		}
		extra[9].add(SubmitButton);

		this.setVisible(true);


	}


	private void setDesign() {
		try {
			UIManager.setLookAndFeel(
					"com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch(Exception e) {   
		}

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==SubmitButton)
		{
			Submitbutton();
		}
	}
	private void setTextAre(JTextField o,String s)
	{

		o.setFont(font);
		o.setEditable(false);
		o.setHorizontalAlignment(JTextField.CENTER);
		o.setPreferredSize(displayDimension);
		o.setBackground(bg);
		o.setBorder(null);	
		o.setText(s);
		o.setForeground(whiteColor);

	}
	public void Submitbutton()
	{
		this.setVisible(false);
		String p1=Player1name.getText();
		String p2=Player2name.getText();
		NewOthello GameAfterSubmission=new NewOthello();
		GameAfterSubmission.feedPlayerData(p1, p2);

	}



}