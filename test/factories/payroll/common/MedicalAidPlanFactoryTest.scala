package factories.payroll.common
import domain.payroll.common.MedicalAidPlan
import org.scalatest.FunSuite

/**
  * Created by Malu.Mukendi on 2016-09-30.
  */
class MedicalAidPlanFactoryTest extends FunSuite {
  test("testMedicalAidPlan")
  {
    val values = Map("medicalAidId"->"MAP100", "name"->"LifeSaver")
    val medPlan =  MedicalAidPlanFactory.createMedicalAidPlan(values)
    val del: Map[String, String] = Map()
    assert(medPlan == MedicalAidPlan(medicalAidId = "MAP100", name ="LifeSaver", details = del))
  }

}
