
 import java.util.Scanner;
 import java.lang.Thread;
public class game {
	public static void main(String[] args) {
		//��ʼ����
		Scanner scanner= new Scanner(System.in);
		System.out.println("��ӭ����21����Ϸ");
		System.out.println("�˰汾����"+"\n"+"1.ǰ���غ��Զ�����"
					+"\n"+"2.ׯ������"
					+"\n"+"3.һ��������Է�������ʤ����Ϸ����"
					+"\n"+"4.һ��ѡ�����������˺��������"
					+"\n"+"5.˫��������������������"
					+"\n"+"���롰y���Կ�ʼ��Ϸ������������������Ϸ����");	
		String o= scanner.nextLine();
		switch(o) {
		case "y":
			System.out.println("OK");
			break;
		default:
			System.out.println("Bye");
			return;
		}
		//�����ƿ�
		int []deck=new int[52];
		for (int i=0;i<deck.length;i++) {
			deck[i]=i+1;
		}
		//ϴ��	
		for(int i=0;i<52;i++) {
			int ob=(int)(Math.random()*52);
			int para;
			para = deck[i];
			deck[i]=deck [ob];
			deck[ob]= para;	
		}
		//����������
		int h1 = 0;
		int h2 = 0;
		gambler banker=new gambler(h1,"ׯ��",true);
		gambler gamer=new gambler(h2,"���",true);
		gambler dealer=new gambler(0,"����Ա",false);
		//��һ�غ�
		System.out.println("��1�غ�"+"\n"+"��ǰ�غϣ�"+banker.getName());
		banker.h=banker.deal(banker.getH(),deck[1]);
		System.out.println("���س�������ǰ�غ�");
		new Scanner(System.in).nextLine();
		System.out.println("5��������һ�غ�");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<400;i++){
			 System.out.println("");
		 }
		System.out.println("��2�غ�"+"\n"+"��ǰ�غϣ�"+gamer.getName());
		gamer.h=gamer.deal(gamer.getH(), deck[2]);
		System.out.println("���س�������ǰ�غ�");
		new Scanner(System.in).nextLine();
		System.out.println("5��������һ�غ�");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<400;i++){
			 System.out.println("");
		 }
		
		//�����غ�
		while(banker.r||gamer.r) {
		dealer.Counting();
		int i=dealer.getCount();
		//System.out.println("�غϼ�����:"+i);
		System.out.println("��"+i+"�غ�"); 
		int t=i%2;
		if(t==1) {			
			banker.choose1(deck);
		}else {
			gamer.choose2(deck);
		}	
	}
		System.out.println("˫�����ѷ������ƣ����ڽ���˫������");
		System.out.println("ׯ�ҵ�����"+banker.h);
		System.out.println("��ҵ�����"+gamer.h);
			if(banker.h>gamer.h) {
				System.out.println("ׯ�һ�ʤ"+"\n"+"��Ϸ����");
			}else if(banker.h==gamer.h) {
				System.out.println("Push!ƽ��"+"\n"+"��Ϸ����");
			}else {
				System.out.println("��һ�ʤ"+"\n"+"��Ϸ����");
			}	
	}
}
class gambler{
	//������������Ϣ
	int h;				//�����ܵ���
	String name;
	boolean r;   		//�Ƿ�Ҫ��
	public gambler(int h, String name,boolean r) {
		super();
		this.h = h;
		this.name = name;
		this.r=r;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isR() {
		return r;
	}
	public void setR(boolean r) {
		this.r = r;
	}
	public boolean td() {
		return r=false;
	}
		//���巢�Ʒ���
	
	//������
	int count=2;
	int count1=1;
	int count2=2;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getCount1() {
		return count1;
	}
	public void setCount1(int count1) {
		this.count1 = count1;
	}
	public int getCount2() {
		return count2;
	}
	public void setCount2(int count2) {
		this.count2= count2;
	}
	public void Counting(){
		count++;
	}
	public void Counting1(){
		count1+=2;
	}
	public void Counting2(){
		count2+=2;
	}
	
	//���Ʒ�������
	public int deal(int a,int b) {
	int c=b%13;
	int d=b/13;
	String suit;
	String number;
	int point;
	if (d==0) {
		suit="����";
	}else if(d==1) {
		suit="����";
	}else if(d==2) {
		suit="÷��";
	}else {
		suit="��Ƭ";
	}	
	if (c==1) {
		number="A";
		point=11;
	}else if(c==11) {
		number="J";
		point=10;	
	}else if(c==12) {
		number="Q";
		point=10;
	}else if(c==0) {
		number="K";
		point=10;
	}else {
		number=c+"";
		point=c;
	}
	a=a+point;
	System.out.println("��鵽��һ��"+suit+number+"������Ϊ"+point);
	System.out.println("�㵱ǰ�ܵ�����:"+a);
		if(a>21) {
			System.out.println("����!�Է���ʤ!"+"\n"+"��Ϸ����");			
			System.exit(0);
			return 0;
		}else{
	System.out.println("#####################################################"+"\n");
	return a;
		}	
	}		
 	//��������߽��Ʒ���
 public void choose1(int deck[]) {   			//ׯ��
	 Scanner scanner= new Scanner(System.in);
	 System.out.println("��ǰ�غϣ�"+name);
	 if (r) {
	 System.out.println("�㵱ǰ�ܵ���Ϊ"+getH()+"\n"+"���롰y���Խ��ƣ����롰n����˺����������");
	 String o= scanner.nextLine();
	 switch(o) {
		case "y":
			Counting1();
			int i=getCount1();
			//System.out.println("���Ʒ����еļ�������"+i);
			h=deal(h,deck[i]);
			break;
		case "n":
			System.out.println("���Ѿ���������");
			System.out.println("#####################################################"+"\n");
			r=td();
			
	 }
	}else {
		System.out.println("����ѡ���������"+"\n"+"�㵱ǰ�ܵ���Ϊ"+getH());		
		System.out.println("#####################################################"+"\n");
	}
	 System.out.println("���س�������ǰ�غ�");
	 new Scanner(System.in).nextLine();
		System.out.println("5��������һ�غ�");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 for(int i=0;i<400;i++){
		 System.out.println("");
	 }
 }
 public void choose2(int deck[]) {   			//���
	 Scanner scanner= new Scanner(System.in);
	 System.out.println("��ǰ�غϣ�"+name);
	 if(r) {
	 System.out.println("�㵱ǰ�ܵ���Ϊ"+getH()+"\n"+"���롰y���Խ��ƣ����롰n����˺����������");
	 String o= scanner.nextLine();
	 switch(o) {
		case "y":
			Counting2();
			int i=getCount2();
			System.out.println("���Ʒ����еļ�������"+i);
			h=deal(h,deck[i]);
			break;
		case "n":
			System.out.println("���Ѿ���������");
			System.out.println("#####################################################"+"\n");
			r=td();
	 	}
	 }else {
		 System.out.println("����ѡ���������"+"\n"+"�㵱ǰ�ܵ���Ϊ"+getH());
		 System.out.println("#####################################################"+"\n");
	 }
	 System.out.println("���س�������ǰ�غ�");
	 new Scanner(System.in).nextLine();
		System.out.println("5��������һ�غ�");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 for(int i=0;i<400;i++){
		 System.out.println("");
	 }
 	}
 
}


