$(document).ready(function(){
    loadBalance();
    loadOrders();
})

function configure_slider()
{
    $('.slider').slick({
        arrows:false,
        dots:true,
        appendDots:'.slider-dots',
        dotsClass:'dots'
    });
}

function add(){
    var input = document.getElementById("Amount_inp").value;
    const url = "http://localhost:8080/api/wallet";
    const data = Number(input);
    const params = {
        headers : { "content-type" : "application/json; charset=UTF-8" },
        body : data,
        method : "POST",
        mode : "cors"
    };
    fetch(url, params)
        .then(function(response) {
            if (!response.ok) {
                console.log('Something went wrong while making HTTP call');
            }
            return response.text()
        }).then(amount => {
            document.getElementById("Wallet_amt").innerText=amount;
        })
        .catch(error => console.log('There is some error, please check server'));
}

function loadBalance() {
    const url = 'http://localhost:8080/api/wallet';
    fetch(url)
        .then(response => {
            if (!response.ok) {
                console.log('Something went wrong while making HTTP call');
            }
            return response.text();
        })
        .then(amount => {
            console.log(amount);
            document.getElementById("Wallet_amt").innerText = String(amount);
        })
        .catch(error => console.log('There is some error, please check server'));
}

let hamburger = document.querySelector('.hamburger');
let times = document.querySelector('.times');
let mobileNav = document.querySelector('.mobile-nav');
let listItem = mobileNav.querySelectorAll("ul > li > a");


hamburger.addEventListener('click', function(){
    mobileNav.classList.add('open');
});

times.addEventListener('click', function(){
    mobileNav.classList.remove('open');
});

listItem.forEach(function(e){
    e.addEventListener('click', function(){
        mobileNav.classList.remove('open');
    });
});

function goToContact()
{
    let contact =  document.querySelector('#contact');
    contact.scrollIntoView();
}

function goUp()
{
    let header = document.querySelector('header');
    let arrowUp = document.querySelector('.arrow-up');
    arrowUp.addEventListener('click', function(){
        header.scrollIntoView();
    });
}

function getCustomerMonitordetails()
{
    let currentPrice = Number(document.getElementById("currentMonitorPrice").innerText);
    let customerPrice = Number(document.getElementById("customerMonitorPrice").value);
    let customerQuantity = Number(document.getElementById("customerMonitorQuantity").value);

    console.log(currentPrice);
    console.log(customerPrice);
    console.log(customerQuantity);

    buyAtPrice(1, customerPrice, customerQuantity, "postMonitorPriceSubmit", currentPrice);
}

function getCustomerTapedetails()
{
    let currentPrice = Number(document.getElementById("currentTapePrice").innerText);
    let customerPrice = Number(document.getElementById("customerTapePrice").value);
    let customerQuantity = Number(document.getElementById("customerTapeQuantity").value);

    console.log(currentPrice);
    console.log(customerPrice);
    console.log(customerQuantity);

    buyAtPrice(2, customerPrice, customerQuantity, "postTapePriceSubmit", currentPrice);
}


function buyAtPrice(productId, customerPrice, customerQuantity, elementId, currentPrice) {
    if(customerPrice == 0)
    {
        document.getElementById(elementId).style.color = "orange";
        document.getElementById(elementId).innerHTML = "Please set a price, at which you want to buy!";
    }
    else if(currentPrice <= customerPrice){
        document.getElementById(elementId).style.color = "red";
        document.getElementById(elementId).innerHTML = "Please set a price lower than current price!";
    }
    else {
        document.getElementById(elementId).style.color = "green";
        const url = "http://localhost:8080/api/buyatprice";
        const data = {
            'productId': productId,
            'price': customerPrice,
            'quantity': customerQuantity,
        };
        let params;
        params = {
            headers: {"content-type": "application/json;"},
            body: JSON.stringify(data),
            method: "POST",
            mode: "cors"
        };
        fetch(url, params)
            .then(function (response) {
                if (!response.ok) {
                    console.log('Something went wrong while making HTTP call');
                }
                return response.text()
            }).then(response => {
            document.getElementById(elementId).innerHTML = "We'll buy this product for you at or less than: ₹ " + customerPrice;
        })
            .catch(error => console.log(error));
    }
}

function loadOrders() {
    const url = 'http://localhost:8080/api/orders';
    fetch(url)
        .then(response => {
            if (!response.ok) {
                console.log('Something went wrong while making HTTP call');
            }
            return response.json();
        })
        .then(orders => {
            orders.forEach(order => {
                addOrder(order.orderProducts[0].product.name, order.orderProducts[0].quantity, order.totalAmount, order.dateCreated);
            });
        })
        .catch(error => console.log('There is some error, please check server'));
    configure_slider();
}

function addOrder(pn,q,a,t)
{
    //Should be reset via api call

    var ProductNameP= document.createElement('p');
    var textnode = document.createTextNode("Product Name:"+Array(43).fill('\xa0').join('')+pn);
    ProductNameP.appendChild(textnode);

    console.log(ProductNameP);
    var quantityP= document.createElement('p');
    var textnode2 = document.createTextNode("Quantity:"+ Array(55).fill('\xa0').join('') +q);
    quantityP.appendChild(textnode2);

    var TotalAmountP= document.createElement('p');
    var textnode3 = document.createTextNode("Total Amount:"+ Array(45).fill('\xa0').join('') +a);
    TotalAmountP.appendChild(textnode3);

    var orderTimeP= document.createElement('p');
    var textnode4 = document.createTextNode("Ordered At:"+ Array(49).fill('\xa0').join('') +t);
    orderTimeP.appendChild(textnode4);

    var childdiv = document.createElement('div')
    childdiv.className = 'slide';
    childdiv.appendChild(ProductNameP);
    childdiv.appendChild(quantityP);
    childdiv.appendChild(TotalAmountP);
    childdiv.appendChild(orderTimeP);

    document.getElementById("OrderID").appendChild(childdiv);
}
