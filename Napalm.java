public class Napalm
{
	private int h;
	private int w;
	private int xcord;
	private int ycord;
	public Napalm(int xcord,int ycord, int h,int w)
	{
		this.h=h;
		this.w=w;
		this.xcord=xcord;
		this.ycord=ycord;
	}
	public int getH()
	{
		return h;
	}
	public int getW()
	{
		return w;
	}
	public int getXcord()
	{
		return xcord;
	}
	public int getYcord()
	{
		return ycord;
	}
	public int moveDown()
	{
		return 8;
	}
	public void addYcord(int r)
	{
		ycord+=r;
	}
	public void setYcord(int r)
	{
		ycord=r;
	}
}
