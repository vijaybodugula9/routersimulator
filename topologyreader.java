import java.util.*;
import java.io.*;
public class topologyreader
{
     public LinkedList nodename=new LinkedList();
     public LinkedList nodexcord=new LinkedList();
     public LinkedList nodeycord=new LinkedList();
     public LinkedList linkname=new LinkedList();
     public LinkedList linkstart=new LinkedList();
     public LinkedList linkend=new LinkedList();
     public LinkedList linkspeed=new LinkedList();
     public LinkedList linkdelay=new LinkedList();
     public LinkedList linkcost=new LinkedList();
     public LinkedList eventtime=new LinkedList();
     public LinkedList eventtype=new LinkedList();
     void freader(String fname)
     {
          String s="";
          nodename.clear();nodexcord.clear();
          nodeycord.clear();linkname.clear();
          linkstart.clear();linkend.clear();
          linkspeed.clear();linkdelay.clear();
          linkcost.clear();eventtime.clear();
          eventtype.clear();
          try
          {
           BufferedReader bf=new BufferedReader(new FileReader(fname));
           s=bf.readLine();
           while(s!=null)
           {
             s=s.trim();
             StringTokenizer st=new StringTokenizer(s);
             String command=st.nextToken();
             if(command.equalsIgnoreCase("node"))
             {
              nodecatcher(st);
             }
             if(command.equalsIgnoreCase("link"))
             {
               linkcatcher(st);
             }
             if(command.equalsIgnoreCase("event"))
             {
             }
              s=bf.readLine();
            }
           }
          catch(Exception e){}
     }
     void nodecatcher(StringTokenizer st)
     {
       nodename.add(st.nextToken().toUpperCase());
       nodexcord.add(st.nextToken().toUpperCase());
       nodeycord.add(st.nextToken().toUpperCase());
     }
     void linkcatcher(StringTokenizer st)
     {
       linkname.add(st.nextToken().toUpperCase());
       linkstart.add(st.nextToken().toUpperCase());
       linkend.add(st.nextToken().toUpperCase());
       linkspeed.add(st.nextToken().toUpperCase());
       linkdelay.add(st.nextToken().toUpperCase());
       linkcost.add(st.nextToken().toUpperCase());
     }
     void eventcatcher(StringTokenizer st)
     {
     }
}

