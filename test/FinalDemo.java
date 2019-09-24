import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;


public class FinalDemo
{
	Scanner sc=new Scanner(System.in);
	ArrayList<Contact> AList = new ArrayList<Contact>();//�D�n
	ArrayList<Contact> CList = new ArrayList<Contact>();//(�Ƨǥ�)
	int d_name=0,d_birthday=0,d_phone_number=0,d_group=0,d_email=0;//��ܱ���(0��� 1�����)
	Contact someone;
	int code=0,s=0,r=0;//s�O����ƧǤ覡 ( 0�O��1-12 1�O��12-1 )
					   //o�O�Y�ɦs�ɱ��� (0�O�} 1�O��)

//�D�{��
	public static void main(String[] args)throws IOException
	{
		FinalDemo demo = new FinalDemo();

		//demo.login();
		demo.load_contacts();
		demo.main_menu();
	}

//�n�J�{��
	public void login()
	{
		String ID="",password="";

		while(ID.equals("cis")==false||password.equals("1234")==false)
		{
			System.out.println("�п�J�b���K�X");
			System.out.print("�b��:");
			ID=sc.nextLine();
			System.out.print("�K�X:");
			password=sc.nextLine();

			if(ID.equals("cis")==true||password.equals("1234")==true)
				System.out.println("*****�n�J���\*****");
			else
				System.out.print("�b���αK�X���~�A�Э��s�n�J");
		}
	}

//���J�q�T���ɮ�	
	public void load_contacts() throws IOException
	{
		AList.clear();
		String line="";
		String name="",birthday="",phone_number="",group="",email="";
		FileReader fr = new FileReader("contact.txt");
    	BufferedReader br = new BufferedReader(fr);
    	int count=0;
    	
    	while((line=br.readLine())!=null) //��Ū����@���
        {
            String[] aArray=line.split("\\s+"); //�H�ťդ��θ��
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

//�D�e�����
	public void main_menu() throws IOException
	{
		System.out.println("_________________________MENU_________________________\n");
		System.out.println("( 1 )�ϥΪ̥\��        ( 2 )�޲z�̥\��        ( 3 )���}\n");
		System.out.print("�п�J�\��N�X : ");
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
				System.out.print("��J���~! �Э��s��J : ");
				code=sc.nextInt();
			}
		}
	}

//�ϥΪ̥\����
	public void user_menu() throws IOException
	{
		System.out.println("\n________________________________USER MENU________________________________");
		System.out.println("\n( 1 )�s�W�p���H     ( 2 )����p���H     ( 3 )�d���p���H(�ק�/�R��)");
		System.out.println("\n( 4 )�^�D�e��");
		System.out.print("\n�п�J�\��N�X : ");
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
				System.out.print("��J���~! �Э��s��J : ");
				code=sc.nextInt();
			}
		}
	}
//�޲z�̥\����
	public void manager_menu() throws IOException
	{
		System.out.println("\n________________________________MANAGER MENU________________________________");
		System.out.println("\n( 1 )������]�w     ( 2 )��ܱƧǳ]�w     ( 3 )�Y�ɦs�ɳ]�w");
		System.out.println("\n( 4 )���s�ƧǨ��x�s   ( 5 )�榡��           ( 6 )�^�D�e��");
		System.out.print("\n�п�J�\��N�X : ");
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
				System.out.print("��J���~! �Э��s��J : ");
				code=sc.nextInt();
			}
		}
	}
