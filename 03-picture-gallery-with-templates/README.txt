Questo esercizio ha due obiettivi:
 
 * Imparare a usare Freemarker come templating engine
 * Imparare a testare le view in maniera flessibile usando xpath.
 
0) Verificare che l'app funzioni
   Verificare che i test passino

1) Usare GalleryView dentro a GalleryController
   (Verifica che l'app funzioni ancora)
   
2) Creare un template Freemarker e usarlo dentro 
   a GalleryView (il test deve sempre passare)
   
3) Usare il template Yellowing per rimodernare GalleryView
   (il test deve sempre passare)

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

