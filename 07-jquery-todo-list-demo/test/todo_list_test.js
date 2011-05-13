var todoListTest = TestCase.create({
    name: 'Some test',

    testEmptyItem: function() {
        this.assertEquals("<li>???</li>", make_todo_item(""));
    },
    
    testNonEmptyItem: function() {
        var checkbox = "<input type='checkbox' onclick='delete_this_item(this)'/>";
        var expected = "<li>" + checkbox + " Bla bla</li>";
        this.assertEquals(expected, make_todo_item("Bla bla"));
    },
    
});
