package edu.neu.coe.csye7200

import scala.util.Random

case class RandomStream(r: Random) {
  lazy val stream: Stream[Double] = Stream.continually(r.nextDouble())
}

case class GaussianStream(r: Random) {
  val randomStream = RandomStream(r)
  val stream = randomStream.stream
      for (xl <- stream.grouped(2).toStream;
           x <- GaussianStream.methodBoxMuller(xl))
        yield x


}
  object GaussianStream extends App {

  def methodBoxMuller(pairs: Stream[Double]): Stream[Double] = pairs match {
    case u #:: v #:: t =>
      val k = math.sqrt(-2 * math.log(u))
      Stream(k * math.cos(2 * math.Pi * v),  k * math.sin(2 * math.Pi * v))
  }

  val z = GaussianStream(new Random(0L))
  println(z.stream.toList.take(10))
}
