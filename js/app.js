$(document).ready(function(){
    //addOrder("Jeans", 1, 400, "12:35");
    //addOrder("Jeans", 1, 400, "12:35");

    $('.slider').slick({
        arrows:false,
        dots:true,
        appendDots:'.slider-dots',
        dotsClass:'dots'
    });
})

let wallet_Amt=1000;

function add(){
    var input = document.getElementById("Amount_inp").value;
    wallet_Amt=wallet_Amt+Number(input);
    document.getElementById("Wallet_amt").innerText=wallet_Amt;
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
    let customerQuantity = Number(document.getElementById("customerMonitorQuantity"));

    console.log(currentPrice);
    console.log(customerPrice);
    console.log(customerQuantity);

    if(customerPrice == 0)
    {
        document.getElementById("postMonitorPriceSubmit").style.color = "orange";
        document.getElementById("postMonitorPriceSubmit").innerHTML = "Please set a price, at which you want to buy!";
    }
    else if(currentPrice <= customerPrice){
        document.getElementById("postMonitorPriceSubmit").style.color = "red";
        document.getElementById("postMonitorPriceSubmit").innerHTML = "Please set a price lower than current price!";
    }
    else{
        document.getElementById("postMonitorPriceSubmit").style.color = "green";
        document.getElementById("postMonitorPriceSubmit").innerHTML = "We'll buy this product for you at or less than: ₹ " + customerPrice;
    }
}

function getCustomerTapedetails()
{
    let currentPrice = Number(document.getElementById("currentTapePrice").innerText);
    let customerPrice = Number(document.getElementById("customerTapePrice").value);
    let customerQuantity = Number(document.getElementById("customerMonitorQuantity"));

    console.log(currentPrice);
    console.log(customerPrice);
    console.log(customerQuantity);

    if(customerPrice == 0)
    {
        document.getElementById("postTapePriceSubmit").style.color = "orange";
        document.getElementById("postTapePriceSubmit").innerHTML = "Please set a price, at which you want to buy!";
    }
    else if(currentPrice <= customerPrice){
        document.getElementById("postTapePriceSubmit").style.color = "red";
        document.getElementById("postTapePriceSubmit").innerHTML = "Please set a price lower than current price!";
    }
    else{
        document.getElementById("postTapePriceSubmit").style.color = "green";
        document.getElementById("postTapePriceSubmit").innerHTML = "We'll buy this product for you at or less than: ₹ " + customerPrice;
    }
}


//addOrder("Jeans", 1, 400, "12:35"); can call the addorder() function with the required parameters to add the order details

function addOrder(pn,q,a,t)
{
    //Should be reset via api call

    var ProductNameP= document.createElement('p');
    var textnode = document.createTextNode("Product Name: "+ pn);
    ProductNameP.appendChild(textnode);

    console.log(ProductNameP);
    var quantityP= document.createElement('p')
    var textnode2 = document.createTextNode("Quantity: "+ q);
    quantityP.appendChild(textnode2);

    var TotalAmountP= document.createElement('p')
    var textnode3 = document.createTextNode("Total Amount: "+ a);
    TotalAmountP.appendChild(textnode3);

    var orderTimeP= document.createElement('p')
    var textnode4 = document.createTextNode("Ordered At: "+ t);
    orderTimeP.appendChild(textnode4);

    var childdiv = document.createElement('div')
    childdiv.className = 'slide';
    childdiv.appendChild(ProductNameP);
    childdiv.appendChild(quantityP);
    childdiv.appendChild(TotalAmountP);
    childdiv.appendChild(orderTimeP);

    document.getElementById("OrderID").appendChild(childdiv);

}
