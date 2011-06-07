

function chat_update() {
  $.get("/chat", function(data){
    $("#log").html(data);
  });
}

function chat_send_message(text) {
  $.post("/chat", { message: text }, function(data){
    $("#log").html(data);
  });  
}

function chat_form_submit() {
  chat_send_message($("input:text").val());
  $("input:text").val("");
  return false;
}

function chat_init() {
  $("form").click(chat_form_submit);
  setInterval(chat_update, 3000);
	load_rooms();
}

function chat_enter_room_handler() {
  chat_enter_room($(this).attr("id"));
}

function chat_enter_room() {
  
}

function load_rooms() {
	$.get("/rooms", function(data) {
		$("#rooms").html(data);
	});
}

