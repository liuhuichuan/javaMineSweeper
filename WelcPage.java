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
	private JFrame jFrame = new JFrame("开始界面");
    private Container c = jFrame.getContentPane();
    private JLabel a1 = new JLabel("行数");
    private JTextField row = new JTextField();
    private JLabel a2 = new JLabel("列数");
    private JTextField col = new JTextField();
    private JLabel a3 = new JLabel("雷数");
    private JTextField num = new JTextField();
    private JButton okbtn = new JButton("开始");
    private JButton cancelbtn = new JButton("关闭");
    private int row_val = 0;
    private int col_val = 0;
    private int num_val = 0;
    boolean startOrnot = false;
    
    public WelcPage() {
        //设置窗体的位置及大小
        jFrame.setBounds(600, 200, 300, 220);
        //设置一层相当于桌布的东西
        c.setLayout(new BorderLayout());//布局管理器
        //设置按下右上角X号后关闭
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //初始化--往窗体里放其他控件
        init();
        //设置窗体可见
        jFrame.setVisible(true);

    }
    public void init() {
        /*标题部分--North*/
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(new JLabel("参数设定"));
        c.add(titlePanel, "North");
        
        /*输入部分--Center*/
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
        
        /*按钮部分--South*/
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(okbtn);
        buttonPanel.add(cancelbtn);
        c.add(buttonPanel, "South");
        
        //对两个按钮绑定事件监听
        listener();
    }
    //测试
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
