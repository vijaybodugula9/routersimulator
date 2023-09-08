import java.util.*;
public class link
{
  topologyreader tr=new topologyreader();
  int efficiancy1=2;
  int efficiancy2=1;
  double delay=0;
 public link()
 {
   tr.freader(start.topologyname);
   if(tr.linkname.size()>0)
    delay=Double.parseDouble(""+tr.linkdelay.get(0));
 }
 public double getLinkDelay()
 {
  return(delay);
 }
 public String getLink(String source,String dest)
 {
   for(int i=0;i<tr.linkname.size();i++)
   {
    if((source.equals(""+tr.linkstart.get(i))) && (dest.equals(""+tr.linkend.get(i))))
      return ""+tr.linkname.get(i);
    if((source.equals(""+tr.linkend.get(i))) && (dest.equals(""+tr.linkstart.get(i))))
      return ""+tr.linkname.get(i);
   }
   return "The link does not exists";
 }
 public String getLinkStart(String linkname)
 {
  for(int i=0;i<tr.linkname.size();i++)
  {
    if((""+tr.linkname.get(i)).equals(linkname))
    {
     return ""+tr.linkstart.get(i);
    }
  }
  return "The Link does not exists";
 }
 public int getLinkEfficiancy(int speed)
 {
  if(speed==57344)
    return(efficiancy2);
  if(speed==131000)
    return(efficiancy1);
  return 0;
 }
 public String getLinkEnd(String linkname)
 {
  for(int i=0;i<tr.linkname.size();i++)
  {
    if((""+tr.linkname.get(i)).equals(linkname))
    {
     return ""+tr.linkend.get(i);
    }
  }
  return "The Link does not exists";
 }
 public int getLinkSpeed(String linkname)
 {
  for(int i=0;i<tr.linkname.size();i++)
  {
    if((""+tr.linkname.get(i)).equals(linkname))
    {
     return Integer.parseInt(""+tr.linkspeed.get(i));
    }
  }
  return 0;
 }
 public int getLinkCost(String linkname)
 {
  for(int i=0;i<tr.linkname.size();i++)
  {
    if((""+tr.linkname.get(i)).equals(linkname))
    {
     return Integer.parseInt(""+tr.linkcost.get(i));
    }
  }
  return 0;
 }
 public LinkedList getNextLink(String linkname)
 {
   LinkedList ln=new LinkedList();
   link l=new link();
   for(int i=0;i<tr.linkname.size();i++)
   {
    if((getLinkStart(linkname)).equals(""+tr.linkstart.get(i)))
     {
        LinkedList lu=findStartLink(getLinkEnd(linkname));
        for(int j=0;j<lu.size();j++)
         ln.add(""+lu.get(j));
     }
   }
   ln=l.removeDupli(ln);
   return ln;
 }
 public LinkedList getNextLink1(String linkname)
 {
   LinkedList ln=new LinkedList();
   link l=new link();
   ln.add(""+linkname);
   for(int i=0;i<tr.linkname.size();i++)
   {
    if((getLinkStart(linkname)).equals(""+tr.linkstart.get(i)))
     {
        LinkedList lu=findStartLink(getLinkEnd(linkname));
        for(int j=0;j<lu.size();j++)
         ln.add(""+lu.get(j));
     }
   }
   ln=l.removeDupli(ln);
   return ln;
 }

 public LinkedList getPrevLink(String linkname)
 {
  LinkedList ln=new LinkedList();
  link l=new link();
  for(int i=0;i<tr.linkname.size();i++)
  {
    if((getLinkEnd(linkname)).equals(""+tr.linkend.get(i)))
     {
        LinkedList lu=findEndLink(getLinkStart(linkname));
        for(int j=0;j<lu.size();j++)
         ln.add(""+lu.get(j));
     } 
  }
   ln=l.removeDupli(ln);
   return ln;
 }
 public LinkedList getPrevLink1(String linkname)
 {
  LinkedList ln=new LinkedList();
  link l=new link();
  ln.add(""+linkname);
  for(int i=0;i<tr.linkname.size();i++)
  {
    if((getLinkEnd(linkname)).equals(""+tr.linkend.get(i)))
     {
        LinkedList lu=findEndLink(getLinkStart(linkname));
        for(int j=0;j<lu.size();j++)
         ln.add(""+lu.get(j));
     } 
  }
   ln=l.removeDupli(ln);
   return ln;
 }

