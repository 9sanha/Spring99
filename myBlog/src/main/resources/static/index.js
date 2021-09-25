
$(document).ready(function (){
    getPostingList();

});



function postingDisplay(){
    $(".hide").css("display","block")
}

//모든 포스트 조회
function getPostingList(){
    $.ajax({
        type: 'GET',
        url: '/api/post',
        success: function (response) {
            $('#card-box').empty();
            for (let i = 0; i < response.length; i++) {
                //console.log(response[i]);
                let itemDto = response[i];
                console.log(itemDto.createAt);
                let year = itemDto.createdAt.substring(0,4)
                let month = itemDto.createdAt.substring(5,7)
                let day = itemDto.createdAt.substring(8,10)
                //let time = itemDto.createdAt.substring(11,)
                let tempHtml = addHTML(itemDto);
                $('#card-box').append(tempHtml);
            }

        }
    })
}

//포스트 card html 리턴
function addHTML(itemDto){
    return `<!--게시글-->
                <div class="card-header">
                    ${itemDto.nickname}
                </div>
                <div class="card-body">
                    <blockquote class="blockquote mb-0">
                        <p>${itemDto.title}</p>
                        <footer class="blockquote-footer">이 글은<cite title="Source Title">`${year}`년 sas${month}월${day}일 </cite>에 작성되었습니다.</footer>
                    </blockquote>
                </div>
                <!-- Button trigger modal -->
<button onclick="detail(${itemDto.id});" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
  게시글 보기
</button>
            </div>`
}
//글 상세 모달


function detail(id) {
    $('#card-box').empty();
    $.ajax({
        type: 'GET',
        url: `/api/detail/${id}`,
        success: function (response) {
            console.log(response)

            let tempHtml = `<div class="card">
  <div class="card-header">
    ${response.nickname}
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
  </div>
</div>
`
            $('#card-box').append(tempHtml);
        }

    })
}



function saveMyPosting(){
    //html 만들어야 함
    let title = $('#title').val();
    let contents = $('#contents').val();
    let nickname = $('#nickname').val();
    console.log(nickname)
    $.ajax({
        type: "POST",
        url: '/api/post',
        contentType: "application/json",
        data: JSON.stringify({title:title,contents:contents,nickname:nickname}),
        success: function (response) {
            alert('가능')
            // 2. 응답 함수에서 modal을 뜨게 하고, targetId 를 reponse.id 로 설정 (숙제로 myprice 설정하기 위함)
            $('#container').addClass('active');
            targetId = response.id;
            $(".hide").css("display","none")
            window.location.reload()
        }
    })

}