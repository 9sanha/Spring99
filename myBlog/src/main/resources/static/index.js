
$(document).ready(function (){
    getPostingList();

});



function postingDisplay(){
    $('#card-box').empty();
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

                //let time = itemDto.createdAt.substring(11,)
                let tempHtml = addHTML(itemDto);
                $('#card-box').append(tempHtml);
            }

        }
    })
}

//포스트 card html 리턴
function addHTML(itemDto){
    let year = itemDto.createdAt.substring(0,4)
    let month = itemDto.createdAt.substring(5,7)
    let day = itemDto.createdAt.substring(8,10)
    let time = itemDto.createdAt.substring(11,13)
    let minute = itemDto.createdAt.substring(14,16)
    let second = itemDto.createdAt.substring(17,21)

    return `<!--게시글-->
                <div class="card-header">
                    ${itemDto.nickname}
                </div>
                <div class="card-body">
                    <blockquote class="blockquote mb-0">
                        <p>${itemDto.title}</p>
                        <p>이 글은<cite title="Source Title">${year}년 ${month}월 ${day}일 ${time}시 ${minute}분 ${second}초</cite>에 작성되었습니다.</p>
                        
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
            let year = response.createdAt.substring(0,4)
            let month = response.createdAt.substring(5,7)
            let day = response.createdAt.substring(8,10)
            let time = response.createdAt.substring(11,13)
            let minute = response.createdAt.substring(14,16)
            let second = response.createdAt.substring(17,21)

            let tempHtml = `<div class="card">
  <div class="card-header">
    ${response.nickname}
  </div>
  <div class="card-body">
    <blockquote class="blockquote mb-0">
      <p>${response.title}</p>
      <p>${response.contents}</p>
      <p>이 글은<cite title="Source Title">${year}년 ${month}월 ${day}일 ${time}시 ${minute}분 ${second}초</cite>에 작성되었습니다.</p>
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
    if(title===''){
        return alert('제목을 입력해주세요!');
    }else if (contents===''){
        return alert('본문을 입력해주세요!');
    }else if (nickname===''){
        return alert('닉네임을 입력해주세요!');
    }
    console.log(nickname)
    $.ajax({
        type: "POST",
        url: '/api/post',
        contentType: "application/json",
        data: JSON.stringify({title:title,contents:contents,nickname:nickname}),
        success: function (response) {
            if(response){
                alert('게시물이 등록되었습니다.');
                $('#container').addClass('active');
                $(".hide").css("display","none")
            }else{
                alert(' ^  ㅗ  ^ ');
            }
            window.location.reload()
        }
    })

}