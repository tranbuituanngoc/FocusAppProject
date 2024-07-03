$(document).ready(function () {

    function generateDateInWeekArray() {
        let currentDate = new Date();
        currentDate.setDate(currentDate.getDate() - currentDate.getDay()); // Start from the first day of the week (Sunday)
        let dateArray = [];
        for (let day = 0; day < 7; day++) {
            dateArray.push(new Date(currentDate));
            currentDate.setDate(currentDate.getDate() + 1);
        }
        return dateArray;
    }

    function formatDateInWeekString(date) {
        const daysOfWeek = ["Chủ nhật", "Thứ Hai", "Thứ Ba", "Thứ Tư", "Thứ Năm", "Thứ Sáu", "Thứ Bảy"];
        return `${daysOfWeek[date.getDay()]}`;
    }

    function createDateElement(date) {
        const dayString = formatDateInWeekString(date);
        const day = date.getDate();
        const today = new Date();
        let dayClass = "day";
        let dateClass = "date-coming";
        let lineClass = "date-bottom-line-normal";

        if (date.toDateString() === today.toDateString()) {
            dayClass = "day";
            dateClass = "date-active";
            lineClass = "date-bottom-line-active";
        } else if (date < today) {
            dayClass = "day-disable";
            dateClass = "date-disable";
        }

        return `
    <div class="todo-date-unit">
        <div class="${dayClass}">${dayString}</div>
        <div class="date ${dateClass}">${day}</div>
        <div class="date-bottom-line ${lineClass}"></div>
    </div>`;
    }

    function displayDatesInWeek() {
        let dates = generateDateInWeekArray();
        dates.forEach((date) => {
            $('.to-do-week').append(createDateElement(date));
        });
    }


    function generateDateArray(startDay, numDays) {
        let currentDate = new Date();
        currentDate.setDate(currentDate.getDate() + startDay);
        let dateArray = [];
        for (let day = 0; day < numDays; day++) {
            dateArray.push(new Date(currentDate));
            currentDate.setDate(currentDate.getDate() + 1);
        }
        return dateArray;
    }

    function formatDateString(date, index) {
        const daysOfWeek = ["Chủ nhật", "Thứ Hai", "Thứ Ba", "Thứ Tư", "Thứ Năm", "Thứ Sáu", "Thứ Bảy"];
        const months = ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"];
        let dayString;

        if (index === 0) {
            dayString = "Hôm nay";
        } else if (index === 1) {
            dayString = "Ngày mai";
        }
        if (dayString !== undefined) {
            return `${date.getDate()} ${months[date.getMonth()]} - ${dayString} - ${daysOfWeek[date.getDay()]}`;
        }
        return `${date.getDate()} ${months[date.getMonth()]} - ${daysOfWeek[date.getDay()]}`;
    }

    function toLocalISODateString(date) {
        const offset = date.getTimezoneOffset();
        const localDate = new Date(date.getTime() - offset * 60000);
        return localDate.toISOString().split('T')[0];
    }

    function createToDoElement(date, index) {
        const dateString = formatDateString(date, index);
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
                            <div class="todo-date">
                                <div class="to-do-date-title">${dateString}</div>
                                <div class="to-do-date-line"></div>
                            </div>
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
                        <div class="todo-date">
                            <div class="to-do-date-title">${dateString}</div>
                            <div class="to-do-date-line"></div>
                        </div>
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

    function displayDates() {
        let dates = generateDateArray(0, 60);
        let promises = dates.map((date, index) => createToDoElement(date, index));
        Promise.all(promises).then(results => {
            results.forEach(result => {
                $('.calendar-todo').append(result);
            });
        });

        // Attach click event to .add-task elements
        $('.calendar-todo').on('click', '.add-task', function () {
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

        $('.calendar-todo').on('submit', '.create-task-form', function (e) {
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
                    // Append new task to the correct date-to-do section
                    var $dateTodo = $('#' + date);
                    $('.task', $dateTodo).replaceWith(newTaskHtml).show();
                    // Clean up form
                    $(e.target).remove();
                    // Show add-task button
                    console.log("taskDateValue", taskDateValue);
                    console.log("date", date);
                    var $addTaskButton = $('.add-task[data-date="' + taskDateValue + '"]');
                    if (date === taskDateValue) {
                        $addTaskButton.appendTo($dateTodo).show();
                    } else {
                        $addTaskButton.show();
                        $('.add-task[data-date="' + date + '"]').appendTo($('#' + date)).show();
                    }

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

        $('.calendar-todo').on('click', '.undone-task', function () {
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

        $('.calendar-todo').on('click', '.done-task', function () {
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

    let isFetching = false;

    function loadMoreDates() {
        if (isFetching) return;
        isFetching = true;

        const startDay = $('.calendar-todo .date-to-do').length;
        let moreDates = generateDateArray(startDay, 30);

        moreDates.forEach((date, index) => {
            $('.calendar-todo').append(createToDoElement(date, startDay + index));
        });

        isFetching = false;
    }

    $(".calendar-todo").scroll(function () {
        const scrollContainer = $(this)[0];
        if ($(".calendar-todo").innerHeight() + $(".calendar-todo").scrollTop() >= scrollContainer.scrollHeight - 2) {
            loadMoreDates();
        }
        let topDateElement = null;
        $(".date-to-do").each(function () {
            if ($(this).offset().top >= $(".calendar-todo").offset().top) {
                topDateElement = $(this);
                return false;
            }
        });

        if (topDateElement) {
            const dateString = topDateElement.attr('id');
            const date = new Date(dateString);
            $("#to-do-date-picker").val(formatMonthYear(date));
            $("#to-do-date-picker").css("width", `${$("#to-do-date-picker").val().length}ch`);
        }
    });

    displayDates();
    displayDatesInWeek();

    var today = new Date().toISOString().split('T')[0];
    $('#taskDate').attr('min', today);
    $('#taskDate').val(today);

    $('#taskDate').on('input', function () {
        if ($(this).val() === '') {
            $(this).val(today);
        }
    });

    $("#datepicker").datepicker({
        dateFormat: "dd/mm/yy",
        onSelect: function (dateText) {
            let selectedDate = $(this).datepicker("getDate");
            addDateToCalendar(selectedDate);
        }
    });

    // Show datepicker on click
    $(".date-selection").on("click", function () {
        $("#datepicker").datepicker("show");
    });

    function formatMonthYear(date) {
        const months = ["Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"];
        return `${months[date.getMonth()]} ${date.getFullYear()}`;
    }

    let selectedDateForScrolling = null;


    $("#to-do-date-picker").datepicker({
        dateFormat: "dd/mm/yy",
        showButtonPanel: true,
        minDate: 0,
        onSelect: function (dateText) {
            let selectedDate = $(this).datepicker("getDate");
            $(this).val(formatMonthYear(selectedDate));
            $(this).css("width", `${this.value.length}ch`);


            selectedDateForScrolling = selectedDate;
            scrollToSelectedDate(selectedDate);
        },
        beforeShow: function (input, inst) {
            if (selectedDateForScrolling) {
                $(this).datepicker("setDate", selectedDateForScrolling);
            }
            setTimeout(function () {
                inst.dpDiv.css({
                    top: $(input).offset().top + $(input).outerHeight(),
                    left: $(input).offset().left
                });
            }, 0);
        }
    });

    // Function to scroll to the selected date in the calendar
    function scrollToSelectedDate(selectedDate) {
        const selectedDateIsoString = toLocalISODateString(selectedDate);

        let found = false;
        $(".date-to-do").each(function () {
            const date = new Date($(this).attr('id'));
            if (date.toDateString() === selectedDate.toDateString()) {
                $(".calendar-todo").animate({
                    scrollTop: $(this).offset().top - $(".calendar-todo").offset().top + $(".calendar-todo").scrollTop()
                }, 500);
                found = true;
                return false; // break loop
            }
        });
        if (!found) {
            loadMoreDates();
            scrollToSelectedDate(selectedDate);
        }
    }

    let todayVal = new Date();
    $("#to-do-date-picker").val(formatMonthYear(todayVal));
    $("#to-do-date-picker").css("width", `${$("#to-do-date-picker").val().length}ch`); // Adjust width based on content

});
$('.to-do-week').on('click', function () {
    $('.calendar-todo').animate({
        scrollTop: 0
    }, 1500);
});
