import cats.effect.ExitCode
import cats.syntax.all.*
import monix.eval.*
import monix.execution.*

object main extends TaskApp:
  def run(args: List[String]): Task[ExitCode] =
    ExitCode.Success.pure[Task]
