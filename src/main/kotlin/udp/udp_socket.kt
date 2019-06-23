package udp_socket
import java.net.DatagramSocket
import java.net.DatagramPacket



class UDP()
{
  fun run() {
      while(true){
        try {
          val server = DatagramSocket(8888)
          val data = ByteArray(1024)
          var packet = DatagramPacket(data, data.size)
          server.receive(packet)
          val test = "omega drive"
          val test_byte = test.toByteArray(Charsets.UTF_8)
          packet.setData(test_byte)
          server.send(packet)
        } catch (e: Exception) {
          println("error")
          break
      }
    }
  }
}
