package services.payroll.funding

import com.websudos.phantom.dsl._
import domain.payroll.funding.FundingSources
import services.payroll.funding.Impl.FunderServiceImpl

import scala.concurrent.Future
/**
  * Created by Malu.Mukendi on 2016-11-22.
  */
trait FunderService {
  def createOrUpdate(fundingSources:FundingSources):Future[ResultSet]

  def getFileResultById(organisationId: String, fundingSourcesId: String): Future[Option[FundingSources]]

  def getFunder(organisationId: String): Future[Seq[FundingSources]]
}
object FunderService{
  def apply: FunderService = new FunderServiceImpl()
}
