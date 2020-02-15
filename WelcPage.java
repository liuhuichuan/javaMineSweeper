import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WelcPage extends JPanel {
	private JFrame jFrame = new JFrame("��ʼ����");
    private Container c = jFrame.getContentPane();
    private JLabel a1 = new JLabel("����");
    private JTextField row = new JTextField();
    private JLabel a2 = new JLabel("����");
    private JTextField col = new JTextField();
    private JLabel a3 = new JLabel("����");
    private JTextField num = new JTextField();
    private JButton okbtn = new JButton("��ʼ");
    private JButton cancelbtn = new JButton("�ر�");
    private int row_val = 0;
    private int col_val = 0;
    private int num_val = 0;
    boolean startOrnot = false;
    
    public WelcPage() {
        //���ô����λ�ü���С
        jFrame.setBounds(600, 200, 300, 220);
        //����һ���൱�������Ķ���
        c.setLayout(new BorderLayout());//���ֹ�����
        //���ð������Ͻ�X�ź�ر�
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //��ʼ��--��������������ؼ�
        init();
        //���ô���ɼ�
        jFrame.setVisible(true);

    }
    public void init() {
        /*���ⲿ��--North*/
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(new JLabel("�����趨"));
        c.add(titlePanel, "North");
        
        /*���벿��--Center*/
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(null);
        a1.setBounds(50, 20, 50, 20);
        a2.setBounds(50, 60, 50, 20);
        a3.setBounds(50, 100, 50, 20);
        fieldPanel.add(a1);
        fieldPanel.add(a2);
        fieldPanel.add(a3);
        row.setBounds(110, 20, 120, 20);
        col.setBounds(110, 60, 120, 20);
        num.setBounds(110,100,120,20);
        fieldPanel.add(col);
        fieldPanel.add(row);
        fieldPanel.add(num);
        c.add(fieldPanel, "Center");
        
        /*��ť����--South*/
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(okbtn);
        buttonPanel.add(cancelbtn);
        c.add(buttonPanel, "South");
        
        //��������ť���¼�����
        listener();
    }
    //����
    public static void main(String[] args) {
        WelcPage w = new WelcPage();
    }
    
   public void listener() {
    	okbtn.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			row_val = Integer.parseInt(row.getText());
    			col_val = Integer.parseInt(col.getText());
    			num_val = Integer.parseInt(num.getText());
    			startOrnot = true;
    			MapFrame m = new MapFrame(row_val,col_val,num_val);
            	m.initUI();
    		}
    	});
    	cancelbtn.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			System.exit(0);
    		}
    	}
    	);
    }
    
    
    
    
    
    
    
    
}
