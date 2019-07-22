/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package packet

import kotlin.test.Test
import kotlin.test.assertNotNull
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.io.*

object PacketTest: Spek({
  describe("A calculator") {
    it("should contain item") {
      var test = RTP()
      assertEquals(test.greeting(), "Hello world.")
    }
  }

  describe("Image_Byte") {
    describe("change_png_to_byte") {
      it ("jpgをバイトファイルに変換します") {
        // Fixme: あとで相対パスに変更する
        val image_file = File("src/test/resources/bird.png")
        val image = Image_Byte(image_file)
        assertTrue(image.change_png_to_byte() is ByteArray)
      }
    }
  }
})
