
Continuiamo l'esercizio chat: aggiungiamo diverse "stanze" indipendenti, e iniziamo a lavorare in TDD su JavaScript.

PREREQUISITO
- aggiungiamo una div con id="rooms" sia a index.html
  che a test_runner.html


PUNTO 0

La funzione chat_rooms_update():
 - interroga con ajax "/rooms"
 - ottiene un html del tipo
    <ul>
      <li><a id="123" href="#">Room 123</a></li>
      <li><a id="456" href="#">Room 456</a></li>
    </ul>
    
  - rimpiazza il contenuto della div #rooms con questo
    html

Questa funzionalità si può testare con due test, simili a
testSendMessageCopiesDataToChatLog e testSendMessageRequest

PUNTO 1

  Dovremo legare una funzione all'evento onclick dei link
  questa funzione la chiamiamo chat_room_enter_handler() e deve:
    - prendere l'id della stanza da $(this).attr("id")
      (per esempio "123")
    - deve cambiare il colore del link cliccato: deve
      diventare rosa
    - deve ripristinare il colore di default su tutti gli altri link

Il test è testChatEnterRoomHandlerInvokesChatEnterRoomWithRoomId. E'
incompleto, manca la parte sul colore rosa. Suggerimento: usare addClass()


PUNTO 2

  La funzione chat_enter_room() deve:
    - invocare con ajax "/rooms/123"
      (assumiamo che il server risponde con "<p>pippo</p>")
    - copiare "<p>pippo</p>" nella div #log    
    - modificare una variabile globale $current_room_id, il 
      valore deve diventare 123
    
PUNTO 3  
  
  Modificare la funzione chat_update() per invocare Ajax sulla 
    "/room/" + $current_room_id

PUNTO 4
 
  Implementare le modifiche lato server per fare funzionare il tutto.  
  
PUNTO 5 

  Assicurarsi che la funzione chat_rooms_update() associ la funzione "chat_room_enter_handler" all'evento click di tutti i link dentro alla div #rooms.
  