/*
 * Copyright 2011-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.vertx.groovy.core.http

import org.vertx.java.core.Handler

class HttpServer extends org.vertx.java.core.http.HttpServer {

  private reqHandler;
  private wsHandler;

  HttpServer requestHandler(Closure hndlr) {
    super.requestHandler(wrapRequestHandler(hndlr))
    this.reqHandler = hndlr
    this
  }

  def getRequestHandler() {
    return reqHandler;
  }

  HttpServer websocketHandler(Closure hndlr) {
    super.websocketHandler(wrapWebsocketHandler(hndlr))
    this.wsHandler = hndlr
    this
  }

  def getWebsocketHandler() {
    wsHandler;
  }

  void close(Closure hndlr) {
    super.close(hndlr as Handler)
  }

  protected wrapRequestHandler(Closure hndlr) {
    return {hndlr.call(new HttpServerRequest(it))} as Handler
  }

  protected wrapWebsocketHandler(Closure hndlr) {
    return {hndlr.call(new ServerWebSocket(it))} as Handler
  }

}
