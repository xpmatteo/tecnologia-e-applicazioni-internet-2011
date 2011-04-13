Questa è una versione rifattorizzata della soluzione dell'esercizio 03.

L'obiettivo è imparare 

 - A separare i nostri oggetti dalle API esterne (architettura esagonale).  Per questo abbiamo incapsulato la HttpServletResponse in una TaiResponse (API nostra che incapsula una API esterna), e abbiamo scritto una interfaccia Controller che fa le veci dello Handler di Jetty, isolando i nostri controller sia da Jetty che dalla Servlet API
 
 - A testare in maniera unitaria i controller.  In questo ci viene in aiuto il fatto che i controller usino interfacce semplificate rispetto a quelle di Jetty e della Servlet API
 
 - A costruire oggetti disaccoppiati e riusabili. Il vecchio GalleryHandler è diventato un ReusableJettyHandler.  
 
 - A fare in modo che gli oggetti ricevano i loro collaboratori dall'esterno, invece che crearseli al loro interno con delle new.  Questo permette di scrivere componenti riusabili (perché il comportamento viene in parte definito da quali collaboratori vengono passati) e testabili (perché nei test posso passare dei collaboratori facili da configurare).
 
 - Il valore dell'OCP: fare in modo che l'applicazione si possa estendere senza dovere sempre modificare i file esistenti, ma aggiungendo file nuovi.  In questo modo l'applicazione diventa meno costosa e rischiosa da manutenere.  

 - Il valore del SRP.  Ciascun oggetto deve avere una sola ragione per cambiare, ovvero occuparsi di fare una sola cosa.  Abbiamo tolto dal PictureController la responsabilità di copiare i byte del file e abbiamo spostato questa responsabilità nella TaiResponse.  Questo semplifica i controller.  
 
 - Il valore del Simple Design. Notare che la TaiResponse ha un interfaccia al tempo stesso più semplice della HttpServletResponse (4 metodi contro 50) e più ricca (permette di copiare un intero file).  La TaiResponse non è adatta a qualsiasi applicazione; fa solo le cose che servono per la nostra.  Un'altra applicazione definirà una sua API interna ad hoc per quello che le serve, diversa da quella che serve per la nostra.  Così evitiamo di usare API inutilmente complesse per i nostri scopi, o meglio le usiamo ma in un posto solo. 
 
L'esercizio consiste in:

 0) Per prima cosa verificare che l'applicazione funzioni (Run As Java Application) e che i test siano verdi (Run As JUnit Test)
 
 1) Scrivere un test unitario per il GalleryController, analogo al PictureControllerTest.  Deve verificare il codice di stato HTTP, il tipo MIME, e l'output html.  Notare che il contenuto della GalleryView è già testato nel GalleryViewTest.  Qui ci basta testare che l'output del GalleryController sia uguale a quello della GalleryView.  (Suggerimento: istanziare una GalleryView nel test e confrontarne l'output con quello del GalleryController)

 2) Leggere il contenuto dei file di properties contenuti nella cartella "pictures-at-an-exhibition" e usarli per passare titolo e didascalia alla GalleryView.
 
 3) Aggiungere un nuovo controller che risponda a url della forma
      /gallery-images/boccioni.lastrada 
(notare: senza suffisso .jpg) e che presenti una pagina simile a quella della home page del servizio, ma con una immagine sola: quella specificata nella url. 
  
 4) (Facoltativo) Testare che la TaiResponseFromServletResponse funzioni correttamente; in particolare il metodo copyThisFile... 
