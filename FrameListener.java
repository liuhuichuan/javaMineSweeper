import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.MediaTracker;

public class FrameListener implements  MouseListener {
	public MapFrame mf;
	public int[][] opRes;
	public JLabel lb2;
	public int num ;
	public int count ;
	public int row = 0;
	public int col = 0; 
	
	public FrameListener(MapFrame mf,JLabel lb2, int row, int col, int num) {
		this.mf = mf;
		this.lb2 = lb2;
		this.row = row;
		this.col = col;
		this.num = num;
		this.count = row*col;
		opRes = new int [row][];
		for(int i=0;i<row;i++) {
			opRes[i] = new int [col];
		}
	}
	
	public void mouseClicked(java.awt.event.MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		//����õ������ĸ������ο���
		//��Ĭ���û��㲻�б߽�
		int xnum = (x-20)/40;
		int ynum = (y-20)/40;
		Graphics g = mf.getGraphics();
		Image image1 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\Desktop\\mine\\java\\image\\flag.jpg");
		Image image2 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\Desktop\\mine\\java\\image\\question.jpg");
		Image image3 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\Desktop\\mine\\java\\image\\mine.jpg");
		int i = e.getButton();
		//��������Ҽ�
		if(num > 0 && i == MouseEvent.BUTTON3) {
			
			if(opRes[xnum][ynum]==0) {
				opRes[xnum][ynum] = -1;
				this.count--;
				this.num--;
				
				//���drawImage������һ�ε��ò���ʾ
				while(false ==g.drawImage(image1, 20+xnum*40+3, 20+ynum*40+3, 60+xnum*40-3, 60+ynum*40-3, 0, 0, 220, 220, null));
				this.lb2.setText(String.valueOf(num));
			}
			else if(opRes[xnum][ynum]==-1) {
				this.num++;
				opRes[xnum][ynum] = -2;
				this.count++;
				while(false == g.drawImage(image2, 20+xnum*40+3, 20+ynum*40+3, 60+xnum*40-3, 60+ynum*40-3, 0, 0, 29, 29, null));
				this.lb2.setText(String.valueOf(num));
			}
			else if(opRes[xnum][ynum]==-2) {
				opRes[xnum][ynum] = 0;
				g.setColor(Color.white);
				g.fillRect(20+xnum*40+3, 20+ynum*40+3, 34, 34);
			}
		}
		//��ס����������Ϊ�հ״����¼�����
		if(opRes[xnum][ynum] == 0 && i == MouseEvent.BUTTON1) {
			System.out.println("here");
			//ѡ�е���
			if(mf.isMine[xnum][ynum] == -1) {
				for(int k=0;k<row;k++) {
					for(int j=0;j<col;j++) {
						if(mf.isMine[k][j] == -1) {
							while(false == g.drawImage(image3, 20+k*40+3, 20+j*40+3, 60+k*40-3, 60+j*40-3, 0, 0, 190, 190, null));
						}
					}
				}
				try {
					Thread.sleep(5000);
				}catch (Exception exc) {
					System.exit(0);
				}
				JOptionPane.showMessageDialog(null, "����Ϸʧ�ܣ��r(�s���t)�q", "��ʾ",JOptionPane.ERROR_MESSAGE); 
				System.exit(0);
			}
			//ûѡ�е��ף�ѡ�д������ֵ�λ��
			draw(xnum,ynum);
			//ѡ�пհ�λ��
			if(mf.isMine[xnum][ynum] == 0) {
				g.setColor(Color.GRAY);
				g.fillRect(20+xnum*40+3, 20+ynum*40+3, 34, 34);
				//���η�Ϊ���ϡ����ϡ����¡������ĸ��������������������̽Ѱ��Ѱ�������Ե�����ּ�ֹͣ
				//����
				this.expand(xnum, ynum);
			}
		}
		if(count == 0) {
			JOptionPane.showMessageDialog(null, "��ϲ��ͨ���ˣ���"); 
			System.exit(0);
		}
	}
	public void expand(int xnum, int ynum) {
		if(xnum<0 || xnum>row-1 || ynum<0 || ynum>col-1)
			return ;
		if(opRes[xnum][ynum] != 0)
			return ;
		//��������ֹ����
		opRes[xnum][ynum] = 1;
		this.count--;
		Graphics g = mf.getGraphics();
		if(xnum-1>=0 && opRes[xnum-1][ynum]==0 && this.mf.isMine[xnum-1][ynum] == 0) { //�����Ч���ǿհ�
			g.setColor(Color.GRAY);
			g.fillRect(20+(xnum-1)*40+3, 20+ynum*40+3, 34, 34);
			expand(xnum-1,ynum);
		}
		if(xnum-1>=0 && opRes[xnum-1][ynum]==0 && this.mf.isMine[xnum-1][ynum]>0) { //�����Ч����Ϊ����
			draw(xnum-1,ynum);
		}
		if(ynum-1>=0 && opRes[xnum][ynum-1]==0 && this.mf.isMine[xnum][ynum-1] == 0) { //�Ͽ�հ�
			g.setColor(Color.GRAY);
			g.fillRect(20+(xnum)*40+3, 20+(ynum-1)*40+3, 34, 34);
			expand(xnum,ynum-1);
		}
		if(ynum-1>=0 && opRes[xnum][ynum-1]==0 && this.mf.isMine[xnum][ynum-1]>0) { //�ϱ���Ч����Ϊ����
			draw(xnum,ynum-1);
		}
		if(xnum-1>=0 && ynum-1>=0 && opRes[xnum-1][ynum-1]==0 && this.mf.isMine[xnum-1][ynum-1] == 0) { //����
			g.setColor(Color.GRAY);
			g.fillRect(20+(xnum-1)*40+3, 20+(ynum-1)*40+3, 34, 34);
			expand(xnum-1,ynum-1);
		}
		if(xnum-1>=0 && ynum-1>=0 && opRes[xnum-1][ynum-1]==0 && this.mf.isMine[xnum-1][ynum-1]>0) { //������Ч����Ϊ����
			draw(xnum-1,ynum-1);
		}
		if(xnum+1<row && ynum-1>=0 && opRes[xnum+1][ynum-1]==0 && this.mf.isMine[xnum+1][ynum-1] == 0) { //����
			g.setColor(Color.GRAY);
			g.fillRect(20+(xnum+1)*40+3, 20+(ynum-1)*40+3, 34, 34);
			expand(xnum+1,ynum-1);
		}
		if(xnum+1<row && ynum-1>=0 && opRes[xnum+1][ynum-1]==0 && this.mf.isMine[xnum+1][ynum-1]>0) { //������Ч����Ϊ����
			draw(xnum+1,ynum-1);
		}
		if(xnum+1<row  && opRes[xnum+1][ynum]==0 && this.mf.isMine[xnum+1][ynum] == 0) { //��
			g.setColor(Color.GRAY);
			g.fillRect(20+(xnum+1)*40+3, 20+(ynum)*40+3, 34, 34);
			expand(xnum+1,ynum);
		}
		if(xnum+1<row  && opRes[xnum+1][ynum]==0 && this.mf.isMine[xnum+1][ynum]>0) { //����Ч����Ϊ����
			draw(xnum+1,ynum);
		}
		if(xnum+1<row && ynum+1<col && opRes[xnum+1][ynum+1]==0 && this.mf.isMine[xnum+1][ynum+1] == 0) { //����
			g.setColor(Color.GRAY);
			g.fillRect(20+(xnum+1)*40+3, 20+(ynum+1)*40+3, 34, 34);
			expand(xnum+1,ynum+1);
		}
		if(xnum+1<row && ynum+1<col && opRes[xnum+1][ynum+1]==0 && this.mf.isMine[xnum+1][ynum+1]>0) { //������Ч����Ϊ����
			draw(xnum+1,ynum+1);
		}
		if(xnum-1>=0 && ynum+1<col && opRes[xnum-1][ynum+1]==0 && this.mf.isMine[xnum-1][ynum+1] == 0) { //����
			g.setColor(Color.GRAY);
			g.fillRect(20+(xnum-1)*40+3, 20+(ynum+1)*40+3, 34, 34);
			expand(xnum-1,ynum+1);
		}
		if(xnum-1>=0 && ynum+1<col && opRes[xnum-1][ynum+1]==0 && this.mf.isMine[xnum-1][ynum+1]>0) { //������Ч����Ϊ����
			draw(xnum-1,ynum+1);
		}
		if( ynum+1<col && opRes[xnum][ynum+1]==0 && this.mf.isMine[xnum][ynum+1] == 0) { //��
			g.setColor(Color.GRAY);
			g.fillRect(20+(xnum)*40+3, 20+(ynum+1)*40+3, 34, 34);
			expand(xnum,ynum+1);
		}
		if( ynum+1<col && opRes[xnum][ynum+1]==0 && this.mf.isMine[xnum][ynum+1]>0) { //����Ч����Ϊ����
			draw(xnum,ynum+1);
		}
	}

