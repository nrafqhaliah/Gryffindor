// Get the payment buttons, card info section, card form, and result div
var cashBtn = document.getElementById("cash");
var cardBtn = document.getElementById("card");
var cardInfoDiv = document.getElementById("card-info");
var cardForm = document.getElementById("card-form");
var resultDiv = document.getElementById("result");

// Add click event listeners to the payment buttons
cashBtn.addEventListener("click", function() {
    cardInfoDiv.style.display = "none";
    resultDiv.textContent = "Payment method selected: Cash";
});

cardBtn.addEventListener("click", function() {
    cardInfoDiv.style.display = "block";
    resultDiv.textContent = "";
});

// Submit event listener for the card form
cardForm.addEventListener("submit", function(event) {
    event.preventDefault(); // Prevent form submission

    var cardNumber = document.getElementById("card-number").value;
    var cardName = document.getElementById("card-name").value;
    var expiryDate = document.getElementById("expiry-date").value;

    resultDiv.textContent = "Payment method selected: Card";
    resultDiv.textContent += "\nCard Number: " + cardNumber;
    resultDiv.textContent += "\nCardholder Name: " + cardName;
    resultDiv.textContent += "\nExpiry Date: " + expiryDate;

    cardForm.reset(); // Reset form inputs
});





