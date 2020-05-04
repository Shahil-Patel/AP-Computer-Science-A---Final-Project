public class Text
{
	private int h;
	private int w;
	private String message;
	public Text(String message,int h,int w)
	{
		this.message=message;
		this.h=h;
		this.w=w;
	}
	public int getH()
	{
		return h;
	}
	public int getW()
	{
		return w;
	}
	public String getMsg()
	{
		return message;
	}
}