package domain.storage

import play.api.libs.json.Json

/**
 * Created by hashcode on 2016/01/05.
 */
case class StorageUrl(id:String,name:String,url:String)

object StorageUrl{
  implicit val storageFmt = Json.format[StorageUrl]

}
