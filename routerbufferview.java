import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class routerbufferview extends JFrame
{
 JScrollPane jsp;
 JTable jtab;
 Container con;
 Toolkit tk=Toolkit.getDefaultToolkit();
 Dimension d1,d2;
 String sdf[][]=new String[20][3];
 String heads[]={"Source","Destination","Algorithm"};
 public routerbufferview()
 {
  con=getContentPane();
  setSize(400,400);
  d1=tk.getScreenSize();
  d2=getSize();
  setTitle("Router Buffer");
  con.setLayout(null);
  setLocation((d1.width-d2.width)/2,(d1.height-d2.height)/2);
  jtab=new JTable(sdf,heads);
  jtab.setRowSelectionAllowed(false);
  jtab.setCellSelectionEnabled(false);

  jtab.setBackground(new Color(120,130,180));
  jsp=new JScrollPane(jtab);
  jsp.setBounds(0,0,d2.width,d2.height);
  con.add(jsp);
 }
 public void showBuffer(String rname)
 {
  routerbuffer rb=new routerbuffer();
  LinkedList ll=rb.getRouterBuffers(rname);
  setTitle("Router Buffer :"+rname);
  for(int i=0;i<ll.size();i++)
  {
   LinkedList lk=(LinkedList) multipackets.mpackets.get(i);
   Packet p=(Packet) ll.get(i);
   jtab.setValueAt(""+p.getPacketSource(),i,0);
   jtab.setValueAt(""+p.getPacketDestination(),i,1);
   jtab.setValueAt(""+lk.get(2),i,2);
  }
 }
}
