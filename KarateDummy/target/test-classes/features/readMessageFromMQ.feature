Feature: Read messages from ActiveMQ

  Background:
    * def mqUtils = Java.type('utils.ActiveMQUtilityClass')
    * def mq = new mqUtils()

  @readMessage @Regression
  Scenario: Read a single message from TEST.QUEUE
    Given def receivedMessage = mq.readMessage('TEST.QUEUE', 5000)
    And def receivedMessageStr = receivedMessage == null ? null : receivedMessage.toString()
    Then print 'Message read from MQ and the message is:', receivedMessageStr
    And match receivedMessageStr != null
