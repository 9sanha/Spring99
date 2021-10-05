console.log("여기까지 오긴 하니")

function onclickAdmin() {
    console.log("여기까지 오긴 하니")
// Get the checkbox
    let checkBox = document.getElementById("admin-check");
// Get the output text
    let box = document.getElementById("admin-token");

// If the checkbox is checked, display the output text
    if (checkBox.checked === true){
        box.style.display = "block";
    } else {
        box.style.display = "none";
    }
}


function check(){
    console.log("여기까지 오긴 하니1")// 안옴
    let id = document.getElementsByName('username').val();
    let password = document.getElementsByName('password').val();
    let passwordCheck = document.getElementsByName('passwordCheck').val();


    if (checkId(id)&&checkPw(password,passwordCheck)){
        document.signup.action="/user/signup"
        document.signup.submit()
        alert("회원가입이 완료되었습니다.")
    }
}

function checkId(id) { // 검사 통과하면 true값 반환
    console.log("여기까지 오긴 하니")
    let idReg = /^[a-zA-Z0-9]{3,}$/;
    let isNewId=true;
    if (idReg.test(id).val()) {
        alert("아이디의 형식이 맞지 않습니다 (3자 이상, 알파벳 대,소문자, 숫자 가능)")
        isNewId=false;
    } else if (id.length < 3) {
        alert("아이디 최소 길이는 3 입니다.")
        isNewId=false;
    }
    $.ajax({
        type: "GET",
        url: '/user/checkId/{username}',
        success: function (response) {
            if (!response) {// 아이디가 중복될 때
                alert("중복된 아이디입니다.")
                isNewId=false;
            }
        }
    })
    if (!isNewId){
        document.signup.password.focus();
    }
    return isNewId;
}

function checkPw(password,passwordCheck){
    console.log("여기까지 오긴 하니")
    let isPwOk = true;
    let pwReg = /^[a-zA-Z0-9]{4,}$/;
    if (passwordCheck !== password){
        alert("비밀번호가 일치하지 않습니다.")
        document.signup.passwordCheck.focus();
        isPwOk=false;
    }else if (pwReg.test(password).val()) {
        alert("비밀번호의 형식이 맞지 않습니다 (4자 이상, 알파벳 대,소문자, 숫자 가능)")
        document.signup.password.focus();
        isPwOk=false;
    }else if (!password.includes(id)){
        alert("비밀번호는 아이디를 포함할 수 없습니다")
        document.signup.password.focus();
        isPwOk=false;
    }
    return isPwOk;
}
