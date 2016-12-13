package factories.users

import domain.users.UserEmploymentHistory
import org.joda.time.DateTime

/**
  * Created by SONY on 2016-09-28.
  */
class UserEmploymentHistoryFactory {
  def createPersonEmploymentHistory(values: Map[String, String], date:DateTime, stringMap: Map[String, BigDecimal]):UserEmploymentHistory={
    UserEmploymentHistory(organisationId = values("organisationId"), userId = values("userId"), personEmploymentHistoryId = values("personEmploymentHistoryId"),
      companyName = values("companyName"), companyAddress = values("companyAddress"), companyTelephone = values("companyTelephone"),
      applicantSupervisor = values("applicantSupervisor"), contactSupervisor = values("contactSupervisor"), reasonsForLeaving = values("reasonsForLeaving"),
      startDate = date, endDate = date, startingSalary = stringMap("startingSalary"), endingSalary = stringMap("endingSalary"),
      currencyId = values("currencyId"), jobResponsibility = values("jobResponsibility"), jobId = values("jobId"), date = date,
      state = values("state"))
  }

}
