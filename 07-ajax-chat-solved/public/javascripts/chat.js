

function periodically_update_chat() {
  $.get("/chat", function(data) {
    $("#log").html(data);
  }); 
  
  setTimeout("periodically_update_chat()", 2000);
}

function add_chat_line() {
  var theMessage = $("input").val();
  $.post("/chat", {message: theMessage}, function(data) {
    $("#log").html(data);
  }); 
  $("input").val("");
  return false;
}

$(document).ready(function() {
  $("form").submit(add_chat_line);
  periodically_update_chat();
});