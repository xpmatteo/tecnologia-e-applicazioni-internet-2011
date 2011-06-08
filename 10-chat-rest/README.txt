
In questo progetto mostro alcuni miglioramenti alla tecnica di stubbing; in
particolare definisco in stub.js alcune funzioni per rimpiazzare una funzione
JS con uno "stub". Vengono usate in rooms_test.js.

Illustro anche la tecnica di rimettere a posto gli stub nel teardown della
classe di test; si confronti il setup e teardown di chat_test.js con quelli
della lezione precedente.

La funzione stubFn è presa a prestito da Test-Driven JavaScript Development
(http://tddjs.com/).

Un'altra cosa è l'uso di TestSuite per eseguire più TestCase in un colpo solo (si veda test_runner.html).
