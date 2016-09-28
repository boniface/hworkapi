package domain.users

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/12/16.
 */
case class PersonEmploymentHistory(organisationId: String,
                                   userId: String,
                                   personEmploymentHistoryId: String,
                                   companyName: String,
                                   companyAddress: String,
                                   companyTelephone: String,
                                   applicantSupervisor: String,
                                   contactSupervisor: String,
                                   reasonsForLeaving: String,
                                   startDate: DateTime,
                                   endDate: DateTime,
                                   startingSalary: BigDecimal,
                                   endingSalary: BigDecimal,
                                   currencyId: String,
                                   jobResponsibility: String,
                                   jobId: String,
                                   date: DateTime,
                                   state: String)

object PersonEmploymentHistory {
  implicit val personehistFmt = Json.format[PersonEmploymentHistory]

}
