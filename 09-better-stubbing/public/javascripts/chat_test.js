
var chatTest = TestCase.create({
    name: 'Chat test',

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
      
			this.old_chat_enter_room = chat_enter_room;
			this.old_setInterval = setInterval;
			this.old_send_message = chat_send_message;      
    },

		teardown: function() {
			chat_enter_room = this.old_chat_enter_room;
			setInterval = this.old_setInterval;
			chat_send_message = this.old_send_message;      
		},
				
    "test Update Chat Works": function() {
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

		"test chat_form_submit return false so that page is not reloaded": function() {
      this.assertEqual(false, chat_form_submit());			
		},

		"test chat_form_submit clears the input field": function() {
      $("input:text").val("a message");
      chat_form_submit();
      this.assertEqual("", $("input:text").val());			
		},

    testFormSubmit: function() {
			chat_send_message = stubFn();
      $("input:text").val("a message");

      this.assertFalse(chat_send_message.was_called, "non ancora chiamata");
      
      chat_form_submit();

      this.assertTrue(chat_send_message.was_called, "chiamata chat_send_message");
			this.assertEqual("a message", chat_send_message.args[0]);
    },

    testChatInitSetsPeriodicUpdate: function() {
			setInterval = stubFn();
      
      chat_init();
      
      this.assertEqual(chat_update, setInterval.args[0]);
      this.assertEqual(3000, setInterval.args[1]);
    },

    testChatEnterRoomHandlerInvokesChatEnterRoomWithRoomId: function() {
      // stub chat_enter_room
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
    },
    
});
