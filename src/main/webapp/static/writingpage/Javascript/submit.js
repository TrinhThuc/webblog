const submitBtn = document.querySelector(".writing-blog__submit-btn");
const confirmSubmitBtn = document.querySelector(".modal__submit-btn");


const submit = {
    handleEvent: function() {
        confirmSubmitBtn.addEventListener('click', submit.submitForm);

    },

    submitForm: function () {
        var result = document.querySelector('.select-box__option.selected');
        console.log(result);
        return result.getAttribute('id');
    },

    start: function() {
        submit.handleEvent();
    }
}

submit.start();