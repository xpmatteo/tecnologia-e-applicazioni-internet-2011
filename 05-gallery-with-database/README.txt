Obiettivo di questa lezione è imparare un pattern di uso di Jdbc.

Abbiamo questa gerarchia di oggetti:

 * Database si occupa di eseguire codice SQL

 * AllPictures è un Repository che fornisce servizi di salvataggio e ricerca,
   emulando una collezione di oggetti persistente.

 * Picture è un oggetto "di dominio"; rappresenta un concetto che ha senso nel
   dominio della nostra applicazione.

L'esercizio consiste nell'implementare AllPictures, usando TDD.  I primi due test sono già stati impostati.

Per prima cosa occorre creare i database con il comando

  script/create_databases.sh
  
Poi eseguire i test e verificare che ne falliscano due.

Una nota: si può implementare Picture come un "bean" con getTitle(),
setTitle(), eccetera; oppure con due metodi generici get(attributeName) e
set(attributeName, attributeValue). Ci sono pro e contro per entrambi gli
approcci. Se Picture contiene logica di dominio è da preferirsi la prima; se
invece viene usata solo per trasportare dati dal database alla view, va bene
anche la seconda e forse è meglio.

Secondo passo: modificare l'applicazione per utilizzare AllPictures per popolare la pagina principale.
