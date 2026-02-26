Feature: Send message to ActiveMQ

  Background:
    * def dbUtils = Java.type('utils.databaseUtilityClass')
    * def mqUtils = Java.type('utils.ActiveMQUtilityClass')
    * def mq = new mqUtils()

  @Regression @Purge
  Scenario: Purge all the MQ messages
    Given eval mq.purgeQueue('TEST.QUEUE')

  @Regression1 @JsonPut
  Scenario: Send Json to MQ
    Given def message = read('classpath:payloads/testTransaction.json')
    And def messageStr = karate.toJson(message)
    And print 'Payload as String:', messageStr
    When eval mq.sendMessage('TEST.QUEUE', messageStr + '')
    Then print 'JSON Message sent successfully to MQ'

  @Regression @FlatFilePut
  Scenario: Send Flat File to MQ
    Given def message = read('classpath:payloads/testFlatFileTransaction.txt')
    When eval mq.sendMessage('TEST.QUEUE', message)
    Then print 'Flat File sent successfully to MQ'