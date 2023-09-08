import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class sourcerouting extends JFrame implements ActionListener
{
 String source="";
 Container con;
 JComboBox jc1;
 JLabel l1;
 JScrollPane jsp;
 JTable jtab;
 String s[][]=new String[200][2];
 String heads[]={"Via","NextHop(s)"};
 JButton b1;
 Toolkit tk=Toolkit.getDefaultToolkit();
 Dimension d1,d2;
 topologyreader tr=new topologyreader();
 LinkedList nexthop;
 router rou=new router();
 Image ii;
 public sourcerouting(String psource)
 {
  source=psource;
  setTitle("Source Routing->Source :"+psource);
  con=getContentPane();
  d1=tk.getScreenSize(); 
  setSize(400,500);
  d2=getSize();
  setLocation((d1.width-d2.width)/2,(d1.height-d2.height)/2);
  con.setLayout(new FlowLayout());
  b1=new JButton("OK");
  l1=new JLabel("Destination");
  jc1=new JComboBox();
  jtab=new JTable(s,heads);
  jtab.setRowSelectionAllowed(false);
  jtab.setCellSelectionEnabled(false);
  ii=tk.getImage("Mycomp.gif");
  setIconImage(ii);
  jtab.setBackground(new Color(120,130,180));
  jtab.setFont(new Font("Dialog",Font.BOLD,12));
  jsp=new JScrollPane(jtab);
  con.add(l1);
  con.add(jc1);
  con.add(b1);
  b1.addActionListener(this);
  tr.freader(start.topologyname);
  jc1.addItem("-Select-");
  for(int i=0;i<tr.nodename.size();i++)
  {
   if(!(""+tr.nodename.get(i)).equals(source))
     jc1.addItem(""+tr.nodename.get(i));
  }
 }
 public void actionPerformed(ActionEvent ae)
 {
   String pdest="";
  if((""+jc1.getSelectedItem()).equals("-Select-"))
   JOptionPane.showMessageDialog(this,"Please select a node as Destination");
  else
  {
   pdest=""+jc1.getSelectedItem();
  simulate(pdest);
  con.removeAll();
  con.setLayout(null);
  Dimension d10=getSize();
  jsp.setBounds(0,0,d10.width-10,d10.height-10);
  con.add(jsp);
  setTitle("Source Routing->Source :"+source+" Destination :"+pdest);
  setSize(d2.width-1,d2.height-1);
 }
 }
 public void simulate(String dest)
 {
  LinkedList temp1=rou.getNextHop(""+source,rou.getRouterState(source));
  LinkedList via=new LinkedList();
  for(int j=0;j<tr.nodename.size();j++)
     via.add(""+tr.nodename.get(j));
 if((rou.getRouterState(dest)).equals("Up"))
 {
  for(int j=0;j<via.size();j++)
  {
    jtab.setValueAt(""+via.get(j),j,0);
   nexthop=new LinkedList();
   if((rou.getRouterState(""+via.get(j))).equals("Up"))
   {
    for(int i=0;i<temp1.size();i++)
    {
     if(!dest.equals(""+temp1.get(i)))
      findDest(dest,""+temp1.get(i),""+via.get(j));
      if(via.get(j).equals(""+temp1.get(i)) && dest.equals(""+temp1.get(i)))
      {
       nexthop.add(""+temp1.get(i));
      }
    }
   }
   jtab.setValueAt(""+nexthop,j,1);
  }
  }
  else
    {
     setVisible(false);
    JOptionPane.showMessageDialog(this,"The Destination is Dowm");
    }
 }
 public void findDest(String dest,String nodename,String via)
 {
  link l=new link();
  boolean rflag=false;
  router rou=new router();
  if((rou.getRouterState(nodename)).equals("Up"))
  {
   LinkedList ln1=rou.getNextHop(nodename,rou.getRouterState(nodename));
   ln1=l.findRemove(ln1,source);
   LinkedList nodell1=new LinkedList();
   nodell1.add(""+nodename);
  for(int i=0;i<ln1.size();i++)
   nodell1.add(""+ln1.get(i));
  nodell1=l.removeDupli(nodell1);
  nodell1=l.findRemove(nodell1,source);
 if(true)
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
      LinkedList lt1=new LinkedList();
      lt1=rou.getNextHop(""+ln1.get(i),rou.getRouterState(""+ln1.get(i)));
      for(int j=0;j<lt1.size();j++)
      {
       if((rou.getRouterState(""+lt1.get(j))).equals("Up"))
        ln1.add(""+lt1.get(j));
      }
      ln1=l.findRemove(ln1,source);
      if((ln1.size())>=200*(tr.nodename.size()))
       break;
     }
   }
   else
   {
    break;
   }
  }
    nodell1=l.removeDupli(nodell1);
    nodell1=l.findRemove(nodell1,source);
    
    boolean flag1=true;
    for(int i=0;i<nodell1.size();i++)
    {
     if(flag1==true)
     {
      if(via.equals(""+nodell1.get(i)))
      {
       flag1=false;
        i=0;
      }  
     }
     if(flag1==false)
     {
      if(dest.equals(""+nodell1.get(i)))
      {
        nexthop.add(""+nodename);
        break;
       }
     }
    }
   }
  }
 }
 public LinkedList findDest(Packet p,String via)
 {
  routerbuffer rb=new routerbuffer();
  LinkedList ertcount=new LinkedList();
  link l=new link();
  boolean rflag=false;
  router rou=new router();
  LinkedList temp1=rou.getNextHop(""+p.getPacketSource(),rou.getRouterState(""+p.getPacketSource()));
  LinkedList akl=new LinkedList();
  LinkedList stare=new LinkedList();
  for(int li=0;li<temp1.size();li++)
  {
   int akcount=0;
    akl=new LinkedList();
   String aksource=""+temp1.get(li);
  if((rou.getRouterState(aksource)).equals("Up"))
  {
   if(via.equals(""+temp1.get(li)) && (""+p.getPacketDestination()).equals(""+temp1.get(li)))
   {
    LinkedList nodell2=new LinkedList();
    ertcount.add(""+akcount);
    nodell2.add(""+aksource);
    akl=nodell2;
    akl.addFirst(""+p.getPacketSource());
    stare.add(akl);
   }
   else
   {
   LinkedList ln1=rou.getNextHop(aksource,rou.getRouterState(aksource));
   ln1=l.findRemove(ln1,""+p.getPacketSource());
   if(ln1.size()>1)
   akcount=akcount+ln1.size()-1;
   LinkedList nodell1=new LinkedList();
   nodell1.add(aksource);
   for(int i=0;i<ln1.size();i++)
   nodell1.add(""+ln1.get(i));
  nodell1=l.removeDupli(nodell1);
  nodell1=l.findRemove(nodell1,""+p.getPacketSource());
 if(true)
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
      LinkedList lt1=new LinkedList();
      lt1=rou.getNextHop(""+ln1.get(i),rou.getRouterState(""+ln1.get(i)));
      if(lt1.size()>2)
      akcount=akcount+lt1.size()-2;
      for(int j=0;j<lt1.size();j++)
      {
       if((rou.getRouterState(""+lt1.get(j))).equals("Up"))
         ln1.add(""+lt1.get(j));
      }
      ln1=l.findRemove(ln1,""+p.getPacketSource());
      if((ln1.size())>=200*(tr.nodename.size()))
       break;
     }
   }
   else
   {
    break;
   }
  }
    ertcount.add(""+akcount);
    nodell1=l.removeDupli(nodell1);
    nodell1=l.findRemove(nodell1,""+p.getPacketSource());
    akl=nodell1;
    akl.addFirst(""+p.getPacketSource());
    stare.add(akl);
   }
   }
  }
 }
  LinkedList akl1=new LinkedList();
  System.out.println(""+stare);
  for(int i=0;i<stare.size();i++)
  {
    LinkedList all=(LinkedList) stare.get(i);
    boolean flag=true;
    boolean flag1=true;
    for(int j=0;j<all.size();j++)
    {
     if(flag1==true)
     {
      if((all.get(j)).equals(via))
      {
       flag1=false;
       j=0;
      }
     }
     if(flag1==false)
     {
       if((all.get(j)).equals(""+p.getPacketDestination()))
       {
         akl1=all;
         akl1.removeLast();
         akl1.addLast(""+ertcount.get(i));
         flag=false;
         return akl1;
       }
      }
    }
    if(flag==false)
     break;
  }
    return akl1;
 }

 public double getSourceRoutingEfficiancy(Packet p,String via1)
 {
  router rou=new router();
  routerbuffer rb=new routerbuffer();
  LinkedList akl=findDest(p,via1);
  for(int bi=0;bi<akl.size()-1;bi++)
   rb.addPacketToBuffer(p,""+akl.get(bi));
  double efficiancy=0;
  int total=0;
  double efficiancy1=0;
  try{
  int ur=Integer.parseInt(""+akl.getLast());
  if(ur==0)
  {
   for(int i=0;i<(akl.size()-1);i++)
    efficiancy=efficiancy+rou.getRouterEfficiancy();
    total=tr.nodename.size();
   efficiancy1=efficiancy/total;
  }
  else
  {
   for(int i=0;i<(akl.size()-ur);i++)
    efficiancy=efficiancy+rou.getRouterEfficiancy();
    total=tr.nodename.size();
   efficiancy1=efficiancy/total;
  }
  return(efficiancy1);
  }catch(Exception e){return 0;}
 }
}



