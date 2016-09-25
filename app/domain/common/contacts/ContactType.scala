package domain.common.contacts

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/08/12.
  */
case class ContactType(id:String,
                       name:String) {

}

object ContactType{
    implicit val ContacttypFmt=Json.format[ContactType]
}