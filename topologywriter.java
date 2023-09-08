import java.util.*;
import java.io.*;
public class topologywriter
{
 public topologywriter()
 {
 }
 public void writeRing(int b)
 {
  try
  {
   Random ran=new Random();
   File f=new File("ring.net");
   FileOutputStream fout=new FileOutputStream("ring.net");
   f.delete();
   double x1=0,y1=0;
   for(int i=0;i<b;i++)
   {
      y1=ran.nextDouble()+0.2;
      x1=ran.nextDouble();
     String u="Node "+" r"+i+" "+x1+" "+y1+"\n";
     byte by[]=u.getBytes();
     fout.write(by);
   }
   for(int i=0;i<b;i++)
   {
    String uk="";
    if(i<(b-1))
      uk="Link "+" l"+i+" "+"r"+i+" "+"r"+(i+1)+" "+"57600"+" "+"0.01"+" "+"1"+"\n";
    else
     uk="Link "+" l"+i+" "+"r"+i+" "+"r0"+" "+"57600"+" "+"0.01"+" "+"1"+"\n";
     byte by[]=uk.getBytes();
     fout.write(by);
   }
  }catch(Exception e){}
 }
 public void writeMesh(int b)
 {
  try
  {
   File f=new File("mesh.net");
   FileOutputStream fout=new FileOutputStream("mesh.net");
   f.delete();
   double x1=0,y1=0;
   Random ran=new Random();
   int count=0;
   for(int i=0;i<b;i++)
   {
      x1=ran.nextDouble();
      y1=ran.nextDouble()+0.2;
     String u="Node "+" r"+i+" "+x1+" "+y1+"\n";
     byte by[]=u.getBytes();
     fout.write(by);
   }
   for(int i=0;i<b;i++)
   {
    for(int j=i;j<b;j++)
    {
     String uk="";
     if(i!=j)
      uk="Link "+" l"+count+" "+"r"+i+" "+"r"+j+" "+"57600"+" "+"0.01"+" "+"1"+"\n";
     byte by[]=uk.getBytes();
     fout.write(by);
     count++;
    }
   }
  }catch(Exception e){}
  
 }
 public void writeStar(int b)
 {
  try
  {
   Random ran=new Random();
   File f=new File("star.net");
   FileOutputStream fout=new FileOutputStream("star.net");
   f.delete();
   double x1=0.0,y1=0;
   for(int i=0;i<b;i++)
   {
      y1=ran.nextDouble()+0.2;
      x1=ran.nextDouble();
     String u="Node "+" r"+i+" "+x1+" "+y1+"\n";
     byte by[]=u.getBytes();
     fout.write(by);
   }
   for(int i=0;i<b-1;i++)
   {
    String uk="";
      uk="Link "+" l"+i+" "+"r"+i+" "+"r"+(b-1)+" "+"57600"+" "+"0.01"+" "+"1"+"\n";
     byte by[]=uk.getBytes();
     fout.write(by);
   }
  }catch(Exception e){}
 }
 public void writeBus(int b)
 {
  try
  {
   Random ran=new Random();
   File f=new File("bus.net");
   FileOutputStream fout=new FileOutputStream("bus.net");
   f.delete();
   double x1=0.0,y1=0;
   for(int i=0;i<b;i++)
   {
      x1=ran.nextDouble();
      y1=ran.nextDouble()+0.2;
     String u="Node "+" r"+i+" "+x1+" "+y1+"\n";
     byte by[]=u.getBytes();
     fout.write(by);
   }
   for(int i=0;i<b;i++)
   {
    String uk="";
    if(i<(b-1))
      uk="Link "+" l"+i+" "+"r"+i+" "+"r"+(i+1)+" "+"57600"+" "+"0.01"+" "+"1"+"\n";
     byte by[]=uk.getBytes();
     fout.write(by);
   }
  }catch(Exception e){}
 }
 public static void main(String ar[])
 {
  topologywriter tw=new topologywriter();
  tw.writeBus(7);
  tw.writeMesh(7);
  tw.writeRing(7);
 }
}
