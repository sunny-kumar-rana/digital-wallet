function loadBalance(){

fetch("balance")
.then(res => res.text())
.then(data => {

document.getElementById("balance").innerText = data;

});
}

function loadTransactions(){

fetch("transactions")
.then(res => res.text())
.then(data => {

document.getElementById("txList").innerText = data;

});
}