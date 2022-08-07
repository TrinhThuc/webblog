const dropDownList = document.querySelectorAll(".modal__drop-down-list");
const returnBtn = document.querySelector(".modal__return-btn");
const confirmSubmitBtn = document.querySelector(".modal__submit-btn");
const submitBtn = document.querySelector(".writing-blog__submit-btn");
const modalWritingPage = document.querySelector(".modal__writing-page-submition-form");
const notificationIcon = document.querySelector(".header__icon__notificartion");
const userAvatar = document.querySelector(".header__icon__user-navbar");
const modalUserNavbar = document.querySelector(".header__modal__user-navbar");
const modalNotification = document.querySelector(".header__modal__notification");

var result = document.querySelector('blog-post-genre modal__selected-choice');

const application = () => {
    dropDownList.forEach((modalSelect) => {
        modalSelect.addEventListener("click", (e) => {
            if (!e.target.classList.contains("modal__selected-choice")) {
                return;
            }

            const selectBox = modalSelect.firstElementChild;
            const optionBox = modalSelect.firstElementChild.nextElementSibling.firstElementChild;

            if (optionBox.classList.contains("active")) {
                optionBox.classList.remove('active');
            } else {
                let currentlyActive = document.querySelector(".select-box__options.active")

                if (currentlyActive) {
                    currentlyActive.classList.remove('active');
                }
                modalSelect.querySelector(".select-box__options").classList.toggle("active");
            }

            modalSelect.querySelectorAll(".select-box__option")
                .forEach((option) => {
                    option.addEventListener("click", () => {
                        modalSelect.querySelectorAll(".select-box__option").forEach((option) => {
                            if(option.classList.contains("selected")) {
                                option.classList.remove("selected");
                            }
                        })
                        option.classList.toggle("selected");
                        selectBox.innerHTML = option.querySelector("label").innerHTML;
                        optionBox.classList.remove('active');
                    });
                });
        });

        const makeModalHidden = () => {
            modalWritingPage.classList.remove('visible');
        }

        const makeModalVisible = () => {
            modalWritingPage.classList.add('visible');
        }

        returnBtn.addEventListener('click', makeModalHidden);
        submitBtn.addEventListener('click', makeModalVisible);
    });

    const closeWhenClickOutside = (e) => {
        if(!e.target.closest(".active") && !e.target.closest(".fa-bell")) {
            modalNotification.classList.remove("active");
        }
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

    document.addEventListener("click", closeWhenClickOutside)
    userAvatar.addEventListener("click", toggleUserNavbar)
    notificationIcon.addEventListener("click", toggleNotification)

    BalloonEditor.create(document.querySelector(".editor"), {
        placeholder: "Nội dung bài viết",
               // filebrowserBrowseUrl : '/ckfinder/ckfinder.html',
        // filebrowserImageBrowseUrl : '/ckfinder/ckfinder.html?type=Images',
        // filebrowserFlashBrowseUrl : '/ckfinder/ckfinder.html?type=Flash',
        // filebrowserUploadUrl : '/ckfinder/core/connector/java/connector.java?command=QuickUpload&amp;type=Files',
        // filebrowserImageUploadUrl : '/ckfinder/core/connector/java/connector.java?command=QuickUpload&amp;type=Images',
        // filebrowserFlashUploadUrl : '/ckfinder/core/connector/java/connector.java?command=QuickUpload&amp;type=Flash'
    })
        .catch((error) => {
            console.error(error);
        });

    };
    function submitForm() {
        console.log(result);
    };

    confirmSubmitBtn.addEventListener('click', submitForm);

application();

// var createPostBtn = document.querySelector('#createPostBtn')
//     function createPost(data, callback){
//         var options = {
//             method : 'POST',
//             headers: {
//                 'Content-Type': 'application/json'
//             },
//             body : JSON.stringify(data)
//         };
//         fetch("http://localhost:8080/api/post", options)
//             .then(function (response){
//                 console.log(response)
//                 // window.location.href = "http://localhost:8080/bai-dang/viet-bai"
//                 return response.json()
//             })
//             .then(callback)
//     }
//     createPostBtn.onclick = function (){
//         var title = document.querySelector('div[name="title"]').textContent
//         var content = document.querySelector('div[name="content"]').innerHTML
//         var shortDescription = document.querySelector('textarea[name="shortDescription"]').value
//         var categoryId = result
//         var post = {
//             title : title,
//             content : content,
//             shortDescription : shortDescription,
//             categoryId : result
//         }
//         createPost(post)
//         console.log(post)
//     }
