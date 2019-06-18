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
          val output = String(data, Charsets.UTF_8)
          println(output)
          println(data.size)
        } catch (e: Exception) {
          println("error")
          break
      }
    }
  }
}