 public LinkedList getNextLinks(String linkname)
 {
  LinkedList l=new LinkedList();
  for(int i=0;i<tr.linkname.size();i++)
  {
   if((getLinkEnd(linkname)).equals(""+tr.linkend.get(i))||(getLinkStart(linkname)).equals(""+tr.linkstart.get(i))||(getLinkEnd(linkname)).equals(""+tr.linkstart.get(i))||(getLinkStart(linkname)).equals(""+tr.linkend.get(i)))
     {
        LinkedList lu=findEndLink(getLinkStart(linkname));
        for(int j=0;j<lu.size();j++)
         l.add(""+lu.get(j));
        lu=findStartLink(getLinkEnd(linkname));
        for(int j=0;j<lu.size();j++)
         l.add(""+lu.get(j));
        lu=findEndLink(getLinkEnd(linkname));
        for(int j=0;j<lu.size();j++)
         l.add(""+lu.get(j));
        lu=findStartLink(getLinkStart(linkname));
        for(int j=0;j<lu.size();j++)
         l.add(""+lu.get(j));
     } 
   }
   l=removeDupli(l);
   l=findRemove(l,linkname);
   return l;
 }
 public LinkedList getNextLinksWN(String nodename)
 {
  LinkedList l=new LinkedList();
  for(int i=0;i<tr.linkname.size();i++)
  {
   if(nodename.equals(""+tr.linkend.get(i)) || nodename.equals(""+tr.linkstart.get(i)))
     {
        LinkedList lu=findEndLink(nodename);
        for(int j=0;j<lu.size();j++)
         l.add(""+lu.get(j));
        lu=findStartLink(nodename);
        for(int j=0;j<lu.size();j++)
         l.add(""+lu.get(j));
     } 
   }
   l=removeDupli(l);
   l=findRemove(l,nodename);
   return l;
 }
 public LinkedList getNextLinks1(String linkname)
 {
  LinkedList l=new LinkedList();
   l.add(""+linkname);
  for(int i=0;i<tr.linkname.size();i++)
  {
  if((getLinkEnd(linkname)).equals(""+tr.linkend.get(i))||(getLinkStart(linkname)).equals(""+tr.linkstart.get(i))||(getLinkEnd(linkname)).equals(""+tr.linkstart.get(i))||(getLinkStart(linkname)).equals(""+tr.linkend.get(i)))
     {
        LinkedList lu=findEndLink(getLinkStart(linkname));
        for(int j=0;j<lu.size();j++)
         l.add(""+lu.get(j));
        lu=findStartLink(getLinkEnd(linkname));
        for(int j=0;j<lu.size();j++)
         l.add(""+lu.get(j));
        lu=findEndLink(getLinkEnd(linkname));
        for(int j=0;j<lu.size();j++)
         l.add(""+lu.get(j));
        lu=findStartLink(getLinkStart(linkname));
        for(int j=0;j<lu.size();j++)
         l.add(""+lu.get(j));
     } 
  }
   l=removeDupli(l);
   l=findRemove(l,linkname);
   return l;
 }

 public LinkedList findStartLink(String node)
 {
   LinkedList l=new LinkedList();
   for(int i=0;i<tr.linkname.size();i++)
   {
    if(node.equals(""+tr.linkstart.get(i)))
     {
       l.add(""+tr.linkname.get(i));
     } 
   }
  return(l);
 }
 public LinkedList findEndLink(String node)
 {
   LinkedList l=new LinkedList();
   for(int i=0;i<tr.linkname.size();i++)
   {
    if(node.equals(""+tr.linkend.get(i)))
     {
       l.add(""+tr.linkname.get(i));
     } 
   }
     return(l);
 }
  public LinkedList removeDupli(LinkedList ll1)
  {
    LinkedList le1=ll1;
    for(int i=0;i<ll1.size();i++)
    {
     int imac=0;
     for(int j=i;j<le1.size();j++)
     {
       if((""+ll1.get(i)).equals(""+le1.get(j)))
       {
        imac=imac+1;
        if(imac>1)
         le1.remove(j);
         j=i;
        }
     }
    }
    ll1=le1;
    return(ll1);
  }
  public LinkedList findRemove(LinkedList ll1,String find)
  {
   for(int i=0;i<ll1.size();i++)
   {
    if(find.equals(""+ll1.get(i)))
     ll1.remove(i);
   }
   return ll1;
  }
  public LinkedList allLinkedList(LinkedList ll1)
  {
    LinkedList akc=new LinkedList();
   for(int i=0;i<ll1.size();i++)
   {
    LinkedList ll2=(LinkedList) ll1.get(i);
    for(int j=0;j<ll2.size();j++)
     akc.add(""+ll2.get(j));
   }
   return(akc);
  }
  public static void main(String args[])
  {
   LinkedList ll1=new LinkedList();
   LinkedList ll2=new LinkedList();
   ll1.add("n1");
   ll1.add("n2");
   ll2.add(ll1);
   ll2.add(ll1);
   link l=new link();
   System.out.println(""+l.allLinkedList(ll2));
  }
}