//�ϥΪ̥\��_�s�W�p���H
	public void add_contact() throws IOException
	{
		int stop;
		String temp;
		String name="",birthday="",phone_number="",group="",email="";
		System.out.println("\n�п�J�s�W�p���H���");
		System.out.print("\n�m�W : ");
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
				System.out.println("��J���~! �Э��s��J");
				temp=sc.next();
			}
		}
		System.out.print("\n�ͤ� (�d��:����G���п�J0602) : ");
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
				System.out.println("��J���~! �Э��s��J");
				temp=sc.next();
			}
		}
		System.out.print("\n�q�ܸ��X ( �Y�n��J���ܽШ̽d�� : (07)6772034  ) : ");
		temp=sc.next();
		stop=0;
		while(stop==0)
		{
			if(temp.contains("(")&&temp.contains(")"))//����
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
					System.out.println("��J���~! �Э��s��J");
					temp=sc.next();
				}
			}
			else if(temp.length()==10&&is_number(temp)==true)//���
			{
				phone_number=temp;
				stop=1;
			}
			else
			{
				System.out.println("��J���~! �Э��s��J");
				temp=sc.next();
			}
		}
		System.out.print("\n�п���p���H���� : ");
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
					System.out.print("��J���~! �Э��s��J : ");
					code=sc.nextInt();
			}
		}
		System.out.print("\n�q�l�H�c : ");
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
				System.out.println("��J���~! �Э��s��J");
				temp=sc.next();
			}
		}
		someone=new Contact(name,birthday,phone_number,group,email);
        AList.add(someone);
        System.out.println("\n***�s�W���\***");
        if(r==0) 
        	save(AList);
        pause();
        user_menu();
	}

//�ϥΪ̥\��_����p���H���
	public void show_contacts_menu() throws IOException
	{
		System.out.print("\n�п����ܤ覡 : ");
		System.out.println("( 1 )�Ҧ��p���H     ( 2 )��@���O�p���H     ( 3 )�^�W�@��");
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
				System.out.print("��J���~! �Э��s��J : ");
				code=sc.nextInt();
			}
		}
	}

//�ϥΪ̥\��_��ܩҦ��p���H
	public void show_all_contacts() throws IOException
	{
		Contact temp;
		System.out.print("\n�п����ܤ覡 : ");
		System.out.println("( 1 )�歶���              ( 2 )�������");
		System.out.println("                 ( 3 )�歶���(�̥ͤ�Ƨ�)   ( 4 )�������(�̥ͤ�Ƨ�)   ( 5 )�^�W�@��");
		code=sc.nextInt();
		while(true)
		{
			if(code==1)//�歶���
			{
				column();
				for(int i=0;i<AList.size();i++)
					show_contact(AList,i);
				pause();
				show_contacts_menu();
			}
			else if(code==2)//�������
				show_all_contacts_page(AList);
			else if(code==3)//�歶���(�̥ͤ�Ƨ�)
			{
				sorting();
				column();
				for(int i=0;i<CList.size();i++)
					show_contact(CList,i);
				pause();
				show_contacts_menu();
			}
			else if(code==4)//�������(�̥ͤ�Ƨ�)
			{
				sorting();
				show_all_contacts_page(CList);
			}
			else if(code==5)//�^�W�@��
				show_contacts_menu();
			else
			{
				System.out.print("��J���~! �Э��s��J : ");
				code=sc.nextInt();
			}
		}
	}
//�ϥΪ̥\��_��ܳ�@���O�p���H_����p���H����
	public void show_group_contacts() throws IOException
	{
		System.out.print("\n�п���p���H���� : ");
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
					System.out.print("��J���~! �Э��s��J : ");
					code=sc.nextInt();
            }
		}
	}

//�L�X�p���H
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

//��������p���H
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
					System.out.println("---�w��̩�---");
					stop=1;
					break;
				}
			}
			System.out.println("( 1 )�W�@��     ( 2 )�U�@��     ( 3 )��J���X     ( 4 )��^����p���H���");
			int code1=sc.nextInt();
			while(true)
			{
				if(code1==1)
				{
					if(now==0)
					{
						System.out.println("�w�g�O�Ĥ@���A�Э��s��J : ");
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
					if(j<=List.size()-1)//�p�G�s�b�U�@�����
					{
						now=now+1;
						next=next+1;
						break;
					}
					else
					{
						System.out.println("�w�g�O�̫�@���A�Э��s��J : ");
						code1=sc.nextInt();
					}

				}
				else if(code1==3)
				{
					System.out.println("�п�J���X : ");
					int page=sc.nextInt();
					int has;
					has=0;
					//�P�_�ӭ��O�_�s�b
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
							System.out.print("�L�����X! �Э��s��J���X : ");
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
					System.out.print("��J���~! �Э��s��J : ");
					code1=sc.nextInt();
				}
			}					
					

		}
	}

