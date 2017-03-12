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

public class WinnerJapnel extends JFrame implements ActionListener  {

	private static final long serialVersionUID = 1L;
	JPanel showWinner=new JPanel();
	JPanel extra[]=new JPanel[12];
	JPanel option=new JPanel();
	int[] dimW = {150,50,100,90};
	int[] dimH = {35, 50};
	Dimension displayDimension = new Dimension(dimW[0], dimH[0]);
	JButton NewGame=new JButton();
	Color bg=new Color(47, 73, 110);
	public final static int PLAYER1WON = 1;
	public final static int PLAYER2WON = 2;
	public final static int DRAW = 3;
	public final static int INCOMPLETE = 4;
	Font font = new Font("ColorFont.font", Font.BOLD, 12);

	public WinnerJapnel(int status) {

		super("Winner ofThe Game");
		setDesign();
		setSize(500, 500);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		GridLayout grid = new GridLayout(14,10);
		setLayout(grid);
		FlowLayout f4 = new FlowLayout(FlowLayout.CENTER,0,0);
		showWinner.setLayout(f4);
		option.setLayout(f4);

		for(int i=0;i<extra.length;i++)
		{
			extra[i]=new JPanel();
			extra[i].setBackground(bg);
			extra[i].setBorder(null);
		}
		showWinner.setBorder(null);
		showWinner.setBackground(bg);
		

		NewGame.setText("New Game");
		NewGame.setFont(font);
		NewGame.setPreferredSize(displayDimension);
		NewGame.addActionListener(this);

		option.setBorder(null);
		option.setBackground(bg);
		option.add(NewGame);
		

		JTextField Winnerdisplay = new JTextField(20);
		Font font = new Font("ColorFont.font", Font.BOLD, 12);
		Winnerdisplay.setFont(font);
		Winnerdisplay.setEditable(false);
		Winnerdisplay.setHorizontalAlignment(JTextField.CENTER);
		Winnerdisplay.setPreferredSize(displayDimension);
		Winnerdisplay.setBackground(Color.white);
		Winnerdisplay.setBorder(null);		

		showWinner.add(Winnerdisplay);

		if(status==DRAW)
		{
			Winnerdisplay.setText("ITS A DRAW");		
		}	
		if(status==PLAYER1WON)
		{
			Winnerdisplay.setText("Player 1 / Black Won ");		
		}
		if(status==PLAYER2WON)
		{
			Winnerdisplay.setText("Player 2 / White Won");		
		}
		add(extra[0]);
		add(showWinner);
		for(int i=1;i<=3;i++)
		{
			add(extra[i]);
		}
		add(option);
		for(int i=3;i<extra.length;i++)
		{
			add(extra[i]);
		}
		
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
		if(e.getSource()==NewGame)
		{
			this.setVisible(false);
			NewOthello newGame=new NewOthello();
		}

	}



}
