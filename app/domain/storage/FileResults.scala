package domain.storage

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/12/30.
 */
case class FileResults(
                        id: String,
                        url: String,
                        size: Option[String]
                        )


object FileResults {
  implicit val fileFmt = Json.format[FileResults]
}
