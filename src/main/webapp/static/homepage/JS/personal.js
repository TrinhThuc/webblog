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
const deleteBtn = document.querySelector('.table_icon .delete');

// API

// const urlPost = "http://localhost:3000/posts";

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
        if (!e.target.closest(".active") && !e.target.closest(".fa-bell")) {
            modalNotification.classList.remove("active");
        }
        console.log(e.target.closest(".active"))
        if (!e.target.closest(".active") && !e.target.closest("img")) {
            modalUserNavbar.classList.remove("active");
        }
    }

    const toggleUserNavbar = () => {
        modalUserNavbar.classList.toggle("active");
    }

    const toggleNotification = () => {
        modalNotification.classList.toggle("active");
    }

    // const getPost = (callback) => {
    //     fetch(urlPost)
    //         .then((response) => response.json())
    //         .then(callback)
    // }



    // const renderPost = (posts) => {
    //     const listPost = document.querySelector(".content__table");
    //     var htmls = posts.map((post) => {
    //         return `
    //         <li id="content__table-item-${post.id}" class="content__table-item">
    //                     <a href="" class="table__img-link">
    //                         <img src="${post.img}" alt="">
    //                     </a>
    //                     <div class="table__content">
    //                         <h4 class="table__tag">${post.category}
    //                             <div class="table_icon">
    //                                 <a href="" id="modify"><i class="fa fa-pen"></i></a>
    //                                 <i onclick="deletePost(${post.id})" class="fa fa-trash-can"></i>
    //                             </div>
    //                         </h4>
    //                         <h2 class="table__title">
    //                             <a href="">
    //                             ${post.title}
    //                             </a>
    //                         </h2>
    //                         <div class="table__sub-title">
    //                         ${post.description}
    //                         </div>
    //                         <div class="table__time">${post.time}</div>
    //                     </div>
    //                 </li>
    //         `
    //     });

    //     listPost.innerHTML = htmls.join('');
    // }

    if (userAvatar && notificationIcon) {
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
    // getPost(renderPost);
}

// const deletePost = (id) => {
//     options = {
//         method: 'DELETE',
//         headers: {
//             'Content-Type': 'application/json'
//             // 'Content-Type': 'application/x-www-form-urlencoded',
//         },
//         // body: JSON.stringify(id)
//     }
//     fetch(urlPost + "/" + id, options)
//         .then((response) => {
//             return response.json();
//         })
//         .then(function () {
//             var postItem = document.querySelector("#content__table-item-" + id);

//             postItem.remove();
//         })
// }


app();