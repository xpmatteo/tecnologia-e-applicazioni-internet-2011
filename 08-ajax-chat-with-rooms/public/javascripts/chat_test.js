
var chatTest = TestCase.create({
    name: 'Some test',

    setup: function() {
      $("#log").html("");  
      
      // stub Ajax per tutti i test
      $this = this;    
      this.ajaxOptions = null;      
      $.ajax = function(options) {
        $this.ajaxOptions = options;
        if ($this.assumeAjaxReturns) {
          $this.ajaxOptions.success($this.assumeAjaxReturns);
        }
      }
      
    },
    
    testUpdateChat: function() {
      this.assumeAjaxReturns = "<p>ciao ciao</p>";
      chat_update();
      this.assertEqual("<p>ciao ciao</p>", $("#log").html());
    },
    
    testSendMessageCopiesDataToChatLog: function() {      
      this.assumeAjaxReturns = "<p>pippo</p>";

      chat_send_message("pippo");

      this.assertEqual("<p>pippo</p>", $("#log").html());
    },
    
    testSendMessageRequest: function() {
      chat_send_message("pippo");
      
      this.assertEqual("post", this.ajaxOptions["type"]);
      this.assertEqual("/chat", this.ajaxOptions["url"]);
      this.assertEqual({message: "pippo"}, this.ajaxOptions["data"]);
    },

    testFormSubmit: function() {
      var old_send_message = chat_send_message;
      var messageSent = null;
      chat_send_message = function(message) {
        messageSent = message;
      }

      $("input:text").val("a message");
      
      var result = chat_form_submit();
      
      this.assertEqual(false, result);      
      this.assertEqual("a message", messageSent);
      this.assertEqual("", $("input:text").val());
      
      chat_send_message = old_send_message;
    },

    testChatInitSetsPeriodicUpdate: function() {
      var old_setInterval = setInterval;
      var theExpression, theTimeout;
      setInterval = function(expression, timeout) {
        theExpression = expression;
        theTimeout = timeout;
      }
      
      chat_init();
      
      this.assertEqual(3000, theTimeout);
      this.assertEqual(chat_update, theExpression);
      setInterval = old_setInterval;
    },

    testChatEnterRoomHandlerInvokesChatEnterRoomWithRoomId: function() {
      // stub chat_enter_room
      var old_chat_enter_room = chat_enter_room;      
      var the_room_id;
      chat_enter_room = function(room_id) {
        the_room_id = room_id;
      }
      
      // associamo lo handler a un link
      $("#the_link_id").click(chat_enter_room_handler);
      
      // simuliamo un click
      $("#the_link_id").trigger('click');

      // verifichiamo che chat_enter_room è stata chiamata
      // con l'argomento giusto
      this.assertEqual("the_link_id", the_room_id);
      
      // unstub chat_enter_room
      chat_enter_room = old_chat_enter_room;
    }
    
});
