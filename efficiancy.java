import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class efficiancy extends JFrame implements ActionListener
{
 JComboBox jc1,jc2,jc3;
 JLabel l1,l2,l3,l4,l5,l6;
 JButton b1,b2;
 Container con;
 JTable jtab;
 JScrollPane jsp;
 Toolkit tk=Toolkit.getDefaultToolkit();
 Dimension d1,d2;
 topologyreader tr=new topologyreader();
 JPanel p1;
 public efficiancy()
 {
  con=getContentPane();
  setSize(400,300);
  d2=getSize();
  d1=tk.getScreenSize();
  setLocation((d1.width-d2.width)/2,(d1.height-d2.height)/2);
  con.setLayout(null);
  setTitle("Algorithmic Efficiancy");
  setBackground(new Color(120,130,180));
  l1=new JLabel("Packet Information");
  l2=new JLabel("Source");
  l3=new JLabel("Destination");
  l5=new JLabel("(For Source Routing)");
  l6=new JLabel("Via");
  p1=new JPanel(null);
  ImageIcon iicon=new ImageIcon("back.gif");
  l1.setBackground(new Color(120,130,180));
  l2.setBackground(new Color(120,130,180));
  l3.setBackground(new Color(120,130,180));
  l5.setBackground(new Color(120,130,180));
  l6.setBackground(new Color(120,130,180));
  
  b2=new JButton(iicon);
  b2.setBackground(new Color(120,130,180));

  b2.setPressedIcon(new ImageIcon("back1.gif"));
  b2.setFocusPainted(false);
  b2.setBorderPainted(false);
  b2.setContentAreaFilled(false);
  l1.setForeground(new Color(0,0,0));
  l2.setForeground(new Color(0,0,0));
  l3.setForeground(new Color(0,0,0));
  l5.setForeground(new Color(0,0,0));
  l6.setForeground(new Color(0,0,0));
  jc1=new JComboBox();
  jc2=new JComboBox();
  jc3=new JComboBox();
  jc1.setBackground(new Color(120,130,180));
  jc2.setBackground(new Color(120,130,180));
  jc3.setBackground(new Color(120,130,180));
  p1.setBackground(new Color(120,130,180));

  tr.freader(start.topologyname);
  jc1.addItem("Select");
  jc2.addItem("Select");
  jc3.addItem("Select");
  for(int i=0;i<tr.nodename.size();i++)
  {
   jc1.addItem(""+tr.nodename.get(i));
   jc2.addItem(""+tr.nodename.get(i));
   jc3.addItem(""+tr.nodename.get(i));
  }
  b1=new JButton("OK");
  b1.setBackground(new Color(120,130,180));
  p1.setBounds(0,0,d2.width,d2.height);
  con.add(p1);
  l1.setBounds(20,20,200,25);
  l1.setFont(new Font("Dialog",Font.BOLD,14));
  l2.setBounds(20,60,100,35);
  jc1.setBounds(150,60,100,25);
  l3.setBounds(20,100,100,25);
  jc2.setBounds(150,100,100,25);
  l6.setBounds(20,140,100,25);
  jc3.setBounds(150,140,100,25);
  l5.setBounds(250,140,100,25);
  b1.setBounds(160,200,100,25);
  b1.addActionListener(this);
  b2.addActionListener(this);
  l5.setFont(new Font("Dialog",Font.PLAIN,10));
  p1.add(l1);p1.add(l2);p1.add(jc1);
  p1.add(l3);p1.add(jc2);p1.add(b1);
  p1.add(l6);p1.add(l6);p1.add(jc3);con.add(l5);
 }
 public void actionPerformed(ActionEvent ae)
 {
   link l=new link();
   router r=new router();
   String ser=ae.getActionCommand();
   if(ser.equals("OK"))
   {
    if((""+jc1.getSelectedItem()).equals("Select") || (""+jc2.getSelectedItem()).equals("Select") || (""+jc3.getSelectedItem()).equals("Select"))
    {
      JOptionPane.showMessageDialog(this,"Please Select All the Values");  
    }
    else
    {
        String source=""+jc1.getSelectedItem();
        String dest=""+jc2.getSelectedItem();
        String via=""+jc3.getSelectedItem();
      
      if((r.getRouterState(""+source)).equals("Up") && (r.getRouterState(""+dest)).equals("Up")&&(r.getRouterState(""+via)).equals("Up"))
      {
        setSize(500,250);
        String s[][]=new String[6][3];
        String heads[]={"Algorithm","Efficiancy","Delay"};
        setTitle("Source :"+jc1.getSelectedItem()+" Destination :"+jc2.getSelectedItem()+" Via :"+jc3.getSelectedItem());
        jtab=new JTable(s,heads);
        jtab.setRowSelectionAllowed(false);
        jtab.setCellSelectionEnabled(false);

        jsp=new JScrollPane(jtab);
        d2=getSize();
        l4=new JLabel();
        l4.setBackground(new Color(120,130,180));

        l4.setText("Router Efficiancy  : "+r.getRouterEfficiancy()+"   Link Efficiancy(Speed:57344) : "+l.getLinkEfficiancy(57344)+" Link Efficiancy(Speed:131000) : "+l.getLinkEfficiancy(131000));
        l4.setBounds(5,d2.height-70,d2.width,25);
        l4.setFont(new Font("Dialog",Font.PLAIN,12));
        l4.setForeground(new Color(0,0,0));
        b2.setBounds(d2.width-100,d2.height-90,80,40);
        p1.setBounds(0,0,d2.width,d2.height);
        b2.setFont(new Font("Dialog",Font.BOLD,1));
        b2.setText("back");
        setLocation((d1.width-d2.width)/2,(d1.height-d2.height)/2);
        jtab.setForeground(new Color(0,0,0));
        jtab.setFont(new Font("Dialog",Font.BOLD,14));
        jsp.setBounds(5,5,d2.width-20,d2.height-100);
        jtab.setValueAt("Flooding",0,0);
        jtab.setBackground(new Color(120,130,180));
        jtab.setValueAt("Hot-Potato",1,0);
        jtab.setValueAt("Source Routing",2,0);
        jtab.setValueAt("Distence Vector Routing",3,0);
        jtab.setValueAt("Routing Information Protocal",4,0);
        jtab.setValueAt("Link State Routing",5,0);
        double efr=0;

        flooding flood=new flooding();
         efr=flood.getFloodingEfficiancy(new Packet(source,dest));
        jtab.setValueAt(""+efr,0,1);
        
        hotpotato hp=new hotpotato();
        efr=hp.getHotpotatoEfficiancy(new Packet(source,dest));
        jtab.setValueAt(""+efr,1,1);

        sourcerouting sour=new sourcerouting(source);
        efr=sour.getSourceRoutingEfficiancy(new Packet(source,dest),""+jc3.getSelectedItem());
        jtab.setValueAt(""+efr,2,1);

         distencevr dvr=new distencevr(source);
         efr=dvr.getDistenceVectorEfficiancy(new Packet(source,dest));
         jtab.setValueAt(""+efr,3,1);
         jtab.setValueAt(""+dvr.getDistenceVectorDelay(),3,2);

         rip akrip=new rip(source);
         efr=akrip.getRIPEfficiancy(new Packet(source,dest));
         jtab.setValueAt(""+efr,4,1);

         linkstaterouting lsr=new linkstaterouting(source);
         efr=lsr.getLinkStateEfficiancy(new Packet(source,dest));
         jtab.setValueAt(""+efr,5,1);
         jtab.setValueAt(""+lsr.getLinkStateDelay(),5,2);


          p1.removeAll();
          p1.add(jsp);
          p1.add(l4);
          p1.add(b2);
          setSize(d2.width-1,d2.height-1);
          setSize(d2.width+1,d2.height+1);
          setSize(d2.width-1,d2.height-1);
          setSize(d2.width+1,d2.height+1);
      }
      else
        JOptionPane.showMessageDialog(this,"One of the Selected Item is Down Please Chack it");
     }
   }
   if(ser.equals("back"))
   {
     setSize(400,300);
     d2=getSize();
     p1.setSize(d2.width,d2.height);
     setLocation((d1.width-d2.width)/2,(d1.height-d2.height)/2);
     p1.removeAll();
     p1.add(l1);p1.add(l2);p1.add(jc1);
     p1.add(l3);p1.add(jc2);p1.add(b1);
     p1.add(l6);p1.add(l6);p1.add(jc3);p1.add(l5);
          setSize(d2.width-1,d2.height-1);
          setSize(d2.width+1,d2.height+1);
          setSize(d2.width-1,d2.height-1);
          setSize(d2.width+1,d2.height+1);

   }
 }

}
