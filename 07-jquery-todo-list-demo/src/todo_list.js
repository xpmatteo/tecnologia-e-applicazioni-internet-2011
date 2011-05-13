
function delete_this_item(event) {
  $(this).hide("slow");
}

function make_todo_item(itemText) {
  if (0 == itemText.length) {
    return "<li>???</li>"
  }
  var checkbox = "<input type='checkbox'/>";
  return "<li>" + checkbox + " " + itemText + "</li>";
}

function add_item_to_list(event) {
  var itemField = $("input[name=item]");
  var itemText = itemField.val();
  $("#list").append(make_todo_item(itemText));
  itemField.val("");
  return false;
}

$(document).ready(function() {
  $("form").submit(add_item_to_list);
  $("li").live('click', delete_this_item)
  $("button").click(function() {
    $("#intro").load("intro.html");
  })
});