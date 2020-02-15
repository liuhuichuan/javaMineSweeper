import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.BorderLayout; //分成上下两部分
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;

public class MapFrame extends JPanel {               //把它的构造函数改成传参，row、col、num
	public Graphics g; //定义一支画笔，用来画格子
	public int row = 0;
	public int col = 0;
	public int num = 0;
	public int[][] isMine; //定义一个row*col的矩阵
		
	//利用构造函数实现地雷地图的初始化
	public MapFrame(int row,int col,int num) {
		this.row = row;
		this.col = col;
		this.num = num;
		isMine = new int[row][];
		for(int i = 0;i < row;i++) {
			isMine[i] = new int[col];
		}
		Random r = new Random();
		for(int i=0;i<num;) {          //这个循环控制的是地雷的布置，可以改变数目，多布置地雷
			int x = r.nextInt(row);
			int y = r.nextInt(col);
			if(isMine[x][y]==0) {
				isMine[x][y] = -1;
				i++;
			}
		}
		for(int i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				if(isMine[i][j]!=-1) {
					int test = 0;
					if(i-1>=0 && j-1>=0 && isMine[i-1][j-1] == -1)
						test++;
					if(i-1>=0 && j>=0 && isMine[i-1][j] == -1)
						test++;
					if(i-1>=0 && j+1<col && isMine[i-1][j+1] == -1)
						test++;
					if(i>=0 && j-1>=0 && isMine[i][j-1] == -1)
						test++;
					if(i>=0 && j+1<col && isMine[i][j+1] == -1)
						test++;
					if(i+1<row && j-1>=0 && isMine[i+1][j-1] == -1)
						test++;
					if(i+1<row && j>=0 && isMine[i+1][j] == -1)
						test++;
					if(i+1<row && j+1<col && isMine[i+1][j+1] == -1)
						test++;
					isMine[i][j] = test;
				}
			}
		}
	}
	
	public void initUI() {
		JFrame jf = new JFrame();
		jf.setTitle("扫雷");
		jf.setSize(40+40*col,150+40*row);         //大Frame的参数可以改
		jf.setLocationRelativeTo(null);  //窗体居中显示
		jf.setDefaultCloseOperation(3);  //点叉就关闭
		
		jf.setLayout(new BorderLayout()); //设置顶级容器为框架布局 东西南北
		
		Dimension dim1 = new Dimension(40+40*col,150);//设置上半部分的大小，可调
		Dimension dim2 = new Dimension(40+40*col,40+40*row);//设置下半部分的大小
		Dimension dim3 = new Dimension(100,40);//设置上半部分标签组件的大小
		
		//实现左边的界面，把MapFrame的对象添加到框架布局的中间部分
		this.setPreferredSize(dim2);//设置下棋界面的大小
		this.setBackground(Color.WHITE); //设置地图界面的颜色
		//把下边的画板添上去，并指明是在框架布局的底部
		jf.add(this,BorderLayout.SOUTH);
		
		//实现上边的JPanel容器界面
		JPanel jp = new JPanel();
		jp.setPreferredSize(dim1);
		jp.setBackground(Color.white);//设置上边的界面颜色为白色
		jf.add(jp,BorderLayout.NORTH);
		jp.setLayout(new FlowLayout()); //设置JPanel为流式布局
		
		//接下来把标签一次加到JPanel上
		JLabel lb1 = new JLabel("地雷数：");
		lb1.setPreferredSize(dim3);
		lb1.setFont(new Font("宋体",Font.BOLD, 20));
		lb1.setVisible(true);
		JLabel lb2 = new JLabel(String.valueOf(num));
		lb2.setPreferredSize(dim3);
		lb2.setFont(new Font("宋体",Font.BOLD, 20));
		lb2.setVisible(true);
		jp.add(lb1);
		jp.add(lb2);
		
		//加入事件监听
		FrameListener framelistener = new FrameListener(this,lb2,row,col,num);
		//framelistener.setGraphics(this);
		this.addMouseListener(framelistener);
		//以上均为初始化过程
		jf.setVisible(true);
	}
	
	//重写重绘方法，画出藏雷地图
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.black);
		for(int i=0;i<=row;i++) 
			g.drawLine(20, 20+40*i, 20+40*col, 20+40*i);
		for(int j=0;j<=col;j++)
			g.drawLine(20+40*j, 20, 20+40*j, 20+40*row);
		
	}
}
