import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class linkstaterouting extends JFrame
{
 String source="";
 Container con;
 int ashokcost=0;
 JScrollPane jsp;
 JTable jtab;
 String s[][]=new String[200][3];
 String heads[]={"Destination","NextHop(s)","Cost"};
 Toolkit tk=Toolkit.getDefaultToolkit();
 Dimension d1,d2;
 topologyreader tr=new topologyreader();
 LinkedList nexthop;
 router rou=new router();
 LinkedList cost;
 int eff=0;
 Image ii;
 public linkstaterouting(String psource)
 {
  source=psource;
  setTitle("Link State Routing-Source :"+psource);
  con=getContentPane();
  d1=tk.getScreenSize(); 
  setSize(400,500);
  d2=getSize();
  setLocation((d1.width-d2.width)/2,(d1.height-d2.height)/2);
  con.setLayout(null);
  jtab=new JTable(s,heads);
  jtab.setRowSelectionAllowed(false);
  jtab.setCellSelectionEnabled(false);
  ii=tk.getImage("Mycomp.gif");
  setIconImage(ii);
  jtab.setBackground(new Color(120,130,180));
  jtab.setFont(new Font("Dialog",Font.BOLD,12));
  jsp=new JScrollPane(jtab);
  jsp.setBounds(0,0,d2.width-10,d2.height-25);
  con.add(jsp);
  tr.freader(start.topologyname);
  simulate();
 }
 public void simulate()
 {
  LinkedList temp1=rou.getNextHop(""+source,rou.getRouterState(source));
  LinkedList dest=new LinkedList();
  for(int j=0;j<tr.nodename.size();j++)
  {  if(!source.equals(""+tr.nodename.get(j)))
      dest.add(""+tr.nodename.get(j));
  }
  for(int j=0;j<dest.size();j++)
  {
   jtab.setValueAt(""+dest.get(j),j,0);
   nexthop=new LinkedList();
   cost=new LinkedList();
   //System.out.println("--------"+dest.get(j));
   if((rou.getRouterState(""+dest.get(j))).equals("Up"))
   {
    for(int i=0;i<temp1.size();i++)
    {
     if(!source.equals(""+dest.get(j)))
      findDest(""+dest.get(j),""+temp1.get(i));
    }
   }
   try{
   int min=getSmall(cost);
   jtab.setValueAt(""+nexthop.get(min),j,1);
   jtab.setValueAt(""+cost.get(min),j,2);
   }catch(Exception e){/*System.out.println(""+e);*/}
  }
 }
 public void findDest(String dest,String nodename)
 {
  try{
  link l=new link();
  router rou=new router();
  LinkedList ln1=rou.getNextHop(nodename,rou.getRouterState(nodename));
  ln1=l.findRemove(ln1,source);
  int ert=ln1.size();
  LinkedList nodell1=new LinkedList();
   nodell1.add(""+nodename);
  if(dest.equals(nodename))
  {
   if((rou.getRouterState(dest)).equals("Up"))
   {
    nexthop.add(""+l.getLink(source,nodename));
    cost.add(""+(nodell1.size()));
   }
  }
  for(int i=0;i<ln1.size();i++)
   nodell1.add(""+ln1.get(i));
  nodell1=l.removeDupli(nodell1);
  boolean rflag=false;
 if(!dest.equals(nodename))
 { 
  for(int i=0;i<ln1.size();i++)
  {
   if((rou.getRouterState(""+ln1.get(i))).equals("Up"))
   {
    if(dest.equals(""+ln1.get(i)))
    {
     nodell1.add(""+ln1.get(i));
     break;
    }
    else
    {
     nodell1.add(""+ln1.get(i));
     LinkedList lt1=rou.getNextHop(""+ln1.get(i),rou.getRouterState(""+ln1.get(i)));
     for(int j=0;j<lt1.size();j++)
     {
       if((rou.getRouterState(""+lt1.get(j))).equals("Up"))
        ln1.add(""+lt1.get(j));
     }
     ln1=l.findRemove(ln1,nodename);
     if((ln1.size())>=200*(tr.nodename.size()))
     {
      rflag=true;
      break;
     }
    }
    }
    else
     break;
   }
   if(rflag==false)
   {
    nodell1=l.removeDupli(nodell1);
    nodell1=l.findRemove(nodell1,source);
    boolean flag1=true;
    for(int ki=0;ki<nodell1.size();ki++)
    {
     if(dest.equals(""+nodell1.get(ki)))
      flag1=false;
    }
    if(flag1==false)
    {
     nexthop.add(""+l.getLink(source,""+nodename));
     if(ert==1)
      cost.add(""+(nodell1.size()));
     else
      cost.add(""+(nodell1.size()-(ert-1)));
     }
   }
  }
  }catch(Exception e){}
 }
 public double getLinkStateEfficiancy(Packet p)
 {
  LinkedList erty=new LinkedList();
  if((rou.getRouterState(""+p.getPacketDestination())).equals("Down"))
   JOptionPane.showMessageDialog(this,"The Destination is Down");
  else
  {
  try
  {
  link l=new link();
  router rou=new router();
  LinkedList temp1=rou.getNextHop(""+p.getPacketSource(),rou.getRouterState(""+p.getPacketSource()));
  cost=new LinkedList();
  for(int li=0;li<temp1.size();li++)
  {
  if((rou.getRouterState(""+temp1.get(li))).equals("Up"))
  {
  LinkedList ln1=rou.getNextHop(""+temp1.get(li),rou.getRouterState(""+temp1.get(li)));
  ln1=l.findRemove(ln1,""+p.getPacketSource());
  int ert=ln1.size();
  LinkedList nodell1=new LinkedList();
   nodell1.add(""+p.getPacketSource());
  if((""+p.getPacketDestination()).equals(""+temp1.get(li)))
  {
   if((rou.getRouterState(""+p.getPacketDestination())).equals("Up"))
   {
    cost.add(""+(nodell1.size()));
   }
  }
   nodell1.add(temp1.get(li));
  for(int i=0;i<ln1.size();i++)
   nodell1.add(""+ln1.get(i));
  nodell1=l.removeDupli(nodell1);
  boolean rflag=false;
 if(!(""+p.getPacketDestination()).equals(""+temp1.get(li)))
 { 
  for(int i=0;i<ln1.size();i++)
  {
   if((rou.getRouterState(""+ln1.get(i))).equals("Up"))
   {
    if((""+p.getPacketDestination()).equals(""+ln1.get(i)))
    {
     nodell1.add(""+ln1.get(i));
     break;
    }
    else
    {
     nodell1.add(""+ln1.get(i));
     LinkedList lt1=rou.getNextHop(""+ln1.get(i),rou.getRouterState(""+ln1.get(i)));
     for(int j=0;j<lt1.size();j++)
     {
      if((rou.getRouterState(""+lt1.get(j))).equals("Up"))
       ln1.add(""+lt1.get(j));
     }
     ln1=l.findRemove(ln1,source);
     if((ln1.size())>=200*(tr.nodename.size()))
     {
      rflag=true;
      break;
     }
    }
    }
    else
     break;
   }
   if(rflag==false)
   {
    nodell1=l.removeDupli(nodell1);
    nodell1=l.findRemove(nodell1,""+p.getPacketSource());
    nodell1.addFirst(""+p.getPacketSource());
    for(int ki=0;ki<nodell1.size();ki++)
    {
      if((nodell1.get(ki)).equals(""+p.getPacketDestination()))
      {
       nodell1.removeLast();
       erty.add(nodell1);
       if(ert==1)
        cost.add(""+(nodell1.size()));
       else
        cost.add(""+(nodell1.size()-(ert-1)));
      }
    }
   }
  }
  }
  }
  }catch(Exception e){System.out.println(" --"+e);}
  if(cost.size()>0)
  {
  routerbuffer rb=new routerbuffer();
  int rp=getSmall(cost);
  LinkedList were=(LinkedList) erty.get(rp);
  for(int ji=0;ji<were.size();ji++)
   rb.addPacketToBuffer(p,""+were.get(ji));
  int akcost=Integer.parseInt(""+cost.get(rp));
  ashokcost=akcost;
  double efficiancy=0;
  for(int i=0;i<akcost;i++)
    efficiancy=efficiancy+rou.getRouterEfficiancy();
  int total=tr.nodename.size();
  double efficiancy1=efficiancy/total;
  return(efficiancy1);
  }
 }
  return 0;
 }
 public double getLinkStateDelay()
 {
   link l=new link();
   double delay=0;
   for(int i=0;i<ashokcost;i++)
    delay=delay+l.getLinkDelay();
   return(delay);
 }
 public int getSmall(LinkedList ll)
 {
  int min=Integer.parseInt(""+ll.get(0));
  int pos=0;
  for(int i=1;i<ll.size();i++)
  {
   int lk=Integer.parseInt(""+ll.get(i));
    if(min>lk)
    {
     min=lk;
     pos=i;
    }
  }
  return pos;
 }
}