	public void draw(int xnum, int ynum) {
		Graphics g = mf.getGraphics();
		Image img1 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\Desktop\\mine\\java\\image\\img1.jpg");
		Image img2 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\Desktop\\mine\\java\\image\\img2.jpg");
		Image img3 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\Desktop\\mine\\java\\image\\img3.jpg");
		Image img4 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\Desktop\\mine\\java\\image\\img4.jpg");
		Image img5 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\Desktop\\mine\\java\\image\\img5.jpg");
		Image img6 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\Desktop\\mine\\java\\image\\img6.jpg");
		Image img7 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\Desktop\\mine\\java\\image\\img7.jpg");
		Image img8 = Toolkit.getDefaultToolkit().getImage("C:\\Users\\ASUS\\Desktop\\mine\\java\\image\\img8.jpg");
		switch(mf.isMine[xnum][ynum]) {
		case 1:
			opRes[xnum][ynum] = 1;
			this.count--;
			while(false == g.drawImage(img1, 20+xnum*40+3, 20+ynum*40+3, 60+xnum*40-3, 60+ynum*40-3, 0, 0, 30, 30, null));
			break;
		case 2:
			opRes[xnum][ynum] = 1;
			this.count--;
			while(false == g.drawImage(img2, 20+xnum*40+3, 20+ynum*40+3, 60+xnum*40-3, 60+ynum*40-3, 0, 0, 30, 30, null));
			break;
		case 3:
			opRes[xnum][ynum] = 1;
			this.count--;
			while(false == g.drawImage(img3, 20+xnum*40+3, 20+ynum*40+3, 60+xnum*40-3, 60+ynum*40-3, 0, 0, 30, 30, null));
			break;
		case 4:
			opRes[xnum][ynum] = 1;
			this.count--;
			while(false == g.drawImage(img4, 20+xnum*40+3, 20+ynum*40+3, 60+xnum*40-3, 60+ynum*40-3, 0, 0, 30, 30, null));
			break;
		case 5:
			opRes[xnum][ynum] = 1;
			this.count--;
			while(false == g.drawImage(img5, 20+xnum*40+3, 20+ynum*40+3, 60+xnum*40-3, 60+ynum*40-3, 0, 0, 30, 30, null));
			break;
		case 6:
			opRes[xnum][ynum] = 1;
			this.count--;
			while(false == g.drawImage(img6, 20+xnum*40+3, 20+ynum*40+3, 60+xnum*40-3, 60+ynum*40-3, 0, 0, 30, 30, null));
			break;
		case 7:
			opRes[xnum][ynum] = 1;
			this.count--;
			while(false == g.drawImage(img7, 20+xnum*40+3, 20+ynum*40+3, 60+xnum*40-3, 60+ynum*40-3, 0, 0, 30, 30, null));
			break;
		case 8:
			opRes[xnum][ynum] = 1;
			this.count--;
			while(false == g.drawImage(img8, 20+xnum*40+3, 20+ynum*40+3, 60+xnum*40-3, 60+ynum*40-3, 0, 0, 30, 30, null));
			break;
	}
	}
	
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