//��@���O�p���H���_�����ܤ覡
	public void show_one_group(String choose) throws IOException
	{
		Contact temp;
		System.out.print("\n�п����ܤ覡 : ");
		System.out.println("( 1 )�歶���              ( 2 )�������");
		System.out.println("                 ( 3 )�歶���(�̥ͤ�Ƨ�)   ( 4 )�������(�̥ͤ�Ƨ�)   ( 5 )�^�W�@��");
		int code1=sc.nextInt();
		while(true)
		{
			if(code1==1)//�歶���
			{
				column();
				for(int i=0;i<AList.size();i++)
					if(AList.get(i).group.equals(choose)==true)
						show_contact(AList,i);
				pause();
				show_contacts_menu();
			}
			else if(code1==2)//�������
				show_one_group_page(AList,choose);
			else if(code1==3)//�歶���(�̥ͤ�Ƨ�)
			{
				sorting();
				column();
				for(int i=0;i<CList.size();i++)
					if(CList.get(i).group.equals(choose)==true)
						show_contact(CList,i);
				pause();
				show_contacts_menu();
			}
			else if(code1==4)//�������(�̥ͤ�Ƨ�)
			{
				sorting();
				show_one_group_page(CList,choose);
			}
			else if(code1==5)//�^�W�@��
				show_group_contacts();
			else
			{
				System.out.print("��J���~! �Э��s��J : ");
				code1=sc.nextInt();
			}
		}
	}

//��@���O�p���H������ܫe�m�@�~
	public void show_one_group_page(ArrayList<Contact> List,String choose)throws IOException
	{
		ArrayList<Contact> BList = new ArrayList<Contact>();//���X�Ҧ��Ӥ������p���H��JBlist
		for(int j=0;j<List.size();j++)
			if(List.get(j).group.equals(choose)==true)
       	 		BList.add(List.get(j));

		show_all_contacts_page(BList);
	}

//�d���p���H���
	public void search_contact_menu() throws IOException
	{
		System.out.println("\n_______________________________�d���p���H_______________________________");
		System.out.print("\n�п�J�N�X : ");
		System.out.println("( 1 )�d�ߨíק�/�R��     ( 2 )²��j�M     ( 3 )�^�W�@��");
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
				System.out.print("��J���~! �Э��s��J : ");
				code=sc.nextInt();
			}
		}
	}
//�d���p���H(�ק�/�R��)
	public void search_one() throws IOException
	{
		String str="";
		int i=0,get=0,code1;
		System.out.print("\n�п�ܬd�ߤ覡 : ");
		System.out.println("( 1 )�̩m�W     ( 2 )�̹q�ܸ��X     ( 3 )�^�W�@��");
		code=sc.nextInt();
		while(true)
		{
			if(code==1)//�̩m�W
			{
				System.out.print("�п�J���d�ߪ��m�W : ");
				str=sc.next();
				for(i=0;i<AList.size();i++)
					if(AList.get(i).name.equals(str))
					{
						System.out.println("�d�M���G : ");
						column();
						show_contact(AList,i);
						get=1;
						break;
					}
				if(get==0)
				{
					System.out.print("\n�d�L���H!");
					pause();
					search_contact_menu();
				}
				else//�����
				edit_or_delete(i);
			}
			else if(code==2)//�̹q�ܸ��X
			{
				System.out.print("�п�J���d�ߪ��p���H���q�ܸ��X : ");
				str=sc.next();
				for(i=0;i<AList.size();i++)
					if(AList.get(i).phone_number.equals(str))
					{
						System.out.println("�d�M���G : ");
						column();
						show_contact(AList,i);
						get=1;
						break;
					}
				if(get==0)
				{
					System.out.print("\n�d�L���H!");
					pause();
					search_contact_menu();
				}
				else//�����
					edit_or_delete(i);
			}
			else if(code==3)
				search_contact_menu();
			else
			{
				System.out.print("��J���~! �Э��s��J : ");
				code=sc.nextInt();
			}
		}
	}
