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
