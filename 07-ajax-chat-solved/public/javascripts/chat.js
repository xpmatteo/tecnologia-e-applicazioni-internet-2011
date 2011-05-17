
function ajax_put_message() {
  $.ajax({
    type: 'GET',
    url: '/chat',
    success: function(data) {
      return data;
    },
  });
}

function add_chat_line() {
  var para = $("<p/>");
  para.text("pippo");
  $("#log").append(para);
  return false;
}

$(document).ready(function() {
  $("form").submit(add_chat_line);
});