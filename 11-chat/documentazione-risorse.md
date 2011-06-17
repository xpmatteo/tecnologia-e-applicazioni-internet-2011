
# Documentazione risorse applicazione chat #

## Stanze ##

### Richiedere la lista ###

    GET /rooms HTTP/1.1
    200 OK
    (vari header)
    \r\n
    <ul>
      <li><a href="/rooms/123">Room 123</a></li>
      <li><a href="/rooms/456">Room 456</a></li>
    </ul>

### Richiere informazioni sulla stanza ###

    GET /rooms/123 HTTP/1.1
    200 OK
    (vari header)
    \r\n
    <h1 class="name">Room 123</h1>
    <ul>  
      <li><a href="/rooms/123/messages" rel="messages">Messages</a></li>
      <li><a href="/rooms/123/users" rel="users">Users</a></li>
    </ul>
    <form action="/rooms/123/messages" method="get">
      <input type="text" name="since" />
    </form>

### Richiedere i messaggi relativi a una stanza ###

Richiesta non condizionata: restituisce tutti i messaggi, se sono al massimo 100, altrimenti gli ultimi 100.  La dimensione della pagina è 100.

    GET /rooms/123/messages HTTP/1.1
    200 OK
    (vari header)
    \r\n
    <ul>
      <li>
        <p class="message">
          <span class="message-id">13252727</a>
          <a href="/users/4444" class="author">Mario Rossi</a> 
          <span class="message">Messaggio 1</span>
          <span class="timestamp">2011-06-08 10:01:00</span>
        </p>
      </li>
      ...
    </ul>


Richiesta di tutti i messaggi successivi a quello con un dato ID.  Assumiamo che i messaggi abbiano un ID sequenziale.

Restituisce tutti i messaggi ordinati per ID successivi a quello con ID 1313131313.  

Con un massimo di 100 messaggi.  

Se ci sono più di 100 messaggi, fabbrica un link alla paginata successiva di risultati.

Se siamo a una pagina successiva, fabbrica anche il link alla paginata precedente.

    GET /rooms/123/messages?since=1313131313

    200 OK
    (vari header)
    \r\n
    <ul>
      <li>  </li>
      <li>  </li>
      <li>  </li>
      <li>  </li>
      ... (un totale di 100 messaggi) ...
    </ul>

    <a href="/rooms/123/messages?since=1313131313&amp;start=100" rel="next">next page</a>

### Aggiungere un messaggio ###

Il messaggio viene aggiunto alla lista permanente dei messaggi di quella stanza

    POST /rooms/123/messages HTTP/1.1
    \r\n
    text=bla bla bla

    200 OK



### La lista degli utenti "dentro" una stanza ###

    GET /rooms/123/users HTTP/1.1

    200 OK
    (vari header)
    \r\n
    <ul>
      <li><a href="/users/444">Mario Rossi</a></li>
      <li><a href="/users/777">Paolino Paperino</a></li>
    </ul>



## autenticazione ##

Implementiamo l'autenticazione tramite un authentication token.  Quando ci autentichiamo con successo, il server restituisce un authentication token.  

    POST /users/authenticate HTTP/1.1
    (vari headers)
    \r\n
    name=pippo&password=secret

    200 OK
    <span id="authentication-token">1234abcdef</span>

L'accesso alle risorse protette restituisce un 401 Unauthorized a meno che la richiesta non contenga lo header
    
    X-Chat-Authentication-Token: 1234abcdef







