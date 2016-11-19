package app.services.payroll.common.Impl

import app.services.payroll.common.BenefitService
import domain.payroll.common.Benefit
import repositories.payroll.common.BenefitRepository
import services.Service
import com.websudos.phantom.dsl._
import scala.concurrent.Future

/**
  * Created by Malu.Mukendi on 2016-11-19.
  */
class BenefitServiceImpl extends BenefitService with Service{
  override def createOrUpdate(benefit: Benefit): Future[ResultSet] = {
    BenefitRepository.save(benefit)
  }

  override def getFileResultById(organisationId: String, benefitId: String): Future[Option[Benefit]] = {
    BenefitRepository.getFileResultById(organisationId, benefitId)
  }

  override def getBenefit(organisationId: String): Future[Seq[Benefit]] = {
    BenefitRepository.getBenefit(organisationId)
  }
}
