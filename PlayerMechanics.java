package othello;

import java.awt.Color;

public class PlayerMechanics {
	public final static int PLAYER1WON = 1;
	public final static int PLAYER2WON = 2;
	public final static int DRAW = 3;
	public final static int INCOMPLETE = 4;

	static int blackCount=2;      								   
	static int whiteCount=2;	

	public static int gameStatus(NewOthello o)
	{
		if(ifanyPossible(o.blackColor,o))
		{
			return INCOMPLETE;
		}
		if(ifanyPossible(o.whiteColor,o))
		{
			return INCOMPLETE;
		}

		else{
			if(blackCount==0)
			{
				return PLAYER2WON;
			}
			if(whiteCount==0)
			{
				return PLAYER1WON;
			}
			if(blackCount>whiteCount)
			{
				return PLAYER1WON;	
			}
			if(whiteCount>blackCount)
			{
				return PLAYER2WON;	
			}
			else{
				return DRAW;	
			}
		}
	}


	public static boolean ifanyPossible(Color PlayerColor, NewOthello o)
	{

		boolean done=false;
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++)
			{
				Color presentSymbol= o.button[i][j].getBackground();	
				if(checkIfPossible(i,j,PlayerColor,o)==true&&!presentSymbol.equals(o.blackColor)&&!presentSymbol.equals(o.whiteColor))
				{

					return true;
				}

			}
		}
		return done;
	}

	public  static boolean checkIfPossible(int x, int y,Color PlayerColor,NewOthello o) {

		boolean done=false;
		int xDir[]={-1,-1,-1,0,1,1,1,0};
		int yDir[]={-1,0,1,1,1,0,-1,-1};
		int count=0;
		for(int i=0;i<xDir.length;i++)
		{
			int currentX=x;
			int currentY=y;
			int stepX=xDir[i];
			int stepY=yDir[i];
			currentX+=stepX;
			currentY+=stepY;
			count=0;
			while(currentX>-1&&currentX<8&&currentY>-1&&currentY<8)
			{
				Color presentSymbol=o.button[currentX][currentY].getBackground();
				if(presentSymbol.equals(o.buttonColor)||presentSymbol.equals(Color.red))
				{
					break;  
				}	
				else if(presentSymbol.equals(PlayerColor))
				{

					if(count>0)
					{
						return true;
					}
					break;
				}

				else
				{
					currentX+=stepX;
					currentY+=stepY;
					count++;
				}
			}

		}

		return done;
	}

	public static boolean showAllPossible(Color PlayerColor,NewOthello o)
	{

		boolean done=false;
		//int a[][]=new int[8][8];
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++)
			{
				Color presentSymbol= o.button[i][j].getBackground();	
				if(checkIfPossible(i,j,PlayerColor,o)==true&&!presentSymbol.equals(o.blackColor)&&!presentSymbol.equals(o.whiteColor))
				{
					o.button[i][j].setBackground(Color.red);
				}
			}
		}
		return done;
	}

	public static boolean playerMove(int x, int y, Color c,NewOthello o) {
		//Color compColor=c.equals(o.blackColor)?o.whiteColor:o.blackColor;
		int xDir[]={-1,-1,-1,0,1,1,1,0};
		int yDir[]={-1,0,1,1,1,0,-1,-1};
		boolean done=false;
		for(int i=0;i<xDir.length;i++)
		{
			int currentX=x;               
			int currentY=y;
			int stepX=xDir[i];   //xDir[]={-1,-1,-1,0,1,1,1,0};
			int stepY=yDir[i];    //yDir[]={-1,0,1,1,1,0,-1,-1};
			currentX+=stepX;
			currentY+=stepY;
			int count=0;
			while(currentX>-1&&currentX<8&&currentY>-1&&currentY<8)
			{
				Color presentSymbol=o.button[currentX][currentY].getBackground();
				if(presentSymbol.equals(o.buttonColor))
				{
					break;
				}	
				else if(presentSymbol.equals(c))
				{
					if(count>0){
						int ci=0;
						int movex=currentX-stepX;
						int movey=currentY-stepY;
						while(ci<=count)
						{
							o.button[movex][movey].setBackground(c);
							movex-=stepX;
							movey-=stepY;
							ci++;
							done=true;
						}   }
					break;
				}
				else
				{
					currentX+=stepX;
					currentY+=stepY;
					count++;
				}   }   }
		return done;
	}
	public static void changeCount(NewOthello o)
	{

		blackCount=0;
		whiteCount=0;
		for(int i = 0; i < 8; i++){
			for(int j=0;j<8;j++){
				Color presentSymbol= o.button[i][j].getBackground();
				if(presentSymbol.equals(o.blackColor))
				{
					blackCount++;
				}
				if(presentSymbol.equals(o.whiteColor))
				{
					whiteCount++;
				}

			}
		}


	}

	public static int returnBlackCount()
	{
		return blackCount;
	}
	public static int returnWhiteCount()
	{
		return whiteCount;
	}

}

