import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GamePlay extends JPanel implements ActionListener {
    private boolean play = false;
    private int totalBricks = 21;
    private Timer timer;
    private int delay = 8;
    private int ballposX=250;
    private int ballposY=400;
    private int ballxdir=-2;
    private int ballydir =-2;
    private int playerX=350;
    private JButton b1;
    private JButton b2;
    private MapGenerator map;

    public GamePlay(){
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        setSize(700,700);
        setLayout(null);
        b1 = new JButton("Left");
        b2 = new JButton("Right");

        b1.setBounds(50, 620, 100, 30);
        b2.setBounds(550, 620, 100, 30);
        
        b1.addActionListener(this);
        b2.addActionListener(this);

        add(b1);
        add(b2);

        timer = new Timer(delay, this);
        timer.start();

        map = new MapGenerator(3,7);
    }

    protected void paintComponent(Graphics g){
        //set the Backgraound 
		g.setColor(new Color(0,120,150).brighter());
		g.fillRect(1,1,692,592);

        //set border
		g.setColor(Color.GREEN);
		g.fillRect(0,0,692,3);
		g.fillRect(0,3,3,592);
		g.fillRect(691,3,3,592);
        g.fillRect(0,592,692,3);

        //set the padel
		g.setColor(new Color(150,255,100).brighter());
		g.fillRect(playerX,550,100,8);

        //set Ball
		g.setColor(new Color(250,1,1).brighter());
		g.fillOval(ballposX,ballposY,20,20);
        //create bricks
        map.draw((Graphics2D)g);
        
    }
    private void moveLeft(){
        playerX -= 50;
    }

    private void moveRight(){
        playerX += 50;
    }

	public void actionPerformed(ActionEvent e){

        if (play){
            if(ballposX<=0 )
            {
                ballxdir=-ballxdir;
            }
            if(ballposX>=670)
            {
                ballxdir=-ballxdir;
            }
            if(ballposY<=0)
            {
                ballydir=-ballydir;
            }

            Rectangle ball= new Rectangle(ballposX,ballposY,20,20);
            Rectangle padel = new Rectangle(playerX,550,100,8);
            Rectangle downBorder = new Rectangle(0,592,692,3);


            A:for(int i=0; i<map.map.length;i++){
                for(int j=0; j<map.map[0].length; j++){

                    if(map.map[i][j]>0)
                    {
                        int hight=map.BrickHight;
                        int width=map.BrickWidth;
                        int brickposX=j*width+80;
                        int brickposY=i*hight+50;
                        Rectangle brick = new Rectangle(brickposX,brickposY,width,hight);

                        if(ball.intersects(brick))
                        {
                            map.setBricks(0,i,j);
                            totalBricks--;

                            if( ballposX+15 <= brickposX || ballposX+5>= brickposX+width){
                                ballxdir = -ballxdir;
                            }
                            else{
                                ballydir=-ballydir;
                            }
                            if(totalBricks==0)
                            {   play=false;
                                b1.setEnabled(false);
                                b2.setEnabled(false);
                                String s=String.valueOf(21-totalBricks);
                                JOptionPane.showMessageDialog(this,"You Are Win\nyour score is : "+s);
                            }
                           break A ;
                        }
                    }
                }
            }

            if(ball.intersects(downBorder))
            {
                play=false;
                b1.setEnabled(false);
                b2.setEnabled(false);
                JOptionPane.showMessageDialog(this,"Game is Over.....!");
            }
            if(ball.intersects(padel))
            {
                ballydir=-ballydir;
            }
            ballposX+=ballxdir;
            ballposY+=ballydir;
        }
        if(e.getSource()==b1)
        {
            play=true;
            if(playerX<=0)
                playerX=0;
            else
                moveLeft();
        }
        if (e.getSource()==b2)
        {
            play=true;
            if(playerX>=492)
                playerX=592;
            else
                moveRight();
        }
        repaint();
    }


}
