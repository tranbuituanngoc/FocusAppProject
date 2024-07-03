$(document).ready(function () {
    var countdownInterval;
    var countdownRunning = false;
    var video = $('#timer')[0]; // Lấy phần tử video

    $('#create-study-set').on('click', function () {
        if (countdownRunning) {
            alert("Bạn đã đang có một đếm ngược đang chạy.");
            return;
        }

        console.log("Create study set button clicked");
        var selectedValue = $('#timerDropdown').val();
        var countdownTime = selectedValue == '1' ? 25 * 60 : 50 * 60; // Chuyển đổi phút sang giây
        var display = $('.timer-dropdown option:selected').text();

        video.addEventListener('ended', function () {
            this.currentTime = 0;
            this.play();
        });

        startCountdown(countdownTime, display);
        disableCreateStudySetButton();
        disableTimerDropdown();
        countdownRunning = true;
    });

    function startCountdown(duration, display) {
        var timer = duration, minutes, seconds;
        countdownInterval = setInterval(function () {
            minutes = parseInt(timer / 60, 10);
            seconds = parseInt(timer % 60, 10);

            minutes = minutes < 10 ? "0" + minutes : minutes;
            seconds = seconds < 10 ? "0" + seconds : seconds;

            $('#timerDropdown').find(':selected').text(minutes + ":" + seconds);
            if (duration - timer === 1) {
                video.play();
            }
            if (--timer < 0) {
                clearInterval(countdownInterval);
                video.pause();
                alert("Hết giờ!");
                enableCreateStudySetButton();
                enableTimerDropdown();
                countdownRunning = false;
                video.currentTime = 0;
                // Gọi API ở đây
            }
        }, 1000);
    }

    function disableCreateStudySetButton() {
        $('#create-study-set').prop('disabled', true);
    }

    function enableCreateStudySetButton() {
        $('#create-study-set').prop('disabled', false);
    }

    function disableTimerDropdown() {
        $('#timerDropdown').prop('disabled', true);
    }

    function enableTimerDropdown() {
        $('#timerDropdown').prop('disabled', false);
    }

    window.addEventListener('beforeunload', function (e) {
        if (countdownRunning) {
            var confirmationMessage = 'Bạn đang có một đếm ngược đang chạy. Bạn có chắc chắn muốn rời khỏi trang này?';
            (e || window.event).returnValue = confirmationMessage;
            return confirmationMessage;
        }
    });
});