//�ק�P�R�����
	public void edit_or_delete(int i) throws IOException
	{
		System.out.print("�п�ܭn���檺�ʧ@ : ");
		System.out.println("( 1 )�ק��p���H���     ( 2 )�R���p���H     ( 3 )�^�W�@��");
		int code1=sc.nextInt();
		while(true)
		{
			if(code1==1)
				edit_contact(i);
			else if(code1==2)
			{
				System.out.println("�T�w�R��? ��J (Y)�R�� (N)����");
				String check=sc.next();
				while(true)
				{
					if(check.equalsIgnoreCase("Y"))
					{
						AList.remove(i);
						System.out.print("\n***�w�R��***");
						if(r==0)
							save(AList);
						pause();
						search_contact_menu();
					}
					else if(check.equalsIgnoreCase("N"))
					{
						System.out.print("�п�ܭn���檺�ʧ@ : ");
						System.out.println("( 1 )�ק��p���H���     ( 2 )�R���p���H     ( 3 )�^�W�@��");
						code1=sc.nextInt();
						break;
					}
					else
					{
						System.out.print("��J���~! �Э��s��J : ");
						check=sc.next();
					}
				}
			}
			else if(code1==3)
				search_contact_menu();
			else
			{
				System.out.print("��J���~! �Э��s��J : ");
				code1=sc.nextInt();
			}
		}
	}
//²��d��
	public void simple_search() throws IOException
	{
		String str="";
		int i=0,get=0;
		System.out.print("\n�п�ܬd�ߤ覡 : ");
		System.out.println("( 1 )�̩m�W     ( 2 )�̹q�ܸ��X     ( 3 )�^�W�@��");
		code=sc.nextInt();
		while(true)
		{
			if(code==1)//�̩m�W
			{
				System.out.print("�п�J���d�ߪ��m�W : ");
				str=sc.next();
				int L=str.length();
				for(i=0;i<AList.size();i++)
				{
					if(AList.get(i).name.length()>=L)
						if(AList.get(i).name.substring(0,L).equals(str))
						{
							if(get==0)//���L�u�L�@���N�n
							{
								System.out.println("\n�d�M���G : ");
								column();
							}
							show_contact(AList,i);
							get=1;
						}
				}
				if(get==0)
				{
					System.out.print("\n�d�L���H!");
					pause();
					search_contact_menu();
				}
				else//���d��
				{
					pause();
					search_contact_menu();
				}
			}
			else if(code==2)
			{
				System.out.print("�п�J���d�ߪ����X : ");
				str=sc.next();
				int L=str.length();
				for(i=0;i<AList.size();i++)
				{
					if(AList.get(i).phone_number.length()>=L)
						if(AList.get(i).phone_number.substring(0,L).equals(str))
						{
							if(get==0)//���L�u�L�@���N�n
							{
								System.out.println("\n�d�M���G : ");
								column();
							}
							show_contact(AList,i);
							get=1;
						}
				}
				if(get==0)
				{
					System.out.print("\n�d�L���H!");
					pause();
					search_contact_menu();
				}
				else//���d��
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
				System.out.print("��J���~! �Э��s��J : ");
				code=sc.nextInt();
			}
		}
	}
