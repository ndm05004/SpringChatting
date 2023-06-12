//1. 페이지 로딩 완료시 채팅창을 맨 아래로 내리기.
// 즉시 실행함수. IIFE
(function () {
  const displayChatting =
    document.getElementsByClassName("display-chatting")[0];

  if (displayChatting != null) {
    displayChatting.scrollTop = displayChatting.scrollHeight;
  }
})();

document.getElementById("send").addEventListener("click", sendMessage);

//채팅보내는함수
function sendMessage() {
  //채팅이 입력되는 textarea요소 가져오기.
  const inputChatting = document.getElementById("inputChatting");

  //클라이언트가 채팅내용을 입력하지 않은상태로 보내기 버튼을 누른경우
  if (inputChatting.value.trim().length == 0) {
    alert("채팅내용을 입력해주세요..");

    inputChatting.value = ""; // 공백문자 제거해주기.
    inputChatting.focus();
  } else {
    // 입력이 된경우
    //메세지 입력시 필요한 데이터를 js객체로 생성.
    const chatMessage = {
      userNo: chatCNo,
      memId: memId,
      chatRoomNo: chatRNo,
      chatContent: inputChatting.value,
    };

    // JSON.parse(문자열) : JSON -> JS object로 변환
    // JSON.stringify(객체) : JS Ojbect -> JSON

    console.log(chatMessage);
    console.log(JSON.stringify(chatMessage));

    // chatSocket(웹소켓객체)를 이용하여 메세지 보내기
    // chatSocket.send(값) : 웹소켓 핸들러로 값을 보냄.

    chatSocket.send(JSON.stringify(chatMessage));

    inputChatting.value = "";
  }
}

// 웹소켓에서  sendMessage라는 함수가 실행되었을때 -> 메세지가 전달되었을때

chatSocket.onmessage = function (e) {
  //debugger;
  // 매개변수 e : 발생한 이벤트에 대한 정보를 담고있는 객체
  // e.data  : 전달된 메세지 -> message.getPayload() -> (JSON형태) 로

  // 전달받은 메세지를 JS객체로 변환
  const chatMessage = JSON.parse(e.data); // js객체로 변환.

  /*
    	<li>
    		[<b></b>]
    		<p>메세지</p>
    		<span>메세지 보낸 날짜</span>
    	</li>
    */
  const li = document.createElement("li");
  const p = document.createElement("p");

  p.classList.add("chat");

  p.innerHTML = chatMessage.message.replace(/\\n/gm, "<br>"); //줄바꿈 처리

  //span태그 추가
  const span = document.createElement("span");
  span.classList.add("chatDate");

  span.innerText = getCurrentTime();

  // 내가 쓴 채팅
  // 남이 쓴 채팅

  //내가쓴 채팅
  if (chatMessage.userNo == userNo) {
    li.append(span, p);
    li.classList.add("myChat"); //본인글일시
  } else {
    li.innerHTML = "<b>" + chatMessage.userName + "</b><br>";
    li.append(p, span);
  }

  // 채팅창
  const displayChatting =
    document.getElementsByClassName("display-chatting")[0];

  // 채팅창에 채팅 추가
  displayChatting.append(li);

  // 채팅창을 제일밑으로 내리기
  displayChatting.scrollTop = displayChatting.scrollHeight;
  // scrollTop : 스크롤 이동
  // scrollHeight : 스크롤이되는 요소의 전체 높이.
};

function getCurrentTime() {
  const now = new Date();

  const time =
    now.getFullYear() +
    "년 " +
    addZero(now.getMonth() + 1) +
    "월 " +
    addZero(now.getDate()) +
    "일 " +
    addZero(now.getHours()) +
    ":" +
    addZero(now.getMinutes()) +
    ":" +
    addZero(now.getSeconds()) +
    " ";

  return time;
}

// 10보다 작은수가 매개변수로 들어오는경우 앞에 0을 붙여서 반환해주는함수.
function addZero(number) {
  return number < 10 ? "0" + number : number;
}
