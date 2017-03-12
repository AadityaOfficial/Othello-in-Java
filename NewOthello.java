package othello;

import java.awt.*;
import othello.PlayerMechanics;
import othello.WinnerJapnel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class NewOthello extends JFrame implements ActionListener {
	public final static int PLAYER1WON = 1;
	public final static int PLAYER2WON = 2;
	public final static int DRAW = 3;
	public final static int INCOMPLETE = 4;

	private static final long serialVersionUID = 1L;

	JPanel[] row = new JPanel[13];			//rows that will contain buttons
	JButton[][] button = new JButton[8][8]; //64 buttons on board 
	JPanel upperGap=new JPanel();           //to give gap between window and board

	JButton help=new JButton();   

	JButton blackStats=new JButton();
	JButton whiteStats=new JButton();

	public static JButton NewGame=new JButton();   //To start a new game  
	JButton setPlayerName=new JButton();           //To set Player Names 
	JButton showHint=new JButton(); 			   //To give hints to Players

	int[] dimW = {150,50,100,90};				   //dimension for buttons and text fields		
	int[] dimH = {35, 50};								

	Dimension displayDimension = new Dimension(dimW[0], dimH[0]); 
	Dimension regularDimension = new Dimension(dimW[1], dimH[1]);
	Dimension rColumnDimension = new Dimension(dimW[2], dimH[1]);
	Dimension smallText = new Dimension(20,30 );
	Dimension smallButton = new Dimension(30,30 );
	
	JTextField display = new JTextField(20); //display field to show current player
	Font font = new Font("ColorFont.font", Font.BOLD, 12); //Creating object of font class to set a font
	static JTextField player[] = new JTextField[4];

	Color blackColor=new Color(0,0,0);                     //black color piece
	Color whiteColor=new Color(255, 255, 255)	;		//white color piece
	Color buttonColor=new Color(0,153,230);			//color of board	
	Color bg=new Color(47, 73, 110);	

	JTextField blackCounting = new JTextField(20);
	JTextField WhiteCounting = new JTextField(20);

	boolean Player1Turn=true,Player1possible=true,Player2possible=true;   //variables to check turns

	NewOthello() {														//Constructor				
		super("Othello-The Game");	                                    //Name of Window
		setDesign();													//Set Design for window		
		setSize(500, 700);
		setResizable(false);											//Window cant be resized			
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		GridLayout grid = new GridLayout(14,10);						//Grid for layout
		setLayout(grid);												//Set Layout



		FlowLayout f2 = new FlowLayout(FlowLayout.CENTER,-1,-1); 		//Flow layout for buttons	
		FlowLayout f3 = new FlowLayout(FlowLayout.CENTER,5,5);
		FlowLayout f5 = new FlowLayout(FlowLayout.CENTER,10,10);//flow layout for new game/hint buttons

		for(int i = 0; i < row.length; i++){
			row[i] = new JPanel();										//Making all rows a new JPanel
			row[i].setBackground(bg);				//setting backgrounds for rows 
		}

		for(int i = 0; i < row.length; i++)								//Setting Layout for rows
			row[i].setLayout(f2);

		for(int i = 0; i < 8; i++) {                                    //Making all buttons new Jbutton
			for(int j=0;j<8;j++){
				button[i][j] = new JButton();
				button[i][j].setBackground(buttonColor);				//setting button background
				button[i][j].addActionListener(this);					//set action listeners
				}
		}

		buttonSet(NewGame, Color.white, "New Game",displayDimension);
		buttonSet(setPlayerName, Color.white, "Set Player Names",displayDimension);
		buttonSet(showHint, Color.white, "Show Hint",displayDimension);
		buttonSet(blackStats,blackColor,"",smallButton);
		buttonSet(whiteStats,whiteColor,"",smallButton);
		
		DisplaySettings(display, bg,displayDimension);
        DisplaySettings(blackCounting, bg,smallText);
        DisplaySettings(WhiteCounting, bg,smallText);

		for(int i = 0; i < 8; i++){
			for(int j=0;j<8;j++){

				button[i][j].setPreferredSize(regularDimension);
			}
		}

		upperGap.setBackground(bg);
		upperGap.setLayout(f2);
		add(upperGap);
		for(int i =0; i < 8; i++){
			for(int j=0;j<8;j++){
				row[i].add(button[i][j]);
				add(row[i]);
			}
		}
		button[3][3].setBackground(blackColor);
		button[4][4].setBackground(blackColor);	
		button[4][3].setBackground(whiteColor);
		button[3][4].setBackground(whiteColor);
		row[8].add(display);
		add(row[8]);
		add(row[9]);

		
//		blackCounting.setText(PlayerMechanics.returnBlackCount()+" ");
//		WhiteCounting.setText(PlayerMechanics.returnWhiteCount()+" ");
		
		blackCounting.setText("2 ");
		WhiteCounting.setText("2 ");
		row[9].setLayout(f5); //setting layout of the panels
		row[10].setLayout(f5);
		row[9].add(blackStats);  //adding buttons to the JPanels
		row[9].add(blackCounting); 
		row[10].add(whiteStats);
		row[10].add(WhiteCounting);

		row[12].setLayout(f3);
		row[12].add(NewGame);
		row[12].add(setPlayerName);
		row[12].add(showHint);
		for(int i=10;i<row.length;i++)
		{
			add(row[i]); //adding JPanel to the frame
		}
		row[11].setLayout(f5);
		for(int i=0;i<4;i++)
		{
			player[i]=new JTextField();
			player[i].setBorder(null);
			player[i].setBackground(bg);;
			player[i].setForeground(whiteColor);
			row[11].add(player[i]);
		}
		ChangeDisplay();
		setVisible(true);

	}

	public void ChangeDisplay()
	{
		if(Player1Turn==true)
		{
			display.setText("Player 1 Turn");
		}	
		else
		{
			display.setText("Player 2 Turn");
		}	
	}

	public void startGame()
	{


		this.setVisible(false);
		NewOthello newGame=new NewOthello();	

	}

	public void Resetbutton()
	{
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				Color get=button[i][j].getBackground();
				if(get!=blackColor&&get!=whiteColor)
				{
					button[i][j].setBackground(buttonColor);
				}

			}
		}

	}

	public final void setDesign() {
		try {
			UIManager.setLookAndFeel(
					"com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch(Exception e) {   
		}
	}

	public void actionPerformed(ActionEvent ae) { //action performed for newGame button


		if(ae.getSource() ==NewGame)
		{
			startGame();
		}
	
		if(ae.getSource()==setPlayerName)
		{
			this.setVisible(false);
			PlayerData d=new PlayerData();	
		}
		if(PlayerMechanics.gameStatus(this)!=INCOMPLETE)
		{
			showWinner(PlayerMechanics.gameStatus(this));
			return;
		}
		else {
			if(Player1Turn)
			{
				Resetbutton();
				if(ae.getSource()==showHint)
				{
					PlayerMechanics.showAllPossible(blackColor,this);
					return;
				}
				Player1possible=PlayerMechanics.ifanyPossible(blackColor,this);			
				if(!Player1possible)
				{
					Player1Turn=false;
					display.setText("Move not possible for black");
					return; 
				}
				else 
				{
					int posX=-1;
					int posY=-1;
					for(int i=0;i<8;i++)   {
						for(int j=0;j<8;j++)
						{	
							if(ae.getSource()==button[i][j])
							{
								posX=i;
								posY=j;
								Color presentSymbol=button[posX][posY].getBackground();
								if(presentSymbol.equals(whiteColor)||presentSymbol.equals(blackColor))
								{
									return;	
								}
								break;	
							}
						} 
					}
					if(!PlayerMechanics.playerMove(posX,posY,blackColor,this)&&PlayerMechanics.ifanyPossible(blackColor,this))
					{
						return;
					}	
					Player1Turn=false;
					ChangeDisplay();
				}
			}
			else
			{
				Resetbutton();
				if(ae.getSource()==showHint)
				{
					PlayerMechanics.showAllPossible(whiteColor,this);
					return;
				}			
				Player2possible=PlayerMechanics.ifanyPossible(whiteColor,this);			
				if(!Player2possible)
				{
					Player1Turn=true;
					display.setText("Move not possible for white");
					return; 
				}
				else 
				{
					int posX=-1;
					int posY=-1;
					for(int i=0;i<8;i++)   {
						for(int j=0;j<8;j++)
						{	
							if(ae.getSource()==button[i][j])
							{
								posX=i;
								posY=j;
								Color presentSymbol=button[posX][posY].getBackground();
								if(presentSymbol.equals(whiteColor)||presentSymbol.equals(blackColor))
								{
									return;	
								}
								break;	
							}
						} 
					}
					if(!PlayerMechanics.playerMove(posX,posY,whiteColor,this)&&PlayerMechanics.ifanyPossible(whiteColor,this))
					{
						return;
					}	
					Player1Turn=true;
					ChangeDisplay();
				}
			}

		}

		PlayerMechanics.changeCount(this);
		blackCounting.setText(PlayerMechanics.returnBlackCount()+" ");
		WhiteCounting.setText(PlayerMechanics.returnWhiteCount()+" ");
	}

	public void showWinner(int status)
	{
		this.setVisible(false);

		WinnerJapnel w=new WinnerJapnel(status);
	}
	public void buttonSet(JButton b,Color c,String s,Dimension d)
	{
		b.setText(s); 												//Making new game button
		b.setFont(font);											//setting font for text
		b.setPreferredSize(d);
		b.addActionListener(this);
		b.setBackground(c);
	}

	public static void feedPlayerData(String p1, String p2) {

		player[0].setText("Player 1: ");	
		player[1].setText(p1);
		player[2].setText("Player 2: ");	
		player[3].setText(p2);
	}
	public void DisplaySettings(JTextField a,Color c,Dimension d) //this function is used to set the text field
	{
		a.setFont(font); //Sets the font for text in the field
		a.setEditable(false); //cannot be edited
		a.setHorizontalAlignment(JTextField.CENTER); //allignment of font in text field
		a.setPreferredSize(d); //Setting dimension of textfield
		a.setBackground(c); //set background color
     	a.setBorder(null); //no Borders
	    a.setForeground(new Color(255,255,255)); //set color of text
	}

}