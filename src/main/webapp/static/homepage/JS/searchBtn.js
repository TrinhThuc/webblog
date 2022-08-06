var searchBtn = document.querySelector('.header__search-bar .fas.fa-search')

searchBtn.onclick = function () {
    var tab = document.querySelector('.header__search-bar > input').value
    window.location.href = "/danh-sach/tim-kiem?tab="+tab+"&page=1&limit=5"
}