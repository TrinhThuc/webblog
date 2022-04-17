

(function clickToNavBar () {
    const followBtn = document.querySelector('.header__navbar-list');
    const listNavbar = document.querySelector('.header__navbar-followed-list');
    let flag = false;

    function openLists () {
        // if (!flag) {
        //     listNavbar.classList.add('openFlex');
        //     flag = true;
        // }
        // else {
        //     listNavbar.classList.remove('openFlex');
        //     flag = false;
        // }
        listNavbar.classList.toggle('openFlex');
    }

    followBtn.addEventListener('click', openLists);

    
}) ();