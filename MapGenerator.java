import java.awt.*;
public class MapGenerator{
	public int map[][];
	public int BrickHight;
	public int BrickWidth;

	public MapGenerator(int row,int col){
		map= new int[row][col];
		for(int i=0; i<row;i++)
		{
			for(int j=0 ; j<col; j++)
			{
				map[i][j]=1;
			}
		}

		BrickWidth=560/col;
		BrickHight=230/row;	
	}
	public void setBricks(int val,int r,int c)
	{
		map[r][c]=val;
	}
	public void draw(Graphics2D g){
		for(int i=0; i<map.length;i++)
		{
			for(int j=0 ; j<map[0].length; j++)
			{
				if(map[i][j]>0)
				{
					g.setColor(Color.WHITE);
					g.fillRect(j*BrickWidth+80,i*BrickWidth+50,BrickWidth,BrickHight);

					g.setColor(Color.BLACK);
					g.setStroke(new BasicStroke(3));
					g.drawRect(j*BrickWidth+80,i*BrickWidth+50,BrickWidth,BrickHight);
				}
			}
		}
	}

	
}