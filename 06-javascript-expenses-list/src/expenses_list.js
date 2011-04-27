
window.onload = function() {
  var form = document.getElementsByTagName("form")[0];
  form.onsubmit = add_expense;
}

function add_expense() {
  alert("ciao!");
  return false;
}

