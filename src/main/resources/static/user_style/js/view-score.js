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
                    url: "/api/diem/get-score",
                    type: "GET",
                    contentType: "application/json",
                    data: {
                        userId: user.id
                    },
                    success: function (response) {
                        console.log(response);
                        response.forEach(semester => {
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
                            <div class="semester-title">${semester.semester_name}</div>
                        </div>
                        <div class="score-detail">`;

                            semester.list_scores.forEach(score => {
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
                            <div class="col-text" id="test-score">${score.score}</div>
                        </div>
                        <div class="fin10-text">
                            <div class="col-text" id="fin10">${score.final_score_10}</div>
                        </div>
                        <div class="fin4-text">
                            <div class="col-text" id="fin4">${score.final_score_4}</div>
                        </div>
                        <div class="res-text">
                            <div class="col-text" id="result">${score.result===true?`<img src="/user_style/img/icon/bi_check.svg" />`:`<img src="/user_style/img/icon/bi_x.svg" />`}</div>
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
                                      <span class="semester-subtext-normal">${semester.gpa_4}</span>
                                    </span>
                                </div>
                                <div class="semester-subtext">
                                    <span>
                                      <span class="semester-subtext-normal">
                                        Điểm trung bình học kỳ hệ 10:
                                      </span>
                                      <span class="semester-subtext-normal">${semester.gpa_10}</span>
                                    </span>
                                </div>
                                <div class="semester-subtext">
                                    <span>
                                      <span class="semester-subtext-normal">
                                       Số tính chỉ đạt trong học kì:
                                      </span>
                                      <span class="semester-subtext-normal">${semester.credit_hours}</span>
                                    </span>
                                </div>
                            </div>
                            <div class="overall-score">
                                <div class="semester-subtext-normal">
                                    <span>
                                      <span class="semester-subtext-normal">
                                        Điểm trung bình tích lũy hệ 4:
                                      </span>
                                      <span class="semester-subtext-normal">${semester.cumulative_gpa_4}</span>
                                    </span>
                                </div>
                                <div class="semester-subtext-normal">
                                    <span>
                                      <span class="semester-subtext-normal">
                                        Điểm trung bình tích lũy hệ 10:
                                      </span>
                                      <span class="semester-subtext-normal">${semester.cumulative_gpa_10}</span>
                                    </span>
                                </div>
                                <div class="semester-subtext-normal">
                                    <span>
                                      <span class="semester-subtext-normal">
                                        Số tính chỉ tích lũy:
                                      </span>
                                      <span class="semester-subtext-normal">${semester.cumulative_credit}</span>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>`;

                            $('#grade-board').append(semesterHtml);
                        });
                    },
                    error: function (response) {
                        console.error(response);
                    }
                });
            }
        }
    });
});