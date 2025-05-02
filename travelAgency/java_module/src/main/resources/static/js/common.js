$(document).ready(function() {

    /**
     * 세션 체크
     * */
    // 공통 변수
    const SESSION_TIMEOUT = 30 * 60 * 1000; // 30분 (ms)
    const WARNING_TIME= 1 * 60 * 1000; // 1분전 (ms)

    // 2. 내부 사용 변수
    let remainingTime;        // 남은 시간 (ms)
    let countdownInterval;    // setInterval ID
    let warningTimer;         // 경고용 setTimeout ID
    let logoutTimer;          // 강제 로그아웃용 setTimeout ID

    // 3. 화면에 남은 시간 표시 함수
    function updateCountdownDisplay() {
        const $el = $('#session-timer');
        if (!$el.length) return;

        const totalSec = Math.max(0, Math.floor(remainingTime / 1000));
        const m = String(Math.floor(totalSec / 60)).padStart(2, '0');
        const s = String(totalSec % 60).padStart(2, '0');
        $el.text(`${m}:${s}`);
    }

    // 4. 경고창 띄우기
    function showWarning() {
        // 남은 분 단위로 계산
        const minsLeft = Math.ceil(remainingTime / 60000);
        if (confirm(`세션이 ${minsLeft}분 후에 만료됩니다.\n연장하시겠습니까?`)) {
            // 서버에 갱신 호출 (예: /keepAlive)
            fetch('/keepAlive', { method: 'GET' })
                .then(() => startTimers())
                .catch(() => console.warn('세션 연장 실패'));
        }
    }

    // 5. 강제 로그아웃
    function logoutUser() {
        window.location.href = '/logout';
    }

    // 6. 타이머(카운트다운 + 경고 + 로그아웃) 모두 초기화하고 재시작
    function startTimers() {
        // 기존 타이머 전부 제거
        clearInterval(countdownInterval);
        clearTimeout(warningTimer);
        clearTimeout(logoutTimer);

        // 남은 시간을 세션 만료 시간으로 초기화
        remainingTime = SESSION_TIMEOUT;
        updateCountdownDisplay();

        // 1초마다 남은 시간 갱신
        countdownInterval = setInterval(() => {
            remainingTime -= 1000;
            if (remainingTime <= 0) {
                clearInterval(countdownInterval);
                $('#session-timer').text('00:00');
                logoutUser();
            } else {
                updateCountdownDisplay();
            }
        }, 1000);

        // 만료 1분 전 경고
        warningTimer = setTimeout(showWarning, SESSION_TIMEOUT - WARNING_TIME);

        // 만료 시 강제 로그아웃
        logoutTimer = setTimeout(logoutUser, SESSION_TIMEOUT);
    }

    /**
     * 페이지 init
     * */
    window.addEventListener('load', startTimers);
    window.addEventListener('scroll', scrollFunction);

    /**
     * 최상단 이동버튼
     * */
    $('#topBtn').click(function() {
        $('html, body').animate({scrollTop: 0}, 'slow'); // 부드러운 스크롤 애니메이션 추가
    });

    /**
     * 다크모드 유지
     * */
    if (localStorage.getItem('darkMode') === 'enabled') {
        $('body').addClass('dark-mode');
        $('.dark-mode-toggle').text("라이트 모드");
    } else {
        $('body').removeClass('dark-mode');
        $('.dark-mode-toggle').text("다크 모드");
    }

    /**
     * 다크모드 세팅
     * */
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

    /**
     * 모달창 열기
     * */
    $(document).on("click", "#openModal", function () {
        $("#preferenceModal, #preferenceModalOverlay").show();
    });

    /**
     * 모달창 닫기
     * */
    $(document).on("click", "#closeModal, #preferenceModalOverlay", function () {
        $("#preferenceModal, #preferenceModalOverlay").hide();
    });

    /**
     * 최상단으로 이동버튼 show on/off
     * */
    function scrollFunction() {
        if ($(document).scrollTop() > 20) {
            $('#topBtn').fadeIn(); // 부드러운 페이드인 효과
        } else {
            $('#topBtn').fadeOut(); // 부드러운 페이드아웃 효과
        }
    }

    /**
     * datepicker 캘린더 적용
     * */
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

    /**
     * slick 슬라이더 적용
     * */
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