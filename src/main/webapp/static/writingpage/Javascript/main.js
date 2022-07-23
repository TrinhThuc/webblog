const dropDownList = document.querySelectorAll(".modal__drop-down-list");
const selectedAll = document.querySelectorAll(".modal__selected-choice");
const returnBtn = document.querySelector(".modal__return-btn");
const confirmSubmitBtn = document.querySelector(".modal__submit-btn");
const submitBtn = document.querySelector(".writing-blog__submit-btn");
const modal = document.querySelector(".modal");
var result = document.querySelector('modal__selected-choice');

const application = () => {
    let currentLyVisible = false;

    dropDownList.forEach((modalSelect) => {
        //optionBox and selectBox is the same;
        
        modalSelect.addEventListener("click", (e) => {
            if (!e.target.classList.contains("modal__selected-choice")) {
                return;
            }

            const selectBox = modalSelect.firstElementChild;
            const optionBox = modalSelect.firstElementChild.nextElementSibling.firstElementChild;

            if( optionBox.classList.contains("active") )
            {
                optionBox.classList.remove('active');
            } else {
                let currentlyActive = document.querySelector(".select-box__options.active")

                if ( currentlyActive ) {
                    currentlyActive.classList.remove('active');
                }

                modalSelect.querySelector(".select-box__options").classList.toggle("active");
            }   
            
            modalSelect.querySelectorAll(".select-box__option")
            .forEach((option) => {
                option.addEventListener("click", () => {
                    selectBox.innerHTML = option.querySelector("label").innerHTML;
                    result = option.getAttribute("id")
                    optionBox.classList.remove('active');

                });
            });
        });

        const makeModalHidden = () => {
                modal.classList.remove('visible');
        }

        const makeModalVisible = () => {
            modal.classList.add('visible');
        }

        returnBtn.addEventListener('click', makeModalHidden);
        submitBtn.addEventListener('click', makeModalVisible);
        // confirmSubmitBtn.addEventListener('click', submitForm);
    });
        
    BalloonEditor.create(document.querySelector(".editor"), {
        placeholder: "Nội dung bài viết",
    })
        .then((editor) => {
            console.log(editor);
        })
        .catch((error) => {
            console.error(error);
        });

    var createPostBtn = document.querySelector('#createPostBtn')
    function createPost(data, callback){
        var options = {
            method : 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body : JSON.stringify(data)
        };
        fetch("http://localhost:8080/api/post", options)
            .then(function (response){
                console.log(response)
                window.location.href = "http://localhost:8080/bai-dang/viet-bai"
                return response.json()
            })
            .then(callback)
    }
    createPostBtn.onclick = function (){
        var title = document.querySelector('div[name="title"]').textContent
        var content = document.querySelector('div[name="content"]').innerHTML
        var shortDescription = document.querySelector('textarea[name="shortDescription"]').value
        var categoryId = result
        var post = {
            title : title,
            content : content,
            shortDescription : shortDescription,
            categoryId : result
        }
        createPost(post)
    }
};

application();
