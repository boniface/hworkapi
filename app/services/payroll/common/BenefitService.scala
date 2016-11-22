package app.services.payroll.common

import app.services.payroll.common.Impl.BenefitServiceImpl
import domain.payroll.common.Benefit
import com.websudos.phantom.dsl._
import scala.concurrent.Future
/**
  * Created by Malu.Mukendi on 2016-11-11.
  */
trait BenefitService {
  def createOrUpdate(benefit:Benefit):Future[ResultSet]

def getFileResultById(organisationId: String, benefitId: String): Future[Option[Benefit]]

def getBenefit(benefit: String): Future[Seq[Benefit]]
}
object BenefitService{
  def apply: BenefitService = new BenefitServiceImpl()
}