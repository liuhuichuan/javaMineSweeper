import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.BorderLayout; //�ֳ�����������
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.Random;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;

public class MapFrame extends JPanel {               //�����Ĺ��캯���ĳɴ��Σ�row��col��num
	public Graphics g; //����һ֧���ʣ�����������
	public int row = 0;
	public int col = 0;
	public int num = 0;
	public int[][] isMine; //����һ��row*col�ľ���
		
	//���ù��캯��ʵ�ֵ��׵�ͼ�ĳ�ʼ��
	public MapFrame(int row,int col,int num) {
		this.row = row;
		this.col = col;
		this.num = num;
		isMine = new int[row][];
		for(int i = 0;i < row;i++) {
			isMine[i] = new int[col];
		}
		Random r = new Random();
		for(int i=0;i<num;) {          //���ѭ�����Ƶ��ǵ��׵Ĳ��ã����Ըı���Ŀ���಼�õ���
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
		jf.setTitle("ɨ��");
		jf.setSize(40+40*col,150+40*row);         //��Frame�Ĳ������Ը�
		jf.setLocationRelativeTo(null);  //���������ʾ
		jf.setDefaultCloseOperation(3);  //���͹ر�
		
		jf.setLayout(new BorderLayout()); //���ö�������Ϊ��ܲ��� �����ϱ�
		
		Dimension dim1 = new Dimension(40+40*col,150);//�����ϰ벿�ֵĴ�С���ɵ�
		Dimension dim2 = new Dimension(40+40*col,40+40*row);//�����°벿�ֵĴ�С
		Dimension dim3 = new Dimension(100,40);//�����ϰ벿�ֱ�ǩ����Ĵ�С
		
		//ʵ����ߵĽ��棬��MapFrame�Ķ�����ӵ���ܲ��ֵ��м䲿��
		this.setPreferredSize(dim2);//�����������Ĵ�С
		this.setBackground(Color.WHITE); //���õ�ͼ�������ɫ
		//���±ߵĻ�������ȥ����ָ�����ڿ�ܲ��ֵĵײ�
		jf.add(this,BorderLayout.SOUTH);
		
		//ʵ���ϱߵ�JPanel��������
		JPanel jp = new JPanel();
		jp.setPreferredSize(dim1);
		jp.setBackground(Color.white);//�����ϱߵĽ�����ɫΪ��ɫ
		jf.add(jp,BorderLayout.NORTH);
		jp.setLayout(new FlowLayout()); //����JPanelΪ��ʽ����
		
		//�������ѱ�ǩһ�μӵ�JPanel��
		JLabel lb1 = new JLabel("��������");
		lb1.setPreferredSize(dim3);
		lb1.setFont(new Font("����",Font.BOLD, 20));
		lb1.setVisible(true);
		JLabel lb2 = new JLabel(String.valueOf(num));
		lb2.setPreferredSize(dim3);
		lb2.setFont(new Font("����",Font.BOLD, 20));
		lb2.setVisible(true);
		jp.add(lb1);
		jp.add(lb2);
		
		//�����¼�����
		FrameListener framelistener = new FrameListener(this,lb2,row,col,num);
		//framelistener.setGraphics(this);
		this.addMouseListener(framelistener);
		//���Ͼ�Ϊ��ʼ������
		jf.setVisible(true);
	}
	
	//��д�ػ淽�����������׵�ͼ
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(Color.black);
		for(int i=0;i<=row;i++) 
			g.drawLine(20, 20+40*i, 20+40*col, 20+40*i);
		for(int j=0;j<=col;j++)
			g.drawLine(20+40*j, 20, 20+40*j, 20+40*row);
		
	}
}
