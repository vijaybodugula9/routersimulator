import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
public class flooding
{
  LinkedList nexthop=new LinkedList();
  LinkedList source=new LinkedList();
  topologyreader tr=new topologyreader();
  routerbuffer rb=new routerbuffer();
  public flooding()
  {
   tr.freader(start.topologyname);
  }
  public void getHops()
  {
    akbutton akb;
    router r=new router();
   for(int i=0;i<tr.nodename.size();i++)
    {
    LinkedList lk=r.getNextHop(""+tr.nodename.get(i));
    for(int j=0;j<lk.size();j++)
     rb.addPacketToBuffer(new Packet(""+tr.nodename.get(i),""+lk.get(j)),""+tr.nodename.get(i));
     source.add(""+tr.nodename.get(i)); 
     nexthop.add(""+r.getNextHop(""+tr.nodename.get(i)));
    }
    viewflooding vf=new viewflooding(source,nexthop);
    vf.show();
  }
  public double getFloodingEfficiancy(Packet p)
  {
    double efficiancy=0;
    link l=new link();
    router r=new router();
    routerbuffer rb=new routerbuffer();
    LinkedList lr=r.getNextHop(""+p.getPacketSource());
   for(int i=0;i<lr.size();i++)
    {
     if((r.getRouterState(""+lr.get(i))).equals("Down"))
      lr.remove(i);
     if((""+p.getPacketDestination()).equals(""+lr.get(i)))
     {
       lr.clear();
       lr.add(""+p.getPacketSource());
       break;
     }
     LinkedList lt1=r.getNextHop(""+lr.get(i));
     boolean boo=true;
     for(int j=0;j<lt1.size();j++)
     {
       if((""+p.getPacketDestination()).equals(""+lt1.get(j)))
       {
           boo=false;
           break;
       }
       if((""+r.getRouterState(""+lt1.get(j))).equals("Up"))
         lr.add(""+lt1.get(j));
     }
     if(boo==false)
       break;
      lr=l.findRemove(lr,""+p.getPacketSource());
    }
    lr=l.removeDupli(lr);
    lr=l.findRemove(lr,""+p.getPacketSource());
    lr.addFirst(""+p.getPacketSource());
    System.out.println(" "+lr);
    for(int i=0;i<lr.size();i++)
    {
     rb.addPacketToBuffer(p,""+lr.get(i));
     efficiancy=efficiancy+r.getRouterEfficiancy();
    }
    int total=tr.nodename.size();
    double efficiancy1=efficiancy/total;  
    return(efficiancy1);
  }
}
class viewflooding extends JFrame 
{
  Toolkit tk=Toolkit.getDefaultToolkit();
  JTable jtab;
  JScrollPane jsp;
  Container cont;
  String[][] s=new String[200][2];
  String[] heads={"Source","NextHop(s)"};
  Dimension d1,d2;
  Image ii;
 public viewflooding(LinkedList source,LinkedList nexthops)
 {
  cont=getContentPane();
  jtab=new JTable(s,heads);
  jtab.setBackground(new Color(120,130,180));
  jtab.setFont(new Font("Dialog",Font.BOLD,12));
  jtab.setRowSelectionAllowed(false);
  jtab.setCellSelectionEnabled(false);
  jsp=new JScrollPane(jtab);
  setSize(300,400);
  setTitle("Flooding");
  d1=getSize();
  ii=tk.getImage("Mycomp.gif");
  setIconImage(ii);
  d2=tk.getScreenSize();
  setLocation((d2.width-d1.width)/2,(d2.height-d1.height)/2);
  cont.setLayout(new BorderLayout());
  cont.add(jsp);
  for(int i=0;i<source.size();i++)
   jtab.setValueAt(source.get(i),i,0);
  for(int i=0;i<nexthops.size();i++)
   jtab.setValueAt(nexthops.get(i),i,1);
 }
}
