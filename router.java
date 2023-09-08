import java.util.*;
public class router 
{
  LinkedList nexthop=new LinkedList();
  topologyreader tr=new topologyreader();
  String rstate="Up";
  int efficiancy=2;
  double delay=0;
  public router()
  {
   tr.freader(start.topologyname);
  }
  public router(String name)
  {
   tr.freader(start.topologyname);
  }
  public  String getRouterState(String s)
  {
    for(int i=0;i<akbutton.states.size();i++)
    {
     if(s.equals(""+akbutton.states.get(i)))
      return(""+akbutton.states.get(i+1));
    }
     return "";
  }
  public int getRouterEfficiancy()
  {
   return(efficiancy);
  }
  public LinkedList getNextHop(String source,String state)
  {
    LinkedList dl=new LinkedList();
   for(int i=0;i<tr.linkname.size();i++)
   {
    if(source.equals(""+tr.linkstart.get(i)) && (state.equals("Up")))
     {
      if((getRouterState(""+tr.linkend.get(i))).equals("Up"))
        dl.add(""+tr.linkend.get(i));
     }
    if(source.equals(""+tr.linkend.get(i)) && (state.equals("Up")))
      {
      if((getRouterState(""+tr.linkstart.get(i))).equals("Up"))
        dl.add(""+tr.linkstart.get(i));
       
      }
   }
   return dl;
  }
  public LinkedList getNextHop(String source)
  {
    LinkedList dl=new LinkedList();
   for(int i=0;i<tr.linkname.size();i++)
   {
    if(source.equals(""+tr.linkstart.get(i)))
        dl.add(""+tr.linkend.get(i));
    if(source.equals(""+tr.linkend.get(i)))
        dl.add(""+tr.linkstart.get(i));
   }
   return dl;
  }

}
