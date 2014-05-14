package com.flipcast.model.config

import com.typesafe.config.Config

/**
 * Model to represent Server configuration
 * @param config Sub config that resolves to server configuration
 *
 * @author Phaneesh Nagaraja
 */
case class ServerConfig (config: Config) {

  def hostname = {
    try {
      config.getString("host")
    } catch {
      case ex: Exception => "127.0.0.1"
    }
  }

  def port = {
    try {
      config.getInt("port")
    } catch {
      case ex: Exception => 9200
    }
  }

  def bulkMessageBatchSize = {
    try {
      config.getInt("bulk-message-batch-size")
    } catch {
      case ex: Exception => 1000
    }
  }

}
