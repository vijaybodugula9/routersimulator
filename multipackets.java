import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class multipackets extends JFrame implements ActionListener,ItemListener
{
 JButton b1,b2;
 JComboBox jc1,jc2,jc3,jc4;
 JLabel l1,l2,l3,l4,l5;
 JPanel p1;
 Container con;
 int count=0;
 topologyreader tr=new topologyreader();
 Toolkit tk=Toolkit.getDefaultToolkit();
 Dimension d1,d2;
 static LinkedList mpackets=new LinkedList();
 public multipackets()
 {
  mpackets.clear();
  start.routerbuffers.clear();
  tr.freader(start.topologyname);
  for(int i=0;i<tr.nodename.size();i++)
  {
   LinkedList lo=new LinkedList();
   lo.add(""+tr.nodename.get(i));
   start.routerbuffers.add(lo);
  }
  con=getContentPane();
  setSize(400,300);
  d1=tk.getScreenSize();
  d2=getSize();
  setLocation((d1.width-d2.width)/2,(d1.height-d2.height)/2);
  con.setLayout(null);
  setTitle("Number of Packets Added : 0");
  p1=new JPanel(null);
  b1=new JButton("Add");
  b2=new JButton("Process");
  jc1=new JComboBox();
  jc2=new JComboBox();
  jc3=new JComboBox();
  jc4=new JComboBox();
  l1=new JLabel("Source");
  l2=new JLabel("Destination");
  l3=new JLabel("Algorithm");
  l4=new JLabel("Via");
  l5=new JLabel("(Source Routing)");
  jc1.addItem("-Select-");
  jc2.addItem("-Select-");
  jc3.addItem("-Select-");
  jc4.addItem("-Select-");
  for(int i=0;i<tr.nodename.size();i++)
  {
   jc1.addItem(""+tr.nodename.get(i));
   jc2.addItem(""+tr.nodename.get(i));
   jc4.addItem(""+tr.nodename.get(i));
  }
  jc3.addItem("Flooding");
  jc3.addItem("Hot-Potato");
  jc3.addItem("Source Routing");
  jc3.addItem("Distence Vector Routing");
  jc3.addItem("Routing Information Protocal");
  jc3.addItem("Link State Routing");
  p1.setBounds(0,0,d2.width,d2.height);
  l1.setBounds(20,20,100,25);
  jc1.setBounds(130,20,150,25);
  l2.setBounds(20,60,100,25);
  jc2.setBounds(130,60,150,25);
  l3.setBounds(20,100,100,25);
  jc3.setBounds(130,100,150,25);
  l4.setBounds(20,140,100,25);
  jc4.setBounds(130,140,150,25);
  l5.setBounds(300,140,150,25);
  b1.setBounds(100,230,100,25);
  b2.setBounds(210,230,100,25);
  l1.setForeground(new Color(0,0,0));
  l2.setForeground(new Color(0,0,0));
  l3.setForeground(new Color(0,0,0));
  l4.setForeground(new Color(0,0,0));
  l5.setForeground(new Color(0,0,0));
  l5.setFont(new Font("Dialog",Font.PLAIN,10));
  p1.setBackground(new Color(120,130,180));
  l1.setBackground(new Color(120,130,180));
  jc1.setBackground(new Color(120,130,180));
  l2.setBackground(new Color(120,130,180));
  jc2.setBackground(new Color(120,130,180));
  l3.setBackground(new Color(120,130,180));
  jc3.setBackground(new Color(120,130,180));
  l4.setBackground(new Color(120,130,180));
  jc4.setBackground(new Color(120,130,180));
  l5.setBackground(new Color(120,130,180));
  b1.setBackground(new Color(120,130,180));
  b2.setBackground(new Color(120,130,180));

  p1.add(l1);p1.add(jc1);p1.add(l2);p1.add(jc2);
  p1.add(l3);p1.add(jc3);p1.add(b1);p1.add(b2);
  con.add(p1);

  jc3.addItemListener(this);
  b1.addActionListener(this);
  b2.addActionListener(this);
 }
 public void itemStateChanged(ItemEvent ie)
 {
  String ser=""+jc3.getSelectedItem();
  if(ser.equals("Source Routing"))
  {
   p1.add(jc4);p1.add(l4);p1.add(l5);
   setSize(d2.width-1,d2.height-1);
   setSize(d2.width+1,d2.height+1);
  }
 }
 public void actionPerformed(ActionEvent ae)
 {
  String ser=ae.getActionCommand();
  routerbuffer rb=new routerbuffer();
  if(ser.equals("Add"))
  {
   setTitle("No of Packets Added : "+count);
   LinkedList ll=new LinkedList();
   router r=new router();
   if(!(""+jc4.getSelectedItem()).equals("-Select-")||!(""+jc3.getSelectedItem()).equals("-Select-")|| !(""+jc2.getSelectedItem()).equals("-Select-") || !(""+jc1.getSelectedItem()).equals("-Select-"))
   {
    if(!(r.getRouterState(""+jc1.getSelectedItem())).equals("Down")||!(r.getRouterState(""+jc2.getSelectedItem())).equals("Down")|| !(r.getRouterState(""+jc1.getSelectedItem())).equals("Down"))
    { 
     ll.add(""+jc1.getSelectedItem());
     ll.add(""+jc2.getSelectedItem());
     ll.add(""+jc3.getSelectedItem());
     if(!(""+jc4.getSelectedItem()).equals("-Select-"))
     {
      ll.add(""+jc4.getSelectedItem());
      count+=1; 
     }
     else
      {
      count+=1;
      ll.add("");
      }
     }
     else
      JOptionPane.showMessageDialog(this,"Check one of your selected node is down");
   }
   else
    JOptionPane.showMessageDialog(this,"Please Select all the Fields");
    mpackets.add(ll);
    jc1.setSelectedIndex(0);
    jc2.setSelectedIndex(0);
    jc3.setSelectedIndex(0);
    jc4.setSelectedIndex(0);
    p1.remove(jc4);
    p1.remove(l4);
    p1.remove(l5);
    setSize(d2.width-1,d2.height-1);
    setSize(d2.width+1,d2.height+1);
  }
  if(ser.equals("Process"))
  {
    setVisible(false);
    flooding flod=new flooding();
    hotpotato hp=new hotpotato();
    sourcerouting sr=new sourcerouting("");
    distencevr dvr=new distencevr("");
    rip ri=new rip("");
    linkstaterouting lsr=new linkstaterouting("");
    for(int i=0;i<mpackets.size();i++)
    {
     LinkedList lk=(LinkedList) mpackets.get(i);
        if((""+lk.get(2)).equals("Flooding"))
         flod.getFloodingEfficiancy(new Packet(""+lk.get(0),""+lk.get(1)));
        if((""+lk.get(2)).equals("Hot-Potato"))
         hp.getHotpotatoEfficiancy(new Packet(""+lk.get(0),""+lk.get(1)));
        if((""+lk.get(2)).equals("Source Routing"))
         sr.getSourceRoutingEfficiancy(new Packet(""+lk.get(0),""+lk.get(1)),""+lk.get(3));
        if((""+lk.get(2)).equals("Distence Vector Routing"))
         dvr.getDistenceVectorEfficiancy(new Packet(""+lk.get(0),""+lk.get(1)));
        if((""+lk.get(2)).equals("Routing Information Protocal"))
         ri.getRIPEfficiancy(new Packet(""+lk.get(0),""+lk.get(1)));
        if((""+lk.get(2)).equals("Link State Routing"))
         lsr.getLinkStateEfficiancy(new Packet(""+lk.get(0),""+lk.get(1)));
     }
   }
 }
} 

