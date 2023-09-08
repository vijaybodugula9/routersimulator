import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
public class about extends JFrame implements HyperlinkListener
{
 Container con;
 JEditorPane jep;
 JScrollPane jsp;
 Toolkit tk=Toolkit.getDefaultToolkit();
 Dimension d1,d2;
 public about()
 {
  con=getContentPane();
  setSize(300,300);
  d1=tk.getScreenSize();
  d2=getSize();
  setTitle("Routing Simulator : About");
  setLocation((d1.width-d2.width)/2,(d1.height-d2.height)/2);
  con.setLayout(new BorderLayout());
  jep=new JEditorPane();
  jep.setBackground(new Color(120,130,180));
  try{
  File f=new File("about.html");
  jsp=new JScrollPane(jep);
  String s=f.getAbsolutePath();
  jep.setPage("file:"+s);
  con.add(jsp);
  jep.setEditable(false);
  jep.addHyperlinkListener(this);
  }catch(Exception e){System.out.println(" "+e);}
 }
 public void hyperlinkUpdate(HyperlinkEvent he)
 {
  try
  {
   if(he.getEventType()==HyperlinkEvent.EventType.ACTIVATED)
   {
    String hl=he.getDescription();
    File f=new File(hl);
    String ser=f.getAbsolutePath();
    jep.setPage("file:"+ser);
   }
  }catch(Exception e){}
  
 }
}





















