package domain.organisations

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/09/14.
  */
case class OrganisationAddress(orgCode: String,
                               email: String,
                               id: String,
                               locationId:String,
                               addressId: String,
                               details: Map[String, String]) {

}

object OrganisationAddress{
   implicit val orgaddressFmt=Json.format[OrganisationAddress]
}