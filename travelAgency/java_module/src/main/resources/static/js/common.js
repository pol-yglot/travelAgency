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

    /* datepicke 캘린더 적용 */
    $('.datepicker').datepicker();

    $.datepicker.setDefaults({
        dateFormat: 'yy-mm-dd',
        prevText: '이전 달',
        nextText: '다음 달',
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        showMonthAfterYear: true,
        yearSuffix: '년'
    });

    /*$('.post-wrapper').slick({
        slidesToShow: 1,
        slidesToScroll: 1,
        autoplay: true,
        autoplaySpeed: 2000,
    });*/

    $('.slider-container').slick({
        dots: false,
        infinite: true,
        autoplaySpeed: 2000,
        autoplay: true,
        slidesToShow: 1,
        adaptiveHeight: true,
        arrows: true, // <<< 이거 추가: 좌우 버튼 켜기
        prevArrow: '<i class="slick-prev fa fa-chevron-left"></i>',
        nextArrow: '<i class="slick-next fa fa-chevron-right"></i>',
        responsive: [
            {
                breakpoint: 1000,
                settings: {
                    arrows: false,
                    dots: true
                }
            }
        ]
    });
});