package domain.storage

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/12/30.
 */
case class FileResults( organisationId:String,
                        fileResultsId: String,
                        url: String,
                        size: Option[String])


object FileResults {
  implicit val fileFmt = Json.format[FileResults]
}
