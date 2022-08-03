const notificationIcon = document.querySelector(".header__icon__notificartion");
const userAvatar = document.querySelector(".header__icon__user-navbar");
const modalUserNavbar = document.querySelector(".header__modal__user-navbar");
const modalNotification = document.querySelector(".header__modal__notification");

// ==== Personal Page Variable ====
const chooseContents = document.querySelectorAll('.content__header-choose');
const modalBtn = document.querySelector('.modal__btn');
const modalChangePswBtn = document.querySelector('.modal__change-psw');
const modalCancelBtn = document.querySelector('.modal__cancel-btn');
const modalEdit = document.querySelector('.modal__edit');
const containerContent = document.querySelector('.container__content');
const userEdit = document.querySelector('.user__edit');
const userTable = document.querySelector('.container__user');

const app = function () {
    let isOpen = false;

    const styleChangePsw = function () {
        if (isOpen) {
            modalChangePswBtn.style.opacity = 0;
            modalChangePswBtn.style.transform = `translateY(-15px)`;
            isOpen = !isOpen;
            setTimeout(() => {
                modalChangePswBtn.style.display = 'none';
            }, 301);
            return;
        }
        modalChangePswBtn.style.display = 'block';
        modalChangePswBtn.style.opacity = 0;
        setTimeout(() => {
            modalChangePswBtn.style.opacity = 1;
            modalChangePswBtn.style.transform = `translateY(0)`;
            isOpen = !isOpen;
        }, 0.1);

        // modalChangePswBtn.style.display = 'block';
    };

    const hideModalEdit = function () {
        modalEdit.classList.remove('appear-block');
        containerContent.classList.add('appear-block');
    };

    const hideContainerContent = function () {
        modalEdit.classList.add('appear-block');
        containerContent.classList.remove('appear-block');
    };

    const closeWhenClickOutside = (e) => {
        if(!e.target.closest(".active") && !e.target.closest(".fa-bell")) {
            modalNotification.classList.remove("active");
        }
        console.log(e.target.closest(".active"))
        if(!e.target.closest(".active") && !e.target.closest("img")) {
            modalUserNavbar.classList.remove("active");
        }   
    }

    const toggleUserNavbar = () => {
        modalUserNavbar.classList.toggle("active");
    }
    
    const toggleNotification = () => {
        modalNotification.classList.toggle("active");
    }

    if(userAvatar && notificationIcon) {
        document.addEventListener("click", closeWhenClickOutside)
        userAvatar.addEventListener("click", toggleUserNavbar)
        notificationIcon.addEventListener("click", toggleNotification)
    }

    chooseContents.forEach(chooseContent => {
        chooseContent.onclick = function () {
            document.querySelector('.content__header span.active').classList.remove('active');
            chooseContent.classList.add('active');
        }
    });
    
    modalBtn.addEventListener('click', styleChangePsw);
    modalCancelBtn.addEventListener('click', hideModalEdit);
    userEdit.addEventListener('click', hideContainerContent);
}


app();