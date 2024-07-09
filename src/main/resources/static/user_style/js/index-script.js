$(document).ready(function () {
    var countdownInterval;
    var countdownRunning = false;
    var video = $('#timer')[0]; // Lấy phần tử video

    function updateSavingTime(timeInMilliseconds) {
        // Chuyển đổi mili giây thành giờ và phút
        var hours = Math.floor(timeInMilliseconds / 3600000); // 1 giờ = 3600000 mili giây
        var minutes = Math.floor((timeInMilliseconds % 3600000) / 60000); // 1 phút = 60000 mili giây

        if (hours >= 1) {
            $('.saving-time').text(hours + ' giờ ' + minutes + ' phút');
        } else {
            $('.saving-time').text(minutes + ' phút');
        }
    }

    function getTotalTime() {
        $.ajax({
            url: '/api/archivement/total-time',
            method: 'GET',
            data: {
                userId: user.id,
            },
            success: function (response) {
                updateSavingTime(response);
            }
        })
    }

    getTotalTime();

    function updateTotalTime(savingTime) {
        $.ajax({
            url: '/api/archivement/update-total-time',
            method: 'PUT',
            data: JSON.stringify({
                userId: user.id,
                totalTime: savingTime,
            }),
            contentType: 'application/json',
            success: function (response) {
                Toastify({
                    text: "Tích lũy thành thích thành công!",
                    duration: 3000,
                    close: true,
                    gravity: "bottom",
                    position: "right",
                    stopOnFocus: true,
                    className: "my-toast",
                    style: {
                        background: '#59D5E0',
                    },
                    onClick: function () {
                    }
                }).showToast();
                updateSavingTime(response);
            }
        })
    }

    $('#create-study-set').on('click', function () {
        if (countdownRunning) {
            alert("Bạn đã đang có một đếm ngược đang chạy.");
            return;
        }

        var selectedValue = $('#timerDropdown').val();
        var countdownTime = selectedValue == '1' ? 25 * 60 : 50 * 60;
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

                var selectedValue = $('#timerDropdown').val();
                var countdownTime = selectedValue == '1' ? 25 * 60000 : 50 * 60000;
                updateTotalTime(countdownTime)
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

//    script for to-do list
    function toLocalISODateString(date) {
        const offset = date.getTimezoneOffset();
        const localDate = new Date(date.getTime() - offset * 60000);
        return localDate.toISOString().split('T')[0];
    }

    function createToDoElement(date) {
        let dateToIso = toLocalISODateString(date);

        return new Promise((resolve, reject) => {
            $.ajax({
                url: '/api/to-do/get-list',
                method: 'GET',
                data: {
                    userId: user.id,
                    date: dateToIso,
                },
                success: function (response, textStatus, xhr) {
                    let result;
                    if (xhr.status === 200 && response.length > 0) {
                        let taskHtml = '';
                        response.forEach((task) => {
                            if (task.is_done) {
                                taskHtml += `
                            <div class="task" id="${task.id}">
                                <div class="done-task">
                                    <div class="checked">
                                        <div class="done-eclipse"></div>
                                        <img
                                            class="task-iconamoon-check"
                                            src="/user_style/img/icon/iconamoon_check.svg"
                                        />
                                    </div>
                                    <div class="content-task">
                                        <div class="done-task-name strike-through">${task.title}</div>
                                        <div class="done-task-des strike-through">${task.content}</div>
                                    </div>
                                </div>
                            </div>`;
                            } else {
                                taskHtml += `
                            <div class="task" id="${task.id}">
                                <div class="undone-task">
                                    <div class="to-do-undone">
                                        <div class="undone-eclipse"></div>
                                        <div class="content-task">
                                            <div class="undone-task-name">${task.title}</div>
                                            <div class="task-des">${task.content}</div>
                                        </div>
                                    </div>
                                </div>
                            </div>`;
                            }
                        });
                        result = `
                        <div class="date-to-do" id="${dateToIso}">
                            ` + taskHtml + `
                            <div class="add-task" data-date="${dateToIso}">
                                <div class="frame-37">
                                    <div class="frame-38">
                                        <div class="task-gg-add">
                                            <img class="group2" src="/user_style/img/icon/group1.svg" />
                                        </div>
                                        <div class="content-add-task">
                                            <div class="add-task-title">Thêm công việc mới</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="task" style="display:none;">
                                <div class="undone-task">
                                    <div class="to-do-undone">
                                        <div class="undone-eclipse"></div>
                                        <div class="content-task">
                                            <div class="undone-task-name">Việc làm A</div>
                                            <div class="task-des">Mô tả</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>`;
                    } else if (xhr.status === 204) {
                        result = `
                    <div class="date-to-do" id="${dateToIso}">
                        <div class="add-task" data-date="${dateToIso}">
                            <div class="frame-37">
                                <div class="frame-38">
                                    <div class="task-gg-add">
                                        <img class="group2" src="/user_style/img/icon/group1.svg" />
                                    </div>
                                    <div class="content-add-task">
                                        <div class="add-task-title">Thêm công việc mới</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="task" style="display:none;">
                            <div class="undone-task">
                                <div class="to-do-undone">
                                    <div class="undone-eclipse"></div>
                                    <div class="content-task">
                                        <div class="undone-task-name">Việc làm A</div>
                                        <div class="task-des">Mô tả</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>`;
                    }
                    resolve(result);
                },
                error: function (error) {
                    console.error('Error fetching tasks:', error);
                    reject(error);
                }
            })
        });
    }

    function isDone(studySlot) {
        const slotEndTimes = {
            1: '07:00',
            4: '09:30',
            7: '12:15',
            10: '14:50'
        };

        const currentTime = new Date();

        const [endHours, endMinutes] = slotEndTimes[studySlot].split(':');
        const endTime = new Date();
        endTime.setHours(endHours, endMinutes);

        return currentTime >= endTime;
    }

    function createCourseElement() {
        let date = new Date();
        let dateToIso = toLocalISODateString(date);

        return new Promise((resolve, reject) => {
            $.ajax({
                url: '/api/tkb/get-schedule-by-date',
                method: 'GET',
                data: {
                    userId: user.id,
                },
                success: function (response, textStatus, xhr) {
                    let result;
                    if (xhr.status === 200 && response.length > 0) {
                        let taskHtml = '';
                        response.forEach((course) => {
                            var title = course.course_name;
                            var content = course.course_room;
                            if (isDone(course.study_slot)) {
                                taskHtml += `
                            <div class="task" id="${course.course_id}">
                                <div class="done-task">
                                    <div class="checked">
                                        <div class="done-eclipse"></div>
                                        <img
                                            class="task-iconamoon-check"
                                            src="/user_style/img/icon/iconamoon_check.svg"
                                        />
                                    </div>
                                    <div class="content-task">
                                        <div class="done-task-name strike-through">${title}</div>
                                        <div class="done-task-des strike-through">${content}</div>
                                    </div>
                                </div>
                            </div>`;
                            } else {
                                taskHtml += `
                            <div class="task" id="${course.course_id}">
                                <div class="undone-task">
                                    <div class="to-do-undone">
                                        <div class="undone-eclipse"></div>
                                        <div class="content-task">
                                            <div class="undone-task-name">${title}</div>
                                            <div class="task-des">${content}</div>
                                        </div>
                                    </div>
                                </div>
                            </div>`;
                            }
                        });
                        result = `
                        <div class="date-to-do" id="${dateToIso}">
                            ` + taskHtml + `
                        </div>`;
                    } else if (xhr.status === 204) {
                        result = `
                    <div style="display: flex; justify-content: center; align-items: center; width:100%; height: 100%;">
                        Hôm nay bạn không có môn học nào
                    </div>
                    `;
                    }
                    resolve(result);
                },
                error: function (error) {
                    console.error('Error fetching tasks:', error);
                    reject(error);
                }
            })
        });
    }

    function displayCourses() {
        createCourseElement().then(result => {
            $('#course-list').append(result);
        });
    }

    displayCourses();

    function displayDates() {
        let currentDate = new Date();
        createToDoElement(currentDate).then(result => {
            $('#to-do-list').append(result);
        });

        // Attach click event to .add-task elements
        $('#to-do-list').on('click', '.add-task', function () {
            const taskDate = $(this).data('date');
            const formHtml = `
        <form class="create-task-form">
            <input type="hidden" name="taskDate" value="${taskDate}" />
            <div class="form-content">
                <div class="task-content">
                    <input type="text" class="task-name" name="taskName" required placeholder="Tên công việc" />
                    <input type="text" class="done-task-des" name="description" placeholder="Mô tả" />
                </div>
                <div class="task-footer">
                    <div class="button-to-do">
                        <input type="date" class="task-date" name="taskDeadline" id="taskDeadline" value="${taskDate}" />
                    </div>
                    <div class="task-button-control">
                        <div class="cancel-task-button">
                            <div class="cancel-task">Hủy</div>
                        </div>
                        <button class="create-task-button" type="submit">
                            <div class="create-task">Thêm</div>
                        </button>
                    </div>
                </div>
            </div>
        </form>`;
            $(this).hide();
            $(this).closest('.date-to-do').append(formHtml);

            // Attach click event to cancel button
            $(this).closest('.date-to-do').find('.cancel-task-button').off('click').on('click', function () {
                $(this).closest('.date-to-do').find('.create-task-form').remove();
                $('.add-task[data-date="' + taskDate + '"]').show().appendTo($(this).closest('.date-to-do'));
            });
        });

        $('#to-do-list').on('submit', '.create-task-form', function (e) {
            e.preventDefault();
            var taskDateValue = $('input[name="taskDate"]').val();
            var userId = user.id;
            var title = $('input[name="taskName"]').val();
            var content = $('input[name="description"]').val();
            var date = $('input[name="taskDeadline"]').val();
            $.ajax({
                url: '/api/to-do/create',
                method: 'POST',
                data: JSON.stringify({
                    user_id: userId,
                    title: title,
                    content: content,
                    deadline: date,
                }),
                contentType: 'application/json',
                success: function (response) {
                    const newTaskHtml = `
                    <div class="task" id="${response}">
                        <div class="undone-task">
                            <div class="to-do-undone">
                                <div class="undone-eclipse"></div>
                                <div class="content-task">
                                    <div class="undone-task-name">${title}</div>
                                    <div class="task-des">${content}</div>
                                </div>
                            </div>
                        </div>
                    </div>`;
                    var $dateTodo = $('#' + taskDateValue);
                    $dateTodo.append(newTaskHtml);
                    // Clean up form
                    $(e.target).remove();
                    // Show add-task button
                    $('.add-task', $dateTodo).appendTo($dateTodo).show();

                    Toastify({
                        text: "Thêm công việc thành công",
                        duration: 3000,
                        close: true,
                        gravity: "bottom",
                        position: "right",
                        stopOnFocus: true,
                        className: "my-toast",
                        style: {
                            background: '#59D5E0',
                        },
                        onClick: function () {
                        }
                    }).showToast();
                },
                error: function (error) {
                    Toastify({
                        text: "Có lỗi xảy ra, vui lòng thử lại!",
                        duration: 3000,
                        close: true,
                        gravity: "bottom",
                        position: "right",
                        stopOnFocus: true,
                        className: "my-toast",
                        style: {
                            background: '#F4538A',
                        },
                        onClick: function () {
                        }
                    }).showToast();
                }
            });
        });

        $('#to-do-list').on('click', '.undone-task', function () {
            const taskName = $(this).find('.undone-task-name').text();
            const taskDescription = $(this).find('.task-des').text();
            const taskId = $(this).closest('.task').attr('id');
            console.log(taskId);
            const doneTaskHtml = `
            <div class="task" id="${taskId}">
                <div class="done-task">
                    <div class="checked">
                    <div class="done-eclipse"></div>
                    <img
                        class="task-iconamoon-check"
                        src="/user_style/img/icon/iconamoon_check.svg"
                    />
                    </div>
                    <div class="content-task">
                    <div class="done-task-name strike-through">${taskName}</div>
                    <div class="done-task-des strike-through">${taskDescription}</div>
                    </div>
                </div>
            </div>`;
            const taskElement = $(this).closest('.task');
            $.ajax({
                url: '/api/to-do/update/' + taskId,
                method: 'POST',
                data: {
                    status: true
                },
                success: function (response) {
                    taskElement.replaceWith(doneTaskHtml);
                },
                error: function (error) {
                    console.error('Error updating task:', error);
                }
            })
        });

        $('#to-do-list').on('click', '.done-task', function () {
            const doneTaskName = $(this).find('.done-task-name').text();
            const doneTaskDes = $(this).find('.done-task-des').text();
            const taskId = $(this).closest('.task').attr('id');
            const undoneTaskHtml = `
            <div class="task" id="${taskId}">
                <div class="undone-task">
                    <div class="to-do-undone">
                        <div class="undone-eclipse"></div>
                        <div class="content-task">
                            <div class="undone-task-name">${doneTaskName}</div>
                            <div class="task-des">${doneTaskDes}</div>
                        </div>
                    </div>
                </div>
            </div>`;
            const taskElement = $(this).closest('.task');
            $.ajax({
                url: '/api/to-do/update/' + taskId,
                method: 'POST',
                data: {
                    status: true
                },
                success: function (response) {
                    taskElement.replaceWith(undoneTaskHtml);
                },
                error: function (error) {
                    console.error('Error updating task:', error);
                }
            })
        });
    }

    displayDates();
});
