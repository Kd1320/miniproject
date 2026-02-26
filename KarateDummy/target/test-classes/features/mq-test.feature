Feature: MQ to Oracle flow validation

  Scenario: Validate transaction flow
    * def transactionId = 'TX123'

  # Step 1: Purge MQ (via Java or API)
    * def purgeResult = call read('classpath:purge-mq.feature')

  # Step 2: Wait for processing
    * retry until response.length > 0
    * def query = "SELECT * FROM processed_data WHERE transaction_id = '" + transactionId + "'"
    * def response = karate.callSingle('classpath:db-check.feature', { query: query })

  # Step 3: Validate Oracle DB
    * match response == { expected structure }