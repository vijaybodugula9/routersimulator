import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import java.awt.event.*;
public class algosel extends JPanel implements ActionListener,ItemListener
{
 JComboBox jc1;
 JLabel jl1;
 JButton b1,b2,b4;
 public static String selalgo="";
 Dimension d1;
 String akser="";
 public algosel()
 {
  setSize(200,300);
  setLocation(580,5);
  d1=getSize();
  setBorder(new EtchedBorder(4));
  setLayout(null);
  setBackground(new Color(120,130,180));
  jc1=new JComboBox();
  jl1=new JLabel("Select a Routing Alogrithm:");
  b1=new JButton("Simulate");
  b2=new JButton("Efficiancy");
  b4=new JButton("Send Packets");
  jc1.setBackground(new Color(120,130,180));
  b1.setBackground(new Color(120,130,180));
  b2.setBackground(new Color(120,130,180));
  b4.setBackground(new Color(120,130,180));
  jc1.addItem("-Select-");
  jc1.addItem("Flooding");
  jc1.addItem("Hot-Potato");
  jc1.addItem("Source Routing");
  jc1.addItem("Distence Vector");
  jc1.addItem("Routing Information Protocal");
  jc1.addItem("Link State Routing");
  jl1.setBounds(5,5,190,25);
  jl1.setForeground(new Color(0,0,0));
  jc1.setBounds(5,35,190,25);
  b1.setBounds((d1.width-120)/2,100,120,25);
  b2.setBounds((d1.width-120)/2,140,120,25);
  b4.setBounds((d1.width-120)/2,180,120,25);
  add(jl1);add(jc1);add(b1);add(b2);add(b4);
  b1.addActionListener(this);
  b2.addActionListener(this);
  b4.addActionListener(this);
  jc1.addItemListener(this);
 }
  public void itemStateChanged(ItemEvent ie)
  {
  }
  public void actionPerformed(ActionEvent ae)
  {
    String acom=ae.getActionCommand();
   if(acom.equals("Send Packets"))
   {
    new multipackets().setVisible(true);
   }
   if(acom.equals("Simulate"))
   {
    selalgo=""+jc1.getSelectedItem();
    if((""+jc1.getSelectedItem()).equals("Flooding"))
    {
     flooding flood=new flooding();
      flood.getHops();
    }
    if((""+jc1.getSelectedItem()).equals("Hot-Potato"))
    {
     hotpotato hot=new hotpotato();
      hot.getHops();
    }
   }
   if(acom.equals("Efficiancy"))
   {
    efficiancy eff=new efficiancy();
     eff.show();
   }
  }
}
