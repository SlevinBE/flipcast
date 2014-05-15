package com.flipcast.push.example

import com.flipcast.push.model.{DeviceOperatingSystemType, PushMessageData}
import spray.json._
import akka.event.slf4j.Logger
import com.flipcast.push.common.PushMessageTransformer
import org.apache.commons.codec.binary.Base64

/**
 * This transformer doesn't assume a certain format. It's up to the user of the service to provide the
 * valid format for the device it's being send to.
 */
object RawPushMessageTransformer extends PushMessageTransformer with DefaultPushMessageProtocolSupport {

  val log = Logger("DefaultPushMessageTransformer")

  override def transform(configName: String, message: String) = {
    val msg = JsonParser(message).convertTo[DefaultPushMessage]
    val data = new PushMessageData()
    data.addPayload(DeviceOperatingSystemType.ANDROID, msg.message)
    data.addPayload(DeviceOperatingSystemType.iOS, msg.message)
    data.addPayload(DeviceOperatingSystemType.WindowsPhone, msg.message)
    data
  }

}
