import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;
import java.io.*;
public class start extends JFrame implements ActionListener
{
 public static String topologyname=""; 
 Toolkit tk=Toolkit.getDefaultToolkit();
 Container c;
 Dimension d1;
 topologyreader tr=new topologyreader();
 public LinkedList n1=new LinkedList();
 public LinkedList n2=new LinkedList();
 Dimension d10;
 ImageIcon iicon;
 Image ashok=tk.getImage("Mycomp.gif");
 int akmac=0;
 JScrollPane jsp,jsp1;
 JPanel p1;
 static LinkedList routerbuffers=new LinkedList();
 JMenuBar jmb;
 JMenu jm;
 JMenuItem jmi1,jmi2;
 public start()
 {
  c=getContentPane();
  c.setLayout(null);
  setIconImage(ashok);
  setBackground(new Color(120,130,180));
  d1=tk.getScreenSize();
  setSize(d1.width,d1.height);
  setTitle("Routing Simulator");
  d10=getSize();
  jmb=new JMenuBar();
  jm=new JMenu("Help");
  jmi1=new JMenuItem("Notes");
  jmi2=new JMenuItem("About..");
  jm.add(jmi1);jm.add(jmi2);
  jmb.add(jm);
  setJMenuBar(jmb);
  p1=new JPanel(null);
  p1.setBounds(0,0,d10.width,d10.height);
  p1.setBackground(new Color(120,130,180));
  c.add(p1);
  p1.add(new algosel());
  p1.add(new topologysel());
  addWindowListener(new mywindow());
  jmi1.addActionListener(this);
  jmi2.addActionListener(this);
 }
 public void actionPerformed(ActionEvent ae)
 {
   if((ae.getActionCommand()).equals("Notes"))
   {
    notes note=new notes();
    note.show();
   }
   if((ae.getActionCommand()).equals("About.."))
   {
    about abo=new about();
    abo.show();
   }
 }
 public void drawtopology()
 {
   akmac+=1;
   setTitle("Routing Simulator  : "+topologyname.toUpperCase());
         try
         {
           if(akmac>1)
           {
            p1.removeAll();
           linkdraw ld=new linkdraw();
           nodedraw nd=new nodedraw();
           p1.add(ld);
           p1.add(nd);
           p1.add(new algosel());
            p1.add(new topologysel());
           }
           linkdraw ld=new linkdraw();
           nodedraw nd=new nodedraw();
           p1.add(ld);
           p1.add(nd);
           p1.add(new algosel());
           p1.add(new topologysel());
           setSize(d10.width-1,d10.height-1);
           setSize(d10.width+1,d10.height+1);
          }
          catch(Exception e)
          {
           System.out.println("  "+e);
          }
 }
  class topologysel extends JPanel implements ActionListener,ItemListener
 {
  JButton b1,b2,b3;
  JTextField jtf;
  JLabel jl1;
  JComboBox jc1;
  JFileChooser jfc;
  nonodes nn=new nonodes();
  public topologysel()
  {
   setSize(200,150);
   setLocation(580,310);
   setBorder(new EtchedBorder(4));
   setLayout(null);
   setBackground(new Color(120,130,180));
   b1=new JButton("OK");
   b2=new JButton("..");
   jc1=new JComboBox();
   jtf=new JTextField();
   jc1.setBackground(new Color(120,130,180));
   jtf.setBackground(new Color(120,130,180));
   b1.setBackground(new Color(120,130,180));
   b2.setBackground(new Color(120,130,180));
   jc1.addItem("-Select-");
   jc1.addItem("ring.net".toUpperCase());
   jc1.addItem("mesh.net".toUpperCase());
   jc1.addItem("bus.net".toUpperCase());
   jc1.addItem("star.net".toUpperCase());
   jl1=new JLabel("File:");
   jl1.setForeground(new Color(0,0,0));
   jtf.setForeground(new Color(0,0,0));
   jl1.setBounds(15,10,45,25);
   jc1.setBounds(60,10,110,25);
   jtf.setBounds(60,10,110,25);
   b1.setBounds(70,60,70,25);
   b2.setBounds(170,10,25,25);
   add(jl1);add(jc1);add(b1);add(b2);
   b1.addActionListener(this);
   b2.addActionListener(this);
   jc1.addItemListener(this);
  }
  public void itemStateChanged(ItemEvent ie)
  {
    nn.show();
  }
  public void actionPerformed(ActionEvent ae)
  {
   topologywriter tw=new topologywriter();
   
   if((ae.getActionCommand()).equals("OK"))
   {
    String fname="";
    akbutton.states.clear();
    routerbuffers.clear();
    boolean flag=true;
    if(!(fileedit.akfilename).equals(""))
    {
     fname=fileedit.akfilename;
     add(jc1);
     remove(jtf);
     flag=false;
     JOptionPane.showMessageDialog(this,"Topology Selcted is"+fileedit.akfilename);
    }
    else
    {
     add(jc1);
     remove(jtf);
    }
    if(!(""+jc1.getSelectedItem()).equals("-Select-"))
     fname=""+jc1.getSelectedItem();
    else
     {
      if(flag==true)
       JOptionPane.showMessageDialog(this,"Please select the topology name");
     }
    if(fname.equalsIgnoreCase("RING.NET"))
     tw.writeRing(nonodes.nonodes);
    if(fname.equalsIgnoreCase("BUS.NET"))
     tw.writeBus(nonodes.nonodes);
    if(fname.equalsIgnoreCase("MESH.NET"))
     tw.writeMesh(nonodes.nonodes);
    if(fname.equalsIgnoreCase("STAR.NET"))
     tw.writeStar(nonodes.nonodes);
    topologyname=fname;
    tr.freader(fname);
    drawtopology();
    drawtopology();
   }
   if((ae.getActionCommand()).equals(".."))
   {
     remove(jc1);
     add(jtf);
     jc1.setSelectedIndex(0);
     fileedit fe=new fileedit();
     fe.show();
     jtf.setText(""+fileedit.akfilename);
   }
  }
 }
 class mywindow extends WindowAdapter
 {
  public void windowClosing(WindowEvent we)
  {
   System.exit(0);
  }
 }
 public static void main(String args[])
 {
   JFrame sta=new start();
   sta.show();
 }
}
class tcanvas extends Canvas
{
 String nn="";
 public tcanvas(String nodename,int l,int t)
 {
  nn=nodename;
  nn=nn.toUpperCase();
  setFont(new Font("Dialog",Font.BOLD,15));
  setForeground(new Color(170,25,20));
  setSize(35,15);
  setLocation(l,t);
 }
 public void paint(Graphics g)
 {
  g.drawString(nn,10,10);
 }
}
class akbutton extends JButton implements ActionListener
{
 PopupMenu pm;
 MenuItem i1,i2,i3;
 router rou;
 String source="";
 String rstate="Up";
 topologyreader tr=new topologyreader();
 static LinkedList states=new LinkedList();
 String rname="";
 public akbutton(String name,int x,int y,ImageIcon ic)
 {
  tr.freader(start.topologyname);
  source=name;
  setSize(35,35);
  setLocation(x,y);
  setFocusPainted(false);
  setBorderPainted(false);
  setContentAreaFilled(false);
  //setBackground(new Color(120,130,180));
  addActionListener(this);
  setIcon(ic);
  setFont(new Font("Dialog",Font.BOLD,1));
  setText(name);
  pm=new PopupMenu();
  i1=new MenuItem("Router Buffer");
  i2=new MenuItem("Down");
  i3=new MenuItem("Up");
  i1.setFont(new Font("Dialog",Font.BOLD,12));
  i2.setFont(new Font("Dialog",Font.BOLD,12));
  i3.setFont(new Font("Dialog",Font.BOLD,12));
  pm.add(i1);pm.add(i2);
  rou=new router(name);
  add(pm);
  i1.addActionListener(this);
  i2.addActionListener(this);
  i3.addActionListener(this);
  addMouseListener(new mymouse());
  for(int i=0;i<tr.nodename.size();i++)
  {
   states.add(""+tr.nodename.get(i));
   states.add("Up");
  }
  if(states.size()>(2*(tr.nodename.size())))
  {
     int srt=2*(tr.nodename.size());
     for(int i=srt;i<states.size();i++)
       states.remove(i);
  }
 }
 public void setState(String state)
 {
  rstate=state;
 }
 public String getState()
 {
  return(rstate);
 }
 class mymouse extends MouseAdapter
 {
  public void mouseReleased(MouseEvent me)
  {
   rname=getText();
   if(me.isPopupTrigger())
   {
      if((getState()).equals("Down"))
      {
       pm.remove(i2);
       pm.add(i3);
      }
      if((getState()).equals("Up"))
      {
       pm.remove(i3);
       pm.add(i2);
      }
     pm.show(me.getComponent(),me.getX(),me.getY());
   }
  }
 }
 public void actionPerformed(ActionEvent ae)
 {
   String sor=ae.getActionCommand();
   if((sor.equals("Router Buffer")) ||(sor.equals("Down"))||(sor.equals("Up")))
   {
     if(sor.equals("Router Buffer"))
     {
      routerbufferview rbv=new routerbufferview();
      rbv.showBuffer(""+rname);
      rbv.show();
     }
     if(sor.equals("Down"))
     {
       i1.setEnabled(false);
       setState("Down");
       rstate=getState();
       ImageIcon icon=new ImageIcon("Mycomps.gif");
       setIcon(icon);
       for(int i=0;i<states.size();i++)
       {
        if((states.get(i)).equals(source))
        {
         states.set(i+1,"Down");
         break;
        }
       }
     }
     if(sor.equals("Up"))
     {
      i1.setEnabled(true);
      setState("Up");
      rstate=getState();
      ImageIcon icon=new ImageIcon("Mycomp.gif");
      setIcon(icon);
       for(int i=0;i<states.size();i++)
       {
        if((states.get(i)).equals(source))
        {
         states.set(i+1,"Up");
         break;
        }
       }
     }
   }
   else
   {
    if(getState().equals("Up"))
    {
    if((algosel.selalgo).equals("Source Routing"))
    {
     sourcerouting sr=new sourcerouting(sor);
     sr.show();
    }
    if((algosel.selalgo).equals("Distence Vector"))
    {
     distencevr dvr=new distencevr(sor);
     dvr.show();
    }
    if((algosel.selalgo).equals("Routing Information Protocal"))
    {
     rip ri=new rip(sor);
     ri.show();
    }
    if((algosel.selalgo).equals("Link State Routing"))
    {
     linkstaterouting lsr=new linkstaterouting(sor);
     lsr.show();
    }
    }
   }
 }
} 
class fileedit extends JFrame implements ActionListener
{
  JScrollPane jsp;
  JTextArea jta;
  JEditorPane jep;
  JButton b1,b2,b3;
  Container con;
  Dimension d1,d2;
  Toolkit tk=Toolkit.getDefaultToolkit();
  String filename="";
  JFileChooser jfc;
  static String akfilename="";
 public fileedit()
 {
  akfilename="";
  con=getContentPane();
  setSize(500,500);
  d1=tk.getScreenSize();
  d2=getSize();
  setLocation((d1.width-d2.width)/2,(d1.height-d2.height)/2);
  con.setLayout(null);
  jta=new JTextArea();
  jta.setBackground(new Color(120,130,180));
  jta.setForeground(new Color(255,255,255));
  jta.setFont(new Font("Dialog",Font.BOLD,14));
  jep=new JEditorPane();
  jsp=new JScrollPane(jta);
  b1=new JButton("Open");
  b2=new JButton("Save");
  b3=new JButton("Ok");
  jsp.setBounds(0,0,d2.width-20,d2.height-100);
  b1.setBounds(20,(d2.height-70),100,25);
  b2.setBounds(140,(d2.height-70),100,25);
  b3.setBounds(260,(d2.height-70),100,25);
  con.add(jsp);
  con.add(b1);
  con.add(b2);con.add(b3);
  b1.addActionListener(this);
  b2.addActionListener(this);
  b3.addActionListener(this);
 }
 public void actionPerformed(ActionEvent ae)
 {
  if((ae.getActionCommand()).equals("Open"))
  {
   try{
     jfc=new JFileChooser();
     jfc.setCurrentDirectory(new File("e:/jdk1.3/bin/ashok/fresh"));
     jfc.showOpenDialog(this);
     filename=""+jfc.getSelectedFile();
     FileInputStream fin=new FileInputStream(filename);
     String str="";
     int size=fin.available();
     for(int i=0;i<size;i++)
       str=str+(char)fin.read();
     jta.setText(str.toUpperCase());
   }catch(Exception e){}
  }
  if((ae.getActionCommand()).equals("Save"))
  {
   try{
   FileOutputStream fout=new FileOutputStream(filename);
   String str=jta.getText();
   int size=str.length();
   for(int i=0;i<size;i++)
    fout.write((int) (str.charAt(i)));
   }catch(Exception e){}
  }
  if((ae.getActionCommand()).equals("Ok"))
  {
   akfilename=filename;
   setVisible(false);
  }
 }
}
 class nodedraw extends JPanel
 {
  Dimension d1;
  topologyreader tr=new topologyreader();
   static LinkedList n1=new LinkedList();
   static LinkedList n2=new LinkedList();
   Toolkit tk=Toolkit.getDefaultToolkit();
  public nodedraw()
  {
   tr.freader(start.topologyname);
   setSize(550,500);
   setBackground(new Color(120,130,180));
   setBorder(new EtchedBorder(4));
   d1=getSize();
   Image img=tk.getImage("Mycomp.gif");
   boolean nb=tk.prepareImage(img,5,5,this);
   setLocation(5,5);
   ImageIcon iicon=new ImageIcon(img);
   setLayout(null);
   for(int i=0;i<tr.nodename.size();i++)
   {
    double d1=Double.parseDouble((""+tr.nodexcord.get(i)))*(640/2);
    double d2=Double.parseDouble((""+tr.nodeycord.get(i)))*(480/2);
    add(new akbutton(""+tr.nodename.get(i),(int) d1,(int) d2,iicon));
    int i1=(int) d1;
    int i2=(int) d2;
    n1.add(""+i1);
    n2.add(""+i2);
    add(new tcanvas(""+tr.nodename.get(i),(int) (d1),(int) (d2+35)));
   }
  }
 }
 class linkdraw extends JPanel
 {
  Dimension d1;
  topologyreader tr=new topologyreader();
  int x1=0,y1=0,x2=0,y2=0;
  public linkdraw()
  {
   tr.freader(start.topologyname);
   setSize(550,500);
   setBackground(new Color(120,130,180));
   setBorder(new EtchedBorder(5));
   d1=getSize();
   setLocation(5,5);
   setFont(new Font("Dialog",Font.BOLD,14));
  }
 public void paint(Graphics g)
 {
   try
   {
   for(int i=0;i<nodedraw.n1.size();i++)
   {
     findlinkstart(""+tr.linkstart.get(i));
     findlinkend(""+tr.linkend.get(i));
     if(x1==x2)
     {
      if(y1<y2)
       y1=y1+45;
      if(y1>y2)
       y2=y2+45;
       x1=x1+15;
       x2=x2+15;
     }
     if(y1==y2)
     {
      if(x1<x2)
       x2=x2-15;
      if(x1>x2)
       x1=x1-15;
     }
     if(x1!=x2)
     {
      x1=x1+15;
      y1=y1+20;
      y2=y2+20;
      x2=x2+15;
     }
     g.drawLine(x1,y1,x2,y2);
     setForeground(new Color(217,255,0));
     g.drawString((""+tr.linkname.get(i)).toUpperCase(),(x1+x2)/2,(y1+y2)/2+2);
   }
   }catch(Exception e){}
 }
  void findlinkstart(String linkstart)
 {
   double d1=0,d2=0; 
  for(int i=0;i<tr.nodename.size();i++)
  {
    if((""+tr.nodename.get(i)).equals(linkstart))
    {
      d1=Double.parseDouble((""+tr.nodexcord.get(i)))*(640/2);
      d2=Double.parseDouble((""+tr.nodeycord.get(i)))*(480/2);
      x1=(int) d1;
      y1=(int) d2;
    }
   }
  }
 public void findlinkend(String linkend)
 {
   double d1=0,d2=0;
  for(int i=0;i<tr.nodename.size();i++)
  {
   if((""+tr.nodename.get(i)).equals(linkend))
   {
     d1=Double.parseDouble((""+tr.nodexcord.get(i)))*(640/2);
     d2=Double.parseDouble((""+tr.nodeycord.get(i)))*(480/2);
     x2=(int) d1;
     y2=(int) d2;
   }
  }
 }
}
 
