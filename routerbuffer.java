import java.util.*;
public class routerbuffer
{
  public routerbuffer()
  {
  }
  public void addPacketToBuffer(Packet p,String routername)
  {
    for(int i=0;i<start.routerbuffers.size();i++)
    {
     LinkedList r1=(LinkedList) start.routerbuffers.get(i);
     boolean f=true;
      if(routername.equals(""+r1.get(0)))
       {
        r1.add(p);
        if(r1.size()>20)
         r1.removeFirst();
        start.routerbuffers.remove(i);
        start.routerbuffers.add(r1);
        break;
       }
   }
  }
  public LinkedList getRouterBuffers(String rname)
  {
   LinkedList result=new LinkedList();
   for(int i=0;i<start.routerbuffers.size();i++)
   {
    LinkedList ll=(LinkedList) start.routerbuffers.get(i);
    if(rname.equals(""+ll.get(0)))
    {
     for(int j=1;j<ll.size();j++)
      result.add(ll.get(j));
     break;
    }
   }
    return(result);
  }
}

