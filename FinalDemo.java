import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;


public class FinalDemo
{
	Scanner sc=new Scanner(System.in);
	ArrayList<Contact> AList = new ArrayList<Contact>();//主要
	ArrayList<Contact> CList = new ArrayList<Contact>();//(排序用)
	int d_name=0,d_birthday=0,d_phone_number=0,d_group=0,d_email=0;//顯示控制(0顯示 1不顯示)
	Contact someone;
	int code=0,s=0,r=0;//s是控制排序方式 ( 0是由1-12 1是由12-1 )
					   //o是即時存檔控制 (0是開 1是關)

//主程式
	public static void main(String[] args)throws IOException
	{
		FinalDemo demo = new FinalDemo();

		demo.login();
		demo.load_contacts();
		demo.main_menu();
	}

//登入程式
	public void login()
	{
		String ID="",password="";

		while(ID.equals("cis")==false||password.equals("1234")==false)
		{
			System.out.println("請輸入帳號密碼");
			System.out.print("帳號:");
			ID=sc.nextLine();
			System.out.print("密碼:");
			password=sc.nextLine();

			if(ID.equals("cis")==true||password.equals("1234")==true)
				System.out.println("*****登入成功*****");
			else
				System.out.print("帳號或密碼錯誤，請重新登入");
		}
	}

//載入通訊錄檔案	
	public void load_contacts() throws IOException
	{
		AList.clear();
		String line="";
		String name="",birthday="",phone_number="",group="",email="";
		FileReader fr = new FileReader("contact.txt");
    	BufferedReader br = new BufferedReader(fr);
    	int count=0;
    	
    	while((line=br.readLine())!=null) //當讀取到一行時
        {
            String[] aArray=line.split("\\s+"); //以空白分割資料
            for(String x:aArray)
            {
                ++count;
                switch(count)
                {
                    case 1:
                        name=x;
                        break;
                    case 2:
                        birthday=x;
                        break;
                    case 3:
                        phone_number=x;
                        break;
                    case 4:
                        group=x;
                        break;
                    case 5:
                        email=x;
                        break;
                }
            }
                someone=new Contact(name,birthday,phone_number,group,email);
                AList.add(someone);
                count=0;
        }
	}

//主畫面選單
	public void main_menu() throws IOException
	{
		System.out.println("_________________________MENU_________________________\n");
		System.out.println("( 1 )使用者功能        ( 2 )管理者功能        ( 3 )離開\n");
		System.out.print("請輸入功能代碼 : ");
		code=sc.nextInt();

		while(true)
		{
			if(code==1)
				user_menu();
			else if(code==2)
				manager_menu();
			else if(code==3)
			{
				System.out.println("Bye Bye");
				System.exit(0);
			}
			else
			{
				System.out.print("輸入錯誤! 請重新輸入 : ");
				code=sc.nextInt();
			}
		}
	}

//使用者功能選單
	public void user_menu() throws IOException
	{
		System.out.println("\n________________________________USER MENU________________________________");
		System.out.println("\n( 1 )新增聯絡人     ( 2 )顯示聯絡人     ( 3 )查詢聯絡人(修改/刪除)");
		System.out.println("\n( 4 )回主畫面");
		System.out.print("\n請輸入功能代碼 : ");
		code=sc.nextInt();

		while(true)
		{
			if(code==1)
				add_contact();
			else if(code==2)
				show_contacts_menu();
			else if(code==3)
				search_contact_menu();
			else if(code==4)
				main_menu();
			else
			{
				System.out.print("輸入錯誤! 請重新輸入 : ");
				code=sc.nextInt();
			}
		}
	}
//管理者功能選單
	public void manager_menu() throws IOException
	{
		System.out.println("\n________________________________MANAGER MENU________________________________");
		System.out.println("\n( 1 )顯示欄位設定     ( 2 )顯示排序設定     ( 3 )即時存檔設定");
		System.out.println("\n( 4 )重新排序並儲存   ( 5 )格式化           ( 6 )回主畫面");
		System.out.print("\n請輸入功能代碼 : ");
		code=sc.nextInt();

		while(true)
		{
			if(code==1)
				display_control();
			else if(code==2)
				set_sorting();
			else if(code==3)
				set_save();
			else if(code==4)
				sorting_and_save();
			else if(code==5)
				delete_all();
			else if(code==6)
				main_menu();
			else
			{
				System.out.print("輸入錯誤! 請重新輸入 : ");
				code=sc.nextInt();
			}
		}
	}
//使用者功能_新增聯絡人
	public void add_contact() throws IOException
	{
		int stop;
		String temp;
		String name="",birthday="",phone_number="",group="",email="";
		System.out.println("\n請輸入新增聯絡人資料");
		System.out.print("\n姓名 : ");
		temp=sc.next();
		stop=0;
		while(stop==0)
		{
			if(temp.length()<20)
			{
				name=temp;
				stop=1;
			}
			else
			{
				System.out.println("輸入有誤! 請重新輸入");
				temp=sc.next();
			}
		}
		System.out.print("\n生日 (範例:六月二號請輸入0602) : ");
		temp=sc.next();
		stop=0;
		while(stop==0)
		{	
			if(check_birth(temp)==true)
			{
				birthday=temp;
				stop=1;
			}
			else
			{
				System.out.println("輸入有誤! 請重新輸入");
				temp=sc.next();
			}
		}
		System.out.print("\n電話號碼 ( 若要輸入市話請依範例 : (07)6772034  ) : ");
		temp=sc.next();
		stop=0;
		while(stop==0)
		{
			if(temp.contains("(")&&temp.contains(")"))//市話
			{
				String test=temp.replace("(","");
				test=test.replace(")","");
				if(is_number(test)==true)
				{
					phone_number=temp;
					stop=1;
				}
				else
				{
					System.out.println("輸入有誤! 請重新輸入");
					temp=sc.next();
				}
			}
			else if(temp.length()==10&&is_number(temp)==true)//手機
			{
				phone_number=temp;
				stop=1;
			}
			else
			{
				System.out.println("輸入有誤! 請重新輸入");
				temp=sc.next();
			}
		}
		System.out.print("\n請選擇聯絡人分類 : ");
		System.out.println("( 1 )Family      ( 2 )Friend      ( 3 )Classmate");
		System.out.println("                   ( 4 )Colleague   ( 5 )Other");
		code=sc.nextInt();
		stop=0;
		while(stop==0)
		{
			switch(code)
			{
				case 1:
					group="Family";
					stop=1;
					break;
				case 2:
					group="Friend";
					stop=1;
					break;
				case 3:
					group="Classmate";
					stop=1;
					break;
				case 4:
					group="Colleague";
					stop=1;
					break;
				case 5:
					group="Other";
					stop=1;
					break;
				default:
					System.out.print("輸入錯誤! 請重新輸入 : ");
					code=sc.nextInt();
			}
		}
		System.out.print("\n電子信箱 : ");
		temp=sc.next();
		stop=0;
		while(stop==0)
		{
			if(temp.contains("@")&&temp.length()>=5)
			{
				email=temp;
				stop=1;
			}
			else
			{
				System.out.println("輸入有誤! 請重新輸入");
				temp=sc.next();
			}
		}
		someone=new Contact(name,birthday,phone_number,group,email);
        AList.add(someone);
        System.out.println("\n***新增成功***");
        if(r==0) 
        	save(AList);
        pause();
        user_menu();
	}

//使用者功能_顯示聯絡人選單
	public void show_contacts_menu() throws IOException
	{
		System.out.print("\n請選擇顯示方式 : ");
		System.out.println("( 1 )所有聯絡人     ( 2 )單一類別聯絡人     ( 3 )回上一頁");
		code=sc.nextInt();
		while(true)
		{
			if(code==1)
				show_all_contacts();
			else if(code==2)
				show_group_contacts();
			else if(code==3)
				user_menu();
			else
			{
				System.out.print("輸入錯誤! 請重新輸入 : ");
				code=sc.nextInt();
			}
		}
	}

//使用者功能_顯示所有聯絡人
	public void show_all_contacts() throws IOException
	{
		Contact temp;
		System.out.print("\n請選擇顯示方式 : ");
		System.out.println("( 1 )單頁顯示              ( 2 )分頁顯示");
		System.out.println("                 ( 3 )單頁顯示(依生日排序)   ( 4 )分頁顯示(依生日排序)   ( 5 )回上一頁");
		code=sc.nextInt();
		while(true)
		{
			if(code==1)//單頁顯示
			{
				column();
				for(int i=0;i<AList.size();i++)
					show_contact(AList,i);
				pause();
				show_contacts_menu();
			}
			else if(code==2)//分頁顯示
				show_all_contacts_page(AList);
			else if(code==3)//單頁顯示(依生日排序)
			{
				sorting();
				column();
				for(int i=0;i<CList.size();i++)
					show_contact(CList,i);
				pause();
				show_contacts_menu();
			}
			else if(code==4)//分頁顯示(依生日排序)
			{
				sorting();
				show_all_contacts_page(CList);
			}
			else if(code==5)//回上一頁
				show_contacts_menu();
			else
			{
				System.out.print("輸入錯誤! 請重新輸入 : ");
				code=sc.nextInt();
			}
		}
	}
//使用者功能_顯示單一類別聯絡人_選擇聯絡人分類
	public void show_group_contacts() throws IOException
	{
		System.out.print("\n請選擇聯絡人分類 : ");
		System.out.println("( 1 )Family      ( 2 )Friend      ( 3 )Classmate");
		System.out.println("                   ( 4 )Colleague   ( 5 )Other");
		code=sc.nextInt();
		String choose="";

		while(true)
		{
			switch(code)
            {
            	case 1:
            		choose="Family";
            		show_one_group(choose);
            	case 2:
            		choose="Friend";
            		show_one_group(choose);
            	case 3:
            		choose="Classmate";
            		show_one_group(choose);
            	case 4:
            		choose="Colleague";
            		show_one_group(choose);
            	case 5:
            		choose="Other";
            		show_one_group(choose);
				default:
					System.out.print("輸入錯誤! 請重新輸入 : ");
					code=sc.nextInt();
            }
		}
	}

//印出聯絡人
	public void show_contact(ArrayList<Contact> List,int i)
	{
		//System.out.print(i+"    ");
		if(d_name==0)
			System.out.printf("%-6s",List.get(i).name);
		else
			System.out.print("      ");
		if(d_birthday==0)
			System.out.printf("%-7s",List.get(i).birthday);
		else
			System.out.print("       ");
		if(d_phone_number==0)
			System.out.printf("%-15s",List.get(i).phone_number);
		else
			System.out.print("               ");
		if(d_group==0)
			System.out.printf("%-12s",List.get(i).group);
		else
			System.out.print("            ");
		if(d_email==0)
			System.out.printf("%s\n",List.get(i).email);
	}

//分頁顯示聯絡人
	public void show_all_contacts_page(ArrayList List) throws IOException
	{
		column();
		int now=0,next=1,stop=0,j=0;
		while(stop==0)
		{
			for(j=5*now;j<5*next;j++)
			{
				if(j<List.size())
					show_contact(List,j);
				else
				{
					System.out.println("---已到最底---");
					stop=1;
					break;
				}
			}
			System.out.println("( 1 )上一頁     ( 2 )下一頁     ( 3 )輸入頁碼     ( 4 )返回顯示聯絡人選單");
			int code1=sc.nextInt();
			while(true)
			{
				if(code1==1)
				{
					if(now==0)
					{
						System.out.println("已經是第一頁，請重新輸入 : ");
						code1=sc.nextInt();
					}
					else if(now!=0&&stop==1)
					{
						now=now-1;
						next=next-1;
						stop=0;
						break;
					}
					else
					{
						now=now-1;
						next=next-1;
						break;
					}
				}
				else if(code1==2)
				{
					if(j<=List.size()-1)//如果存在下一筆資料
					{
						now=now+1;
						next=next+1;
						break;
					}
					else
					{
						System.out.println("已經是最後一頁，請重新輸入 : ");
						code1=sc.nextInt();
					}

				}
				else if(code1==3)
				{
					System.out.println("請輸入頁碼 : ");
					int page=sc.nextInt();
					int has;
					has=0;
					//判斷該頁是否存在
					while(true)
					{
						if(List.size()/5>=page)
						{
							next=page;
							now=page-1;
							has=1;
							break;
						}
						else if(List.size()/5+1==page&&List.size()%5!=0)
						{
							next=page;
							now=page-1;
							has=1;
							break;
						}
						else
						{
							System.out.print("無此頁碼! 請重新輸入頁碼 : ");
							page=sc.nextInt();
						}
					}
					if(has==1)
						break;
				}
				else if(code1==4)
						show_contacts_menu();
				else
				{
					System.out.print("輸入錯誤! 請重新輸入 : ");
					code1=sc.nextInt();
				}
			}					
					

		}
	}

//單一類別聯絡人顯示_選擇顯示方式
	public void show_one_group(String choose) throws IOException
	{
		Contact temp;
		System.out.print("\n請選擇顯示方式 : ");
		System.out.println("( 1 )單頁顯示              ( 2 )分頁顯示");
		System.out.println("                 ( 3 )單頁顯示(依生日排序)   ( 4 )分頁顯示(依生日排序)   ( 5 )回上一頁");
		int code1=sc.nextInt();
		while(true)
		{
			if(code1==1)//單頁顯示
			{
				column();
				for(int i=0;i<AList.size();i++)
					if(AList.get(i).group.equals(choose)==true)
						show_contact(AList,i);
				pause();
				show_contacts_menu();
			}
			else if(code1==2)//分頁顯示
				show_one_group_page(AList,choose);
			else if(code1==3)//單頁顯示(依生日排序)
			{
				sorting();
				column();
				for(int i=0;i<CList.size();i++)
					if(CList.get(i).group.equals(choose)==true)
						show_contact(CList,i);
				pause();
				show_contacts_menu();
			}
			else if(code1==4)//分頁顯示(依生日排序)
			{
				sorting();
				show_one_group_page(CList,choose);
			}
			else if(code1==5)//回上一頁
				show_group_contacts();
			else
			{
				System.out.print("輸入錯誤! 請重新輸入 : ");
				code1=sc.nextInt();
			}
		}
	}

//單一類別聯絡人分頁顯示前置作業
	public void show_one_group_page(ArrayList<Contact> List,String choose)throws IOException
	{
		ArrayList<Contact> BList = new ArrayList<Contact>();//取出所有該分類的聯絡人放入Blist
		for(int j=0;j<List.size();j++)
			if(List.get(j).group.equals(choose)==true)
       	 		BList.add(List.get(j));

		show_all_contacts_page(BList);
	}

//查詢聯絡人選單
	public void search_contact_menu() throws IOException
	{
		System.out.println("\n_______________________________查詢聯絡人_______________________________");
		System.out.print("\n請輸入代碼 : ");
		System.out.println("( 1 )查詢並修改/刪除     ( 2 )簡單搜尋     ( 3 )回上一頁");
		code=sc.nextInt();
		while(true)
		{
			if(code==1)
				search_one();
			else if(code==2)
				simple_search();
			else if(code==3)
				user_menu();
			else
			{
				System.out.print("輸入錯誤! 請重新輸入 : ");
				code=sc.nextInt();
			}
		}
	}
//查詢聯絡人(修改/刪除)
	public void search_one() throws IOException
	{
		String str="";
		int i=0,get=0,code1;
		System.out.print("\n請選擇查詢方式 : ");
		System.out.println("( 1 )依姓名     ( 2 )依電話號碼     ( 3 )回上一頁");
		code=sc.nextInt();
		while(true)
		{
			if(code==1)//依姓名
			{
				System.out.print("請輸入欲查詢的姓名 : ");
				str=sc.next();
				for(i=0;i<AList.size();i++)
					if(AList.get(i).name.equals(str))
					{
						System.out.println("查尋結果 : ");
						column();
						show_contact(AList,i);
						get=1;
						break;
					}
				if(get==0)
				{
					System.out.print("\n查無此人!");
					pause();
					search_contact_menu();
				}
				else//有找到
				edit_or_delete(i);
			}
			else if(code==2)//依電話號碼
			{
				System.out.print("請輸入欲查詢的聯絡人之電話號碼 : ");
				str=sc.next();
				for(i=0;i<AList.size();i++)
					if(AList.get(i).phone_number.equals(str))
					{
						System.out.println("查尋結果 : ");
						column();
						show_contact(AList,i);
						get=1;
						break;
					}
				if(get==0)
				{
					System.out.print("\n查無此人!");
					pause();
					search_contact_menu();
				}
				else//有找到
					edit_or_delete(i);
			}
			else if(code==3)
				search_contact_menu();
			else
			{
				System.out.print("輸入錯誤! 請重新輸入 : ");
				code=sc.nextInt();
			}
		}
	}
//修改與刪除選單
	public void edit_or_delete(int i) throws IOException
	{
		System.out.print("請選擇要執行的動作 : ");
		System.out.println("( 1 )修改聯絡人資料     ( 2 )刪除聯絡人     ( 3 )回上一頁");
		int code1=sc.nextInt();
		while(true)
		{
			if(code1==1)
				edit_contact(i);
			else if(code1==2)
			{
				System.out.println("確定刪除? 輸入 (Y)刪除 (N)取消");
				String check=sc.next();
				while(true)
				{
					if(check.equalsIgnoreCase("Y"))
					{
						AList.remove(i);
						System.out.print("\n***已刪除***");
						if(r==0)
							save(AList);
						pause();
						search_contact_menu();
					}
					else if(check.equalsIgnoreCase("N"))
					{
						System.out.print("請選擇要執行的動作 : ");
						System.out.println("( 1 )修改聯絡人資料     ( 2 )刪除聯絡人     ( 3 )回上一頁");
						code1=sc.nextInt();
						break;
					}
					else
					{
						System.out.print("輸入錯誤! 請重新輸入 : ");
						check=sc.next();
					}
				}
			}
			else if(code1==3)
				search_contact_menu();
			else
			{
				System.out.print("輸入錯誤! 請重新輸入 : ");
				code1=sc.nextInt();
			}
		}
	}
//簡單查詢
	public void simple_search() throws IOException
	{
		String str="";
		int i=0,get=0;
		System.out.print("\n請選擇查詢方式 : ");
		System.out.println("( 1 )依姓名     ( 2 )依電話號碼     ( 3 )回上一頁");
		code=sc.nextInt();
		while(true)
		{
			if(code==1)//依姓名
			{
				System.out.print("請輸入欲查詢的姓名 : ");
				str=sc.next();
				int L=str.length();
				for(i=0;i<AList.size();i++)
				{
					if(AList.get(i).name.length()>=L)
						if(AList.get(i).name.substring(0,L).equals(str))
						{
							if(get==0)//讓他只印一次就好
							{
								System.out.println("\n查尋結果 : ");
								column();
							}
							show_contact(AList,i);
							get=1;
						}
				}
				if(get==0)
				{
					System.out.print("\n查無此人!");
					pause();
					search_contact_menu();
				}
				else//有查到
				{
					pause();
					search_contact_menu();
				}
			}
			else if(code==2)
			{
				System.out.print("請輸入欲查詢的號碼 : ");
				str=sc.next();
				int L=str.length();
				for(i=0;i<AList.size();i++)
				{
					if(AList.get(i).phone_number.length()>=L)
						if(AList.get(i).phone_number.substring(0,L).equals(str))
						{
							if(get==0)//讓他只印一次就好
							{
								System.out.println("\n查尋結果 : ");
								column();
							}
							show_contact(AList,i);
							get=1;
						}
				}
				if(get==0)
				{
					System.out.print("\n查無此人!");
					pause();
					search_contact_menu();
				}
				else//有查到
				{
					pause();
					search_contact_menu();
				}
			}
			else if(code==3)
			{
				pause();
				search_contact_menu();
			}
			else
			{
				System.out.print("輸入錯誤! 請重新輸入 : ");
				code=sc.nextInt();
			}
		}
	}
//編輯聯絡人
	public void edit_contact(int i) throws IOException
	{
		String temp="";
		System.out.print("\n請輸入欲修改的項目 : ");
		System.out.println("( 1 )姓名   ( 2 )生日       ( 3 )電話號碼");
		System.out.println("                      ( 4 )分類   ( 5 )電子信箱   ( 6 )回上一頁");
		code=sc.nextInt();
		System.out.print("\n");
		int stop;
		stop=0;
		while(stop==0)
		{
			switch(code)
            {
            	case 1:
            		System.out.print("輸入姓名：");
            		temp=sc.next();
            		if(temp.length()<20)
            			{
            			AList.get(i).name=temp;
            			System.out.print("\n***設定成功***");
            			if(r==0)
							save(AList);
            			stop=1;
            			}
            		else
            			System.out.print("\n輸入有誤! 請重新");
            		break;
            	case 2:
            		System.out.print("輸入生日 (範例:六月二號請輸入0602) ：");
            		temp=sc.next();
            		if(check_birth(temp)==true)
						{
							AList.get(i).birthday=temp;
							System.out.print("\n***設定成功***");
							if(r==0)
								save(AList);
            				stop=1;
						}
					else
							System.out.println("輸入有誤! 請重新");
            		break;
            	case 3:
            		System.out.print("輸入電話號碼：");
            		temp=sc.next();
            		if(temp.contains("(")&&temp.contains(")"))//市話
					{
						String test=temp.replace("(","");
						test=test.replace(")","");
						if(is_number(test)==true)
						{
							AList.get(i).phone_number=temp;
							System.out.print("\n***設定成功***");
							if(r==0)
								save(AList);
							stop=1;
						}
						else
							System.out.println("輸入有誤! 請重新");
					}
					else if(temp.length()==10&&is_number(temp)==true)//手機
					{
						AList.get(i).phone_number=temp;
						System.out.print("\n***設定成功***");
						if(r==0)
							save(AList);
						stop=1;
					}
					else
						System.out.println("輸入有誤! 請重新");
            		break;
            	case 4:
            		System.out.print("選擇聯絡人分類 : ");
					System.out.println("( 1 )Family   ( 2 )Friend      ( 3 )Classmate");
					System.out.println("                   ( 4 )Colleague   ( 5 )Other");
					int code2=sc.nextInt();
					int stop1=0;
					while(stop1==0)
					{
						switch(code2)
						{
							case 1:
								AList.get(i).group="Family";
								stop1=1;
								break;
							case 2:
								AList.get(i).group="Friend";
								stop1=1;
								break;
							case 3:
								AList.get(i).group="Classmate";
								stop1=1;
								break;
							case 4:
								AList.get(i).group="Colleague";
								stop1=1;
								break;
							case 5:
								AList.get(i).group="Other";
								stop1=1;
								break;
							default:
								System.out.print("輸入錯誤! 請重新輸入 : ");
								code2=sc.nextInt();
						}
					}
					System.out.print("\n***設定成功***");
					if(r==0)
						save(AList);
					stop=1;
					break;
            	case 5:
            		System.out.print("輸入電子信箱：");
            		temp=sc.next();
            		if(temp.contains("@")&&temp.length()>=5)
					{
						AList.get(i).email=temp;
            			System.out.print("\n***設定成功***");
            			if(r==0)
							save(AList);
            			stop=1;
					}
					else
						System.out.println("輸入有誤! 請重新");
            		break;
            	case 6://回上一頁(查詢)
            		search_contact_menu();
				default:
					System.out.print("輸入錯誤! 請重新輸入 : ");
					code=sc.nextInt();
            }
		}
		pause();
		edit_contact(i);
	}

//排序(依生日)
	public void sorting()
	{
		CList.clear();
		Contact temp;
		for(int i=0;i<AList.size();i++)//將AList的所有聯絡人放進CList
			CList.add(AList.get(i));
		if(s==0)//1-12
			for(int i=0;i<CList.size()-1;i++)
				for(int j=0;j<CList.size()-1;j++)
					{
						if(CList.get(j+1).get_month()<CList.get(j).get_month())
						{
							temp=CList.get(j);
							CList.remove(j);
							CList.add(j+1,temp);
						}
						else if(CList.get(j+1).get_month()==CList.get(j).get_month())//月份相同
							if(CList.get(j+1).get_day()<CList.get(j).get_day())
							{
								temp=CList.get(j);
								CList.remove(j);
								CList.add(j+1,temp);
							}
					}
		else//12-1
			for(int i=0;i<CList.size()-1;i++)
				for(int j=0;j<CList.size()-1;j++)
					{
						if(CList.get(j+1).get_month()>CList.get(j).get_month())
						{
							temp=CList.get(j);
							CList.remove(j);
							CList.add(j+1,temp);
						}
						else if(CList.get(j+1).get_month()==CList.get(j).get_month())//月份相同
							if(CList.get(j+1).get_day()>CList.get(j).get_day())
							{
								temp=CList.get(j);
								CList.remove(j);
								CList.add(j+1,temp);
							}
					}
	}
//管理功能_顯示欄位設定
	public void display_control() throws IOException
	{
		while(true)
		{
			System.out.println("目前設定如下 ( ●顯示   ○不顯示 )");
			show_display_situation();
			System.out.println("請輸入要更改的欄位 : ");
			System.out.println("( 1 )姓名   ( 2 )生日       ( 3 )電話號碼");
			System.out.println("( 4 )分類   ( 5 )電子信箱   ( 6 )回上一頁");
			code=sc.nextInt();
			switch(code)
            {
            	case 1:
            		if(d_name==0)
            			d_name=1;
            		else
            			d_name=0;
            		break;
            	case 2:
            		if(d_birthday==0)
            			d_birthday=1;
            		else
            			d_birthday=0;
            		break;
            	case 3:
            		if(d_phone_number==0)
            			d_phone_number=1;
            		else
            			d_phone_number=0;
            		break;
            	case 4:
            		if(d_group==0)
            			d_group=1;
            		else
            			d_group=0;
            		break;
            	case 5:
            		if(d_email==0)
            			d_email=1;
            		else
            			d_email=0;
            		break;
            	case 6:
            		manager_menu();
            	default:
            		System.out.print("輸入錯誤! 請重新輸入");
            }
		}
	}
//管理功能_設定排序
	public void set_sorting() throws IOException
	{
		while(true)
		{
			System.out.print("目前設定為");
			if(s==0)
				System.out.println(" 1月→12月 排序");
			else
				System.out.println(" 12月→1月 排序");
			System.out.println("請輸入代碼 :   ( 1 )改變排序方式   ( 2 )回上一頁");
			code=sc.nextInt();
			if(code==1)
			{
				if(s==0)
					s=1;
				else
					s=0;
			}
			else if(code==2)
				manager_menu();
			else
				System.out.println("輸入錯誤! 請重新輸入");
		}
	}
//列印目前欄位設定
	public void show_display_situation()
	{
		System.out.println(" 姓名  生日  電話號碼  分類  電子信箱");
		if (d_name==0)
			System.out.print("  ●  ");
		else
			System.out.print("  ○  ");
		if (d_birthday==0)
			System.out.print("  ●  ");
		else
			System.out.print("  ○  ");
		if (d_phone_number==0)
			System.out.print("    ●   ");
		else
			System.out.print("    ○   ");
		if (d_group==0)
			System.out.print("   ●  ");
		else
			System.out.print("   ○  ");
		if (d_email==0)
			System.out.println("     ●    ");
		else
			System.out.println("     ○    ");
	}
//儲存檔案
	public void save(ArrayList<Contact> List) throws IOException
	{
		PrintWriter pw=new PrintWriter(new FileOutputStream("contact.txt"));
		for(int i=0;i<List.size();i++)
		{
		pw.printf("%-6s",List.get(i).name);
		pw.printf("%-7s",List.get(i).birthday);
		pw.printf("%-15s",List.get(i).phone_number);
		pw.printf("%-12s",List.get(i).group);
		pw.printf("%s",List.get(i).email);
		pw.println("");
		}
		pw.close();
		load_contacts();
	}
//即時存檔設定
	public void set_save() throws IOException
	{
		while(true)
		{
			System.out.print("目前即時存檔設定為 : ");
			if(r==0)
				System.out.println("開");
			else
				System.out.println("關");
			System.out.println("請輸入代碼 :   ( 1 )改變存檔方式   ( 2 )回上一頁");
			code=sc.nextInt();
			if(code==1)
			{
				if(r==0)//目前為 開
					r=1;
				else//目前為 關
				{
					save(AList);
					r=0;
				}
			}
			else if(code==2)
				manager_menu();
			else
				System.out.println("輸入錯誤! 請重新輸入");
		}
	}
//重新排序並儲存
	public void sorting_and_save() throws IOException
	{
		while(true)
		{
			int temp_s=s;
			System.out.println("依生日排序並儲存");
			System.out.println("請選擇 : ( 1 )1月→12月   ( 2 )12月→1月   ( 3 )回上一頁");
			code=sc.nextInt();
			if(code==1)
			{
				s=0;
				sorting();
				save(CList);
				System.out.println("***已排序並儲存***");
				s=temp_s;
				pause();
				manager_menu();
			}
			else if(code==2)
			{
				s=1;
				sorting();
				save(CList);
				System.out.println("***已排序並儲存***");
				s=temp_s;
				pause();
				manager_menu();
			}
			else if(code==3)
				manager_menu();
			else
				System.out.println("輸入錯誤! 請重新輸入");
		}
	}
//格式化
	public void delete_all() throws IOException
	{
		System.out.println("確定刪除? 輸入 (Y)刪除 (N)取消");
				String check=sc.next();
				while(true)
				{
					if(check.equalsIgnoreCase("Y"))
					{
						AList.clear();
						save(AList);
						System.out.println("***成功格式化***");
						pause();
						manager_menu();
					}
					else if(check.equalsIgnoreCase("N"))
						manager_menu();
					else
					{
						System.out.print("輸入錯誤! 請重新輸入 : ");
						check=sc.next();
					}
				}
	}

//判斷數字
    public boolean is_number(String str)
    {
        for(int i=0;i<str.length();i++)
        {
            int chr=str.charAt(i);
            if(chr<48 || chr>57)
                return false;
        }
        return true;
    }

//判斷生日
    public boolean check_birth(String str)
    {
			if(str.length()==4)
			{
				int month=Integer.valueOf(str.substring(0,2));
				if(month==1||month==3||month==5||month==7||month==8||month==10||month==12)
				{
					if(Integer.valueOf(str.substring(2))>=1&&Integer.valueOf(str.substring(2))<=31)
						return true;
					else
						return false;
				}
				else if(month==4||month==6||month==9||month==11)
				{
					if(Integer.valueOf(str.substring(2))>=1&&Integer.valueOf(str.substring(2))<=30)
						return true;
					else
						return false;
				}
				else if(month==2)
				{
					if(Integer.valueOf(str.substring(2))>=1&&Integer.valueOf(str.substring(2))<=29)
						return true;
					else
						return false;
				}
				else
					return false;
			}
			else
				return false;
    }

//暫停
	public void pause()
	{
		System.out.print("\n按Enter鍵返回上一頁..");
		try
		{
         	System.in.read();
		}
		catch(Exception e) {}
	}
//顯示欄位
	public void column()
	{
		System.out.println("\n 姓名    生日   電話號碼      分類        電子信箱");
		System.out.println("------------------------------------------------------------------");
	}
}