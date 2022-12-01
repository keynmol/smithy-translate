/* Copyright 2022 Disney Streaming
 *
 * Licensed under the Tomorrow Open Source Technology License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    https://disneystreaming.github.io/TOST-1.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package scala_js

import smithytranslate.formatter.parsers.SmithyParserLive
import smithytranslate.formatter.writers.IdlWriter.idlWriter
import smithytranslate.formatter.writers.Writer.WriterOps

import scala.scalajs.js.annotation._

final class Result(underlying: Either[String, String]) {
  @JSExport
  val error: String = underlying.swap.getOrElse(null)
  @JSExport
  val value: String = underlying.getOrElse(null)
}

@JSExportTopLevel("SmithyFormatter")
object SmithyFormatter {
  @JSExport
  def format(content: String): Result = {
    new Result(
      SmithyParserLive
        .parse(content)
        .map(_.write)
    )
  }
}