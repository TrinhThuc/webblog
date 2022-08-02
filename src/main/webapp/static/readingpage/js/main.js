const arrowToTop = document.querySelector(".goToHeader");

const goToTop = function () {
    window.scrollTo({
        top: 0, 
        left: 0, 
        behavior: "smooth",
    })
};

$(document).ready(function () {
    $('.slider').slick({
        infinite: true,
        slidesToShow: 3,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 8000,
        prevArrow: $('.prev-btn'),
        nextArrow: $('.next-btn'),
    });
});

document.querySelector('.content-readpage').addEventListener("wheel", myFunction);
arrowToTop.addEventListener("click", goToTop);
