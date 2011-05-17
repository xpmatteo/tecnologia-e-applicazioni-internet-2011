var chatTest = TestCase.create({
    name: 'Chat test',

    testBindEventToForm: function() {
        $("body").append($("<form/>"));        

        chat_init($("form"));
        
        this.assertEquals(123, $("form").submit);
        
    },
    
});
