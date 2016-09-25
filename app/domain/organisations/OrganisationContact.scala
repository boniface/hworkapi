package domain.organisations

import play.api.libs.json.Json

/**
  * Created by hashcode on 2016/09/14.
  */
case class OrganisationContact(orgCode: String,
                               email: String,
                               id: String,
                               contactId: String,
                               details: Map[String, String]) {

}

object OrganisationContact{
   implicit  val OrgContactFmt=Json.format[OrganisationContact]
}
