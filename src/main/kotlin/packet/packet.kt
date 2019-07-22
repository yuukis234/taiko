package packet
import java.io.*
import javax.imageio.ImageIO
import java.net.DatagramSocket

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
  var rtp_header = ByteArray(9)
  val version : Byte = 1
  val padding : Byte = 0
  val extension : Byte = 0
  val csrc_count : Byte = 0
  val marker : Byte = 0
  val payload_type : Byte = 0
  val sequence_number : Byte = 0
  val timestamp : Byte = 0
  val ssrc : Byte = 0

// rtp_headerの配列を作ってくれる。
  fun create_header () : ByteArray {
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
class RTP_Controller {}

// RPTの機能をまとめたクラス
// このクラスを主に使う。このクラスに機能を付け加えて、使えるようにする。
// ここが中枢を司る。
class RTP{
  fun greeting (): String = "Hello world."
}
