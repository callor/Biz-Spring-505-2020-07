$(function () {
  /*
    js, jq에서 사용하는 event 핸들러
    event 핸들러 : 사용자가(user) 화면(interface)에 보이는 어떤 대상을
    클릭했을때(또는 어떤 행위를 했을때)까지 기다리고 있다가
    사용자가 클릭을 하면 function()으로 지정된 코드를 실행하도록 만든 코드블럭
    js의 콜백(Call Back)함수 : function() 코드블럭
    */
  $("#p_code_gen").click(function () {
    alert("key");
    $.ajax({
      type: "GET",
      url: `${rootPath}/api/product/keyinput`,
      success: function (result) {
        alert(result);
      },
    });
  });

  // $("#p_code_gen").on("click", function () {
  //  console.log("on 핸들러");
  // });

  function test() {
    alert("안녕하세요");
    alert("우리나라");
  }
});
