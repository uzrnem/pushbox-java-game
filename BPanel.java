import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class BPanel extends JPanel implements KeyListener {
	static JFrame frame;
	static ArrayList<Square> place = new ArrayList();
	static ArrayList<Square> weight = new ArrayList();
	static int xco=1,yco=2;
	static char ch='m';
	static int d=1;
	static int score;
	static Container contentPane;
	static int l=0,lvl=24;
  public BPanel() {
    addKeyListener(this);
  }

  public void keyPressed(KeyEvent evt) {
    int keyCode = evt.getKeyCode();
    if (keyCode == KeyEvent.VK_LEFT){
    		d=4;move();
    }
    else if (keyCode == KeyEvent.VK_RIGHT){
    		d=2;move();
    }
    else if (keyCode == KeyEvent.VK_UP){
    		d=3;move();
    }
    else if (keyCode == KeyEvent.VK_DOWN){
    		d=1;move();
    }
    else if (keyCode == KeyEvent.VK_R){
    		start();
    }
    else if (keyCode == KeyEvent.VK_N){
    		l++;start();
    }
    contentPane.repaint();score++;
    win();
  }

  public void keyReleased(KeyEvent evt) {
  }

  public void keyTyped(KeyEvent evt) {
  }

  public boolean isFocusTraversable() {
    return true;
  }

  public void paintComponent(Graphics g){
	  super.paintComponent(g);
	  for(int c=0;c<place.size();c++)
	  {
	  	Square p=(Square)place.get(c);
	  	if(p.ch=='r')
	  	{
	  		Image img=Toolkit.getDefaultToolkit().getImage("photo/road.png");
		  	g.drawImage(img,p.x*30,p.y*30,30,30,this);
		}
	  	else
	  	if(p.ch=='w')
	  	{
	  		Image img=Toolkit.getDefaultToolkit().getImage("photo/wall.png");
		  	g.drawImage(img,p.x*30,p.y*30,30,30,this);
		}
	  	else
	  	if(p.ch=='s')
	  	{
	  		Image img=Toolkit.getDefaultToolkit().getImage("photo/space.png");
		  	g.drawImage(img,p.x*30,p.y*30,30,30,this);
		}
	  	else
	  	if(p.ch=='f')
	  	{
	  		Image img=Toolkit.getDefaultToolkit().getImage("photo/flag.png");
		  	g.drawImage(img,p.x*30,p.y*30,30,30,this);
		}
	  }
	  for(int c=0;c<weight.size();c++)
	  {
	  	Square p=(Square)weight.get(c);
	  	if(p.ch=='b')
	  	{
	  		Image img=Toolkit.getDefaultToolkit().getImage("photo/box.png");
		  	g.drawImage(img,p.x*30,p.y*30,30,30,this);
		}
	  	else
	  	if(p.ch=='d')
	  	{
	  		Image img=Toolkit.getDefaultToolkit().getImage("photo/done.png");
		  	g.drawImage(img,p.x*30,p.y*30,30,30,this);
		}
	  }
	  if(ch=='m')
	  {
		  Image img=Toolkit.getDefaultToolkit().getImage("photo/man.png");
		  g.drawImage(img,xco*30,yco*30,30,30,this);
	  }
	  if(ch=='h')
	  {
		  Image img=Toolkit.getDefaultToolkit().getImage("photo/hero.png");
		  g.drawImage(img,xco*30,yco*30,30,30,this);
	  }
  }

  public static void main(String[] args) {
	  start();
    frame = new JFrame();
    frame.setTitle("Push - Box");
    frame.setSize(500,400);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setIconImage(Toolkit.getDefaultToolkit().getImage("photo/man.png"));
    contentPane = frame.getContentPane();
    contentPane.add(new BPanel());
    frame.setVisible(true);
  }

  public static void start()
  {
	  score=0;
	  place.clear();
	  weight.clear();
	  New.startLevel(place,weight,"level/level"+(l%lvl)+".pb");
	  xco=New.setX();
	  yco=New.setY();
  }
  public static int x()
  {
	  if(d==4)
	  	return -1;
	  if(d==2)
	  	return +1;
	  return 0;
  }
  public static int y()
  {
	  if(d==3)
	  	return -1;
	  if(d==1)
	  	return +1;
	  return 0;
  }
  public static void move()
  {
	  int x=xco;
	  int y=yco;
      if(isPlace(x+x(),y+y(),'w'))
      	return;
	  if(isBox(x+x(),y+y()))
	  {
		if(isBox(x+x()+x(),y+y()+y())||isPlace(x+x()+x(),y+y()+y(),'w')) return;
		if(isPlace(x+x()+x(),y+y()+y(),'r')||isPlace(x+x()+x(),y+y()+y(),'f'))
						{
							removeBox(x+x(),y+y());
							addBox(x+x()+x(),y+y()+y());
							xco=x+x();
							yco=y+y();
							if(isPlace(x+x(),y+y(),'f')) ch='h';
							else ch='m';
							return;
		  				}
		}
		if(isPlace(x+x(),y+y(),'r')||isPlace(x+x(),y+y(),'f'))
		{
		 xco=x+x();
		 yco=y+y();
		 if(isPlace(x+x(),y+y(),'f')) ch='h';
		 else ch='m';
		 return;
	    }
  }
  public static void addBox(int p,int q)
  {
	  if(isPlace(p,q,'r'))
	  	weight.add(new Square(p,q,'b'));

	  if(isPlace(p,q,'f'))
	  	weight.add(new Square(p,q,'d'));
  }
  public static void removeBox(int p,int q)
  {
	  for(int c=0;c<weight.size();c++)
	  {
		  Square s=(Square)weight.get(c);
		  if(s.x==p && s.y==q)
		  {
			  weight.remove(c);
			  break;
		  }
	  }
  }
public static boolean isPlace(int p,int q,char cz){
  	  for(int c=0;c<place.size();c++){
  		  Square s=(Square)place.get(c);
  		  if(s.x==p && s.y==q &&s.ch==cz)
  			  return true;
  	  }
  	  return false;
  }
public static boolean isBox(int p,int q){
  	  for(int c=0;c<weight.size();c++){
  		  Square s=(Square)weight.get(c);
  		  if(s.x==p && s.y==q )
  			  return true;
  	  }
  	  return false;
  }
  public static void win()
  {
	  for(int c=0;c<weight.size();c++)
	  {
		  Square s=(Square)weight.get(c);
		  if(s.ch!='d')
		  {
			  return;
	  		}
	  }
	  JOptionPane.showMessageDialog(frame, "Your Moves is "+score, "Game Over", JOptionPane.INFORMATION_MESSAGE );
		l++; start();
  }
}
