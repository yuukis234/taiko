/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package game_server

import udp_socket.*
import packet.*
import java.io.*

fun main(args: Array<String>) {
  val image_file = File("src/test/resources/bird.png")
  val half = packet.RTP_Controller(8888, image_file)
  half.send_packet()
}
