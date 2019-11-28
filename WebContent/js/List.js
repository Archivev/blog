$(document).ready(function () {
    $('.nav>ul li').click(function () {
        $(this).addClass("listStyle");
    })
    $('.content').animate({top:'0px',opacity:'1'},"slow")
})