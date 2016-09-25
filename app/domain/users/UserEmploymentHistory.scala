package domain.users

import java.util.Date

import play.api.libs.json.Json

/**
 * Created by hashcode on 2015/12/16.
 */
case class PersonEmploymentHistory(id: String,
                                   personId: String,
                                   companyName: String,
                                   companyAddress: String,
                                   companyTelephone: String,
                                   applicatSupervisor: String,
                                   contactSupervisor: Boolean,
                                   reasonsForLeaving: String,
                                   startDate: Date,
                                   endDate: Date,
                                   startingSalary: BigDecimal,
                                   endingSalary: BigDecimal,
                                   currencyId: String,
                                   jobResponsibility: String,
                                   jobId: String,
                                   date: Date,
                                   state: String)

object PersonEmploymentHistory {
  implicit val personehistFmt = Json.format[PersonEmploymentHistory]

}
