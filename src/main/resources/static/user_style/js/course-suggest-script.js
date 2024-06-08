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
                $('#view').append(
                    `<div class="title-page">
                        <div class="row">
                            <div class="frame-32">
                                <div class="main-title">Đề xuất môn học</div>
                            </div>
                            <div class="frame-6">
                                <div class="dropdown">
                                    <button class="option-select" id="dropdownButton">
                                        <span id="sugesst-type" data-value="1">Theo thời gian</span>
                                        <div class="icon-park-outline-drop-down-list">
                                            <img class="group" src="/user_style/img/icon/Group.svg" />
                                        </div>
                                    </button>
                                    <div class="dropdown-content" id="dropdownContent">
                                        <div class="type-suggest" data-value="1">Theo thời gian</div>
                                        <div class="type-suggest" data-value="2">Theo học lực</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="main-view">
                            <div class="noti-field" id="noti-field">
                <!--                Notify List-->
                            </div>
                            <div class="course-field" id="course-field">
                <!--               Course suggest list-->
                            </div>
                        </div>
                    </div>`
                );
            }
            var activeCourses = [];
            var typeSuggestValue = 1;

            $.ajax({
                url: '/api/course-suggest/' + user.id,
                type: 'GET',
                contentType: 'application/json',
                success(data) {
                    data.forEach(course => {
                        console.log(course.course_id + " " + course.course_name + " " + course.is_mandatory + " " + course.credits);
                        if (course.is_mandatory === true) {
                            $('#course-field').append(`
                        <div class="course-active">
                          <div class="frame-35">
                            <div class="rectangle-722">
                              <img class="rectangle-71" src="/user_style/img/icon/rectangle-710.svg" />
                              <img
                                class="task-iconamoon-check"
                                src="/user_style/img/icon/task-iconamoon-check0.svg"
                              />
                            </div>
                            <div class="course-name-active" data-value="${course.course_id}">${course.course_name} - ${course.credits} Tín chỉ</div>
                          </div>
                        </div>
                    `);
                            activeCourses.push({
                                course_id: course.course_id,
                                course_name: course.course_name,
                                credits: course.credits
                            });
                        } else {
                            $('#course-field').append(`
                    <div class="course">
                      <div class="frame-34">
                        <img class="rectangle-72" src="/user_style/img/icon/rectangle-721.svg" />
                        <div class="course-name" data-value="${course.course_id}">
                          ${course.course_name} - ${course.credits} Tín chỉ
                        </div>
                      </div>
                    </div>
                    `);
                        }
                    });
                    sendAjaxRequest();
                }
            });
            console.log(activeCourses.length)

            function sendAjaxRequest() {
                if (activeCourses.length > 0) {
                    $.ajax({
                        url: '/api/course-suggest/handle-course-suggest',
                        method: 'POST',
                        contentType: 'application/json',
                        data: JSON.stringify({
                            type_suggestion: typeSuggestValue,
                            list_course: activeCourses,
                            user_id: user.id,
                        }),
                        success: function (response) {
                            $('#noti-field').empty();
                            response.forEach(notify => {
                                if (notify.type === 'info') {
                                    $('#noti-field').append(`
                                <div class="notify2">
                                    <div class="noti-title">${notify.title}</div>
                                    <div class="noti-content">
                                       ${notify.message}
                                    </div>
                                </div>
                            `);
                                } else if (notify.type === 'warning') {
                                    $('#noti-field').append(`
                                <div class="notify">
                                    <div class="noti-title">${notify.title}</div>
                                    <div class="noti-content">
                                        ${notify.message}
                                    </div>
                                </div>
                            `);
                                } else if (notify.type === 'danger') {
                                    $('#noti-field').append(`
                            <div class="notify3">
                                <div class="noti-title">${notify.title}</div>
                                <div class="noti-content">
                                    ${notify.message}
                                </div>
                            </div>
                          `);
                                }
                            });
                        }
                    });
                } else {
                    $.ajax({
                        url: '/api/course-suggest/getImprove/' + user.id,
                        type: 'GET',
                        contentType: 'application/json',
                        success(response) {
                            $('#noti-field').empty();
                            response.forEach(notify => {
                                if (notify.type === 'info') {
                                    $('#noti-field').append(`
                                <div class="notify2">
                                    <div class="noti-title">${notify.title}</div>
                                    <div class="noti-content">
                                       ${notify.message}
                                    </div>
                                </div>
                            `);
                                } else if (notify.type === 'warning') {
                                    $('#noti-field').append(`
                                <div class="notify">
                                    <div class="noti-title">${notify.title}</div>
                                    <div class="noti-content">
                                        ${notify.message}
                                    </div>
                                </div>
                            `);
                                } else if (notify.type === 'danger') {
                                    $('#noti-field').append(`
                            <div class="notify3">
                                <div class="noti-title">${notify.title}</div>
                                <div class="noti-content">
                                    ${notify.message}
                                </div>
                            </div>
                          `);
                                }
                            });
                        },
                        error(error) {
                            console.log(error);
                        }
                    });
                }
            }

            $(document).on('click', '.course, .course-active', function () {
                var courseName = $(this).find('.course-name-active, .course-name').text();
                var dataValue = $(this).find('.course-name-active, .course-name').data('value');

                var splitCourseName = courseName.split(' - ');
                var name = splitCourseName[0];
                var credits = parseInt(splitCourseName[1]);

                if ($(this).hasClass('course-active')) {
                    $(this).replaceWith(`
                        <div class="course">
                             <div class="frame-34">
                                <img class="rectangle-72" src="/user_style/img/icon/rectangle-721.svg" />
                                <div class="course-name" data-value="${dataValue}">${courseName}</div>
                             </div>
                       </div>
                    `);
                    activeCourses = activeCourses.filter(function (course) {
                        return course.course_id !== dataValue;
                    });
                } else {
                    $(this).replaceWith(`
                       <div class="course-active">
                           <div class="frame-35">
                             <div class="rectangle-722">
                               <img class="rectangle-71" src="/user_style/img/icon/rectangle-710.svg" />
                               <img class="task-iconamoon-check" src="/user_style/img/icon/task-iconamoon-check0.svg"/>
                                </div>
                                <div class="course-name-active" data-value="${dataValue}">${courseName}</div>
                            </div>
                           </div>   
                        `);
                    activeCourses.push({course_id: dataValue, course_name: name, credits: credits});
                }
                sendAjaxRequest();
            });

            $('.type-suggest').click(function () {
                var text = $(this).text();
                var value = $(this).data('value');

                $('#sugesst-type').text(text);
                $('#sugesst-type').data('value', value);
                typeSuggestValue = value;

                sendAjaxRequest();
            });
        },
        error(error) {
            console.error(error);
        }
    });
});