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
                $(".score-content").css("display", "block");
                $.ajax({
                    url: "/api/diem/get-desired-score",
                    type: "GET",
                    contentType: "application/json",
                    data: {
                        userId: user.id
                    },
                    success: function (response) {
                        if (response.left === 'error') {
                            Swal.fire({
                                icon: 'error',
                                title: 'Điểm mong muốn quá cao',
                                text: response.right,
                                html: '<input id="desire-input" class="swal2-input input-number-form" placeholder="Điểm GPA hệ 4 mong muốn" type="number" required>',
                                showCancelButton: true,
                                allowOutsideClick: false,
                                cancelButtonText: 'Hủy',
                                confirmButtonText: 'Cập nhật',
                                customClass: {
                                    confirmButton: 'confirm-button-class',
                                    cancelButton: 'cancel-button-class',
                                    title: 'title-alert-class',
                                    validationMessage: 'my-validation-message',
                                },
                                reverseButtons: true,
                                buttonsStyling: true,
                                preConfirm: () => {
                                    const value = document.getElementById('desire-input').value;
                                    if (!value) {
                                        Swal.showValidationMessage('Bạn cần phải nhập điểm số mong muốn!');
                                    } else if (value > 4) {
                                        Swal.showValidationMessage('Điểm của bạn không được lớn hơn 4!');
                                    } else if (value > user.desiredScore) {
                                        Swal.showValidationMessage(`Điểm nhập vào không được lớn hơn ${user.desiredScore}!`);
                                    }
                                    return value;
                                },
                                allowOutsideClick: () => !Swal.isLoading()
                            }).then((result) => {
                                if (result.isConfirmed) {
                                    const desiredScore = parseFloat(result.value).toFixed(2);
                                    $.ajax({
                                        url: "/api/nguoi-dung/updateDesiredScore/" + user.id + "?desiredScore=" + desiredScore,
                                        type: "PUT",
                                        contentType: "application/json",
                                        success: function (response) {
                                            Swal.fire({
                                                icon: 'success',
                                                title: 'Thành công',
                                                text: response,
                                                confirmButtonText: 'OK',
                                                allowOutsideClick: false,
                                            }).then((result) => {
                                                if (result.isConfirmed) {
                                                    location.reload();
                                                }
                                            });
                                        },
                                        error: function (response) {
                                            Swal.fire({
                                                icon: 'error',
                                                title: 'Thất bại',
                                                text: response.responseText
                                            });
                                        }
                                    });
                                }
                            });

                        } else {
                            console.log(response.right);
                            let semesterHtml = `
                                    <div class="semester">
                                        <div class="table-title">
                                            <div class="course-id-header">Mã MH</div>
                                            <div class="course-name-header">Tên Môn Học</div>
                                            <div class="credit-header">Số tín chỉ</div>
                                            <div class="test-score-header">Điểm thi</div>
                                            <div class="fin10-header">Điểm TK(10)</div>
                                            <div class="fin4-header">Điểm TK(4)</div>
                                            <div class="result-header">Kết quả</div>
                                        </div>
                                        <div class="frame-21">
                                            <div class="semester-title">${response.right.semester_name}</div>
                                        </div>
                                        <div class="score-detail">`;

                            response.right.list_scores.forEach(score => {
                                semesterHtml += `
                                        <div class="course-score">
                                            <div class="course-id-text">
                                            <div class="col-text" id="course-id">${score.course_id}</div>
                                        </div>
                                        <div class="course-name-text">
                                            <div class="col-text" id="course-name">${score.course_name}</div>
                                        </div>
                                        <div class="credit-text">
                                            <div class="col-text" id="credit">${score.credit}</div>
                                        </div>
                                        <div class="test-score-text">
                                            <div class="col-text desired-score-content" id="test-score">N/A</div>
                                        </div>
                                        <div class="fin10-text">
                                            <div class="col-text desired-score-content" id="fin10">N/A</div>
                                        </div>
                                        <div class="fin4-text">
                                            <div class="col-text desired-score-content" id="fin4">${score.final_score_4}</div>
                                        </div>
                                        <div class="res-text">
                                            <div class="col-text desired-score-content" id="result">${score.result === true ? `<img src="/user_style/img/icon/bi_check.svg" />` : `<img src="/user_style/img/icon/bi_x.svg" />`}</div>
                                        </div>
                                        </div>`;
                            });

                            semesterHtml += `
                                        </div>
                                        <div class="semester-detail">
                                            <div class="cumulative-score">
                                                <div class="semester-subtext">
                                                    <span>
                                                      <span class="semester-subtext-normal">
                                                        Điểm trung bình học kỳ hệ 4:
                                                      </span>
                                                      <span class="semester-subtext-normal desired-semester-content">${response.right.gpa_4}</span>
                                                    </span>
                                                </div>
                                                <div class="semester-subtext">
                                                    <span>
                                                      <span class="semester-subtext-normal">
                                                        Điểm trung bình học kỳ hệ 10:
                                                      </span>
                                                      <span class="semester-subtext-normal desired-semester-content">N/A</span>
                                                    </span>
                                                </div>
                                                <div class="semester-subtext">
                                                    <span>
                                                      <span class="semester-subtext-normal">
                                                       Số tính chỉ đạt trong học kì:
                                                      </span>
                                                      <span class="semester-subtext-normal desired-semester-content">${response.right.credit_hours}</span>
                                                    </span>
                                                </div>
                                            </div>
                                            <div class="overall-score">
                                                <div class="semester-subtext-normal">
                                                    <span>
                                                      <span class="semester-subtext-normal">
                                                        Điểm trung bình tích lũy hệ 4:
                                                      </span>
                                                      <span class="semester-subtext-normal desired-semester-content">${response.right.cumulative_gpa_4}</span>
                                                    </span>
                                                </div>
                                                <div class="semester-subtext-normal">
                                                    <span>
                                                      <span class="semester-subtext-normal">
                                                        Điểm trung bình tích lũy hệ 10:
                                                      </span>
                                                      <span class="semester-subtext-normal desired-semester-content">N/A</span>
                                                    </span>
                                                </div>
                                                <div class="semester-subtext-normal">
                                                    <span>
                                                      <span class="semester-subtext-normal">
                                                        Số tính chỉ tích lũy:
                                                      </span>
                                                      <span class="semester-subtext-normal desired-semester-content">${response.right.cumulative_credit}</span>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>`;

                            $('#grade-board').append(semesterHtml);
                        }
                    },
                    error: function (response) {
                        console.error(response);
                    }
                });
            }
        }
    });
});