//�s���p���H
	public void edit_contact(int i) throws IOException
	{
		String temp="";
		System.out.print("\n�п�J���ק諸���� : ");
		System.out.println("( 1 )�m�W   ( 2 )�ͤ�       ( 3 )�q�ܸ��X");
		System.out.println("                      ( 4 )����   ( 5 )�q�l�H�c   ( 6 )�^�W�@��");
		code=sc.nextInt();
		System.out.print("\n");
		int stop;
		stop=0;
		while(stop==0)
		{
			switch(code)
            {
            	case 1:
            		System.out.print("��J�m�W�G");
            		temp=sc.next();
            		if(temp.length()<20)
            			{
            			AList.get(i).name=temp;
            			System.out.print("\n***�]�w���\***");
            			if(r==0)
							save(AList);
            			stop=1;
            			}
            		else
            			System.out.print("\n��J���~! �Э��s");
            		break;
            	case 2:
            		System.out.print("��J�ͤ� (�d��:����G���п�J0602) �G");
            		temp=sc.next();
            		if(check_birth(temp)==true)
						{
							AList.get(i).birthday=temp;
							System.out.print("\n***�]�w���\***");
							if(r==0)
								save(AList);
            				stop=1;
						}
					else
							System.out.println("��J���~! �Э��s");
            		break;
            	case 3:
            		System.out.print("��J�q�ܸ��X�G");
            		temp=sc.next();
            		if(temp.contains("(")&&temp.contains(")"))//����
					{
						String test=temp.replace("(","");
						test=test.replace(")","");
						if(is_number(test)==true)
						{
							AList.get(i).phone_number=temp;
							System.out.print("\n***�]�w���\***");
							if(r==0)
								save(AList);
							stop=1;
						}
						else
							System.out.println("��J���~! �Э��s");
					}
					else if(temp.length()==10&&is_number(temp)==true)//���
					{
						AList.get(i).phone_number=temp;
						System.out.print("\n***�]�w���\***");
						if(r==0)
							save(AList);
						stop=1;
					}
					else
						System.out.println("��J���~! �Э��s");
            		break;
            	case 4:
            		System.out.print("����p���H���� : ");
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
								System.out.print("��J���~! �Э��s��J : ");
								code2=sc.nextInt();
						}
					}
					System.out.print("\n***�]�w���\***");
					if(r==0)
						save(AList);
					stop=1;
					break;
            	case 5:
            		System.out.print("��J�q�l�H�c�G");
            		temp=sc.next();
            		if(temp.contains("@")&&temp.length()>=5)
					{
						AList.get(i).email=temp;
            			System.out.print("\n***�]�w���\***");
            			if(r==0)
							save(AList);
            			stop=1;
					}
					else
						System.out.println("��J���~! �Э��s");
            		break;
            	case 6://�^�W�@��(�d��)
            		search_contact_menu();
				default:
					System.out.print("��J���~! �Э��s��J : ");
					code=sc.nextInt();
            }
		}
		pause();
		edit_contact(i);
	}

//�Ƨ�(�̥ͤ�)
	public void sorting()
	{
		CList.clear();
		Contact temp;
		for(int i=0;i<AList.size();i++)//�NAList���Ҧ��p���H��iCList
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
						else if(CList.get(j+1).get_month()==CList.get(j).get_month())//����ۦP
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
						else if(CList.get(j+1).get_month()==CList.get(j).get_month())//����ۦP
							if(CList.get(j+1).get_day()>CList.get(j).get_day())
							{
								temp=CList.get(j);
								CList.remove(j);
								CList.add(j+1,temp);
							}
					}
	}
//�޲z�\��_������]�w
	public void display_control() throws IOException
	{
		while(true)
		{
			System.out.println("�ثe�]�w�p�U ( �����   ������� )");
			show_display_situation();
			System.out.println("�п�J�n��諸��� : ");
			System.out.println("( 1 )�m�W   ( 2 )�ͤ�       ( 3 )�q�ܸ��X");
			System.out.println("( 4 )����   ( 5 )�q�l�H�c   ( 6 )�^�W�@��");
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
            		System.out.print("��J���~! �Э��s��J");
            }
		}
	}
