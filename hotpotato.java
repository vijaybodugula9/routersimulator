import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
public class hotpotato
{
  LinkedList nexthop=new LinkedList();
  LinkedList source=new LinkedList();
  topologyreader tr=new topologyreader();
  router r;
  public hotpotato()
  {
   tr.freader(start.topologyname);
   r=new router();
  }
  public void getHops()
  {
   for(int i=0;i<tr.nodename.size();i++)
    {
     source.add(""+tr.nodename.get(i)); 
     nexthop.add(""+r.getNextHop(""+tr.nodename.get(i)));
    }
   viewhotpotato vf=new viewhotpotato(source,nexthop);
    vf.show();
  }
  public double getHotpotatoEfficiancy(Packet p)
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
class viewhotpotato extends JFrame 
{
  Toolkit tk=Toolkit.getDefaultToolkit();
  JTable jtab;
  JScrollPane jsp;
  Container cont;
  String[][] s=new String[200][2];
  String[] heads={"Source","NextHop(s)"};
  Dimension d1,d2;
  Image ii;
 public viewhotpotato(LinkedList source,LinkedList nexthops)
 {
  cont=getContentPane();
  jtab=new JTable(s,heads);
  jtab.setRowSelectionAllowed(false);
  jtab.setCellSelectionEnabled(false);
  jtab.setFont(new Font("Dialog",Font.BOLD,12));
  jtab.setBackground(new Color(120,130,180));
  jsp=new JScrollPane(jtab);
  setSize(300,400);
  setTitle("Hot-Potato");
  d1=getSize();
  d2=tk.getScreenSize();
  ii=tk.getImage("Mycomp.gif");
  setIconImage(ii);
  setLocation((d2.width-d1.width)/2,(d2.height-d1.height)/2);
  cont.setLayout(new BorderLayout());
  cont.add(jsp);
  for(int i=0;i<source.size();i++)
   jtab.setValueAt(source.get(i),i,0);
  for(int i=0;i<nexthops.size();i++)
   jtab.setValueAt(nexthops.get(i),i,1);
 }
}
