package application;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

import java.util.Calendar;
import java.text.SimpleDateFormat;
public class Server implements ActionListener{
	JPanel p1;
	JTextField tf;
	JButton b1;
	static JPanel ta;
	static JFrame f1 = new JFrame();
	
	static Box vertical = Box.createVerticalBox();
	
	
	static ServerSocket skt;
	static Socket s;
	static DataInput din;
	static DataOutput dout;
	
	Boolean typing;	
	
	Server(){
		
		p1= new JPanel();
		p1.setLayout(null);
		p1.setBackground(new Color(7,94,84));
		p1.setBounds(0,0,450,70);
		f1.add(p1);
		
		ImageIcon img1 = new ImageIcon("3.png");
		Image img2 = img1.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
		ImageIcon img3 = new ImageIcon(img2);
		JLabel l1 = new JLabel(img3);
		l1.setBounds(5,17,30,30);
		p1.add(l1);
		
		l1.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent ae) {
				System.exit(0);
			}
		});
		
		ImageIcon img4 = new ImageIcon("1.png");
		Image img5 = img4.getImage().getScaledInstance(60,60,Image.SCALE_DEFAULT);
		ImageIcon img6 = new ImageIcon(img5);
		JLabel l2 = new JLabel(img6);
		l2.setBounds(40,5,60,60);
		p1.add(l2);
		
		
		ImageIcon img7 = new ImageIcon("video.png");
		Image img8 = img7.getImage().getScaledInstance(35,35,Image.SCALE_DEFAULT);
		ImageIcon img9 = new ImageIcon(img8);
		JLabel l5 = new JLabel(img9);
		l5.setBounds(320,18,30,30);
		p1.add(l5);
		
		ImageIcon img10 = new ImageIcon("phone.png");
		Image img11 = img10.getImage().getScaledInstance(35,35,Image.SCALE_DEFAULT);
		ImageIcon img12 = new ImageIcon(img11);
		JLabel l6 = new JLabel(img12);
		l6.setBounds(370,18,30,30);
		p1.add(l6);
		
		ImageIcon img13 = new ImageIcon("3icon.png");
		Image img14 = img13.getImage().getScaledInstance(13,25,Image.SCALE_DEFAULT);
		ImageIcon img15 = new ImageIcon(img14);
		JLabel l7 = new JLabel(img15);
		l7.setBounds(420,20,13,25);
		p1.add(l7);
		
		JLabel l3 = new JLabel("Ashish");
		l3.setBounds(110,15,100,20);
		l3.setFont(new Font("SAN_SERIF",Font.BOLD,20));
		l3.setForeground(Color.white);
		p1.add(l3);
		
		JLabel l4 = new JLabel("Active now");
		l4.setBounds(110,35,100,20);
		l4.setFont(new Font("SAN_SERIF",Font.PLAIN,14));
		l4.setForeground(Color.white);
		p1.add(l4);
		
		Timer t =  new Timer(1,new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!typing) {
					l4.setText("Active now");
				}
				
			}
		});
		t.setInitialDelay(2000);
		
		ta = new JPanel();
//		ta.setBounds(5,75,440,570);
		ta.setFont(new Font("SAN_SERIF",Font.PLAIN,20));
//		ta.setBackground(Color.black);
//		ta.setEditable(false);
//		ta.setLineWrap(true);
//		ta.setWrapStyleWord(true);
//		f1.add(ta);
		
		JScrollPane sp = new JScrollPane(ta);
		sp.setBounds(5,75,440,570);
		sp.setBorder(BorderFactory.createEmptyBorder());
		f1.add(sp);
		tf = new JTextField();
		tf.setBounds(5,655,310,40);
		tf.setFont(new Font("SAN_SERIF",Font.PLAIN,20));
		f1.add(tf);
		
		tf.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke) {
				l4.setText("typing...");
				t.stop();
				typing = true;	
			}
			public void keyReleased(KeyEvent ke) {
				typing = false;
				if(!t.isRunning()) {
					t.start();
				}
			}
		});
		
		b1 = new JButton("Send");
		b1.setBounds(320,655,125,40);
		b1.setBackground(new Color(7,94,84));
		b1.setForeground(Color.white);
		b1.setFont(new Font("SAN_SERIF",Font.BOLD,16));
		b1.addActionListener(this);
		f1.add(b1);
		
		f1.getContentPane().setBackground(Color.white);
		f1.setLayout(null);
		
		f1.setSize(450,735);
		f1.setLocation(500,200);
//		f1.setUndecorated(true);
		f1.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		try {
			String out = tf.getText();
			sendTextToFile(out);
			JPanel p2 = formatLabel(out);
			ta.setLayout(new BorderLayout());
			JPanel right = new JPanel(new BorderLayout());
			right.add(p2,BorderLayout.LINE_END);
			vertical.add(right);
			vertical.add(Box.createVerticalStrut(15));
			ta.add(vertical,BorderLayout.PAGE_START);
//			ta.add(p2);
			dout.writeUTF(out);
			tf.setText("");
		}catch(Exception e) {
			
		}
	}
	public void sendTextToFile(String message)throws FileNotFoundException {
		try(FileWriter f = new FileWriter("chat.txt");
				PrintWriter p = new PrintWriter(new BufferedWriter(f));){
				p.println("Ashish: "+message);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public static JPanel formatLabel(String out){
        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
        
        JLabel l1 = new JLabel("<html><p style = \"width : 150px\">"+out+"</p></html>");
        l1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        l1.setBackground(new Color(37, 211, 102));
        l1.setOpaque(true);
        l1.setBorder(new EmptyBorder(15,15,15,50));
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        
        JLabel l2 = new JLabel();
        l2.setText(sdf.format(cal.getTime()));
        
        p3.add(l1);
        p3.add(l2);
        return p3;
    }

	public static void main(String[] args){
        new Server().f1.setVisible(true);
        
        String msginput = "";
        try{
            skt = new ServerSocket(6001);
            while(true){
                s = skt.accept();
                din = new DataInputStream(s.getInputStream());
                dout = new DataOutputStream(s.getOutputStream());
            
	        while(true){
	                msginput = din.readUTF();
                        JPanel p2 = formatLabel(msginput);
                        
                        JPanel left = new JPanel(new BorderLayout());
                        left.add(p2, BorderLayout.LINE_START);
                        vertical.add(left);
                        f1.validate();
            	}
                
            }
            
        }catch(Exception e){}
    }

}