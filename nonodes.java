import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class nonodes extends JFrame implements ActionListener
{
 Container con;
 JTextField jtf;
 JButton b1;
 JLabel l1;
 Toolkit tk=Toolkit.getDefaultToolkit();
 Dimension d1,d2;
 static int nonodes=0;
 JPanel p1;
 public nonodes()
 {
  nonodes=0;
  con=getContentPane();
  con.setLayout(new BorderLayout());
  p1=new JPanel(new FlowLayout());
  p1.setBackground(new Color(120,130,180));
  setSize(350,100);
  setTitle("Enter the number of nodes..");
  d1=tk.getScreenSize();
  d2=getSize();
  setLocation((d1.width-d2.width)/2,(d1.height-d2.height)/2);
  l1=new JLabel("No. of Nodes:");
  jtf=new JTextField(20);
  b1=new JButton("Ok");
  b1.setBackground(new Color(120,130,180));
  l1.setForeground(new Color(0,0,0));
  jtf.setBackground(new Color(120,130,180));
  jtf.setFont(new Font("Dialog",Font.BOLD,14));
  p1.add(l1);p1.add(jtf);p1.add(b1);
  con.add(p1);
  b1.addActionListener(this);
 }
 public void actionPerformed(ActionEvent ae)
 {
  String ser="";
  try{
  if((ae.getActionCommand()).equals("Ok"))
  {
   if(!(jtf.getText()).equals(""))
   {
    ser=jtf.getText();
    nonodes=Integer.parseInt(ser);
    setVisible(false);
   }
   else
   {
    JOptionPane.showMessageDialog(this,"Plase enter some number"); 
   }
  }
 }catch(Exception e)
 {
  jtf.setText("");
  JOptionPane.showMessageDialog(this,"Please enter some number");}
  jtf.requestFocus();
 }
}
