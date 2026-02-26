function fn() {
  var config = {
    oracleDb: {
      url: 'jdbc:oracle:thin:@host:port:sid',
      username: 'user',
      password: 'pass',
      driverClassName: 'oracle.jdbc.driver.OracleDriver'
    },
    postgresDb: {
      url: 'jdbc:postgresql://host:port/dbname',
      username: 'user',
      password: 'pass',
      driverClassName: 'org.postgresql.Driver'
    }
  };
  return config;
}