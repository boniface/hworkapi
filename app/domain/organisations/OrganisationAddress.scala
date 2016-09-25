package domain.organisations

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/09/14.
  */
case class OrganisationAddress(organisationId: String,
                               organisationEmail: String,
                               organisationAddressId: String,
                               organisationLocationId:String,
                               addressTypeId: String,
                               details: Map[String, String]) {

}

object OrganisationAddress{
   implicit val orgaddressFmt=Json.format[OrganisationAddress]
}