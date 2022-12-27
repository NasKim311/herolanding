// 이메일js API keys 가져오기
const emailjsKey = '15A73AiaHjnvp1DBd';
// 서비스키
const serviceKey = 'service_6kovirc';
// 템플릿키
const templateKey = 'template_n4u76fq';

(function () {
    // 본인의 API keys를 넣어주세요.
    emailjs.init(emailjsKey);
})();


// 비회원용 문의하기 벨리데이션 체크
function check1() {
    var emailname = document.getElementById("emailname").value;
    var emailphone = document.getElementById("emailphone").value;
    var emailemail = document.getElementById("emailemail").value;

    var emailtitle = document.getElementById("emailtitle").value;
    var emailmessage = document.getElementById("emailmessage").value;

    if (emailname == "") {
        alert("이름을 입력해주세요.");
        return false;
        // 한글만
    } else if (!emailname.match(/^[가-힣]*$/)) {
        alert("이름은 한글만 입력해주세요.");
        return false;
    } else if (emailphone == "") {
        alert("연락처를 입력해주세요.");
        return false;
        // 숫자만
    } else if (!emailphone.match(/^[0-9]*$/)) {
        alert("연락처는 숫자만 입력해주세요.");
        return false;
    } else if (emailemail == "") {
        alert("이메일을 입력해주세요.");
        return false;
        // 이메일 정규 표현식
    } else if (!emailemail.match(/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i)) {
        alert("이메일 형식이 올바르지 않습니다.");
        return false;
    } else if (emailtitle == "") {
        alert("제목을 입력해주세요.");
        return false;
    } else if (emailmessage == "") {
        alert("내용을 입력해주세요.");
        return false;
    } else {
        sendMail1();
        return true;
    }
}

// 회원용 문의하기 벨리데이션 체크
function check2() {
    var emailtitle = document.getElementById("emailtitle").value;
    var emailmessage = document.getElementById("emailmessage").value;

    if (emailtitle == "") {
        alert("제목을 입력해주세요.");
        return false;
    } else if (emailmessage == "") {
        alert("내용을 입력해주세요.");
        return false;
    } else {
        sendMail2();
        return true;
    }
}

// 비회원 문의 메일 전송
function sendMail1() {
    var emailname = document.getElementById("emailname").value;
    var emailphone = document.getElementById("emailphone").value;
    var emailemail = document.getElementById("emailemail").value;
    var emailtitle = document.getElementById("emailtitle").value;
    var emailmessage = document.getElementById("emailmessage").value;
    // 본인의 서비스 키와 템플릿 키를 입력해주세요.
    emailjs.send(serviceKey, templateKey, {
        name: emailname,
        phone: emailphone,
        email: emailemail,
        title: emailtitle,
        message: emailmessage
    }).then(function (response) {
        console.log("SUCCESS. status=%d, text=%s", response.status, response.text);
        alert("문의가 접수되었습니다.");
        // 문의 접수 후 새로고침
        location.reload();
    }, function (err) {
        alert("문의 접수에 실패했습니다.");
        console.log("FAILED. error=", err);
    });
}

// 회원용 문의 메일 전송
function sendMail2() {
    var emailname = document.getElementById("emailname").textContent;
    var emailphone = document.getElementById("emailphone").textContent;
    var emailemail = document.getElementById("emailemail").textContent;

    var emailtitle = document.getElementById("emailtitle").value;
    var emailmessage = document.getElementById("emailmessage").value;
    // 본인의 서비스 키와 템플릿 키를 입력해주세요.
    emailjs.send(serviceKey, templateKey, {
        name: emailname,
        phone: emailphone,
        email: emailemail,
        title: emailtitle,
        message: emailmessage
    }).then(function (response) {
        console.log("SUCCESS. status=%d, text=%s", response.status, response.text);
        alert("문의가 접수되었습니다.");
        // 문의 접수 후 새로고침
        location.reload();
    }, function (err) {
        alert("문의 접수에 실패했습니다.");
        console.log("FAILED. error=", err);
    });
}

// 문의하기모달창초기화()
function 문의하기모달창초기화() {
    location.reload();
};