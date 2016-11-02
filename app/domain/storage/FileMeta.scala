package domain.storage

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/12/30.
 */
case class FileMeta(organisationId:String,fileName:String, contentType:String)

object FileMeta{
  implicit val fileFmt = Json.format[FileResults]

}
