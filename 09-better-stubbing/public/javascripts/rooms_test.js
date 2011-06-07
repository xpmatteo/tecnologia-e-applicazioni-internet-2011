
var roomsTest = TestCase.create({
    name: 'Rooms test',

		setup: function() {
			this.old_ajax = $.ajax;
		},
		teardown: function() {
			$.ajax = this.old_ajax;
		},

		"test replace contents of div rooms from ajax": function() {			
			var roomsHtml = "some html";

			$.ajax = stubAjax(roomsHtml);

			load_rooms();
			
			this.assertEquals("/rooms", $.ajax.ajaxOptions.url);
			this.assertEquals(roomsHtml, $("#rooms").html());
		},
		
});
