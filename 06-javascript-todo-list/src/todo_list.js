
function delete_this_item(checkbox) {
  checkbox.parentNode.className = "deleted";
}

function make_todo_item(itemText) {
  if (0 == itemText.length) {
    return "<li>???</li>"
  }
  var checkbox = "<input type='checkbox' onclick='delete_this_item(this)'/>";
  return "<li>" + checkbox + " " + itemText + "</li>";
}

function add_item_to_list() {
  var itemField = document.getElementById("item_field");
  var itemText = itemField.value;
  var list = document.getElementById("list");
  list.innerHTML += make_todo_item(itemText);
  itemField.value = "";
  return false;
}