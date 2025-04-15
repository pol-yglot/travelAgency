$(document).ready(function() {
    /**
     * 페이지 init
     * */
    $(window).scroll(function() {
        scrollFunction();
    });

    // 다크모드 확인
    if (localStorage.getItem('darkMode') === 'enabled') {
        $('body').addClass('dark-mode');
        $('.dark-mode-toggle').text("라이트 모드");
    } else {
        $('body').removeClass('dark-mode');
        $('.dark-mode-toggle').text("다크 모드");
    }

    // 최상단으로 이동
    $('#topBtn').click(function() {
        $('html, body').animate({scrollTop: 0}, 'slow'); // 부드러운 스크롤 애니메이션 추가
    });

    // 다크모드 설정
    $('.dark-mode-toggle').click(function() {
        $('body').toggleClass('dark-mode');

        // localStorage에 다크 모드 설정 저장
        if ($('body').hasClass('dark-mode')) {
            localStorage.setItem('darkMode', 'enabled');
            $(this).text("라이트 모드");
        } else {
            localStorage.setItem('darkMode', 'disabled');
            $(this).text("다크 모드");
        }
    });

    // 열기
    $(document).on("click", "#openModal", function () {
        $("#preferenceModal, #preferenceModalOverlay").show();
    });

// 닫기
    $(document).on("click", "#closeModal, #preferenceModalOverlay", function () {
        $("#preferenceModal, #preferenceModalOverlay").hide();
    });


    /**
     * 최상단으로 이동
     * */
    function scrollFunction() {
        if ($(document).scrollTop() > 20) {
            $('#topBtn').fadeIn(); // 부드러운 페이드인 효과
        } else {
            $('#topBtn').fadeOut(); // 부드러운 페이드아웃 효과
        }
    }

});