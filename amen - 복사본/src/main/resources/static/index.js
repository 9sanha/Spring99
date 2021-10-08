$(document).ready(function () {
    getPostingList();

});

function postingDisplay() {
    $(".hide").css("display", "block")

}


function getPostingList() {
    $.ajax({
        type: 'GET',
        url: '/post',
        success: function (response) {
            console.log(response)
            $('#card-box').empty();

            for (let i = 0; i < response.length; i++) {
                //console.log(response[i]);
                let itemDto = response[i];
                let year = itemDto.createdAt.substring(0, 4)
                let month = itemDto.createdAt.substring(5, 7)
                let day = itemDto.createdAt.substring(8, 10)
                // let time = itemDto.createdAt.substring(11,)
                let tempHtml = addHTML(itemDto,year,month,day);

                $('#card-box').append(tempHtml)
            }

        }
    })
}


function addHTML(itemDto,year,month,day) {
    return `<div class="card-header">

                </div>
                <div class="card-body">
                    <blockquote class="blockquote mb-0">
                        <p>${itemDto.title}</p>
                        <footer class="blockquote-footer">이 글은<cite title="Source Title">${year}년${month}월${day}일 </cite>에 작성되었습니다.</footer>
                    </blockquote>


<button onclick="detail(${itemDto.id});" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
  게시글 보기
</button>
            </div>`

}


function detail(id) {
    $('#card-box').empty();
    $.ajax({
        type: 'GET',
        url: `/post/detail/${id}`,
        success: function (response) {
            console.log(response)
            let tempHtml = `<div class="card">
  <div class="card-header">
    
  </div>
  <div class="card-body">
    <blockquote class="blockquote mb-0">
      <p>${response.title}</p>
      <p>${response.contents}</p>
      <footer class="blockquote-footer">이 글은<cite title="Source Title">${response.createdAt}</cite>에 작성되었습니다.</footer>
    </blockquote>
    <button onclick="window.location.reload()" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
  뒤로 가기
</button>
<div class="form-floating">
  <input type="text" class="form-control" id="rpl" placeholder="reply">
  <label for="floatingPassword">reply</label>
  <button onclick="saveRpl(${id})" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">댓글 작성</button>
</div>
  </div>
</div>
<ul id="reply-list-box" class="list-group list-group-flush">
  <li class="list-group-item">여기 밑으로 댓글 리스트 달려야 함</li>
</ul>`
            $('#card-box').append(tempHtml);



            // 이 리스트 최신순으로 정렬해야 함
            let list=response["reply"]
            console.log(typeof(list))


            console.log(list)
            for (let j=0;j<list.length;j++){
                let temp_detailHtml = `<li class="list-group-item">${list[j]["contents"]}</li>`
                $('#reply-list-box').append(temp_detailHtml);
            }
        }
    })

}

function addReply(){
    // let list=response.reply
    // for (let j=0;j<list.length;j++){
    //     let temp_detailHtml = `<li class="list-group-item">${list[j].contents}</li>`
    //     $('reply-list-box').append(temp_detailHtml);
    // }

}

function saveRpl(postId){

    let rpl = $('#rpl').val();
    $.ajax({
        type: "POST",
        url: `post/detail/reply/${postId}`,
        contentType: "application/json",
        data: JSON.stringify({contents: rpl}),
        success: function (response) {
            detail(postId)
        // 여기부터작성하면됨

        }
    })
}

function saveMyPosting() {
    //html 만들어야 함
    let title = $('#title').val();
    let contents = $('#contents').val();
    console.log(title)
    // let nickname = $('#nickname').val();
    // console.log(nickname)
    $.ajax({
        type: "POST",
        url: 'post',
        contentType: "application/json",
        data: JSON.stringify({title: title, contents: contents}),
        success: function (response) {
            alert('가능')
            // 2. 응답 함수에서 modal을 뜨게 하고, targetId 를 reponse.id 로 설정 (숙제로 myprice 설정하기 위함)
            $('#container').addClass('active');
            targetId = response.id;
            $(".hide").css("display", "none")
            window.location.reload()
        }
    })

}


function saveMyPosting1() {
    let title = $('#title').val

    let contents = $('#contents').val

    $.ajax({
        type: "POST",
        url: '/post',
        contentType: "application/json",
        data: JSON.stringify({"title": title, "contents": contents}),
        success: function (response) {


        }
    })


}