//�޲z�\��_�]�w�Ƨ�
	public void set_sorting() throws IOException
	{
		while(true)
		{
			System.out.print("�ثe�]�w��");
			if(s==0)
				System.out.println(" 1���12�� �Ƨ�");
			else
				System.out.println(" 12���1�� �Ƨ�");
			System.out.println("�п�J�N�X :   ( 1 )���ܱƧǤ覡   ( 2 )�^�W�@��");
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
				System.out.println("��J���~! �Э��s��J");
		}
	}
//�C�L�ثe���]�w
	public void show_display_situation()
	{
		System.out.println(" �m�W  �ͤ�  �q�ܸ��X  ����  �q�l�H�c");
		if (d_name==0)
			System.out.print("  ��  ");
		else
			System.out.print("  ��  ");
		if (d_birthday==0)
			System.out.print("  ��  ");
		else
			System.out.print("  ��  ");
		if (d_phone_number==0)
			System.out.print("    ��   ");
		else
			System.out.print("    ��   ");
		if (d_group==0)
			System.out.print("   ��  ");
		else
			System.out.print("   ��  ");
		if (d_email==0)
			System.out.println("     ��    ");
		else
			System.out.println("     ��    ");
	}
//�x�s�ɮ�
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
//�Y�ɦs�ɳ]�w
	public void set_save() throws IOException
	{
		while(true)
		{
			System.out.print("�ثe�Y�ɦs�ɳ]�w�� : ");
			if(r==0)
				System.out.println("�}");
			else
				System.out.println("��");
			System.out.println("�п�J�N�X :   ( 1 )���ܦs�ɤ覡   ( 2 )�^�W�@��");
			code=sc.nextInt();
			if(code==1)
			{
				if(r==0)//�ثe�� �}
					r=1;
				else//�ثe�� ��
				{
					save(AList);
					r=0;
				}
			}
			else if(code==2)
				manager_menu();
			else
				System.out.println("��J���~! �Э��s��J");
		}
	}
//���s�ƧǨ��x�s
	public void sorting_and_save() throws IOException
	{
		while(true)
		{
			int temp_s=s;
			System.out.println("�̥ͤ�ƧǨ��x�s");
			System.out.println("�п�� : ( 1 )1���12��   ( 2 )12���1��   ( 3 )�^�W�@��");
			code=sc.nextInt();
			if(code==1)
			{
				s=0;
				sorting();
				save(CList);
				System.out.println("***�w�ƧǨ��x�s***");
				s=temp_s;
				pause();
				manager_menu();
			}
			else if(code==2)
			{
				s=1;
				sorting();
				save(CList);
				System.out.println("***�w�ƧǨ��x�s***");
				s=temp_s;
				pause();
				manager_menu();
			}
			else if(code==3)
				manager_menu();
			else
				System.out.println("��J���~! �Э��s��J");
		}
	}
//�榡��
	public void delete_all() throws IOException
	{
		System.out.println("�T�w�R��? ��J (Y)�R�� (N)����");
				String check=sc.next();
				while(true)
				{
					if(check.equalsIgnoreCase("Y"))
					{
						AList.clear();
						save(AList);
						System.out.println("***���\�榡��***");
						pause();
						manager_menu();
					}
					else if(check.equalsIgnoreCase("N"))
						manager_menu();
					else
					{
						System.out.print("��J���~! �Э��s��J : ");
						check=sc.next();
					}
				}
	}

//�P�_�Ʀr
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

//�P�_�ͤ�
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

//�Ȱ�
	public void pause()
	{
		System.out.print("\n��Enter���^�W�@��..");
		try
		{
         	System.in.read();
		}
		catch(Exception e) {}
	}
//������
	public void column()
	{
		System.out.println("\n �m�W    �ͤ�   �q�ܸ��X      ����        �q�l�H�c");
		System.out.println("------------------------------------------------------------------");
	}
}