package services.payroll.funding.Impl

import com.websudos.phantom.dsl.ResultSet
import domain.payroll.funding.FundingSources
import repositories.payroll.funding.FunderRepository
import services.Service
import services.payroll.funding.FunderService
import scala.concurrent.Future

/**
  * Created by Malu.Mukendi on 2016-11-22.
  */
class FunderServiceImpl extends FunderService with Service{
  override def createOrUpdate(fundingSources: FundingSources): Future[ResultSet] = {
    FunderRepository.save(fundingSources)
  }

  override def getFileResultById(organisationId: String, fundingSourcesId: String): Future[Option[FundingSources]] = {
    FunderRepository.getFileResultById(organisationId, fundingSourcesId)
  }

  override def getFunder(organisationId: String): Future[Seq[FundingSources]] = {
    FunderRepository.getFunder(organisationId)
  }
}
