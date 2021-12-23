import java.io.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.*;
import javax.swing.*;
import java.util.ArrayList;
class New
{
	static int xco,yco;
	public static void startLevel(ArrayList<Square> place,ArrayList<Square> weight,String name)
	{
		int e=0;
		try{
		BufferedReader fin=new BufferedReader(new FileReader(name));
		String t=fin.readLine();
		while(t!=null)
		{
			for(int c=0;c<t.length();c++)
			{
				switch(t.charAt(c))
				{
					case 'B':weight.add(new Square(c,e,'b'));
							 place.add(new Square(c,e,'r'));break;

					case 'D':weight.add(new Square(c,e,'d'));
							 place.add(new Square(c,e,'f')); break;

					case 'M':place.add(new Square(c,e,'r'));
							 xco=c;yco=e;break;

					case 'H':place.add(new Square(c,e,'f'));
							 xco=c;yco=e;break;

					case 'F':place.add(new Square(c,e,'f')); break;

					case 'R':place.add(new Square(c,e,'r')); break;

					case 'S':place.add(new Square(c,e,'s')); break;

					case 'W':place.add(new Square(c,e,'w')); break;
				}
			}
			e++;
			t=fin.readLine();
		}
		fin.close();
		}catch(IOException ev){}
	}
	public static int setX()
	{
		return xco;
	}
	public static int setY()
	{
		return yco;
	}
}