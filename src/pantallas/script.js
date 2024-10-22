document.getElementById("sumButton").addEventListener("click", function () {
    const number1 = parseFloat(document.getElementById("number1").value);
    const number2 = parseFloat(document.getElementById("number2").value);
    const result = number1 + number2;

    document.getElementById("resultValue").textContent = isNaN(result) ? "0" : result;
});
