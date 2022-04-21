$(document).ready(function(){
    $('.slider').slick({
        arrows:false,
        dots:true,
        appendDots:'.slider-dots',
        dotsClass:'dots'
    });
})

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

