package services.position

import com.websudos.phantom.dsl._
import domain.position.PositionDesignation
import services.position.Impl.PositionDesignationServiceImpl

import scala.concurrent.Future

/**
  * Created by Theo on 22-Nov-16.
  */
trait PositionDesignationService {
  def createOrUpdate(positionId: PositionDesignation): Future[ResultSet]

  def getDesignationById(positionId: String, positionDesignationId: String): Future[Option[PositionDesignation]]

  def getPositionDesignations(positionId: String): Future[Seq[PositionDesignation]]
}

object PositionDesignationService{
  def apply: PositionDesignationService = new PositionDesignationServiceImpl()
}
