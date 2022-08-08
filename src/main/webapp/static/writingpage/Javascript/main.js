const dropDownList = document.querySelectorAll(".modal__drop-down-list");
const returnBtn = document.querySelector(".modal__return-btn");
const modalWritingPage = document.querySelector(".modal__writing-page-submition-form");
const notificationIcon = document.querySelector(".header__icon__notificartion");
const userAvatar = document.querySelector(".header__icon__user-navbar");
const modalUserNavbar = document.querySelector(".header__modal__user-navbar");
const modalNotification = document.querySelector(".header__modal__notification");

const application = {
    handleEvent: function () {
        document.addEventListener("click", application.closeWhenClickOutside);
        userAvatar.addEventListener("click", application.toggleUserNavbar);
        notificationIcon.addEventListener("click", application.toggleNotification);

        // Xu ly hanh vi click vao dropDown list
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
                                if (option.classList.contains("selected")) {
                                    option.classList.remove("selected");
                                }
                            })
                            option.classList.toggle("selected");
                            selectBox.innerHTML = option.querySelector("label").innerHTML;
                            optionBox.classList.remove('active');
                        });
                    });
            });

            function makeModalHidden() {
                modalWritingPage.classList.remove('visible');
            }

            function makeModalVisible() {
                modalWritingPage.classList.add('visible');
            }

            returnBtn.addEventListener('click', makeModalHidden);
            submitBtn.addEventListener('click', makeModalVisible);
        });

        // Cai gi y chiu :v
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
    },

    closeWhenClickOutside: function (e) {
        if (!e.target.closest(".active") && !e.target.closest(".fa-bell")) {
            modalNotification.classList.remove("active");
        }
        if (!e.target.closest(".active") && !e.target.closest("img")) {
            modalUserNavbar.classList.remove("active");
        }
    },

    toggleUserNavbar: function () {
        modalUserNavbar.classList.toggle("active");
    },

    toggleNotification: function () {
        modalNotification.classList.toggle("active");
    },

    start: function() {
        application.handleEvent();
    }
};

application.start();
