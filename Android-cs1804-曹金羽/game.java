
 import java.util.Scanner;
 import java.lang.Thread;
public class game {
	public static void main(String[] args) {
		//开始界面
		Scanner scanner= new Scanner(System.in);
		System.out.println("欢迎试玩21点游戏");
		System.out.println("此版本规则："+"\n"+"1.前两回合自动发牌"
					+"\n"+"2.庄家先手"
					+"\n"+"3.一方爆牌则对方立即获胜、游戏结束"
					+"\n"+"4.一旦选择放弃叫牌则此后均不叫牌"
					+"\n"+"5.双方均放弃抽牌则结算点数"
					+"\n"+"输入“y”以开始游戏，输入其他内容则游戏结束");	
		String o= scanner.nextLine();
		switch(o) {
		case "y":
			System.out.println("OK");
			break;
		default:
			System.out.println("Bye");
			return;
		}
		//创建牌库
		int []deck=new int[52];
		for (int i=0;i<deck.length;i++) {
			deck[i]=i+1;
		}
		//洗牌	
		for(int i=0;i<52;i++) {
			int ob=(int)(Math.random()*52);
			int para;
			para = deck[i];
			deck[i]=deck [ob];
			deck[ob]= para;	
		}
		//创建参与者
		int h1 = 0;
		int h2 = 0;
		gambler banker=new gambler(h1,"庄家",true);
		gambler gamer=new gambler(h2,"玩家",true);
		gambler dealer=new gambler(0,"发牌员",false);
		//第一回合
		System.out.println("第1回合"+"\n"+"当前回合："+banker.getName());
		banker.h=banker.deal(banker.getH(),deck[1]);
		System.out.println("按回车结束当前回合");
		new Scanner(System.in).nextLine();
		System.out.println("5秒后进入下一回合");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<400;i++){
			 System.out.println("");
		 }
		System.out.println("第2回合"+"\n"+"当前回合："+gamer.getName());
		gamer.h=gamer.deal(gamer.getH(), deck[2]);
		System.out.println("按回车结束当前回合");
		new Scanner(System.in).nextLine();
		System.out.println("5秒后进入下一回合");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<400;i++){
			 System.out.println("");
		 }
		
		//后续回合
		while(banker.r||gamer.r) {
		dealer.Counting();
		int i=dealer.getCount();
		//System.out.println("回合计数器:"+i);
		System.out.println("第"+i+"回合"); 
		int t=i%2;
		if(t==1) {			
			banker.choose1(deck);
		}else {
			gamer.choose2(deck);
		}	
	}
		System.out.println("双方均已放弃叫牌，现在结算双方点数");
		System.out.println("庄家点数："+banker.h);
		System.out.println("玩家点数："+gamer.h);
			if(banker.h>gamer.h) {
				System.out.println("庄家获胜"+"\n"+"游戏结束");
			}else if(banker.h==gamer.h) {
				System.out.println("Push!平局"+"\n"+"游戏结束");
			}else {
				System.out.println("玩家获胜"+"\n"+"游戏结束");
			}	
	}
}
class gambler{
	//创建参与者信息
	int h;				//手牌总点数
	String name;
	boolean r;   		//是否要牌
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
		//定义发牌方法
	
	//计数器
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
	
	//发牌方法主体
	public int deal(int a,int b) {
	int c=b%13;
	int d=b/13;
	String suit;
	String number;
	int point;
	if (d==0) {
		suit="黑桃";
	}else if(d==1) {
		suit="红桃";
	}else if(d==2) {
		suit="梅花";
	}else {
		suit="方片";
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
	System.out.println("你抽到了一张"+suit+number+"，点数为"+point);
	System.out.println("你当前总点数是:"+a);
		if(a>21) {
			System.out.println("爆牌!对方获胜!"+"\n"+"游戏结束");			
			System.exit(0);
			return 0;
		}else{
	System.out.println("#####################################################"+"\n");
	return a;
		}	
	}		
 	//定义参与者叫牌方法
 public void choose1(int deck[]) {   			//庄家
	 Scanner scanner= new Scanner(System.in);
	 System.out.println("当前回合："+name);
	 if (r) {
	 System.out.println("你当前总点数为"+getH()+"\n"+"输入“y”以叫牌，输入“n”则此后均放弃叫牌");
	 String o= scanner.nextLine();
	 switch(o) {
		case "y":
			Counting1();
			int i=getCount1();
			//System.out.println("叫牌方法中的计数器："+i);
			h=deal(h,deck[i]);
			break;
		case "n":
			System.out.println("你已经放弃抽牌");
			System.out.println("#####################################################"+"\n");
			r=td();
			
	 }
	}else {
		System.out.println("你曾选择放弃抽牌"+"\n"+"你当前总点数为"+getH());		
		System.out.println("#####################################################"+"\n");
	}
	 System.out.println("按回车结束当前回合");
	 new Scanner(System.in).nextLine();
		System.out.println("5秒后进入下一回合");
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
 public void choose2(int deck[]) {   			//玩家
	 Scanner scanner= new Scanner(System.in);
	 System.out.println("当前回合："+name);
	 if(r) {
	 System.out.println("你当前总点数为"+getH()+"\n"+"输入“y”以叫牌，输入“n”则此后均放弃叫牌");
	 String o= scanner.nextLine();
	 switch(o) {
		case "y":
			Counting2();
			int i=getCount2();
			System.out.println("叫牌方法中的计数器："+i);
			h=deal(h,deck[i]);
			break;
		case "n":
			System.out.println("你已经放弃抽牌");
			System.out.println("#####################################################"+"\n");
			r=td();
	 	}
	 }else {
		 System.out.println("你曾选择放弃抽牌"+"\n"+"你当前总点数为"+getH());
		 System.out.println("#####################################################"+"\n");
	 }
	 System.out.println("按回车结束当前回合");
	 new Scanner(System.in).nextLine();
		System.out.println("5秒后进入下一回合");
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


