public class Contact
{
	public String name;
	public String birthday;
	public String phone_number;
	public String group;
	public String email;


	public Contact(String A1,String A2,String A3,String A4,String A5)
	{
		name=A1;
		birthday=A2;
		phone_number=A3;
		group=A4;
		email=A5;
	}

	public int get_month()
	{
		return Integer.valueOf(birthday.substring(0,2));
	}

	public int get_day()
	{
		return Integer.valueOf(birthday.substring(2));
	}
}