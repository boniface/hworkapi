package domain.common.util

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/10/14.
  */
case class FileResults( id: String,
                        url: String,
                        size: Option[String],
                        mime:String)

object FileResults {
  implicit val fileFmt = Json.format[FileResults]
}

