const numberOfPaginations = document.querySelectorAll('.container__pagination-item');
const paginationList = document.querySelector('.container__pagination-lists');
const firstBtn = document.querySelector('.firstBtn');
const prevBtn = document.querySelector('.prevBtn');
const nextBtn = document.querySelector('.nextBtn');
const lastBtn = document.querySelector('.lastBtn');
const paginationBar = document.querySelector('.container__pagination');
const pagination = document.querySelector('.container__pagination__total-page');
// const page = 2;
var url ;
const exercutePaginationRender = {
    currentIndex: 0,
    currentPage: 0,

    
    getTotalPage: function () {
        if (pagination) {
            const pages = pagination.getAttribute('totalPage');
            let pagesArray = [];
            for (let i = 1; i <= pages; ++i) {
                pagesArray.push(i);
            }
            return pagesArray;
        }
    },

    getCurrentPage: function() {
        const currentPage = pagination.getAttribute('currentPage');
        return parseInt(currentPage);
        // return this.currentIndex = currentPages;
     },

    render: function () {
        this.currentIndex = this.getCurrentPage();
        if (this.getTotalPage()) {
            const htmls = this.getTotalPage().map((pagination, index) => {
                return `
                <li class="container__pagination-item ${index === this.currentIndex ? 'active' : ''}" data-index="${index}">${pagination}</li>
            `;
            })

            paginationList.innerHTML = htmls.join('');
        }

    },

    renderPagi: function () {
        if (paginationBar) {
            paginationBar.classList.add('appear-flex');
        }
    },

    setUrl : function (data){
        url = data
    },

    getUrl : function (){
        return url;
    },



    handleEvents: function () {
        nextBtn.onclick = function () {
            exercutePaginationRender.nextPage();
        }

        //Xu ly khi bam vao nut prev page
        prevBtn.onclick = function () {
            exercutePaginationRender.prevPage();
        }

        //Xu ly khi bam vao nut last page
        lastBtn.onclick = function () {
            exercutePaginationRender.lastPage();
        }


        //Xu ly khi bam vao nut first page
        firstBtn.onclick = function () {
            exercutePaginationRender.firstPage();
        }

        //Lang nghe hanh vi click vao pagination list
        paginationList.onclick = function (e) {

            if (!e.target.classList.contains('container__pagination-item')) return;

            const paginationNode = e.target.closest('.container__pagination-item:not(.active)');
            exercutePaginationRender.currentPage = parseInt(e.target.dataset.index);
            exercutePaginationRender.currentIndex = Number(paginationNode.dataset.index);
            exercutePaginationRender.render();

            if (paginationNode) {
                exercutePaginationRender.currentIndex = Number(paginationNode.dataset.index);
                exercutePaginationRender.render();
            }
            var indexNextPage = Number(paginationNode.dataset.index) + 1
            console.log(exercutePaginationRender.getUrl())
            window.location.href = exercutePaginationRender.getUrl() + indexNextPage +"&limit=5"
        }
    },
    nextPage: function () {
        this.currentIndex++;
        this.currentPage++;
        if (this.currentIndex >= this.getTotalPage().length) {
            this.currentPage = 0;
            this.currentIndex = 0;
        }

        this.render();
    },

    prevPage: function () {
        this.currentIndex--;
        this.currentPage--;

        if (this.currentIndex < 0) {
            this.currentPage = this.getTotalPage().length - 1;
            this.currentIndex = this.getTotalPage().length - 1;
        }

        this.render();
    },

    lastPage: function () {
        this.currentIndex = this.getTotalPage().length - 1;
        this.currentPage = this.getTotalPage().length - 1;
        this.render();
    },

    firstPage: function () {
        this.currentIndex = 0;
        this.currentPage = 0;
        this.render();
    },

    start: function() {
        this.currentIndex = this.getCurrentPage();
        this.currentPage = this.getCurrentPage();
        this.render();
        this.renderPagi();
        
        //xu ly cac thao tac bang tay
        this.handleEvents();
    }
}

exercutePaginationRender.start();