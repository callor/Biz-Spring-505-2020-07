$(function () {
  // 화면에 scroll이 일어나면
  $(window).scroll(function () {
    let headerHeight = $("header").height(); // header tag의 높이값
    // 화면을 세로방향으로 스크롤할때 윈도우 화면의 최 상단 좌표 가져오기
    let windowTop = $(window).scrollTop();
    if (windowTop > headerHeight) {
      $("#main-nav").css("position", "fixed");
      $("#main-nav").css("top", "0");
    } else {
      $("#main-nav").css("position", "relative");
    }
  });

  // nav의 li tag를 클릭했을때
  $("#main-nav li").click(function () {
    let menu_text = $(this).text();
    if (menu_text === "도서정보") {
      document.location.href = `${rootPath}/books`;
    }
  });
});
