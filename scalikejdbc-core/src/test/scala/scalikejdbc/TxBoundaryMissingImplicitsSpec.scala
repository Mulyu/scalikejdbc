package scalikejdbc

import org.scalatest._

class TxBoundaryMissingImplicitsSpec extends FlatSpec with Matchers {

  behavior of "TxBoundary.Future"

  it should "compile when there is an ExecutionContext in scope" in {
    """
    import scala.concurrent.ExecutionContext.Implicits.global 
    import TxBoundary.Future._
    DB.localTx(session => scala.concurrent.Future.successful(1))
    """ should compile
  }

  it should "not compile when there is no ExecutionContext in scope" in {
    """
    import TxBoundary.Future._
    DB.localTx(session => scala.concurrent.Future.successful(1))
    """ shouldNot compile
  }

}
