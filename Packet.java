import java.util.*;
public class Packet
{
  String source="",dest="";
 public Packet(String psource,String pdest)
 {
  source=psource;
  dest=pdest;
 }
 public String getPacketSource()
 {
  return source;
 }
 public String getPacketDestination()
 {
  return dest;
 }
}
