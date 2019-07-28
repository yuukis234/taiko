package packet
import java.io.*
import javax.imageio.ImageIO
import java.net.DatagramSocket
import java.net.DatagramPacket

// Imageをbyteコードに変えてくれるクラス
// fileにはロードしたい画像のFileオブジェクトを入れる
// fixme:fileをあとでFileではなく、stringで書く。
class Image_Byte (val file : File) {
  // ImageをByteコードに変換してくれる
  // Pngファイルをbyteファイルに変換してくれる
  fun change_png_to_byte (): ByteArray {
    val image = ImageIO.read(file)
    val bos = ByteArrayOutputStream()
    val os = BufferedOutputStream( bos )
    image.flush()
    ImageIO.write( image, "png", os )
    return bos.toByteArray();
  }
}

// RTPのHeaderの構造を収めるクラス
// Headerによって決まりを決めていきます。
class RTP_Header {

  // Headerの構造を入れています。
  private val rtp_header = ByteArray(9)
  private var version : Byte = 1
  private var padding : Byte = 0
  private val extension : Byte = 0
  private val csrc_count : Byte = 0
  private val marker : Byte = 0
  private val payload_type : Byte = 0
  private val sequence_number : Byte = 0
  private val timestamp : Byte = 0
  private val ssrc : Byte = 0

  // rtp_headerの配列を作ってくれる。
  fun create_header (version: Byte = 0, padding: Byte = 0, extension: Byte = 0,
                     csrc_count : Byte = 0, marker : Byte = 0, payload_type : Byte = 0,
                     sequence_number : Byte = 0, timestamp : Byte = 0, ssrc : Byte = 0) : ByteArray {
    this.rtp_header[0] = version
    this.rtp_header[1] = padding
    this.rtp_header[2] = extension
    this.rtp_header[3] = csrc_count
    this.rtp_header[4] = marker
    this.rtp_header[5] = payload_type
    this.rtp_header[6] = sequence_number
    this.rtp_header[7] = timestamp
    this.rtp_header[8] = ssrc
    return this.rtp_header
  }
}

// RTPの制御をしてくれるコントローラー
// 輻輳を制御してネットワークの負荷を欠けないようにするためのクラス
// これをすることによって、ネットワークの負荷を下げて快適なゲーム環境を提供しくれる。
class RTP_Controller(port: Int ,file: File) {
  private val image = Image_Byte(file)
  private val rtp_header = RTP_Header()
  private val server = DatagramSocket(port)

  fun connect_header_file () : ByteArray {
    return rtp_header.create_header() + image.change_png_to_byte()
  }

  fun send_packet () {
    while(true){
      try {
        val data = connect_header_file ()
        val packet = DatagramPacket(data, data.size)
        println(data.size)
        this.server.receive(packet)
        val buffer = packet.getData()
        val str = String(buffer, 0, packet.getLength())
        println(str)
        this.server.send(packet)
      } catch (e: Exception) {
        println(e)
        break
      }
    }
  }
}

// RPTの機能をまとめたクラス
// このクラスを主に使う。このクラスに機能を付け加えて、使えるようにする。
// ここが中枢を司る。
class RTP (file: File) {
  var header = RTP_Header()
  var file = Image_Byte(file)

// header_arrayとarrayをつなげる
// arrayは画像ファイルを想定していて、500kbのpngファイルを想定しています。
// つなげることで一つのパケットを作ることができます。
  fun connect_header_file () : ByteArray {
    return header.create_header() + file.change_png_to_byte()
  }
}
