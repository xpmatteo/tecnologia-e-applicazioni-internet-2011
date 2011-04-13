Questo esercizio ha due obiettivi:
 
 * Imparare a usare Freemarker come templating engine
 * Imparare a testare le view in maniera flessibile usando xpath.
 
0) Verificare che l'app funzioni
   Verificare che i test passino

1) Usare GalleryView dentro a GalleryController.  Inizialmente lo HTML viene generato direttamente dentro al GalleryController.  Abbiamo copiato il codice Java che produce lo HTML dal Controller alla GalleryView, ma manca ancora il passo finale; ovvero togliere la generazione di HTML dal GalleryController e fare in modo che invece deleghi alla GalleryView.
   (Dopo questo passaggio, verifica che l'app funzioni ancora e che i test passino sempre.)
   
2) Creare un template Freemarker e usarlo dentro a GalleryView; ovvero scrivere un template (da mettere nella directory templates), fare in modo che generi lo stesso codice HTML che in questo momento noi generiamo concatenando stringhe dentro la GalleryView.  Poi modificare la GalleryView per invocare il template di Freemarker invece che concatenare le stringhe.  Il resto dell'applicazione non si deve "accorgere" di nulla. (Verifica che i test passino sempre e che l'applicazione funzioni ancora correttamente)
   
3) Usare il template Yellowing per rimodernare GalleryView.  Così facendo cambiamo radicalmente l'output generato, ma il test della GalleryView deve continuare a passare.  Infatti il test verifica solo la presenza degli elementi HTML più importanti per i nostri scopi, quindi non si rompe per modifiche puramente estetiche.   (Quindi, il test deve sempre passare).

Per fare funzionare il passo 3, occorre trovare una maniera di servire 
le risorse statiche come i file css e le immagini di cui il template
Yellowing ha bisogno.  Il primo passo è creare una directory "public"
all'interno del progetto e copiarvi i file css e le immagini del template.

Poi per servire le immagini statiche ci sono due maniere:

 * Una è di servirle con un metodo simile a quello che abbiamo usato per le
   immagini.  Occorre però gestire correttamente anche i tipi MIME.
   
 * L'altra è di usare il ResourceHandler di Jetty, che serve proprio per questo.  Un esempio è il seguente:
 
    Server server = new Server(8080);
    ResourceHandler resourceHandler = new ResourceHandler();
    resourceHandler.setResourceBase("public");
    HandlerList handlers = new HandlerList();
    handlers.setHandlers(new Handler[]{ 
      new GalleryHandler(), resourceHandler, new DefaultHandler()
    });
    server.setHandler(handlers);
    server.start();

Occorre però avere l'accortezza di modificare GalleryHandler per fargli gestire solo le risorse che gli competono, ovvero 

  * La risorsa "/"
  * Le risorse "/image/*"
  
In tutti gli altri casi deve evitare di "gestire" la richiesta, cosa che si ottiene evitando di invocare "setHandled(true)".

