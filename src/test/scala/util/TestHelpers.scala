package util

import monix.eval.*
import monix.execution.*

object TestHelpers:
  given Scheduler = monix.execution.Scheduler.global

  def taskTest[T](f: Task[T])(using Scheduler): T =
    f.runSyncUnsafe()