$(".button-update-desired-score").on("click", function () {
    Swal.fire({
        title: 'Cập nhật điểm mong muốn',
        html: '<input id="desire-input-update" class="swal2-input input-number-form" placeholder="Điểm GPA hệ 4 mong muốn" type="number" required>',
        showCancelButton: true,
        allowOutsideClick: false,
        cancelButtonText: 'Hủy',
        confirmButtonText: 'Cập nhật',
        customClass: {
            confirmButton: 'confirm-button-class',
            cancelButton: 'cancel-button-class',
            title: 'title-alert-class',
            validationMessage: 'my-validation-message',
        },
        reverseButtons: true,
        buttonsStyling: true,
        preConfirm: () => {
            const value = document.getElementById('desire-input-update').value;
            if (!value) {
                Swal.showValidationMessage('Bạn cần phải nhập điểm số mong muốn!');
            } else if (value > 4) {
                Swal.showValidationMessage('Điểm của bạn không được lớn hơn 4!');
            }
            return value;
        },
        allowOutsideClick: () => !Swal.isLoading()
    }).then((result) => {
        if (result.isConfirmed) {
            const desiredScore = parseFloat(result.value).toFixed(2);
            $.ajax({
                url: "/api/nguoi-dung/updateDesiredScore/" + user.id + "?desiredScore=" + desiredScore,
                type: "PUT",
                contentType: "application/json",
                success: function (response) {
                    Swal.fire({
                        icon: 'success',
                        title: 'Thành công',
                        text: response,
                        confirmButtonText: 'OK',
                        allowOutsideClick: false,
                    }).then((result) => {
                        if (result.isConfirmed) {
                            location.reload();
                        }
                    });
                },
                error: function (response) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Thất bại',
                        text: response.responseText
                    });
                }
            });
        }
    });
});