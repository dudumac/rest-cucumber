Feature: the version can be retrieved
  
  @webcontainerstarted
  Scenario: client makes call to GET /version
    When the client calls /version
    Then the client receives status code of 200
    And the client receives server version 1.0