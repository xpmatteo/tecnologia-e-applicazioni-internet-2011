var factorialTest = TestCase.create({
    name: 'Some test',

    testZero: function() {
        this.assertEquals(1, factorial(0));
    },
    
    testOne: function() {
        this.assertEquals(1, factorial(1));
    },
    
    testTwo: function() {
        this.assertEquals(2, factorial(2));
    },
    
    testThree: function() {
        this.assertEquals(6, factorial(3));
    },
    
    testFour: function() {
        this.assertEquals(24, factorial(4));
    },
    
});
