$(document).ready(function () {
    $.ajax({
        url: '/api/dkmh/is-linked/' + user.id,
        type: 'GET',
        contentType: 'application/json',
        success(response) {
            if (!response) {
                $('#view').append(
                    `<div class="notify-user">
                    <div class="not-linked">
                      Bạn chưa liên kết tài khoản đăng ký môn học. Vui lòng liên kết tài khoản trước để sử dụng chức năng này.
                    </div>
                  </div>`
                );
            } else {
                $(".test-schedule-content").css("display", "block");
                $.ajax({
                    url: "/api/lich-thi/get-test-schedule",
                    type: "GET",
                    contentType: "application/json",
                    data: {
                        userId: user.id
                    },
                    success: function (response) {
                        response.forEach(course => {
                            let courseHtml = `
                   <div class="subject-test">
                <div class="test-course-id">
                    <div class="test-col">${course.course_id}</div>
                </div>
                <div class="test-course-name">
                    <div class="test-course-name-col">${course.course_name}</div>
                </div>
                <div class="test-date">
                    <div class="test-col">${course.test_date}</div>
                </div>
                <div class="test-room">
                    <div class="test-col">${course.test_room}</div>
                </div>
                <div class="test-slot">
                    <div class="test-col">${course.test_slot}</div>
                </div>
                <div class="test-time">
                    <div class="test-col">${course.test_time}</div>
                </div>
            </div>`;
                            $(".test-course").append(courseHtml);
                        });
                    },
                    error: function (response) {
                        console.log(response);
                    }
                })
            }
        },
        error(response) {
            console.log(response);
        }
    });
});